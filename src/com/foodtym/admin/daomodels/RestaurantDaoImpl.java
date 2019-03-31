package com.foodtym.admin.daomodels;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.simple.JSONObject;

import com.foodtym.admin.beans.Locality;
import com.foodtym.admin.beans.Restaurant;
import com.foodtym.admin.beans.RestaurantOwner;
import com.foodtym.admin.daomodels.LocalityDao;
import com.foodtym.admin.daomodels.LocalityDaoImpl;
import com.foodtym.admin.daomodels.RestaurantOwnerDao;
import com.foodtym.admin.daomodels.RestaurantOwnerDaoImpl;

public class RestaurantDaoImpl implements RestaurantDao {

	private BasicDataSource dataSource;
	private Properties sqlQueries;

	public RestaurantDaoImpl(BasicDataSource dataSource, Properties sql) {
		this.dataSource = dataSource;
		this.sqlQueries = sql;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_RESTAURANT"));
		statement.setInt(1, restaurantId);
		ResultSet resultSet = statement.executeQuery();
		Restaurant restaurant = new Restaurant();
		if (resultSet.next()) {
			restaurant.setId(restaurantId);
			restaurant.setName(resultSet.getString(1));

			int localityId = resultSet.getInt(2);
			LocalityDao dao = new LocalityDaoImpl(dataSource, sqlQueries);
			Locality locality = dao.getLocality(localityId);
			restaurant.setLocality(locality);

			restaurant.setFssaiNo(resultSet.getString(3));
			restaurant.setNote(resultSet.getString(4));

			int restaurantOwnerId = resultSet.getInt(5);
			RestaurantOwnerDao restaurantOwnerDao = new RestaurantOwnerDaoImpl(dataSource, sqlQueries);
			RestaurantOwner restaurantOwner = restaurantOwnerDao.getRestaurantOwner(restaurantOwnerId);
			restaurant.setOwner(restaurantOwner);

			restaurant.setAddress(resultSet.getString(6));
			restaurant.setOpenTime(resultSet.getString(7));
			restaurant.setCloseTime(resultSet.getString(8));
			restaurant.setRegDate(resultSet.getString(9));
			restaurant.setRegAmount(resultSet.getDouble(10));
			restaurant.setMobileNo(resultSet.getString(11));
			restaurant.setEmail(resultSet.getString(12));
			resultSet.close();
			statement.close();
			connection.close();
			return restaurant;
		} else {
			resultSet.close();
			statement.close();
			connection.close();
			throw new RuntimeException("NOT FOUND");
		}

	}

	@Override
	public int insertRestaurant(Restaurant restaurant) throws SQLException {
		Connection connection = dataSource.getConnection();
		/*
		 * If restaurant owner associated with restaurant does'nt contain a owner id
		 * (owner id is negative) then its a new user and requires a separate entry in
		 * table first. if it has owner id then we can directly link that id as foreign
		 * key in restaurant table.
		 */
		connection.setAutoCommit(false); // Set auto commit false to start a transaction.
		RestaurantOwner restaurantOwner = restaurant.getOwner();

		if (restaurantOwner.getId() < 0) {
			PreparedStatement statement = connection
					.prepareStatement(sqlQueries.getProperty("INSERT_RESTAURANT_OWNER"));

			statement.setString(1, restaurantOwner.getFirstName());
			statement.setString(2, restaurantOwner.getLastName());
			statement.setString(3, restaurantOwner.getDob());
			statement.setString(4, restaurantOwner.getGender().toString());
			statement.setBinaryStream(5, new ByteArrayInputStream(restaurantOwner.getPicture()));
			statement.setString(6, restaurantOwner.getMobileNo());
			statement.setString(7, restaurantOwner.getEmail());
			statement.setString(8, restaurantOwner.getAddress());

			statement.executeUpdate();
			statement.close();

			statement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				restaurantOwner.setId(resultSet.getInt(1));
				resultSet.close();
				statement.close();
			} else
				throw new SQLException("Something went wrong while retriving last insert id");
		}

