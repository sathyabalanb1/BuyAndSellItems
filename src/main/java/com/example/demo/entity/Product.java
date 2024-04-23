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
import jakarta.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String productname;
	
	@Column(name = "status", nullable = false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	@CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ")
    private LocalDateTime creationtime;
    
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP",insertable = false)
    private LocalDateTime updatetime;
    
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "price_id")
	private Price priceid ;
    
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "inventory_id")
	private Inventory inventoryid ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Price getPriceid() {
		return priceid;
	}

	public void setPriceid(Price priceid) {
		this.priceid = priceid;
	}

	public Inventory getInventoryid() {
		return inventoryid;
	}

	public void setInventoryid(Inventory inventoryid) {
		this.inventoryid = inventoryid;
	}
    
    
}
