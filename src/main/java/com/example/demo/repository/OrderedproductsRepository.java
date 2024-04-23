package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Orderedproducts;
import com.example.demo.entity.Orderplacement;


public interface OrderedproductsRepository extends JpaRepository<Orderedproducts,Integer> {
	
	public List<Orderedproducts> findAllByOrderByIdDesc();
	
	public List<Orderedproducts> findByOrderid(Orderplacement order);

}
