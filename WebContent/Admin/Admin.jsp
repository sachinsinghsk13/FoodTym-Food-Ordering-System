<%@page import="com.foodtym.admin.daomodels.FoodTymStatsDao"%>
<%@page import="com.foodtym.admin.beans.FoodTymStats"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/admin.css">
<title>Admin FoodTym</title>

</head>
<body>
	<%--Including the Header --%>
	<c:import url="jspinclude/adminheader.jsp"></c:import>
	<div class="container-fluid my-3">
		<h2>FoodTym Statistics</h2>
		<div class="card-columns">
			<div class="card bg-primary">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Restaurants</p>
					<h4 class="card-title">${pageScope.stats.restaurants}</h4>
				</div>
			</div>
			<div class="card bg-secondary">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Delivery Persons</p>
					<h4 class="card-title">${pageScope.stats.deliveryPerson}</h4>
				</div>
			</div>
			<div class="card bg-danger">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Food Items</p>
					<h4 class="card-title">${pageScope.stats.foodItems}</h4>
				</div>
			</div>
			<div class="card bg-info">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Delhi-NCR Areas</p>
					<h4 class="card-title">${pageScope.stats.localities}</h4>
				</div>
			</div>
			<div class="card bg-success">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Customers</p>
					<h4 class="card-title">${pageScope.stats.customers}</h4>
				</div>
			</div>
			<div class="card bg-warning">
				<div class="card-body text-center">
					<p class="card-text text-capitalize">Orders Waiting</p>
					<h4 class="card-title">${pageScope.stats.orderWaiting}</h4>
				</div>
			</div>
		</div>
		<h2 class="page-header">About Us</h2>

	</div>
	<c:import url="jspinclude/footer.jsp"></c:import>
	<script
		src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</body>
</html>