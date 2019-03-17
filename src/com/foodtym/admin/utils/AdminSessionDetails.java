package com.foodtym.admin.utils;

public class AdminSessionDetails {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private boolean isLoggedIn;
	private String encryptedPassword;
	
	public AdminSessionDetails() {
		isLoggedIn = false;
	}
	
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
