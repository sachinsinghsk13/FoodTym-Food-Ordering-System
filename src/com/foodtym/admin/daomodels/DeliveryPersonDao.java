package com.foodtym.admin.daomodels;

import java.sql.SQLException;

import com.foodtym.admin.beans.DeliveryPerson;

public interface DeliveryPersonDao {
	public int insertDeliveryPerson(DeliveryPerson deliveryPerson) throws SQLException;
	public DeliveryPerson getDeliveryPerson(int id) throws SQLException;
	public byte[] getDeliveryPersonPicture(int id) throws SQLException;
}
