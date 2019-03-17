package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.RestaurantOwner;
import com.foodtym.admin.utils.Gender;

public class RestaurantOwnerDaoImpl implements RestaurantOwnerDao {
	private BasicDataSource dataSource;
	private Properties sqlQueries;
	
	public RestaurantOwnerDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.sqlQueries = sql;
		this.dataSource = dataSource;
	}
	
	
	@Override
	public int insertRestaurantOwner(RestaurantOwner restaurantOwner) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("INSERT_RESTAURANT_OWNER"));
		
		statement.setString(1, restaurantOwner.getFirstName());
		statement.setString(2, restaurantOwner.getLastName());
		statement.setString(3, restaurantOwner.getDob());
		statement.setString(4, restaurantOwner.getGender().toString());
		statement.setString(5, restaurantOwner.getMobileNo());
		statement.setString(6, restaurantOwner.getEmail());
		statement.setString(7, restaurantOwner.getAddress());
		statement.executeQuery();
		statement.close();
		
		statement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next())
			return resultSet.getInt(1);
		else
			throw new SQLException("Something went wrong while retriving last insert id");
	}

	@Override
	public RestaurantOwner getRestaurantOwner(int restaurantOwnerId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_RESTAURANT_OWNER"));
		statement.setInt(1, restaurantOwnerId);
		RestaurantOwner owner = new RestaurantOwner();
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			owner.setId(resultSet.getInt(1));
			owner.setFirstName(resultSet.getString(2));
			owner.setLastName(resultSet.getString(3));
			owner.setDob(resultSet.getString(4));
			
			if (resultSet.getString(5).equals("MALE"))
				owner.setGender(Gender.MALE);
			else
				owner.setGender(Gender.FEMALE);
			
			owner.setMobileNo(resultSet.getString(6));
			owner.setEmail(resultSet.getString(7));
			owner.setAddress(resultSet.getString(8));
			return owner;
		}
		else {
			throw new RuntimeException("ID NOT FOUND");
		}
	}


	@Override
	public byte[] getRestaurantOwnerPicture(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQueries.getProperty("GET_RESTAURANT_OWNER_PICTURE"));
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getBytes(1);
		}
		return new byte[1];
	}

}
