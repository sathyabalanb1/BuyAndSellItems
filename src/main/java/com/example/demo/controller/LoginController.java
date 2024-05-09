package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.securityconfig.CustomUserDetailsService;
import com.example.demo.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	CustomUserDetailsService customuserservice;
	
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	
	@Autowired
	private CustomerRepository customerrepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/success")
	public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
		
		System.out.println("Now we are in loginpageRedirect method");
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Dealer")) {
				response.sendRedirect("/orderdetails");
				return;
			} else {
				response.sendRedirect("/displayorderplacementform");
				
			}
		}
	
	}
	
	@GetMapping("/oauth2LoginSuccess")
	 public String oauthLoginSuccessful(
		        Principal principal,HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		
		    boolean result = false;
			OAuth2AuthenticationToken oAuth2Authentication = (OAuth2AuthenticationToken) principal;
			Map<String,Object> attributes = oAuth2Authentication.getPrincipal().getAttributes();
				
			Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
			ArrayList<String> list1 = new ArrayList<String>();
			authorities.stream().forEach(list -> list1.add(list.getAuthority()));
			String userRole = list1.get(0);
			
		//	com.ds.snacks.model.User user = userRepo.findByEmail((String) attributes.get("email"));
		//	 Role role = roleRepo.findByrollName(userRole);
			
			com.example.demo.entity.Customer cust = customerRepo.findByEmail((String) attributes.get("email"));
			com.example.demo.entity.Role role = roleRepo.findByRolename("Customer").get(0);
			 
			if (cust == null) {
				cust = new com.example.demo.entity.Customer();
				cust.setEmail((String) attributes.get("email"));
				cust.setName((String) attributes.get("name"));
				cust.setPassword("");
				cust.setRoleid(role);
				customerRepo.save(cust);
			}
			
			UserDetails userDetails = customuserservice.loadUserByUsername((String) attributes.get("email"));
			
			
			//update the principal or modify the principal
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			
			result = authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("Customer"));
			session.setAttribute("username", attributes.get("email"));
			System.out.println(result);
			System.out.println(session.getAttribute("username"));
	  
			if (result) {	
				System.out.println("user is called");
				return "redirect:/displayorderplacementform";
			} else {
				System.out.println("admin is called");
				return "redirect:/orderdetails";
			}
	
			
		}
	
	/*	
	@PostMapping("/user/resetPassword")
	public GenericResponse resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
	 //   User user = userService.findUserByEmail(userEmail);
		
		Customer cust = customerservice.fetchCustomerDetails(userEmail);
		
	    if (cust == null) {
	      //  throw new UserNotFoundException();
	    	throw new UsernameNotFoundException("user not found");
	    }
	    String token = UUID.randomUUID().toString();
	    customerservice.createPasswordResetTokenForUser(cust, token);
	    mailSender.send(constructResetTokenEmail(getAppUrl(request), 
	      request.getLocale(), token, cust));
	    return new GenericResponse(
	      messages.getMessage("message.resetPasswordEmail", null, 
	      request.getLocale()));
	}
	*/
	/*
	@PostMapping("/forgotpasswordprocess")
	public String forgotPasswordProcess(CustomerDTO customerDTO) {
		String output = "";
		System.out.println("Now we are in forgotpasswordprocess method");
		System.out.println(customerDTO.getEmail());
		Customer cust = customerservice.fetchCustomerDetails(customerDTO.getEmail());
		
		if (cust != null) {
			output = customerservice.sendEmail(cust);
		}
		if (output.equals("success")) {
			System.out.println("Now we are in success block");
			return "redirect:/displayforgotpasswordform?success";
		}
		return "redirect:/displayforgotpasswordform?mailiderror";
	}
	
	@GetMapping("/resetPassword/{token}")
	public String resetPasswordForm(@PathVariable String token, Model model) {
		PasswordResetToken reset = passwordTokenRepository.findByToken(token);
		if (reset != null && customerservice.hasExipred(reset.getExpiryDateTime())) {
			model.addAttribute("email", reset.getCustomer().getEmail());
			return "resetpasswordform.jsp";
		}
		return "redirect:/forgotPassword?error";
	}
	
	@PostMapping("/resetpasswordprocess")
	public String passwordResetProcess(CustomerDTO customerDTO) {
		Customer cust = customerrepository.findByEmail(customerDTO.getEmail());
		if(cust != null) {
			
			cust.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
			customerrepository.save(cust);
			
		}
		return "redirect:/loginform";
	}

	*/
	
	


}
