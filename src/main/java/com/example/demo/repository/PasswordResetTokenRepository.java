package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer> {
	
	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByCustomer(Customer cust);
}
