package com.invensist.models;

import com.invensist.enums.ItemType;

public class ItemModel {

	private Integer id;

	private String itemCode;

	private String description;

	private Double itemCost;

	private Double assemblyCost;

	private ItemType itemType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getItemCost() {
		return itemCost;
	}

	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	public Double getAssemblyCost() {
		return assemblyCost;
	}

	public void setAssemblyCost(Double assemblyCost) {
		this.assemblyCost = assemblyCost;
	}	

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType){
		this.itemType = itemType;
	}

	public String getType(){
		return this.itemType.getValue();
	}
	
	public void setType(String itemType) {
		this.itemType = ItemType.geTypeByValue(itemType);
	}
}
