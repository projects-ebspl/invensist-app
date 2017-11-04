package com.invensist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.invensist.dao.InventoryDao;
import com.invensist.entities.Item;
import com.invensist.models.ItemModel;

@org.springframework.stereotype.Service("inventoryService")
public class InventoryService extends Service  {

	@Autowired
	InventoryDao inventoryDao;

	public void saveOrUpdateItem(ItemModel itemModel) {
		Item item = this.toItem(itemModel);
		inventoryDao.saveItem(item);
	}
	
	public List<ItemModel> getItems() {
		return inventoryDao.getAllItems().stream().map(item -> toItemModel(item)).collect(Collectors.toList());
	}
	public void deleteItems(String stringIds) {
		String [] ids = stringIds.split(",");
		for(String id:ids){			
			this.deleteItem(Integer.parseInt(id));			
		}		
	}
	
	public void deleteItem(Integer id) {
		inventoryDao.deleteItemById(id);		
	}
	
	public ItemModel getItem(Integer id) {
		return toItemModel(inventoryDao.getItemById(id));
	}

	private ItemModel toItemModel(Item item){
		if(item == null){
			return null;
		}
		ItemModel itemModel = new ItemModel();
		itemModel.setId(item.getId());
		itemModel.setItemCode(item.getItemCode());
		itemModel.setDescription(item.getDescription());
		itemModel.setItemCost(item.getItemCost());
		itemModel.setAssemblyCost(item.getAssemblyCost());
		itemModel.setItemType(item.getItemType());
		return itemModel;
	}
	
	private Item toItem(ItemModel itemModel){
		if(itemModel == null){
			return null;
		}
		Item item = new Item();
		item.setId(itemModel.getId());
		item.setItemCode(itemModel.getItemCode());
		item.setDescription(itemModel.getDescription());
		item.setItemCost(itemModel.getItemCost());
		item.setAssemblyCost(itemModel.getAssemblyCost());
		item.setItemType(itemModel.getItemType());
		return item;
	}

	public void setDao(InventoryDao dao) {
		this.inventoryDao = dao;		
	}
}
