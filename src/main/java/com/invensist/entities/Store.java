package com.invensist.entities;

import com.invensist.enums.StoreType;

public class Store {
	private Integer id;
	
	private String name;
	
	private StoreType type;

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

	/**
	 * @return the type
	 */
	public StoreType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public Store setType(StoreType type) {
		this.type = type;
		return this;
	}
}
