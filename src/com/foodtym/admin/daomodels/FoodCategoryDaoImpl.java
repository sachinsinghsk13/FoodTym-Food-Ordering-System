package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.FoodCategory;

public class FoodCategoryDaoImpl implements FoodCategoryDao {
	
	private BasicDataSource dataSource;
	private Properties sqlQueries;
	
	public FoodCategoryDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sqlQueries = sql;
	}
	
	@Override
	public int insertFoodCategory(String category) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int categoryId = 0;
		
		preparedStatement = connection.prepareStatement(sqlQueries.getProperty("GET_FOOD_CATEGORY_ID"));
		preparedStatement.setString(1, category);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			categoryId = resultSet.getInt(1);
		}
		else {
			preparedStatement.close();
			resultSet.close();
			preparedStatement = connection.prepareStatement(sqlQueries.getProperty("INSERT_FOOD_CATEGORY"));
			preparedStatement.setString(1, category);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				categoryId = resultSet.getInt(1);
			preparedStatement.close();
			resultSet.close();
		}
		connection.close();
		return categoryId;
	}

	@Override
	public int insertFoodSubCategory(String subCategory, int foodCategoryId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int subCategoryId = 0;
		
		preparedStatement = connection.prepareStatement(sqlQueries.getProperty("GET_FOOD_SUB_CATEGORY_ID"));
		preparedStatement.setInt(1, foodCategoryId);
		preparedStatement.setString(2, subCategory);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next())
			subCategoryId = resultSet.getInt(1);
		else {
			preparedStatement.close();
			resultSet.close();
			preparedStatement = connection.prepareStatement(sqlQueries.getProperty("INSERT_FOOD_SUB_CATEGORY"));
			preparedStatement.setInt(1, subCategoryId);
			preparedStatement.setString(2, subCategory);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				subCategoryId = resultSet.getInt(1);
			resultSet.close();
			preparedStatement.close();
		}
		return subCategoryId;	
	}

	@Override
	public FoodCategory getFoodCategory(int foodSubCategoryId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQueries.getProperty("GET_FOOD_CATEGORY_BY_SUB_CATEGORY_ID"));
		ResultSet resultSet = preparedStatement.executeQuery();
		FoodCategory foodCategory = new FoodCategory();
		if (resultSet.next()) {
			foodCategory.setFoodCategoryId(resultSet.getInt(1));
			foodCategory.setFoodCategory(resultSet.getString(2));
			foodCategory.setFoodSubCategoryId(resultSet.getInt(3));
			foodCategory.setFoodSubCategory(resultSet.getString(4));
		}
		return foodCategory;
	}

}
