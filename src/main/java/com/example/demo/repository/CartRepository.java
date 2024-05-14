package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	
	
	public List<Cart> findByCustomerid(Customer customerid);
	
	@Query(value="delete from cart where customer_id= :customerid", nativeQuery = true) 
	public void deleteCustomerCart(int customerid);

}
