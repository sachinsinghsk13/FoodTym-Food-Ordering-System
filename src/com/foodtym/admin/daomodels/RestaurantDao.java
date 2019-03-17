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
	public JSONObject searchRestaurantById(int restaurantId) throws SQLException;
	public List<JSONObject> searchRestaurantByName(String text) throws SQLException;
	public List<JSONObject> searchRestaurantByLocality(String locality, String region) throws SQLException;
}