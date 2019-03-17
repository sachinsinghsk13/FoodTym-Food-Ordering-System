package com.foodtym.admin.beans;

public class PriceType {
	private TypePrice type;
	private double foodtymPrice;
	private double restaurantPrice;
	private int quantity;
	
	public TypePrice getType() {
		return type;
	}
	public void setType(TypePrice type) {
		this.type = type;
	}
	public double getFoodtymPrice() {
		return foodtymPrice;
	}
	public void setFoodtymPrice(double foodtymPrice) {
		this.foodtymPrice = foodtymPrice;
	}
	public double getRestaurantPrice() {
		return restaurantPrice;
	}
	public void setRestaurantPrice(double restaurantPrice) {
		this.restaurantPrice = restaurantPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
