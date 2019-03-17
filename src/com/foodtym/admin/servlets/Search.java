package com.foodtym.admin.servlets;


import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.daomodels.RestaurantDaoImpl;




@WebServlet("/Admin/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		if (type.equals("restaurant")) {
			
			RestaurantDaoImpl daoImpl = new RestaurantDaoImpl(dataSource, sql);
			
			String by = request.getParameter("by");
			if (by.equals("id")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
			}
			else if (by.equals("name")) {
				String text = request.getParameter("name");
				
			}
			else if (by.equals("locality")) {
				String locality = request.getParameter("locality");
				String region = request.getParameter("region");
			}
		}
		
	}

}
