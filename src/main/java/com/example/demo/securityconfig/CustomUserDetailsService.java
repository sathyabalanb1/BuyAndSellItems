package com.example.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;



@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired(required=true)
	private  CustomerRepository customerrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Customer cusdeal = customerrepository.findByEmail(username);
		
		if (cusdeal == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			UserDetails usd= User.withUsername(cusdeal.getEmail())
                    .password(cusdeal.getPassword())
                    .authorities(cusdeal.getRoleid().getRolename()).build();
			return usd;
		}
	}
}