		PreparedStatement preparedStatement = connection.prepareStatement(sqlQueries.getProperty("INSERT_RESTAURANT"));
		preparedStatement.setString(1, restaurant.getName());
		preparedStatement.setInt(2, restaurant.getLocality().getLocalityId());
		preparedStatement.setBinaryStream(3, new ByteArrayInputStream(restaurant.getBanner()));
		preparedStatement.setString(4, restaurant.getFssaiNo());
		preparedStatement.setString(5, restaurant.getNote());
		preparedStatement.setInt(6, restaurant.getOwner().getId());
		preparedStatement.setString(7, restaurant.getAddress());
		preparedStatement.setString(8, restaurant.getOpenTime());
		preparedStatement.setString(9, restaurant.getCloseTime());
		preparedStatement.setDouble(10, restaurant.getRegAmount());
		preparedStatement.setString(11, restaurant.getPassword());
		preparedStatement.setString(12, restaurant.getMobileNo());
		preparedStatement.setString(13, restaurant.getEmail());
		preparedStatement.executeUpdate();
		preparedStatement.close();

		preparedStatement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			resultSet.close();
			preparedStatement.close();
			connection.commit();
			connection.setAutoCommit(true);
			connection.close();
			return id;
		} else {
			resultSet.close();
			preparedStatement.close();
			connection.rollback();
			connection.setAutoCommit(true);
			connection.close();
			throw new SQLException("Something went wrong while retriving last insert id");
		}
	}

	@Override
	public byte[] getRestaurantBanner(int restaurantId) throws SQLException, IOException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_RESTAURANT_BANNER"));
		statement.setInt(1, restaurantId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			InputStream inputStream = resultSet.getBinaryStream(1);
			byte[] data = inputStream.readAllBytes();
			resultSet.close();
			statement.close();
			connection.close();
			return data;
		} else
			throw new RuntimeException("ID NOT FOUND");
	}

	@Override
	public List<Restaurant> searchRestaurantByNameOrId(String text) throws SQLException {
		List<Restaurant> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(sqlQueries.getProperty("SEARCH_RESTAURANT_BY_NAME_OR_ID"));
		preparedStatement.setString(1, text + "%");
		preparedStatement.setString(2, "%" + text + "%");
		preparedStatement.setString(3, text);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			// Create a new Restaurant
			Restaurant restaurant = new Restaurant();

			// set dependent beans
			restaurant.setOwner(new RestaurantOwner());
			restaurant.setLocality(new Locality());

			// fetch results
			restaurant.setId(resultSet.getInt(1));
			restaurant.getOwner().setFirstName(resultSet.getString(2));
			restaurant.getOwner().setLastName(resultSet.getString(3));
			restaurant.setName(resultSet.getString(4));
			restaurant.getLocality().setLocalityName(resultSet.getString(5));
			restaurant.getLocality().setNcrRegionName(resultSet.getString(6));

			// add restaurant to list
			list.add(restaurant);
		}
		return list;
	}

	@Override
	public List<Restaurant> searchRestaurantByLocality(String locality, String region) throws SQLException {
		List<Restaurant> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(sqlQueries.getProperty("SEARCH_RESTAURANT_BY_LOCALITY"));
		preparedStatement.setString(1, locality);
		preparedStatement.setString(2, region);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			// Create a new Restaurant
			Restaurant restaurant = new Restaurant();

			// set dependent beans
			restaurant.setOwner(new RestaurantOwner());
			restaurant.setLocality(new Locality());

			// fetch results
			restaurant.setId(resultSet.getInt(1));
			restaurant.getOwner().setFirstName(resultSet.getString(2));
			restaurant.getOwner().setLastName(resultSet.getString(3));
			restaurant.setName(resultSet.getString(4));
			restaurant.getLocality().setLocalityName(resultSet.getString(5));
			restaurant.getLocality().setNcrRegionName(resultSet.getString(6));

			// add restaurant to list
			list.add(restaurant);
		}
		return list;
	}
	
	public List<Restaurant> getRecentlyAddedRestaurant() throws SQLException {
		List<Restaurant> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQueries.getProperty("SEARCH_RESTAURANT_RECENTLY_ADDED"));
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			// Create a new Restaurant
			Restaurant restaurant = new Restaurant();

			// set dependent beans
			restaurant.setOwner(new RestaurantOwner());
			restaurant.setLocality(new Locality());

			// fetch results
			restaurant.setId(resultSet.getInt(1));
			restaurant.getOwner().setFirstName(resultSet.getString(2));
			restaurant.getOwner().setLastName(resultSet.getString(3));
			restaurant.setName(resultSet.getString(4));
			restaurant.getLocality().setLocalityName(resultSet.getString(5));
			restaurant.getLocality().setNcrRegionName(resultSet.getString(6));

			// add restaurant to list
			list.add(restaurant);
		}
		return list;
	}

}
