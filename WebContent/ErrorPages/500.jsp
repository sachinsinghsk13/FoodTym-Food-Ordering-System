<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>500 Server Error</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/static/jquery.min.js""></script>
<script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
         <div class="jumbotron">
             <h1 class="text-center">500 Server Error</h1>
             <h4 class="text-center text-muted">Something went wrong while processing your request</h4>
             <h3 class="text-center">We're Trying to Fix It. Please Visit Us Later</h3>
             <div class="text-center">
                 <a href="${pageContext.request.contextPath}/" class="btn btn-primary m-2">Go To Admin Home</a>
                 <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Contact At Foodtym</a>
             </div>
         </div>
     </div>
</body>
</html>