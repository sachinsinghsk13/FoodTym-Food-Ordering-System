package com.foodtym.admin.daomodels;

import java.sql.SQLException;

import com.foodtym.admin.beans.RestaurantOwner;

public interface RestaurantOwnerDao {
	public int insertRestaurantOwner(RestaurantOwner restaurantOwner) throws SQLException;
	public RestaurantOwner getRestaurantOwner(int restaurantOwnerId) throws SQLException;
	public byte[] getRestaurantOwnerPicture(int id) throws SQLException;
}
