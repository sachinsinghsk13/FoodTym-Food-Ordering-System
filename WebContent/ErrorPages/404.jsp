<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>404 Not Found</title>
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
             <h1 class="text-center">404 Not Found Error!</h1>
             <p class="text-center text-muted">You request for a resource that is not found on the server.</p>
             <div class="text-center">
                 <a href="${pageContext.request.contextPath}/" class="btn btn-primary m-2">Go To Admin Home</a>
                 <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Contact At Foodtym</a>
             </div>
         </div>
     </div>
</body>
</html>