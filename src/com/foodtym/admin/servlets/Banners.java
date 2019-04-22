package com.foodtym.admin.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.daomodels.RestaurantDaoImpl;



@WebServlet("/Restaurants/Banners")
public class Banners extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String string = request.getParameter("restaurantId");
		int restaurantId = -1;
		try {
			restaurantId = Integer.parseInt(string);
		}
		catch(NumberFormatException e) {
			response.sendError(404);
		}
		
		BasicDataSource dataSource = (BasicDataSource) request.getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl(dataSource,sql);
		byte[] data = null;
		try {
			data = restaurantDaoImpl.getRestaurantBanner(restaurantId);
			if (data != null)
				response.getOutputStream().write(data);
			else {
				InputStream is = getServletContext().getResourceAsStream("WEB-INF/img/download.png");
				byte[] data1 = is.readAllBytes();
				response.getOutputStream().write(data1);
			}
		} catch (SQLException e) {
			InputStream is = getServletContext().getResourceAsStream("WEB-INF/img/download.png");
			byte[] data1 = is.readAllBytes();
			response.getOutputStream().write(data1);
		}
		
	}

}
