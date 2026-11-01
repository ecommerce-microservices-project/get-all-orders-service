package tz.ega.allorderservices.services;

import java.util.List;

import tz.ega.allorderservices.entities.Order;

public interface OrderService {
	
   List<Order> findAllOrders();
   
   Order findOrdersFallBack(int orderId, Throwable throwable);
   
   Order findOrder(int orderId);
}
