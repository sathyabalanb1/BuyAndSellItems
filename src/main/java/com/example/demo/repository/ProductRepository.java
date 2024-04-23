package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;





@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	
//	@Query(value="select product.id, product.productname, price.price, inventory.quantity from product left join price on product.id=price.product_id left join inventory on price.product_id = inventory.product_id", nativeQuery = true)
//	public List<Product> fetchAllProducts();
	
//	@Query(value="select product.id, product.productname, price.price, inventory.quantity from product left join price on product.id=price.product_id left join inventory on price.product_id = inventory.product_id", nativeQuery = true)
//	public List<Object[]> fetchAllProducts();
	   

}
