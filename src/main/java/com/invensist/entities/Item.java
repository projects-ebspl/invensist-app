package com.invensist.entities;

import com.invensist.enums.ItemType;

public class Item {

	int id;
	String code;
	String description;
	double itemcost;
	double assemblycost;
	ItemType type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getItemcost() {
		return itemcost;
	}
	public void setItemcost(double itemcost) {
		this.itemcost = itemcost;
	}
	public double getAssemblycost() {
		return assemblycost;
	}
	public void setAssemblycost(double assemblycost) {
		this.assemblycost = assemblycost;
	}
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	
}
