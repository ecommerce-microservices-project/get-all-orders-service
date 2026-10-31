package tz.ega.allorderservices.services;

import java.util.List;

import tz.ega.allorderservices.entities.Order;

public interface OrderService {
	
   List<Order> findAllOrders();
   
   List<Order> findAllOrdersFallBack();
}
