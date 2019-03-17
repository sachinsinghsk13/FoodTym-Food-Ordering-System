package com.foodtym.admin.beans;



public class FoodItem {
	private int foodItemId;
	private String category;
	private String subCategory;
	private String title;
	private String description;
	private int restaurantId;
	private byte[] img;
	private int preparingTime;
	private Availability availability;
	private Type type;
	private PriceBasis priceBasis;
	private PriceType[] priceTypes;
	
	
	public int getPreparingTime() {
		return preparingTime;
	}
	public void setPreparingTime(int preparingTime) {
		this.preparingTime = preparingTime;
	}
	public int getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public PriceBasis getPriceBasis() {
		return priceBasis;
	}
	public void setPriceBasis(PriceBasis priceBasis) {
		this.priceBasis = priceBasis;
	}
	public PriceType[] getPriceTypes() {
		return priceTypes;
	}
	public void setPriceTypes(PriceType[] priceTypes) {
		this.priceTypes = priceTypes;
	}
	
}
