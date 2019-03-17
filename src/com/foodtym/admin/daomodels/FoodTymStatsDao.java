package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.FoodTymStats;

public class FoodTymStatsDao {
	private BasicDataSource dataSource;
	private Properties sqlQuries; 
	
	public FoodTymStatsDao(BasicDataSource dataSource , Properties sqlQuries) {
		this.dataSource = dataSource;
		this.sqlQuries = sqlQuries;
	}
	
	public FoodTymStats getFoodTymStats() throws SQLException {
		Connection connection = dataSource.getConnection();
		FoodTymStats foodTymStats = new FoodTymStats();
		PreparedStatement statement;
		ResultSet resultSet;
		
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_RESTAURANTS"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setRestaurants(resultSet.getInt(1));
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_FOOD_ITEM"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setFoodItems(resultSet.getInt(1));
		
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_CUSTOMERS"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setCustomers(resultSet.getInt(1));
		
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_LOCALITY"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setLocalities(resultSet.getInt(1));
		
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_WAITING_ORDERS"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setOrderWaiting(resultSet.getInt(1));
		statement = connection
				.prepareStatement(this.sqlQuries.getProperty("FOODTYM_STATS_TOTAL_DELIVERY_PERSON"));
		resultSet = statement.executeQuery();
		if (resultSet.next())
			foodTymStats.setDeliveryPerson(resultSet.getInt(1));
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return foodTymStats;
		
	}
	
}
