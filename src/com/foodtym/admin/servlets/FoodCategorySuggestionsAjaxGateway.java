package com.foodtym.admin.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.simple.JSONArray;

import com.foodtym.admin.daomodels.FoodCategorySuggestions;

@WebServlet("/Admin/FoodCategorySuggestionsAjaxGateway")
public class FoodCategorySuggestionsAjaxGateway extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String category = request.getParameter("category");
		String text = request.getParameter("text");
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		FoodCategorySuggestions foodCategorySuggestions = new FoodCategorySuggestions(dataSource, sql);
		try {
			if (type.equals("subcategory")) {
				List<String> list = foodCategorySuggestions.getSubCategorySuggestions(category, text);
				JSONArray array = new JSONArray();
				array.addAll(list);
				response.getWriter().print(array.toJSONString());
			}
			else {
				List<String> list = foodCategorySuggestions.getCategories();
				JSONArray array = new JSONArray();
				array.addAll(list);
				response.getWriter().print(array.toJSONString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
