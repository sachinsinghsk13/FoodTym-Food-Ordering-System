package com.foodtym.admin.beans;

public class DeliveryArea {
	private int id;
	private Locality locality;
	private int deliveryPersonId;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Locality getLocality() {
		return locality;
	}
	public void setLocality(Locality locality) {
		this.locality = locality;
	}
	public int getDeliveryPersonId() {
		return deliveryPersonId;
	}
	public void setDeliveryPersonId(int deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}
	
	
	
}
