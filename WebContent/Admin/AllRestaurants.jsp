<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cus"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<cus:bootstrapcss />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css"/>
<cus:bootstrapjs />
<script src="${pageContext.request.contextPath}/static/js/foodtym.restaurants.js"></script>
</head>
<body>
	<%--Admin Header --%>
	<cus:adminheader activelink="restaurant" />
	<%--Search Section --%>
	<div class="container-fluid my-3 border-bottom">
		<div class="row">
			<div class="col-sm-4 m-1">
				<a href="${pageContext.request.contextPath}/Admin/RestaurantRegistration.jsp" class="btn btn-success mx-1 my-2"> <i
					class="fas fa-plus"></i> New Restaurant
				</a>
				<button class="btn btn-primary mx-1 my-2" id="search-type-btn"
					data-current="byname">Search In Localities</button>
			</div>
			<div class="col-md-7 m-2" id="search-by-name-box">
				<input type="text" id="restaurant-search-bar"
					placeholder="Enter Restauant ID or Name">
			</div>
			<div class="col-sm-6 m-2" id="search-by-locality-box"
				style="display: none">
				<div class="row">
					<div class="col-sm-5">
						<div class="form-group">
							<select name="" id="" class="form-control"
								style="-webkit-appearance: none !important;">
								<option value="434">Choose NCR Region</option>
							</select>
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group">
							<select name="" id="" class="form-control"
								style="-webkit-appearance: none !important;">
								<option value="">Choose Locality</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%--Result Display Area --%>
	 <div class="container-fluid" id="result-container">
	 	<%--Ajax Loading Animation --%>
        <div class="text-center"  style="display:none;margin-top:150px;" id="loading-animation">
            <div class="spinner-grow text-primary" role="status">
                <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-danger" role="status">
                <span class="sr-only">Loading...</span>
            </div>
            <div class="spinner-grow text-success" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <%--Container to display result items --%>
        <div class="d-flex justify-content-center flex-wrap" id="result-item-container">
            
        </div>

    </div>
</body>
</html>