package tz.ega.allorderservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int productId;
		
	private int quantity;
	
	private int buyingPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
	private Order order;
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int id, int productId, int quantity, int buyingPrice, Order order) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.buyingPrice = buyingPrice;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getBuyingPrice() {
		return buyingPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
