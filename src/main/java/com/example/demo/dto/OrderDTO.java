package com.example.demo.dto;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;

public class OrderDTO {
	
	private int requiredquantity;
	
	private int customerid;
	
	private int productid;

	public int getRequiredquantity() {
		return requiredquantity;
	}

	public void setRequiredquantity(int requiredquantity) {
		this.requiredquantity = requiredquantity;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

		

}
