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

import com.foodtym.admin.beans.Locality;
import com.foodtym.admin.daomodels.LocalityDaoImpl;

@WebServlet("/LocalityServlet")
public class LocalityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_for = request.getParameter("search_for");
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		LocalityDaoImpl daoImpl = new LocalityDaoImpl(dataSource, sql);
		
		if (search_for != null && search_for.equals("regions")) {
			try {
				List<Locality> list = daoImpl.getAllNcrRegions();
				StringBuilder sb = new StringBuilder();
				for (Locality locality : list) {
					sb.append("<option value=\""+locality.getNcrRegionId()+"\">"+locality.getNcrRegionName()+"</option>\n");
				}
				response.getWriter().println(sb.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (search_for != null && search_for.equals("locality")){
			int region_id = Integer.parseInt(request.getParameter("regionid"));
			try {
				List<Locality> list = daoImpl.getAllLocalityInRegion(region_id);
				StringBuilder sb = new StringBuilder();
				for (Locality locality : list) {
					sb.append("<option value=\""+locality.getLocalityId()+"\">"+locality.getLocalityName()+"</option>\n");
				}
				response.getWriter().println(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
