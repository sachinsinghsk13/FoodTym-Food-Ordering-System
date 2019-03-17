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

import com.foodtym.admin.beans.DeliveryPerson;
import com.foodtym.admin.daomodels.DeliveryPersonDao;
import com.foodtym.admin.daomodels.DeliveryPersonDaoImpl;
import com.foodtym.admin.utils.Gender;

@MultipartConfig
@WebServlet("/Admin/DeliveryPersons")
public class DeliveryPersonServlet extends HttpServlet {
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
		String string = request.getParameter("deliveryPersonId");
		int deliveryPersonId = -1;
		try {
			deliveryPersonId = Integer.parseInt(string);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		BasicDataSource dataSource  = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		DeliveryPersonDaoImpl daoImpl = new DeliveryPersonDaoImpl(dataSource,sql);
		DeliveryPerson deliveryPerson = null;
		try {
			deliveryPerson = daoImpl.getDeliveryPerson(deliveryPersonId);
		}
		catch(SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		catch(RuntimeException e) {
			e.printStackTrace(); // When No Delivery Person Found with specified ID
		}
		
		request.setAttribute("deliveryPerson", deliveryPerson);
		request.getRequestDispatcher("/Admin/ViewDeliveryPerson.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part;
		
		part = request.getPart("dpFirstName");
		String dpFirstName = getStringFromPart(part);
		
		part = request.getPart("dpLastName");
		String dpLastName = getStringFromPart(part);
		
		part = request.getPart("dpFatherName");
		String dpFatherName = getStringFromPart(part);
		
		part = request.getPart("dpAddress");
		String dpAddress = getStringFromPart(part);
		
		part = request.getPart("dpDob");
		String dpDob = getStringFromPart(part);
		
		part = request.getPart("dpPicture");
		byte[] dpPicture = getByteArrayFromPart(part);
		
		part = request.getPart("dpGender");
		String dpGender = getStringFromPart(part);
		
		part = request.getPart("dpMobileNo");
		String dpMobileNo = getStringFromPart(part);
		
		part = request.getPart("dpEmail");
		String dpEmail = getStringFromPart(part);
		
		part = request.getPart("dpVehicalNo");
		String dpVehicalNo = getStringFromPart(part);
		
		part = request.getPart("dpSalary");
		String dpSalary = getStringFromPart(part);
		
		part = request.getPart("dpCommSalary");
		String dpCommSalary = getStringFromPart(part);
		
		part = request.getPart("dpAbout");
		String dpAbout = getStringFromPart(part);
		
		part = request.getPart("dpPassword");
		String dpPassword = getStringFromPart(part);
		
		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setFirstName(dpFirstName);
		deliveryPerson.setLastName(dpLastName);
		deliveryPerson.setFatherName(dpFatherName);
		if (dpGender.equals("MALE"))
			deliveryPerson.setGender(Gender.MALE);
		else
			deliveryPerson.setGender(Gender.FEMALE);
		
		deliveryPerson.setDob(dpDob);
		deliveryPerson.setPicture(dpPicture);
		deliveryPerson.setMobileNo(dpMobileNo);
		deliveryPerson.setEmail(dpEmail);
		deliveryPerson.setVehicalNo(dpVehicalNo);
		deliveryPerson.setAddress(dpAddress);
		deliveryPerson.setSalary(Double.parseDouble(dpSalary));
		deliveryPerson.setCommSalary(Double.parseDouble(dpCommSalary));
		deliveryPerson.setNote(dpAbout);
		deliveryPerson.setPasswd(dpPassword);
		
		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");
		
		DeliveryPersonDao dao = new DeliveryPersonDaoImpl(dataSource,sql);
		
		try {
			int id = dao.insertDeliveryPerson(deliveryPerson);
			response.sendRedirect(request.getContextPath()+"/Admin/DeliveryPersons?deliveryPersonId="+id);
		}
		catch(SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
