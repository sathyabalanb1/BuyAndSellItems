package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.Role;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.RoleRepository;



@Service
public class CustomerService {
	
	@Autowired
	private RoleRepository rolerepository;
	
	@Autowired
	private CustomerRepository customerrepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	/*
	public String addCustomer(Customer cus)
	{
		Role role = rolerepository.findByRolename("Customer").get(0);
		cus.setRoleid(role);
		
		String inputemail = cus.getEmail();
		Customer existingcustomer = customerrepository.findByEmail(inputemail);
		
		if (existingcustomer != null) {
			return null; 
		} else { 	
		 String encryptedpassword = passwordEncoder.encode(cus.getPassword());
		 cus.setPassword(encryptedpassword);
		   customerrepository.save(cus);

			return "User Registered Successfully";

		}
		
	}
	*/
	public Customer addCustomer(Customer cus)
	{
		Role role = rolerepository.findByRolename("Customer").get(0);
		cus.setRoleid(role);
		
		String inputemail = cus.getEmail();
		Customer existingcustomer = customerrepository.findByEmail(inputemail);
		
		if (existingcustomer != null) {
			return null; 
		} else { 	
		 String encryptedpassword = passwordEncoder.encode(cus.getPassword());
		 cus.setPassword(encryptedpassword);
		 Customer c = customerrepository.save(cus);

			return c;

		}
		
	}
	
	public Customer fetchCustomerDetails(String email)
	{
		return customerrepository.findByEmail(email);
	}
	/*
	public void createPasswordResetTokenForUser(Customer customer, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, customer);
	    passwordTokenRepository.save(myToken);
	}
	
	private SimpleMailMessage constructResetTokenEmail(
			  String contextPath, Locale locale, String token, User user) {
			    String url = contextPath + "/user/changePassword?token=" + token;
			    String message = messages.getMessage("message.resetPassword", 
			      null, locale);
			    return constructEmail("Reset Password", message + " \r\n" + url, user);
			}
			
	*/
	
	public String sendEmail(Customer cust) {
		try {
			String resetLink = generateResetToken(cust);

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("sathyabalanb1@gmail.com");// input the senders email ID
			msg.setTo(cust.getEmail());

			msg.setSubject("Welcome To My Channel");
			msg.setText("Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
					+ "Regards \n" + "ABC");

			javaMailSender.send(msg);
			
			System.out.println("Now we are in sendmail method");

			return "success";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "error";
		}

	}
	/*
	public String generateResetToken(Customer cust) {
		UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
		PasswordResetToken resetToken = new PasswordResetToken();
		resetToken.setCustomer(cust);
		resetToken.setToken(uuid.toString());
		resetToken.setExpiryDateTime(expiryDateTime);
		PasswordResetToken token = passwordTokenRepository.save(resetToken);
		if (token != null) {
			String endpointUrl = "http://localhost:8080/resetPassword";
			return endpointUrl + "/" + resetToken.getToken();
		}
		return "";
	}
	*/
	
	public String generateResetToken(Customer cust) {
		UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
		
		PasswordResetToken tok = passwordTokenRepository.findByCustomer(cust);
		
		if(tok != null)
		{
			String endpointUrl = "http://localhost:8080/resetPassword";
			return endpointUrl + "/" + tok.getToken();
		}

		PasswordResetToken resetToken = new PasswordResetToken();
		resetToken.setCustomer(cust);
		resetToken.setToken(uuid.toString());
		resetToken.setExpiryDateTime(expiryDateTime);
		PasswordResetToken token = passwordTokenRepository.save(resetToken);
		if (token != null) {
			String endpointUrl = "http://localhost:8080/resetPassword";
			return endpointUrl + "/" + resetToken.getToken();
		}
		return "";
	}
	public boolean hasExipred(LocalDateTime expiryDateTime) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isAfter(currentDateTime);
	}
	
	

}
