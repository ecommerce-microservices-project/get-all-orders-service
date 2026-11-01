package tz.ega.allorderservices.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;
import tz.ega.allorderservices.dto.Product;
import tz.ega.allorderservices.entities.Order;
import tz.ega.allorderservices.entities.OrderItem;
import tz.ega.allorderservices.feign.ProductClient;
import tz.ega.allorderservices.repositories.OrdersDAO;
import tz.ega.allorderservices.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	ProductClient productClient;
	
	@Override
	public List<Order> findAllOrders() {
		
		List<Order> orders = ordersDAO.findAllOrdersOnly();
		
		return orders;
    }

	@Override
	public Order findOrdersFallBack(int orderId, Throwable throwable) {
		Order order = ordersDAO.findById(orderId).orElse(null);
		return order;
	}

	@Override
	public Order findOrder(int orderId) {
				
		  Order order = ordersDAO.findById(orderId).orElse(null);
		  
		  System.out.println("orders::" + new ObjectMapper().writeValueAsString(order));

		  List<Integer> allProductIds = order.getOrderItems().stream()
		                .map(OrderItem::getProductId)
		                .distinct()
		                .toList();
				
		  System.out.println(allProductIds);
				
		  List<Product> productList = productClient.findProductByIds(allProductIds);
				
		  Map<Integer, Product> productMap = productList.stream()
		                .collect(Collectors.toMap(Product::getId, p -> p));
				
		  return new Order(order.getId(),order.getStatus(),order.getCreatedAt(),
	              order.getOrderItems().stream()
	              .map(item -> new OrderItem(item.getId(), item.getProductId(), item.getQuantity(), item.getBuyingPrice(), 
	                       productMap.get(item.getProductId())))
	               .toList() 
	                        
	       );
				
	}
	
}
