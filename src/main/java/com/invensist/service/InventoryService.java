package com.invensist.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.invensist.dao.InventoryDao;

@org.springframework.stereotype.Service("inventoryService")
public class InventoryService extends Service {
	
	@Autowired
	InventoryDao inventoryDao;
	
	public void setDao(InventoryDao dao) {
		this.inventoryDao = dao;		
	}
}
