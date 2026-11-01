package tz.ega.allorderservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.observation.annotation.Observed;
import tz.ega.allorderservices.entities.Order;
import tz.ega.allorderservices.services.OrderService;

@RestController
@RequestMapping("/orders/")
public class OrderControllers {

	@Autowired
	OrderService orderService;
	
	
	@RateLimiter(name = "orderingRateLimiter", fallbackMethod = "rateLimiterFallBack")
	@GetMapping("get-all-orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		System.out.println("****** Calling - getAllOrders ******");
		List<Order> orders = orderService.findAllOrders();
		return ResponseEntity.ok(orders);
	}
	
	@CircuitBreaker(name = "orderingBreaker", fallbackMethod = "getAllOrdersFallBack")
    @Retry(name = "orderingRetry", fallbackMethod = "getAllOrdersFallBack")
	@GetMapping("{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable int orderId){
		System.out.println("****** Calling - findOrder ******");
		Order orders = orderService.findOrder(orderId);
		return ResponseEntity.ok(orders);
	}
	
	public ResponseEntity<?> rateLimiterFallBack(Throwable throwable){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Too much request !! Retry after sometimes");
	}
	
	@Observed(name = "orders.fallback.execution") //zip-kin to track fallback
	public ResponseEntity<Order> getAllOrdersFallBack(int orderId, Throwable throwable){
		Order orders = orderService.findOrdersFallBack(orderId, throwable);
		return new ResponseEntity<>(orders, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
}
