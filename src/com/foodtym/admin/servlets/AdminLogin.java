package com.foodtym.admin.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.daomodels.AdminDao;
import com.foodtym.admin.daomodels.AdminDaoImpl;
import com.foodtym.admin.utils.AdminSessionDetails;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Properties getSqlQueries() {
		return (Properties) getServletContext().getAttribute("SQL_QUERIES");
	}
	
	private BasicDataSource getDataSource() {
		return (BasicDataSource) getServletContext().getAttribute("DataSource");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		String remember = request.getParameter("remember");
		
		AdminSessionDetails adminSessionDetails = null;
		
		AdminDao dao = new AdminDaoImpl(getDataSource(), getSqlQueries());
		
		try {
			adminSessionDetails = dao.checkLogin(username, password); // can't returns a null.
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500); // server side error
		}
		
		if (adminSessionDetails.getIsLoggedIn()) {
			
			// store cookies on client browser
			if (remember!=null) {
				Cookie usernameCookie = new Cookie("username", adminSessionDetails.getUsername());
				Cookie passwordCookie = new Cookie("password", adminSessionDetails.getEncryptedPassword());
				
				usernameCookie.setMaxAge(90 * 24 * 60 * 60);
				passwordCookie.setMaxAge(90 * 24 * 60 * 60);
				
				response.addCookie(usernameCookie);
				response.addCookie(usernameCookie);
			}
			
			// record login details on session immediate veryfication
			HttpSession session = request.getSession();
			session.setAttribute("AdminLoginSession", adminSessionDetails);
			response.sendRedirect(request.getContextPath() + "/Admin/Admin.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/index.jsp?FailedLoginRequest=true");
		}
		
	}

}
