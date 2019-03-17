package com.foodtym.admin.beans;

public class FoodCategory {
	private int foodCategoryId;
	private int foodSubCategoryId;
	private String foodCategory;
	private String foodSubCategory;
	
	public int getFoodCategoryId() {
		return foodCategoryId;
	}
	public void setFoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}
	public int getFoodSubCategoryId() {
		return foodSubCategoryId;
	}
	public void setFoodSubCategoryId(int foodSubCategoryId) {
		this.foodSubCategoryId = foodSubCategoryId;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	public String getFoodSubCategory() {
		return foodSubCategory;
	}
	public void setFoodSubCategory(String foodSubCategory) {
		this.foodSubCategory = foodSubCategory;
	}
	
}
