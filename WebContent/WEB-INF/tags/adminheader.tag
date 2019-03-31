<%@ attribute name="activelink" required="true" rtexprvalue="false" %>
<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm sticky-top">
	<a href="#" class="navbar-brand mb-0 h1">
		<img src="../static/images/logo.png" alt="logo" width="60px" height="60px"> FoodTym Administrator
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarmenu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarmenu" data-="">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item <c:if test="${activelink eq 'home'}">active</c:if> "><a href="/FoodTymAdmin/Admin/Admin.jsp" class="nav-link"><i
					class="fas fa-home"></i> Home</a></li>
					
			<li class="nav-item <c:if test="${activelink eq 'restaurant'}">active</c:if>"> <a href="/FoodTymAdmin/Admin/AllRestaurants.jsp" class="nav-link"><i
					class="fas fa-utensils"></i> Restaurants</a></li>
					
			<li class="nav-item <c:if test="${activelink eq 'deliveryperson'}">active</c:if>"><a href="#" class="nav-link"><i
					class="fas fa-truck"></i> Delivery Persons</a></li>
		</ul>
		<div class="my-2 my-lg-0">
		<form action="/FoodTymAdmin/AdminLogout" method="POST">
			<input type="submit" class="btn btn-danger" value="Logout"></i></input>
		</form>
		</div>
	</div>
</nav>


