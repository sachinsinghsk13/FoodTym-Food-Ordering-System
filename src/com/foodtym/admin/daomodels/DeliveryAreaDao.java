package com.foodtym.admin.daomodels;

import java.sql.SQLException;

import com.foodtym.admin.beans.DeliveryArea;
import com.foodtym.admin.beans.DeliveryPersonDeliveryAreas;
import com.foodtym.admin.beans.Locality;

public interface DeliveryAreaDao {
	public int insertDeliveryAreaIfNotExist(DeliveryArea deliveryArea) throws SQLException;
	public DeliveryPersonDeliveryAreas getDeliveryAreasOfDeliveryPerson(int deliveryPersonId) throws SQLException;
}
