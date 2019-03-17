package com.foodtym.admin.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.simple.parser.ParseException;

import com.foodtym.admin.beans.FoodItem;
import com.foodtym.admin.daomodels.FoodItemDaoImpl;

@MultipartConfig
@WebServlet("/Admin/FoodItems")
public class FoodItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getStringFromPart(Part part) throws IOException {
		InputStream inputStream = part.getInputStream();
		byte[] bytes =inputStream.readAllBytes();
		return new String(bytes);
	}
	
	private byte[] getByteArrayFromPart(Part part) throws IOException {
		InputStream inputStream = part.getInputStream();
		byte[] bytes =inputStream.readAllBytes();
		return bytes;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties properties = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		FoodItemDaoImpl daoImpl = new FoodItemDaoImpl(dataSource, properties);
		
		List<FoodItem> foodItems;
		try {
			foodItems = daoImpl.getRestaurantFoodItems(restaurantId);
			request.setAttribute("foodItems", foodItems);
			request.getRequestDispatcher("/Admin/FoodItems.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part;
		part = request.getPart("foodItem");
		String json = getStringFromPart(part);
		FoodItem foodItem = null;
		try {
			foodItem = FoodItemDaoImpl.getFoodItemFromJson(json);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		part = request.getPart("img");
		foodItem.setImg(getByteArrayFromPart(part));
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		FoodItemDaoImpl foodItemDaoImpl = new FoodItemDaoImpl(dataSource, sql);
		try {
			foodItemDaoImpl.insertFoodItem(foodItem);
			response.getWriter().println("Success");
		} catch (SQLException e) {
			response.getWriter().println("Error");
		}
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
