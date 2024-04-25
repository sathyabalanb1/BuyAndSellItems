package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Orderedproducts;
import com.example.demo.entity.Orderplacement;
import com.example.demo.entity.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;


@Controller
public class ViewController {
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	OrderService orderservice;
	
	@GetMapping("/registerform")
	public ModelAndView displayCustomerRegisterForm()
	{
		ModelAndView model = new ModelAndView();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.setViewName("authentication/register.jsp");
			return model;
		}
		else
		{
			if(authentication.getAuthorities().stream().anyMatch(auth ->auth.getAuthority().equals("Dealer")))
			{
				return new ModelAndView("redirect:/orderdetails");
			}
			else
			{
				return new ModelAndView("redirect:/displayorderplacementform");
			}
		}
		
	//	return "/authentication/register.jsp";
	}
	
	@GetMapping("/loginform")
	public ModelAndView displayLoginform()
	{
		ModelAndView model = new ModelAndView();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.setViewName("authentication/loginform.jsp");
			return model;
		}
		else
		{
			if(authentication.getAuthorities().stream().anyMatch(auth ->auth.getAuthority().equals("Dealer")))
			{
				return new ModelAndView("redirect:/orderdetails");
			}
			else
			{
				return new ModelAndView("redirect:/displayorderplacementform");
			}
		}
		//return "/authentication/loginform.jsp";
	}
	
	@GetMapping("/customerfrontpage")
	public String displayCustomerpage()
	{
		return "customer/customerfrontpage.jsp";
	}
	
	@GetMapping("/dealerfrontpage")
	public String displayDealerpage()
	{
		return "dealer/dealerfrontpage.jsp";
	}
	
	@GetMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/createproductform")
	public String displayCreateProductForm()
	{
		return "product/createproduct.jsp";
	}
	/*
	@GetMapping("/displayorderplacementform")
	public ModelAndView displayOrderPlacementForm(Principal principal)
	{
        ModelAndView model = new ModelAndView();
        
        String email = principal.getName();
        
        Customer cus = customerservice.fetchCustomerDetails(email);
        
        int customerid = cus.getId();
		
		List<Product>products = productservice.getProducts();
		
		model.addObject("products", products);
		model.addObject("customerid", customerid);
		model.setViewName("order/makeorder.jsp");
		
		return model;
	}
	*/
	@GetMapping("/displayorderplacementform")
	public ModelAndView displayOrderPlacementForm()
	{
		System.out.println("now we are in orderplacement form");
        ModelAndView model = new ModelAndView();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String email = authentication.getName();
        
        System.out.println(email);
        
      //  String email = principal.getName();
        
        Customer cus = customerservice.fetchCustomerDetails(email);
        
        int customerid = cus.getId();
		
		List<Product>products = productservice.getProducts();
		
		model.addObject("products", products);
		model.addObject("customerid", customerid);
		model.setViewName("order/makeorder.jsp");
		
		return model;
	}
	
	@GetMapping("/orderdetails")
	public ModelAndView displayOrderDetails ()
	{
		ModelAndView model = new ModelAndView();
		
	//	List<Orderedproducts>ordereditems = orderservice.getOrderedProducts();
		
		List<Orderplacement>ordereditems = orderservice.getOrderedProducts();
		
		model.addObject("ordereditems", ordereditems);
		model.setViewName("order/orderlist.jsp");
		return model;
	}
	
	@GetMapping("/individualorderdetails")
	public ModelAndView displayIndividualOrderDetails(@RequestParam("oid") int orderid)
	{
		ModelAndView model = new ModelAndView();
		
		List<Orderedproducts>orderedproducts = orderservice.getOrderedProductsByOrderId(orderid);
		
	//	List<Orderedproducts>orderedproducts = orderservice.getOrderedProductsByOrderId(orderid);
		
		String customername = orderedproducts.get(0).getOrderid().getCustomerid().getName();
		
		LocalDateTime ordereddate =orderedproducts.get(0).getCreationtime();
		
		boolean orderstatus = orderedproducts.get(0).getOrderid().isEnabled();
		
		LocalDateTime orderupdatedtime = orderedproducts.get(0).getOrderid().getUpdatetime();
		
		if(orderstatus == true)
		{
			model.addObject("orderstatus", "Approved");
		}
		else if(orderstatus == false && orderupdatedtime == null)
		{
			model.addObject("orderstatus","Waiting for Approval");
		}
		else
		{
			model.addObject("orderstatus","Rejected");
		}
		
		
		int quantity = orderedproducts.get(0).getQuantity();
		
		model.addObject("orderid",orderid);
		model.addObject("quantity", quantity);
		model.addObject("orderedproducts", orderedproducts);
		model.addObject("customername", customername);
		model.addObject("ordereddate", ordereddate);
	//	model.addObject("orderstatus", orderstatus);
		model.setViewName("order/orderviewpage.jsp");
		
		return model;
		
	}
	
	@GetMapping("/approvedorderlist")
	public ModelAndView displayApprovedOrders()
	{
		ModelAndView model = new ModelAndView();
		List<Orderplacement>approvedorders = orderservice.getApprovedOrders();
		
		model.addObject("approveditems", approvedorders);
		model.setViewName("order/approvedorderlist.jsp");
		return model;
	}
	
	@GetMapping("/waitinglist")
	public ModelAndView displayWaitingOrders()
	{
		ModelAndView model = new ModelAndView();
		List<Orderplacement>waitingorders = orderservice.getWaitingOrders();
		
		model.addObject("waitingitems", waitingorders);
		model.setViewName("order/waitingorderlist.jsp");
		return model;
	}
	
	@GetMapping("/rejectedlist")
	public ModelAndView displayRejectedOrders()
	{
		ModelAndView model = new ModelAndView();
		List<Orderplacement>rejectedorders = orderservice.getRejectedOrders();
		
		model.addObject("rejecteditems", rejectedorders);
		model.setViewName("order/rejectedorderlist.jsp");
		return model;
	}
	
	@GetMapping("/displayforgotpasswordform")
	public String displayForgotPasswordForm()
	{
		return "authentication/forgotpasswordform.jsp";
	}
	

}
