package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductData;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Orderedproducts;
import com.example.demo.entity.Orderplacement;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderedproductsRepository;
import com.example.demo.repository.OrderplacementRepository;
import com.example.demo.repository.ProductRepository;


@Service
public class OrderService {
	
	@Autowired
	CustomerRepository customerrepository;
	
	@Autowired
	OrderplacementRepository orderplacementrepository;
	
	@Autowired
	ProductRepository productrepository;
	
	@Autowired
	OrderedproductsRepository orderedproductsrepository;
	
	@Autowired
	CartRepository cartrepository;
	
	public String  processOrder (List<ProductData>pd)
	{
		int i=0;
		
		Orderplacement op = new Orderplacement();
		
		while(i<pd.size())
		{
			int customerid = pd.get(i).getCustomerid();
					
			Customer cus = customerrepository.findById(customerid).orElse(null);
			op.setCustomerid(cus);
			Orderplacement confirmorder = orderplacementrepository.save(op);
			
			int productid = pd.get(i).getProductid();
			
			Product product = productrepository.findById(productid).orElse(null);
			
			int quantity = pd.get(i).getRequiredquantity();
			
			insertOrder(quantity,product,confirmorder);
			
			i++;
			
		}
		
		return "Order placement successfully completed";
		
	}
	
	public void insertOrder(int quantity,Product product, Orderplacement confirmorder)
	{
		Orderedproducts orderedproducts = new Orderedproducts();
		orderedproducts.setQuantity(quantity);
		orderedproducts.setProductid(product);
		orderedproducts.setOrderid(confirmorder);
		
		orderedproductsrepository.save(orderedproducts);
	}
	/*
	public List<Orderedproducts> getOrderedProducts()
	{
		 return orderedproductsrepository.findAllByOrderByIdDesc();
	}
	*/
	public List<Orderplacement> getOrderedProducts()
	{
		return orderplacementrepository.findAllByOrderByIdDesc();
	}
	
	public List<Orderedproducts> getOrderedProductsByOrderId(int orderid)
	{
		Orderplacement order = orderplacementrepository.findById(orderid).orElse(null);
		return orderedproductsrepository.findByOrderid(order);
	}
	
	/*
	public List<Orderedproducts> getOrderedProductsByOrderId(int orderid)
	{
		
		Orderplacement order = orderplacementrepository.findById(orderid).orElse(null);
		return orderedproductsrepository.findByOrderid(order);
	}
	*/
	
	public void processOrderApproval(int orderid)
	{
		Orderplacement order = orderplacementrepository.findById(orderid).orElse(null);
		order.setEnabled(true);
		orderplacementrepository.save(order);
	}
	
	public void processOrderDisApproval(int orderid)
	{
		Orderplacement order = orderplacementrepository.findById(orderid).orElse(null);
		ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        
        ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
        
		//order.setEnabled(false);
        
        order.setUpdatetime(LocalDateTime.now());
		orderplacementrepository.save(order);
	}
	
	public List<Orderplacement> getApprovedOrders()
	{
		List<Orderplacement>approvedorders = orderplacementrepository.findApprovedOrders();
		
		return approvedorders;
	}
	
	public List<Orderplacement> getWaitingOrders()
	{
		List<Orderplacement>waitingorders = orderplacementrepository.findWaitingOrders();
		return waitingorders;
	}
	
	public List<Orderplacement> getRejectedOrders()
	{
		List<Orderplacement>rejectedorders = orderplacementrepository.findRejectedOrders();
		return rejectedorders;
	}
	
	//  order from cart
	
	public String  processOrderFromCart (List<OrderDTO> cartitems)
	{
		int i=0;
		
		Orderplacement op = new Orderplacement();
		
		
		while(i<cartitems.size())
		{
			int customerid = cartitems.get(i).getCustomerid();
					
			Customer cus = customerrepository.findById(customerid).orElse(null);
			op.setCustomerid(cus);
			Orderplacement confirmorder = orderplacementrepository.save(op);
			
			int productid = cartitems.get(i).getProductid();
			
			Product product = productrepository.findById(productid).orElse(null);
			
			int quantity = cartitems.get(i).getRequiredquantity();
			
			insertOrder(quantity,product,confirmorder);
			
			i++;
			
		}
		return "Order placement successfully completed";
		
	}
	
	public void insertOrderFromCart(int quantity,Product product, Orderplacement confirmorder)
	{
		Orderedproducts orderedproducts = new Orderedproducts();
		orderedproducts.setQuantity(quantity);
		orderedproducts.setProductid(product);
		orderedproducts.setOrderid(confirmorder);
		
		orderedproductsrepository.save(orderedproducts);
		
	}
	

}
