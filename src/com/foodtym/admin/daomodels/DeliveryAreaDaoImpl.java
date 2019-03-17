package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.DeliveryArea;
import com.foodtym.admin.beans.DeliveryPersonDeliveryAreas;
import com.foodtym.admin.beans.Locality;

public class DeliveryAreaDaoImpl implements DeliveryAreaDao {
	private BasicDataSource dataSource;
	private Properties sqlQueries;
	
	public DeliveryAreaDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sqlQueries = sql;
	}
	
	
	@Override
	public int insertDeliveryAreaIfNotExist(DeliveryArea deliveryArea) throws SQLException {
		Connection connection = dataSource.getConnection();
		LocalityDao dao = new LocalityDaoImpl(dataSource, sqlQueries);
		int locality_Id;
		try {
			locality_Id = dao.getLocalityId(deliveryArea.getLocality().getNcrRegionName(), deliveryArea.getLocality().getLocalityName());		
		}
		catch(RuntimeException e) {
			throw new SQLException();
		}
		// Just checking if delivery area entry already exist
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_DELIVERY_AREA_ID"));
		statement.setInt(1, deliveryArea.getLocality().getLocalityId());
		statement.setInt(2, deliveryArea.getDeliveryPersonId());
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			resultSet.close();
			statement.close();
			connection.close();
			return id;
		}
		// if not exist .. insert the entry
		else {
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement(sqlQueries.getProperty("INSERT_DELIVERY_AREA"));
			statement.setInt(1, locality_Id);
			statement.setInt(2, deliveryArea.getDeliveryPersonId());
			statement.executeUpdate();
			statement.close();
			
			statement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				resultSet.close();
				statement.close();
				connection.close();
				return id;
			}
			else 
			{
				resultSet.close();
				statement.close();
				connection.close();
				throw new SQLException();
			}
			
		}
		
	}

	@Override
	public DeliveryPersonDeliveryAreas getDeliveryAreasOfDeliveryPerson(int deliveryPersonId) throws SQLException {
		Connection connection  = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_DELIVERY_AREAS_OF_DELIVERY_PERSON"));
		statement.setInt(1, deliveryPersonId);
		ResultSet resultSet = statement.executeQuery();
		List<DeliveryArea> areas = new ArrayList<>();
		while(resultSet.next()) {
			Locality locality = new Locality();
			locality.setLocalityName(resultSet.getString(3));
			locality.setNcrRegionName(resultSet.getString(2));
			DeliveryArea deliveryArea = new DeliveryArea();
			deliveryArea.setId(resultSet.getInt(1));
			deliveryArea.setDeliveryPersonId(deliveryPersonId);
			deliveryArea.setLocality(locality);
			areas.add(deliveryArea);
		}
		DeliveryArea[] deliveryAreas = new DeliveryArea[areas.size()];
		areas.toArray(deliveryAreas);
		DeliveryPersonDeliveryAreas deliveryAreas2 = new DeliveryPersonDeliveryAreas();
		deliveryAreas2.setDeliveryAreas(areas.toArray(deliveryAreas));
		deliveryAreas2.setDeliveryPersonId(deliveryPersonId);
		resultSet.close();
		statement.close();
		connection.close();
		return deliveryAreas2;
	}

}
