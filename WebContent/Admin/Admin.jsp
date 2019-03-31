<%@page import="com.foodtym.admin.daomodels.FoodTymStatsDao"%>
<%@page import="com.foodtym.admin.beans.FoodTymStats"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cus"%>

<%-- Including the stats object in request attribute --%>
<%
	FoodTymStatsDao dao = (FoodTymStatsDao) getServletContext().getAttribute("FOODTYM_STATS_DAO");
	FoodTymStats stats = dao.getFoodTymStats();
	pageContext.setAttribute("stats", stats);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<cus:bootstrapcss/>
<cus:bootstrapjs/>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/admin.css">
<title>Admin | ${stats.adminName }</title>

</head>
<body>
	<%--Including the Header --%>
	<cus:adminheader activelink="home"/>
	<div class="container-fluid my-5">
        <p class="display-4">Welcome, <strong>${stats.adminName }</strong></h5>
        
    </div>
    <div class="container-fluid shadow my-3">
        <div class="card-columns">
            <div class="card bg-warning">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                       Restaurants!
                    </p>
                    <h4 class="card-title">${stats.restaurants }</h4>
                </div>
            </div>
            <div class="card bg-success">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                        Delivery Persons
                    </p>
                    <h4 class="card-title">${stats.deliveryPerson}</h4>
                </div>
            </div>
            <div class="card bg-danger">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                        Food Items
                    </p>
                    <h4 class="card-title">${stats.foodItems}</h4>
                </div>
            </div>
            <div class="card bg-info">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                        Delhi-NCR Areas
                    </p>
                    <h4 class="card-title">${stats.localities}</h4>
                </div>
            </div>
            <div class="card bg-success">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                        Customers
                    </p>
                    <h4 class="card-title">${stats.customers}</h4>
                </div>
            </div>
            <div class="card bg-warning">
                <div class="card-body text-center">
                    <p class="card-text text-capitalize">
                        Orders Waiting
                    </p>
                    <h4 class="card-title">${stats.orderWaiting }</h4>
                </div>
            </div>
        </div>
    </div>
     
    <footer class="container-fluid">
        <p class="text-center"><strong>FoodTym &COPY; 2019</strong></p>
    </footer>	
</body>
</html>