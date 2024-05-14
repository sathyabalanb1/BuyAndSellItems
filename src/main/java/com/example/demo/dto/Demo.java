package com.example.demo.dto;

import org.springframework.stereotype.Component;

@Component
public class Demo {
	
	private int  productid;
	private int  customerid;
	private int  requiredquantity;
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
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
	@Override
	public String toString() {
		return "Demo [productid=" + productid + ", customerid=" + customerid + ", requiredquantity=" + requiredquantity
				+ "]";
	}
	
	


}
