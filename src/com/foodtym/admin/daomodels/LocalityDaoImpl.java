package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.Locality;

public class LocalityDaoImpl implements LocalityDao {
	private BasicDataSource dataSource;
	private Properties sqlQuries;
	
	public LocalityDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sqlQuries = sql;
	}

	@Override
	public int insertNcrRegion(String ncrRegionName) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("FIND_NCR_REGION_ID"));
		statement.setString(1, ncrRegionName);
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
			
			statement = connection.prepareStatement(sqlQuries.getProperty("INSERT_NCR_REGION"));
			statement.setString(1, ncrRegionName);
			statement.executeUpdate();
			statement.close();
			
			statement = connection.prepareStatement(sqlQuries.getProperty("LAST_INSERT_ID"));
			resultSet = statement.executeQuery();
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
				throw new SQLException("Something went wrong while retriving last insert id");
			}
			
		}
		
	}

	@Override
	public int insertLocality(String localityName, int ncrRegionId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("FIND_LOCALITY_ID"));
		statement.setString(1, localityName);
		statement.setInt(2, ncrRegionId);
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
			
			statement = connection.prepareStatement(sqlQuries.getProperty("INSERT_LOCALITY"));
			statement.setString(1, localityName);
			statement.setInt(2, ncrRegionId);
			statement.executeUpdate();
			statement.close();
			
			statement = connection.prepareStatement(sqlQuries.getProperty("LAST_INSERT_ID"));
			resultSet = statement.executeQuery();
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
				throw new SQLException("Something went wrong while retriving last insert id");
			}
			
		}
		
	}

	@Override
	public List<Locality> searchLocalityInNcrRegion(String ncrRegion, String text) throws SQLException {
		List<Locality> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("SEARCH_LOCALITY_IN_NCR_REGION"));
		statement.setString(1, ncrRegion);
		statement.setString(2, text + "%"); // include items that has prefix 'text'.
		statement.setString(3, "%"+text+"%"); // include items that contain 'text'
		ResultSet resultSet = statement.executeQuery();
		Locality locality = null;
		while(resultSet.next()) {
			locality = new Locality();
			locality.setNcrRegionId(resultSet.getInt(1));
			locality.setLocalityId(resultSet.getInt(2));
			locality.setNcrRegionName(resultSet.getString(3));
			locality.setLocalityName(resultSet.getString(4));
			list.add(locality);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	@Override
	public List<Locality> searchLocality(String text) throws SQLException {
		List<Locality> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("SEARCH_LOCALITY"));
		statement.setString(1, text + "%"); // include items that has prefix 'text'.
		statement.setString(2, "%" + text + "%"); // include items that contain 'text'
		ResultSet resultSet = statement.executeQuery();
		Locality locality = null;
		while(resultSet.next()) {
			locality = new Locality();
			locality.setNcrRegionId(resultSet.getInt(1));
			locality.setNcrRegionName(resultSet.getString(2));
			list.add(locality);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
		
	}

	@Override
	public List<Locality> searchNcrRegion(String text) throws SQLException {
		List<Locality> list = new ArrayList<>();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("SEARCH_NCR_REGION"));
		statement.setString(1, text + "%"); // include items that has prefix 'text'.
		statement.setString(2, "%" + text + "%"); // include items that contain 'text'
		ResultSet resultSet = statement.executeQuery();
		Locality locality = null;
		while(resultSet.next()) {
			locality = new Locality();
			locality.setNcrRegionId(resultSet.getInt(1));
			locality.setNcrRegionName(resultSet.getString(2));
			list.add(locality);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	@Override
	public Locality getLocality(int localityId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("GET_LOCALITY"));
		statement.setInt(1, localityId);
		ResultSet resultSet = statement.executeQuery();
		Locality locality = new Locality();
		if (resultSet.next()) {
			locality.setLocalityId(localityId);
			locality.setNcrRegionId(resultSet.getInt(1));
			locality.setNcrRegionName(resultSet.getString(2));
			locality.setLocalityName(resultSet.getString(3));
			resultSet.close();
			statement.close();
			connection.close();
			return locality;
		}
		else {
			throw new RuntimeException("NOT FOUND");
		}
	}

	@Override
	public int getLocalityId(String ncr, String locality) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuries.getProperty("GET_LOCALITY_ID"));
		statement.setString(1, locality);
		statement.setString(2, ncr);
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
			throw new RuntimeException("NOT FOUND");
		}
	}
	
	
}
