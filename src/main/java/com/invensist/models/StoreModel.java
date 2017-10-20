package com.invensist.models;

import com.invensist.enums.StoreType;

public class StoreModel {
	
	private Integer id;
	
	private String name;
	
	private StoreType storeType;

	public StoreType getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return storeType.getValue();
	}

	public void setType(String type) {
		this.storeType = StoreType.geTypeByValue(type);
	}
	
}
