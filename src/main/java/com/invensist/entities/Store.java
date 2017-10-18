package com.invensist.entities;

import com.invensist.enums.StoreType;

public class Store {
	private Integer id;
	
	private String name;
	
	private StoreType storeType;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public Store setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public Store setName(String name) {
		this.name = name;
		return this;
	}

	public StoreType getStoreType() {
		return storeType;
	}

	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}
}
