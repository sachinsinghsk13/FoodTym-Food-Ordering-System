package com.foodtym.admin.daomodels;

import java.sql.SQLException;

import com.foodtym.admin.utils.AdminSessionDetails;

public interface AdminDao {
	AdminSessionDetails checkLogin(String username , String password) throws SQLException;
}
