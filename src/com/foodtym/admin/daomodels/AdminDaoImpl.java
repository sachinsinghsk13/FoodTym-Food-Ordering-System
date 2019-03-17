package com.foodtym.admin.daomodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.utils.AdminSessionDetails;

public class AdminDaoImpl implements AdminDao {
	private BasicDataSource dataSource;
	private Properties sqlQueries;
	
	public AdminDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sqlQueries = sql;
	}
	
	
	@Override
	public AdminSessionDetails checkLogin(String username, String password) throws SQLException {
		Connection connection = dataSource.getConnection();
		AdminSessionDetails adminSessionDetails = new AdminSessionDetails();
		PreparedStatement statement = connection.prepareStatement(sqlQueries.getProperty("FOODTYM_ADMIN_LOGIN_CHECK"));
		statement.setString(1, username);  // ? = username
		statement.setString(2, password); // ? = password
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			adminSessionDetails.setId(resultSet.getInt(1));
			adminSessionDetails.setFirstName(resultSet.getString(2));
			adminSessionDetails.setLastName(resultSet.getString(3));
			adminSessionDetails.setUsername(resultSet.getString(4));
			adminSessionDetails.setEncryptedPassword(resultSet.getString(5));
			adminSessionDetails.setIsLoggedIn(true);
		}
		
		return adminSessionDetails;
	}

}
