package tz.ega.allorderservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.observation.annotation.Observed;
import tz.ega.allorderservices.entities.Order;
import tz.ega.allorderservices.services.OrderService;

@RestController
@RequestMapping("/api/orders/")
public class OrderControllers {

	@Autowired
	OrderService orderService;
	
	@CircuitBreaker(name = "orderingService", fallbackMethod = "getAllOrdersFallBack")
	@Retry(name = "orderingService", fallbackMethod = "getAllOrdersFallBack")
	@GetMapping("get-all-orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		
		System.out.println("****** Calling - getAllOrders ******");
		List<Order> orders = orderService.findAllOrders();
		return ResponseEntity.ok(orders);
	}
	
	@Observed(name = "orders.fallback.execution")
	public ResponseEntity<List<Order>> getAllOrdersFallBack(Throwable throwable){
		List<Order> orders = orderService.findAllOrdersFallBack();
		return new ResponseEntity<>(orders, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
