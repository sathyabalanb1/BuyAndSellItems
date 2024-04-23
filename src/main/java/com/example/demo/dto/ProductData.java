package com.example.demo.dto;

import java.math.BigDecimal;

public class ProductData {
	
	private int productid;
	
	private String productname;
	
	private BigDecimal productprice;
	
	private int requiredquantity;
	
	private int customerid;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public BigDecimal getProductprice() {
		return productprice;
	}

	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}	

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getRequiredquantity() {
		return requiredquantity;
	}

	public void setRequiredquantity(int requiredquantity) {
		this.requiredquantity = requiredquantity;
	}

	
	

}
