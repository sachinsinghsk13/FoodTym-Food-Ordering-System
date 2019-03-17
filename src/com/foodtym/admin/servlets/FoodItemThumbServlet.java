package com.foodtym.admin.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.daomodels.FoodItemDaoImpl;

@WebServlet("/Admin/Restaurants/FoodItems/Thumbs")
public class FoodItemThumbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodItemId = Integer.parseInt(request.getParameter("foodItemId"));
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		FoodItemDaoImpl daoImpl = new FoodItemDaoImpl(dataSource, sql);
		try {
			byte[] data = daoImpl.getFoodItemThumb(foodItemId);
			response.getOutputStream().write(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
