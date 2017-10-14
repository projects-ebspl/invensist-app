package com.invensist.models;

public class UserModel {

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String password;
	
	private boolean admin;

	private boolean planner;

	private boolean user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isPlanner() {
		return planner;
	}

	public void setPlanner(boolean planner) {
		this.planner = planner;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}
}
