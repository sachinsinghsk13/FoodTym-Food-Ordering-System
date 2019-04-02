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

import com.foodtym.admin.beans.DeliveryPerson;
import com.foodtym.admin.beans.Restaurant;
import com.foodtym.admin.daomodels.DeliveryPersonDaoImpl;
import com.foodtym.admin.daomodels.RestaurantDaoImpl;

@WebServlet("/Admin/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		BasicDataSource dataSource = (BasicDataSource) getServletContext().getAttribute("DataSource");
		Properties sql = (Properties) getServletContext().getAttribute("SQL_QUERIES");

		if (type.equals("restaurant")) {

			RestaurantDaoImpl daoImpl = new RestaurantDaoImpl(dataSource, sql);

			String by = request.getParameter("by");
			if (by.equals("recentlyadded")) {
				try {
					List<Restaurant> list = daoImpl.getRecentlyAddedRestaurant();
					request.setAttribute("restaurants", list);
					request.getRequestDispatcher("/Admin/RestaurantSearchResult.jsp").forward(request, response);
					
				} catch (SQLException e) {e.printStackTrace();
				}
			}
			else if (by.equals("nameid")) {
				try {
					String nameid = request.getParameter("nameid");
					List<Restaurant> list = daoImpl.searchRestaurantByNameOrId(nameid);
					request.setAttribute("restaurants", list);
					request.getRequestDispatcher("/Admin/RestaurantSearchResult.jsp").forward(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}

			} else if (by.equals("locality")) {
				String locality = request.getParameter("locality");
				String region = request.getParameter("region");
				try {
					List<Restaurant> restaurants = daoImpl.searchRestaurantByLocality(locality, region);
					if (restaurants.size() > 0) {
						request.setAttribute("restaurants", restaurants);
						request.getRequestDispatcher("/Admin/RestaurantSearchResult.jsp").forward(request, response);
					} else {
						response.getWriter().println("<div class=\"container my-5\">\r\n"
								+ "        <h3 class=\"text-center\">No Results Found</h3>\r\n" + "    </div>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if (type.equals("deliveryperson")){
			DeliveryPersonDaoImpl daoImpl = new DeliveryPersonDaoImpl(dataSource, sql);
			String by = request.getParameter("by");
			if (by.equals("recentlyadded")) {
				try {
					List<DeliveryPerson> list = daoImpl.getRecentlyAddedDeliveryPersons();
					request.setAttribute("deliverypersons", list);
					request.getRequestDispatcher("/Admin/DeliveryPersonSearchResult.jsp").forward(request, response);
					
				} catch (SQLException e) {e.printStackTrace();
				}
			}
			else if (by.equals("nameid")) {
				try {
					String nameid = request.getParameter("nameid");
					List<DeliveryPerson> list = daoImpl.searchDeliveryPersonByNameId(nameid);
					request.setAttribute("deliverypersons", list);
					request.getRequestDispatcher("/Admin/DeliveryPersonSearchResult.jsp").forward(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}

			} else if (by.equals("deliveryarea")) {
				String locality = request.getParameter("locality");
				String region = request.getParameter("region");
				try {
					List<DeliveryPerson> restaurants = daoImpl.getDeliveryPersonByDeliveryArea(region, locality);
					if (restaurants.size() > 0) {
						request.setAttribute("deliverypersons", restaurants);
						request.getRequestDispatcher("/Admin/DeliveryPersonSearchResult.jsp").forward(request, response);
					} else {
						response.getWriter().println("<div class=\"container my-5\">\r\n"
								+ "        <h3 class=\"text-center\">No Results Found</h3>\r\n" + "    </div>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
