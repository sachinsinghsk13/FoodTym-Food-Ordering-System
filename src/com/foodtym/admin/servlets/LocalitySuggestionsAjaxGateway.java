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

import com.foodtym.admin.beans.Locality;
import com.foodtym.admin.daomodels.LocalityDaoImpl;


@WebServlet({ "/LocalitySuggestionsAjaxGateway", "/LSAG" })
public class LocalitySuggestionsAjaxGateway extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String searchFor = request.getParameter("search_for");
		String region = request.getParameter("ncr_region");
		List<Locality> list;
		BasicDataSource pool = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		LocalityDaoImpl localityDaoImpl = new LocalityDaoImpl(pool,sql);
		if (search != null && searchFor != null && region != null) {
			System.out.println("in three");
			if (searchFor.equals("locality")) {
				try {
					list = localityDaoImpl.searchLocalityInNcrRegion(region, search);
					JSONArray jsonArray = new JSONArray();
					jsonArray.addAll(list);
					String json = jsonArray.toJSONString();
					response.getWriter().println(json);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} else if (searchFor.equals("ncr_region")){
			try {
				list = localityDaoImpl.searchNcrRegion(search);
				JSONArray jsonArray = new JSONArray();
				jsonArray.addAll(list);
				String json = jsonArray.toJSONString();
				System.out.println("in....");
				response.getWriter().println(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (searchFor.equals("locality")) {
			try {
			list = localityDaoImpl.searchLocality(search);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(list);
			String json = jsonArray.toJSONString();
			response.getWriter().println(json);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
				
	}

}
