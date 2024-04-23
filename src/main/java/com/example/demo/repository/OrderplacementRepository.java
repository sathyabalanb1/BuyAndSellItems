package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Orderplacement;


public interface OrderplacementRepository extends JpaRepository<Orderplacement,Integer> {
	
	public List<Orderplacement> findAllByOrderByIdDesc();
	
//	@Query(value="select * from selection where dsuser_id= :userid and assignment_id = :assid", nativeQuery = true) 
//	public Selection findUserselection(int assid, int userid);
	
	@Query(value="select * from orderplacement where status=true ORDER By id desc", nativeQuery=true)
	public List<Orderplacement> findApprovedOrders();
	
	@Query(value="select * from orderplacement where status=false AND updatetime IS NULL ORDER By id desc", nativeQuery=true)
	public List<Orderplacement> findWaitingOrders();
	
	@Query(value="select * from orderplacement where status=false AND updatetime IS NOT NULL ORDER By id desc", nativeQuery=true)
	public List<Orderplacement> findRejectedOrders();

}
