package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartrepository;
	
	@Autowired
	ProductRepository productrepository;
	
	@Autowired
	CustomerRepository customerrepository;
	
	public Cart  processCart (CartDTO ct)
	{
		Product pd = productrepository.findById(ct.getProductid()).orElse(null);
		
		Customer cs = customerrepository.findById(ct.getCustomerid()).orElse(null);
		
		
		Cart cart = new Cart();
		cart.setProductid(pd);
		cart.setCustomerid(cs);
		cart.setRequiredquantity(ct.getRequiredquantity());
		return cartrepository.save(cart);
	}
	
	public List<Cart> getCartProducts(Customer cust)
	{
		return cartrepository.findByCustomerid(cust);
	}
	
	public void removeCart(Customer cust)
	{
		cartrepository.deleteCustomerCart(cust.getId());
		return;
	}

}
