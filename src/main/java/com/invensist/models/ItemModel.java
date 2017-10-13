package com.invensist.models;

import com.invensist.enums.ItemType;

public class ItemModel {
	
	private Integer id;
	
	private String code;
	
	private String description;
	
	private Double itemcost;
	
	private Double assemblycost;
	
	private ItemType itemType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getItemcost() {
		return itemcost;
	}

	public void setItemcost(Double itemcost) {
		this.itemcost = itemcost;
	}

	public Double getAssemblycost() {
		return assemblycost;
	}

	public void setAssemblycost(Double assemblycost) {
		this.assemblycost = assemblycost;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	public String getType() {
		return this.itemType.getValue();
	}
	
	public void setType(String type) {
		this.itemType = ItemType.geTypeByValue(type);
	}
}
