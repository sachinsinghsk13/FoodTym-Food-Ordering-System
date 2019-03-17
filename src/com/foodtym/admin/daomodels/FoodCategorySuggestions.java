package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class FoodCategorySuggestions {
	private BasicDataSource dataSource;
	private Properties sql;
	
	public FoodCategorySuggestions(BasicDataSource dataSource, Properties sql) {
		this.dataSource = dataSource;
		this.sql = sql;
	}
	
	public List<String> getSubCategorySuggestions(String category,String text) throws SQLException {
		List<String> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql.getProperty("SEARCH_FOOD_SUBCATEGORY"));
		statement.setString(1, category);
		statement.setString(2, text+"%");
		statement.setString(3, "%"+text+"%");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			list.add(resultSet.getString(1));
		}
		connection.close();
		return list;
	}
	
	public List<String> getCategories() throws SQLException {
		List<String> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql.getProperty("GET_ALL_CATEGORIES"));
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			list.add(resultSet.getString(1));
		}
		connection.close();
		return list;
	}
}
