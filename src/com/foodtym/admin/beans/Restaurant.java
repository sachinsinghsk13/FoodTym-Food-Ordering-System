package com.foodtym.admin.beans;

public class Restaurant {
	private int id;
	private String name;
	private Locality locality;
	private byte[] banner;
	private String fssaiNo;
	private String note;
	private RestaurantOwner owner;
	private String address;
	private String mobileNo;
	private String email;
	private String password;
	private String openTime;
	private String closeTime;
	private String regDate;
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	private double regAmount;

	public Restaurant() {
		this.owner = new RestaurantOwner();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public byte[] getBanner() {
		return banner;
	}

	public void setBanner(byte[] banner) {
		this.banner = banner;
	}

	public String getFssaiNo() {
		return fssaiNo;
	}

	public void setFssaiNo(String fssaiNo) {
		this.fssaiNo = fssaiNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public RestaurantOwner getOwner() {
		return owner;
	}

	public void setOwner(RestaurantOwner owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String contactNo) {
		this.mobileNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}


	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	

}
