package com.invensist.entities;

public class Associate {
	
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String notes;
	private boolean client;
	private boolean vendour;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean getClient() {
		return client;
	}
	public void setClient(boolean client) {
		this.client = client;
	}
	public boolean getVendour() {
		return vendour;
	}
	public void setVendour(boolean vendour) {
		this.vendour = vendour;
	}
}
