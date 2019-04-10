<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cus"%>
<c:if test="${sessionScope.AdminLoginSession.isLoggedIn}">
    <c:redirect url="Admin/Admin.jsp" />
</c:if>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/all.min.css">
    <title>Admin Login</title>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm sticky-top">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand mb-0 h1">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="logo" width="60px" height="60px">
            FoodTym Administrator Login
        </a>
    </nav>
    <div class="container p-3">
        <div class="row">
            <div class="col-md-7 m-3 p-3 shadow offset-2">
                <p class="text-center h5">Admin Login</p>
                <hr>
                <div class="admin-login-form p-2">
                    <c:if test="${param.FailedLoginRequest eq true }">
                        <p class="text-danger h6 m-2">Invalid Password or Username</p>
                    </c:if>

                    <form action="AdminLogin" method="post">
                        <input type="text" class="form-control m-2" name="username" placeholder="Username">
                        <input type="password" class="form-control m-2" name="passwd" placeholder="Password">
                        <div class="custom-control custom-checkbox m-2">
                            <input type="checkbox" class="custom-control-input" id="rememberme" name="remember">
                            <label for="rememberme" class="custom-control-label"> Remember Me</label>
                        </div>
                        <input type="submit" class="form-control m-2 btn btn-primary" value="Login">

                    </form>
                </div>
            </div>
            <div class="col-md-4 m-3">
                <p class="display-4">Welcome to
                    <br> FoodTym <br> Administration <br> Panel
                </p>

            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</body>

</html>