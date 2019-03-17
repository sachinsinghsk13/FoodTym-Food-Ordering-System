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

import com.foodtym.admin.daomodels.DeliveryPersonDaoImpl;
import com.foodtym.admin.daomodels.RestaurantOwnerDaoImpl;

@WebServlet("/Admin/Pictures")
public class PicturesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		int id = Integer.parseInt(request.getParameter("id"));
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		if (type.equals("restaurantowner")) {
			RestaurantOwnerDaoImpl daoImpl = new RestaurantOwnerDaoImpl(dataSource, sql);
			try {
				byte[] data = daoImpl.getRestaurantOwnerPicture(id);
				response.getOutputStream().write(data);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("deliveryperson")) {
			DeliveryPersonDaoImpl daoImpl = new DeliveryPersonDaoImpl(dataSource, sql);
			try {
				byte[] data = daoImpl.getDeliveryPersonPicture(id);
				response.getOutputStream().write(data);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
