package com.foodtym.admin.daomodels;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;

import com.foodtym.admin.beans.Restaurant;

public interface RestaurantDao {
	public Restaurant getRestaurant(int restaurantId) throws SQLException;
	public int insertRestaurant(Restaurant restaurant) throws SQLException;
	public byte[] getRestaurantBanner(int restaurantId) throws SQLException, IOException;
	public List<Restaurant> searchRestaurantByNameOrId(String text) throws SQLException;
	public List<Restaurant> searchRestaurantByLocality(String locality, String region) throws SQLException;
}