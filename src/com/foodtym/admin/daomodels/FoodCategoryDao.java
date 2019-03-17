package com.foodtym.admin.daomodels;

import java.sql.SQLException;

import com.foodtym.admin.beans.FoodCategory;

public interface FoodCategoryDao {
	public int insertFoodCategory(String category) throws SQLException;
	public int insertFoodSubCategory(String subCategory , int foodCategoryId) throws SQLException;
	public FoodCategory getFoodCategory(int foodSubCategoryId) throws SQLException;
	
}
