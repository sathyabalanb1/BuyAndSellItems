package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ProductData;
import com.example.demo.service.OrderService;



@Controller
public class OrderController {
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("/makeorder")
	public ResponseEntity<String> makeOrder(@RequestBody List<ProductData> selectedProducts)
	{  
		/*
		System.out.println("sathya make your order");
		System.out.println(selectedProducts.get(0).getCustomerid());
		System.out.println(selectedProducts.get(0).getProductname());
		System.out.println(selectedProducts.get(0).getProductid());
		System.out.println(selectedProducts.get(0).getRequiredquantity());
		System.out.println(selectedProducts.get(0).getProductprice());
		System.out.println("Now we are in makeOrder method");
		*/
		
		orderservice.processOrder(selectedProducts);
		
		return new ResponseEntity<>("Order placed successfully", HttpStatus.OK);
		
	//	return ResponseEntity.ok("Order placed successfully");		
	}
	
	
	/*
	@PostMapping("/makeorder")
	public ResponseEntity<String> makeOrder(@RequestParam("input") String inputdate)
	{
		int x = 55;
		System.out.println("Now we are in makeOrder method");
		return ResponseEntity.ok("Order placed successfully");		
	}
	*/
	
	@GetMapping("/approveorder")
	public ModelAndView makeOrderApproval(@RequestParam("oid") int orderid)
	{	
		orderservice.processOrderApproval(orderid);
		
		return new ModelAndView("redirect:/orderdetails");
			
	}
	
	@GetMapping("/disapproveorder")
	public ModelAndView makeOrderDisApproval(@RequestParam("oid") int orderid)
	{	
		orderservice.processOrderDisApproval(orderid);
		
		return new ModelAndView("redirect:/orderdetails");
			
	}
	
	

}
