<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#logotext {
	font-size: 2em;
	margin: 0px 10px;
	color: whitesmoke
}

.navbar {
	background-color: #000911 !important;
	color: aliceblue;
}

#navbarMenu {
	font-size: 18px;
}
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
	<span class="navbar-text" id="logotext">FoodTym Admin</span>
	<button class="navbar-toggler" data-toggle="collapse"
		data-target="#navbarMenu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse " id="navbarMenu">
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown"
				data-target="#admin_dropdown">Admin</a>
				<div class="dropdown-menu" aria-labelledby="admin_dropdown">
					<a href="#" class="dropdown-item">Home</a> <span class="seperator"></span>
					<form action="${pageContext.request.contextPath}/AdminLogout"
						method="POST" class="dropdown-item">
						<input type="submit" value="Logout"
							class=" btn btn-danger btn-block">
					</form>
				</div></li>
			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown"
				data-target="#restaurant_dropdown">Restaurants</a>
				<div class="dropdown-menu" aria-labelledby="restaurant_dropdown">
					<a href="${pageContext.request.contextPath}/Admin/RestaurantRegistration.jsp"
						class="dropdown-item">New Restaurant</a> 
						<a href="${pageContext.request.contextPath}/Admin/AllRestaurants.jsp" class="dropdown-item">View Restaurant</a>

				</div></li>

			<li class="nav-item dropdown"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown"
				data-target="delivery_person_dropdown">Delivery Person</a>
				<div class="dropdown-menu">
					<a href="${pageContext.request.contextPath}/Admin/DeliveryPersonRegistration.jsp" class="dropdown-item">New Delivery Person</a> 
					<a href="${pageContext.request.contextPath}/Admin/AllDeliveryPerson.jsp"
						class="dropdown-item">View a Delivery Person</a>
				</div></li>
			<li class="nav-item"><a href="#" class="nav-link">Service
					Areas</a></li>
		</ul>
	</div>
</nav>