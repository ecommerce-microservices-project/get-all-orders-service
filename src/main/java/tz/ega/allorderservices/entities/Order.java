package tz.ega.allorderservices.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

enum Status {
    CREATED,
    FULL_FILLED,
    CANCELLED
}

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public Order() {
		// TODO Auto-generated constructor stub
	}
    
	public Order(int id, Status status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}


	public Status getStatus() {
		return status;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    
}
