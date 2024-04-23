package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Customer;
import com.example.demo.securityconfig.CustomUserDetailsService;
import com.example.demo.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	CustomerService service;

	//	@Autowired
	//	private UserDetailsService userDetailService;



	@Autowired
	private CustomUserDetailsService userDetailService;

	//	@Autowired
	//	private CustomUserDetailsService customUserDetail;

	//	@Autowired
	//	private WebSecurityConfig securityConfig;

	@Autowired
	private LoginController logincontrol;

	/*
	@PostMapping("/insertcustomer")
	public String insertCustomer(Customer customer, Model model)
	{
		String message = service.addCustomer(customer);

		if(message == null) {
			model.addAttribute("info",false); 
			return "authentication/register.jsp";

		}
		else {
			model.addAttribute("info", true); 
			return "authentication/register.jsp"; 
		}

	}
	 */
	/*
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	 */
	@Bean
	UserDetailsService userService() {
		return new CustomUserDetailsService();
	}
	/*
	@PostMapping("/insertcustomer")
	 public String registerUser(Customer customer,Model model) {
	        // Register the user
		Customer cus = service.addCustomer(customer);

		if(cus != null)
		{
			UserDetails userDetails = userDetailService.loadUserByUsername(cus.getEmail());
	        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        return "redirect:/customerfrontpage";
		}																						
		else 
		{
			model.addAttribute("info",false);
			return "authentication/register.jsp";
		}

 }
	 */

	@PostMapping("/insertcustomer")
	public String insertCustomer(Customer customer,Model model,HttpServletRequest request,HttpServletResponse response, HttpSession httpSession) {

		Customer cus = service.addCustomer(customer);

		if(cus != null)
		{
			try {
				UserDetails usd= User.withUsername(cus.getEmail())
						.password(cus.getPassword())
						.authorities(cus.getRoleid().getRolename()).build();

				Authentication authentication = new UsernamePasswordAuthenticationToken(usd.getUsername(),
						usd.getPassword(),usd.getAuthorities());

				if(authentication.isAuthenticated())
				{	
					SecurityContextHolder.getContext().setAuthentication(authentication);
					logincontrol.loginPageRedirect(request, response, authentication);
				}

			}
			catch (Exception e) {
				return e.getMessage();
			}
		}
		else 
		{
			model.addAttribute("info",false);
			return "authentication/register.jsp";
		}
		return "redirect:/customerfrontpage";

	}	

}
