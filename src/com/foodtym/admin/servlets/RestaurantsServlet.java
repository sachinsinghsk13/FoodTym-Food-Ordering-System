package com.foodtym.admin.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.Locality;
import com.foodtym.admin.beans.Restaurant;
import com.foodtym.admin.beans.RestaurantOwner;
import com.foodtym.admin.daomodels.LocalityDao;
import com.foodtym.admin.daomodels.LocalityDaoImpl;
import com.foodtym.admin.daomodels.RestaurantDaoImpl;
import com.foodtym.admin.utils.Gender;

@MultipartConfig
@WebServlet("/Admin/Restaurants")
public class RestaurantsServlet extends HttpServlet {
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
		String restaurantIdStr = request.getParameter("restaurantId");
		int restaurantId = Integer.parseInt(restaurantIdStr);
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Restaurant restaurant = null;
		RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl(dataSource, sql);
		try {
			restaurant = restaurantDaoImpl.getRestaurant(restaurantId);
			request.setAttribute("restaurant", restaurant);
			request.getRequestDispatcher("/Admin/ViewRestaurant.jsp").forward(request, response);
		}catch(RuntimeException e) {
			request.getRequestDispatcher("/Admin/restaurant-not-found.jsp").forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");		
		Properties properties = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		Part part;
		
		part = request.getPart("restNcrRegion");
		String restNcrRegion = getStringFromPart(part);
		
		part = request.getPart("restLocality");
		String restLocality = getStringFromPart(part);
	
		LocalityDao localityDao = new LocalityDaoImpl(dataSource, properties);
		int localityId = 0;
		try {
			int ncr_region_id = localityDao.insertNcrRegion(restNcrRegion);
			localityId = localityDao.insertLocality(restLocality, ncr_region_id);
		} catch (SQLException e) {
			response.sendError(500);
		}
		
		
		int ownerId = -1; // Negetive Owner ID denotes that its a new owner.
		part = request.getPart("ownerId");
		String str_owner_id = getStringFromPart(part);
		ownerId = Integer.parseInt(str_owner_id);
		
		part = request.getPart("ownerFirstName");
		String ownerFirstName = getStringFromPart(part);
		
		part = request.getPart("ownerLastName");
		String ownerLastName = getStringFromPart(part);
		
		part = request.getPart("ownerGender");
		String ownerGender = getStringFromPart(part);
		
		part = request.getPart("ownerDob");
		String ownerDob = getStringFromPart(part);
		
		part = request.getPart("ownerPicture");
		byte[] ownerPicture = getByteArrayFromPart(part);
		
		part = request.getPart("ownerMobile");
		String ownerMobile = getStringFromPart(part);
		
		part = request.getPart("ownerEmail");
		String ownerEmail = getStringFromPart(part);
		
		part = request.getPart("ownerAddress");
		String ownerAddress = getStringFromPart(part);
		
		RestaurantOwner restaurantOwner = new RestaurantOwner();
		restaurantOwner.setId(ownerId);
		restaurantOwner.setFirstName(ownerFirstName);
		restaurantOwner.setLastName(ownerLastName);
		
		if (ownerGender.equals("MALE"))
			restaurantOwner.setGender(Gender.MALE);
		else
			restaurantOwner.setGender(Gender.FEMALE);
		
		restaurantOwner.setDob(ownerDob);
		restaurantOwner.setPicture(ownerPicture);
		restaurantOwner.setMobileNo(ownerMobile);
		restaurantOwner.setEmail(ownerEmail);
		restaurantOwner.setAddress(ownerAddress);
		
		
		part = request.getPart("restName");
		String restName = getStringFromPart(part);
		
		part = request.getPart("restAddress");
		String restAddress = getStringFromPart(part);
		
		part = request.getPart("restFssai");
		String restFssai = getStringFromPart(part);
		
		part = request.getPart("restMobile");
		String restMobile = getStringFromPart(part);
		
		part = request.getPart("restEmail");
		String restEmail = getStringFromPart(part);
		
		part = request.getPart("restRegAmount");
		double restRegAmount = Double.parseDouble(getStringFromPart(part));
		
		part = request.getPart("restBanner");
		byte[] restBanner = getByteArrayFromPart(part);
		
		part = request.getPart("restOpenTime");
		String restOpenTime = getStringFromPart(part);
		
		part = request.getPart("restCloseTime");
		String restCloseTime = getStringFromPart(part);
		
		part = request.getPart("restAbout");
		String restAbout = getStringFromPart(part);
		
		part = request.getPart("restPassword");
		String restPassword = getStringFromPart(part);
		
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restName);
		
		Locality locality = new Locality();
		
		locality.setLocalityId(localityId); // only locality id is required further.
		restaurant.setLocality(locality);
		restaurant.setBanner(restBanner);
		restaurant.setFssaiNo(restFssai);
		restaurant.setNote(restAbout);
		restaurant.setOwner(restaurantOwner);
		restaurant.setAddress(restAddress);
		restaurant.setMobileNo(restMobile);
		restaurant.setEmail(restEmail);
		restaurant.setPassword(restPassword);
		restaurant.setOpenTime(restOpenTime);
		restaurant.setCloseTime(restCloseTime);
		restaurant.setRegAmount(restRegAmount);
		
		RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl(dataSource, properties);
		try {
			int restaurantId = restaurantDaoImpl.insertRestaurant(restaurant);
			response.sendRedirect(request.getContextPath()+ "/Admin/Restaurants?restaurantId="+restaurantId);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
