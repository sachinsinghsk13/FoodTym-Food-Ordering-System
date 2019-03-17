package com.foodtym.admin.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogout")
public class AdminLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Remove Cookies
		Cookie usernameCookie = new Cookie("username", "");
		usernameCookie.setMaxAge(0); // save cookie for 3 months
		Cookie passwordCookie = new Cookie("password","");
		passwordCookie.setMaxAge(0);
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);
		
		// Invalidate the session
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		// Redirect user to login page.
		String contextPath = request.getServletContext().getContextPath();
		response.sendRedirect(contextPath+"/");
	}

}
