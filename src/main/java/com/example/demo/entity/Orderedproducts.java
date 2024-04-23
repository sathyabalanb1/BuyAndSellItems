package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orderedproducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_id")
	private Orderplacement orderid ;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
	private Product productid ;
	
	private int quantity;
	
	@CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ")
    private LocalDateTime creationtime;
    
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP",insertable = false)
    private LocalDateTime updatetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Orderplacement getOrderid() {
		return orderid;
	}

	public void setOrderid(Orderplacement orderid) {
		this.orderid = orderid;
	}

	public Product getProductid() {
		return productid;
	}

	public void setProductid(Product productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(LocalDateTime creationtime) {
		this.creationtime = creationtime;
	}

	public LocalDateTime getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(LocalDateTime updatetime) {
		this.updatetime = updatetime;
	}
    
    

}
