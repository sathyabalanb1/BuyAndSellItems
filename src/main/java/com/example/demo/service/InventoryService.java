package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;



@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryrepository;
	
	public Inventory insertInventory(ProductDTO prod)
	{
		Inventory inventory = new Inventory();
		inventory.setQuantity(prod.getProductquantity());
		Inventory invent = inventoryrepository.save(inventory);
		return invent;
	}

}
