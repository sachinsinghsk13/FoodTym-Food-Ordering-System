<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restaurant Not Found</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/static/jquery.min.js""></script>
<script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="jspinclude/adminheader.jsp"></c:import>
<div class="container">
        <div class="jumbotron m-5">
            <h1>Restaurant Not Found!</h1>
            <h3>May be its deleted from database or the Restaurant ID is invalid.</h3>
            <a href="${pageContext.request.contextPath}/" class="btn btn-lg btn-danger">Go To Admin Home</a>
        </div>
</div>
	<c:import url="jspinclude/footer.jsp"></c:import>
</body>
</html>