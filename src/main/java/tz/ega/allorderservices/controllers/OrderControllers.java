package tz.ega.allorderservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tz.ega.allorderservices.dao.OrdersDAO;
import tz.ega.allorderservices.entities.Order;

@RestController
@RequestMapping("/api/orders/")
public class OrderControllers {

	@Autowired
	OrdersDAO ordersDAO;
	
	@GetMapping("get-all-orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = ordersDAO.findAll();
		return ResponseEntity.ok(orders);
	}
}
