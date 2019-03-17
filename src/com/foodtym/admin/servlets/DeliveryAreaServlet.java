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

import com.foodtym.admin.beans.DeliveryArea;
import com.foodtym.admin.beans.DeliveryPersonDeliveryAreas;
import com.foodtym.admin.beans.Locality;
import com.foodtym.admin.daomodels.DeliveryAreaDao;
import com.foodtym.admin.daomodels.DeliveryAreaDaoImpl;


@WebServlet("/Admin/DeliveryArea")
public class DeliveryAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deliveryPersonId = Integer.parseInt(request.getParameter("deliveryPersonId"));
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		DeliveryAreaDao deliveryAreaDao = new DeliveryAreaDaoImpl(dataSource, sql);
		try {
			DeliveryPersonDeliveryAreas deliveryAreas = deliveryAreaDao.getDeliveryAreasOfDeliveryPerson(deliveryPersonId);
			request.setAttribute("areas", deliveryAreas);
			request.getRequestDispatcher("/Admin/DeliveryAreaTable.jsp").forward(request, response);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			response.sendError(404);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deliveryPersonId = Integer.parseInt(request.getParameter("deliveryPersonId"));
		String ncrRegion = request.getParameter("ncrRegion");
		String localityName = request.getParameter("ncrLocality");
		Locality locality = new Locality();
		locality.setLocalityName(localityName);
		locality.setNcrRegionName(ncrRegion);
		DeliveryArea deliveryArea = new DeliveryArea();
		deliveryArea.setDeliveryPersonId(deliveryPersonId);
		deliveryArea.setLocality(locality);
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		DeliveryAreaDao deliveryAreaDao = new DeliveryAreaDaoImpl(dataSource, sql);
		try {
			deliveryAreaDao.insertDeliveryAreaIfNotExist(deliveryArea);
		}
		catch(SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		response.getWriter().println("success");
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
