package com.foodtym.admin.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.BasicDataSource;

import com.foodtym.admin.beans.FoodTymStats;
import com.foodtym.admin.daomodels.FoodTymStatsDao;

@WebListener
public class FoodTymAppInitializer implements ServletContextListener {
	private static final String DB_CONFIG_FILE_NAME = "database-prop.properties";

	public FoodTymAppInitializer() {

	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		Properties p = new Properties();
		BasicDataSource dataSource = new BasicDataSource();
		try {
			p.load(sce.getServletContext().getResourceAsStream("WEB-INF/configurations/" + DB_CONFIG_FILE_NAME));
			dataSource.setDriverClassName(p.getProperty("driver.classname"));
			dataSource.setUrl(p.getProperty("db.url"));
			dataSource.setUsername(p.getProperty("db.credentials.user"));
			dataSource.setPassword(p.getProperty("db.credentials.password"));
			int maxConnections = Integer.parseInt(p.getProperty("db.max-connections"));
			dataSource.setMaxTotal(maxConnections);
			dataSource.getConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		sce.getServletContext().setAttribute("DataSource", dataSource);
		
		Properties sqlfile = new Properties();
		try {
			sqlfile.load(sce.getServletContext().getResourceAsStream("WEB-INF/sql_queries/sql.properties"));
			sce.getServletContext().setAttribute("SQL_QUERIES", sqlfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FoodTymStatsDao foodTymStatsDao = new FoodTymStatsDao(dataSource, sqlfile);
		sce.getServletContext().setAttribute("FOODTYM_STATS_DAO",foodTymStatsDao);
		
	}

}
