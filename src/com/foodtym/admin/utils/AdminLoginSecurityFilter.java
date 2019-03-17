package com.foodtym.admin.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.daomodels.AdminDao;
import com.foodtym.admin.daomodels.AdminDaoImpl;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/Admin/*" })
public class AdminLoginSecurityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String contextPath = req.getServletContext().getContextPath();

		HttpSession session = req.getSession(false); // returns only old session

		if (session != null && session.getAttribute("AdminLoginSession") != null
				&& ((AdminSessionDetails) session.getAttribute("AdminLoginSession")).getIsLoggedIn()) {
			chain.doFilter(request, response);
		} else {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {

				String username = null, password = null;

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username")) {
						username = cookie.getValue();
					}

					if (cookie.getName().equals("password")) {
						password = cookie.getValue();
					}
				}

				BasicDataSource dataSource = (BasicDataSource) req.getServletContext().getAttribute("DataSource");
				Properties sqlQuries = (Properties) req.getServletContext().getAttribute("SQL_QUERIES");

				AdminDao dao = new AdminDaoImpl(dataSource, sqlQuries);

				try {
					AdminSessionDetails adminSessionDetails = dao.checkLogin(username, password);

					if (adminSessionDetails.getIsLoggedIn()) {
						session = req.getSession();
						session.setAttribute("AdminLoginSession", adminSessionDetails);
						res.sendRedirect(contextPath + "/Admin/Admin.jsp");
					} else {
						res.sendRedirect(contextPath + "/index.jsp"); // goto login page if login credentials found
																		// wrong
					}
				} catch (SQLException e) {
					e.printStackTrace();
					res.sendRedirect(contextPath + "/index.jsp"); // goto login page in case of login verification
																	// process fail.
				}
			}
		}
	}

}
