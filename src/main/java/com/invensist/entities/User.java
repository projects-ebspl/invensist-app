package com.invensist.entities;

public class User {
	
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public User setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public User setAddress(String address) {
		this.address = address;
		return this;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public boolean isAdmin() {
		return admin;
	}

	public User setAdmin(boolean admin) {
		this.admin = admin;
		return this;
	}

	public boolean isPlanner() {
		return planner;
	}

	public User setPlanner(boolean planner) {
		this.planner = planner;
		return this;
	}

	public boolean isUser() {
		return user;
	}

	public User setUser(boolean user) {
		this.user = user;
		return this;
	}
	
}
