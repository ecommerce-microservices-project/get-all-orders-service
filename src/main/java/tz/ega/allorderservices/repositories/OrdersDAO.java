package tz.ega.allorderservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.ega.allorderservices.entities.Order;

@Repository
public interface OrdersDAO extends JpaRepository<Order, Integer> {}
