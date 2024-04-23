package com.example.demo.dto;

import java.math.BigDecimal;

public class ProductDTO {
	
	private String productname;
	
	private BigDecimal price;
	
	private int productquantity;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}
	
	

}
