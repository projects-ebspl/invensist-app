package com.invensist.entities;

import com.invensist.enums.ItemType;

public class Item {
	private ItemType itemType;
	private Integer id;
	private String itemCode;
	private String description;
	private double itemCost;
	private double assemblyCost;
	
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
	public double getItemCost() {
		return itemCost;
	}
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	public double getAssemblyCost() {
		return assemblyCost;
	}
	public void setAssemblyCost(double assemblyCost) {
		this.assemblyCost = assemblyCost;
	}
	public String getType() {
		return itemType.getValue();
	}

	public void setType(String type) {
		this.itemType = ItemType.geTypeByValue(type);
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
}
