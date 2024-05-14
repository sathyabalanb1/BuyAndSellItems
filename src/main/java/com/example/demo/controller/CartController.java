package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;

@Controller
public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@Autowired
	CustomerService customerservice;
	
	
	@PostMapping("/makecart")
	public ResponseEntity<String> makeCart(@RequestBody CartDTO cartdto)
	{
		//orderservice.processOrder(selectedProducts);
		System.out.println(cartdto.toString());
		System.out.println("Now we are in make cart method");
		
		cartservice.processCart(cartdto);
		
		return new ResponseEntity<>("Item Added to Cart Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/displaycartpage")
	public ModelAndView displayCartItems(Principal principal)
	{
		String username = principal.getName();
		
		Customer cust = customerservice.fetchCustomerDetails(username);
		
		ModelAndView model = new ModelAndView();
				
		List<Cart>cartitems = cartservice.getCartProducts(cust);
		
		if(cartitems.size() == 0)
		{
			model.addObject("emptycart",true);
			model.setViewName("cart/cartpage.jsp");
			return model;
		}
		
		model.addObject("cartitems", cartitems);
		model.setViewName("cart/cartpage.jsp");
		return model;
	}

}
