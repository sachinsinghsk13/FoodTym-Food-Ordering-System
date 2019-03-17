package com.foodtym.admin.daomodels;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.DeliveryPerson;
import com.foodtym.admin.utils.Gender;

public class DeliveryPersonDaoImpl implements DeliveryPersonDao {
	private BasicDataSource dataSource;
	private Properties sqlQueries;
	
	public DeliveryPersonDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sqlQueries = sql;
	}
	
	@Override
	public int insertDeliveryPerson(DeliveryPerson deliveryPerson) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("INSERT_DELIVERY_PERSON"));
		statement.setString(1, deliveryPerson.getFirstName());
		statement.setString(2, deliveryPerson.getLastName());
		statement.setString(3, deliveryPerson.getDob());
		statement.setBinaryStream(4, new ByteArrayInputStream(deliveryPerson.getPicture()));
		statement.setDouble(5, deliveryPerson.getSalary());
		statement.setString(6, deliveryPerson.getVehicalNo());
		statement.setString(7, deliveryPerson.getAddress());
		statement.setString(8, deliveryPerson.getMobileNo());
		statement.setString(9, deliveryPerson.getEmail());
		statement.setString(10, deliveryPerson.getPasswd());
		statement.setString(11, deliveryPerson.getFatherName());
		statement.setString(12, deliveryPerson.getGender().toString());
		statement.setDouble(13, deliveryPerson.getCommSalary());
		statement.setString(14, deliveryPerson.getNote());
		statement.executeUpdate();
		statement.close();
		
		statement = connection.prepareStatement(sqlQueries.getProperty("LAST_INSERT_ID"));
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			resultSet.close();
			statement.close();
			connection.close();
			return id;
		}
		else {
			resultSet.close();
			statement.close();
			connection.close();
			throw new SQLException("Can't Retrive ID");
		}
	}

	@Override
	public DeliveryPerson getDeliveryPerson(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("GET_DELIVERY_PERSON"));
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		DeliveryPerson deliveryPerson = new DeliveryPerson();
		if (resultSet.next()) {
			deliveryPerson.setId(id);
			deliveryPerson.setFirstName(resultSet.getString(1));
			deliveryPerson.setLastName(resultSet.getString(2));
			deliveryPerson.setDob(resultSet.getString(3));
			deliveryPerson.setPicture(resultSet.getBytes(4));
			deliveryPerson.setSalary(resultSet.getDouble(5));
			deliveryPerson.setVehicalNo(resultSet.getString(6));
			deliveryPerson.setAddress(resultSet.getString(7));
			deliveryPerson.setMobileNo(resultSet.getString(8));
			deliveryPerson.setEmail(resultSet.getString(9));
			deliveryPerson.setFatherName(resultSet.getString(10));
			
			if (resultSet.getString(11).equals("MALE"))
				deliveryPerson.setGender(Gender.MALE);
			else
				deliveryPerson.setGender(Gender.FEMALE);
			
			deliveryPerson.setJoinDate(resultSet.getString(12));
			deliveryPerson.setCommSalary(resultSet.getDouble(13));
			deliveryPerson.setNote(resultSet.getString(14));
			resultSet.close();
			statement.close();
			connection.close();
			return deliveryPerson;
		}
		else {
			resultSet.close();
			statement.close();
			connection.close();
			throw new RuntimeException("NOT FOUND");
		}
	}
	
	public List<DeliveryPerson> getDeliveryPersonByLocality(String locality) throws SQLException {
		Connection connection = dataSource.getConnection();
		return null;
	}
	
	@Override
	public byte[] getDeliveryPersonPicture(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQueries.getProperty("GET_DELIVERY_PERSON_PICTURE"));
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getBytes(1);
		}
		return new byte[1];
	}
}
