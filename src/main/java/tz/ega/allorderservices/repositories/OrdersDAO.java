package tz.ega.allorderservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tz.ega.allorderservices.entities.Order;

@Repository
public interface OrdersDAO extends JpaRepository<Order, Integer> {
	
	@Query("SELECT or FROM tz.ega.allorderservices.entities.Order or")
    List<Order> findAllOrdersOnly();
	
    @Query("SELECT DISTINCT e FROM tz.ega.allorderservices.entities.Order e JOIN tz.ega.allorderservices.entities.OrderItem it ON e.id = it.order.id")
	List<Order> findAllOrderWithItems();
}
