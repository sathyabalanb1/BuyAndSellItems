package com.example.demo.entity;

import java.math.BigDecimal;
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
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(precision=10, scale=2)
	private BigDecimal price;
	
	@CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ")
    private LocalDateTime creationtime;
    
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP",insertable = false)
    private LocalDateTime updatetime ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
