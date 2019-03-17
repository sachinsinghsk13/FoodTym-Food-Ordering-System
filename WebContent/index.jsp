<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.AdminLoginSession.isLoggedIn}">
	<c:redirect url="Admin/Admin.jsp" />
</c:if>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/adminlogin.css">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<p class="navbar-brand">FoodTym Admin Login</p>
	</nav>
	<div class="container py-5 card">
		<div class="row ">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<!-- Login Form -->
				<form action="AdminLogin" method="post">
					<c:if test="${param.FailedLoginRequest eq true }">
						<p class="alert alert-danger">Invalid Password or Username</p>
					</c:if>
					<div class="form-group">
						<label for="username">@<b>Username</b></label> <input type="text"
							name="username" id="username" class="form-control"
							placeholder="Admin Username">
					</div>
					<div class="form-group">
						<label for="passwd"><b>Password</b></label> <input type="password"
							name="passwd" id="passwd" class="form-control"
							placeholder="Password">
					</div>
					<div class="form-group">
						<div class="col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox" name="remember">
									Remember Me
								</label>
							</div>
						</div>
					</div>
					<input type="submit" value="Login"
						class=" form-control btn btn-primary btn-block">
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<footer class="navbar navbar-dark ">
		<p class="navbar-text text-center">FoodTym Copyright : Online Food Ordering System</p>
	</footer>
	<script
		src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</body>
</html>