package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.MailSenderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private CustomerService customerservice;
	
	private final MailSenderService mailService;

	@Autowired
    public ForgotPasswordController(MailSenderService mailService) {
        this.mailService = mailService;
    }
	
	
	@GetMapping("/displayforgotpasswordform")
	public String displayForgotPasswordForm()
	{
		return "authentication/forgotpasswordform.jsp";
	}
	
	
	
	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
	    String email = request.getParameter("email");
	//    String token = RandomString.make(30);
	    UUID uuid = UUID.randomUUID();
	    String token = uuid.toString();
	     
	    try {
	        customerservice.updateResetPasswordToken(token, email);
	     //   String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	        
	        String endpointUrl = "http://localhost:8080/resetpassword";
			String resetPasswordLink = endpointUrl + "?token=" + token;
	      //  sendEmail(email, resetPasswordLink);
			Foo(email, resetPasswordLink);
	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
	        
	    } catch (CustomerNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	    } /*catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }*/
	         
	    return "authentication/forgotpasswordform.jsp";
	}
	   
	 public void Foo(String recipientEmail,String link){
		 
		 String subject = "Here's the link to reset your password";
		 
		 String content = "<p>Hello,</p>"
		            + "<p>You have requested to reset your password.</p>"
		            + "<p>Click the link below to change your password:</p>"
		            + "<p><a href=\"" + link + "\">Change my password</a></p>"
		            + "<br>"
		            + "<p>Ignore this email if you do remember your password, "
		            + "or you have not made the request.</p>";
	 mailService.sendNewMail(recipientEmail,subject, content);

	}
	 
	 @GetMapping("/resetpassword")
	 public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
	     Customer customer = customerservice.getByResetPasswordToken(token);
	     model.addAttribute("token", token);
	      
	     if (customer == null) {
	         model.addAttribute("tokenerror", "Invalid Token");
	        // return "message";
	         return "authentication/forgotpasswordform.jsp";
	     }
	      
	     return "authentication/resetpasswordform.jsp";
	 }
	 
	 @PostMapping("/resetpassword")
	 public String processResetPassword(HttpServletRequest request, Model model) {
	     String token = request.getParameter("token");
	     String password = request.getParameter("password");
	      
	     Customer customer = customerservice.getByResetPasswordToken(token);
	     model.addAttribute("title", "Reset your password");
	      
	     if (customer == null) {
	         model.addAttribute("tokenerror", "Invalid Token");
	       //  return "message";
	         return "authentication/forgotpasswordform.jsp";
	     } else {           
	         customerservice.updatePassword(customer, password);
	          
	         model.addAttribute("passwordchangemessage", "You have successfully changed your password.");
	     }
	      
	   //  return "message";
	     
	     return "authentication/loginform.jsp";
	 }
}
