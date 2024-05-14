package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CustomerService customerservice;
	
	@PostMapping("/addproduct")
	public String addProduct (ProductDTO prod,Model model)
	{
		Product pr = productservice.insertProduct(prod);
		
		if(pr != null)
		{
			model.addAttribute("insertinfo", true);
		}
		
		return "product/createproduct.jsp";
		
		
	}
	
	
	
	@GetMapping("/productlist")
	public ModelAndView listProducts ()
	{
		ModelAndView model = new ModelAndView();
		
		List<Product>products = productservice.getProducts();
		
		model.addObject("products", products);
		model.setViewName("product/productlist.jsp");
		
		return model;
	}
	
	@GetMapping("/plppage")
	public ModelAndView displayPlppage()
	{
		ModelAndView model = new ModelAndView();
		
		List<Product>products = productservice.getProducts();
		
		model.addObject("products", products);
		model.setViewName("product/plppage.jsp");
		
		return model;
	}
	
	@GetMapping("/productdescriptionpage")
	public ModelAndView displayProductDescriptionpage(@RequestParam("pid") int productid, Principal principal)
	{
		ModelAndView model = new ModelAndView();
		
		Product product = productservice.getProductById(productid);
		
		String username = principal.getName();
		
		Customer cust = customerservice.fetchCustomerDetails(username);
		
		int customerid = cust.getId();
		
		model.addObject("customerid", customerid);
		model.addObject("product", product);
		model.setViewName("product/productdescriptionpage.jsp");
		
		return model;
	}
	
	
	

}
