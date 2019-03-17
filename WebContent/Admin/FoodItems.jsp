<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<c:forEach items="${requestScope.foodItems}" var="foodItem"
		varStatus="foodCount">
		<c:if test="${foodCount.count mod 2 != 0}">
			<div class="row my-3">
		</c:if>
		
		<div class="col-lg-5 clearfix food-item shadow m-3 p-3 justify-content-center">
			<img src="${pageContext.request.contextPath}/Admin/Restaurants/FoodItems/Thumbs?foodItemId=${foodItem.foodItemId}" alt="" class="img-thumbnail food-thumb float-left">
			<div class="row">
				<div class="col-sm-6">
					<h5>${foodItem.title}</h5>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<small class="text-muted">${foodItem.description}</small>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 text-danger">
					<small><b><i class="fas fa-hamburger"></i>${foodItem.category}</b></small>
				</div>
				<div class="col-sm-4 text-success">
					<small>${foodItem.subCategory}</small>
				</div>
				<div class="col-sm-4 text-primary">
					<small><i class="far fa-clock"></i> ${foodItem.preparingTime}Min</small>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-8 text-info">
					<c:choose>
						<c:when test="${foodItem.priceBasis eq 'HALF_FULL'}">
							HALF_FULL
						</c:when>
						<c:when test="${foodItem.priceBasis eq 'PCS'}">
							PCS
						</c:when>
						<c:when test="${foodItem.priceBasis eq 'KG'}">
							KG
						</c:when>
					</c:choose>
				</div>
				<div class="col-sm-4 float-right">
					
						<c:if test="${foodItem.type eq 'VEG'}">
							<b class="text-success"><i class="fas fa-carrot"></i>VEG</b>
						</c:if>
						<c:if test="${foodItem.type eq 'NONVEG'}">
							<b class="text-danger"><i class="fas fa-drumstick-bite"></i>NONVEG</b>
						</c:if>
				</div>
			</div>
			<div class="row">
				<table class="table ">
					<thead>
						<tr class="text-">
							<td></td>
							<c:forEach var="priceType" items="${foodItem.priceTypes}">
								<td>${priceType.type}</td>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-primary"><i class="fas fa-rupee-sign"></i>FoodTym</td>
							<c:forEach var="priceType" items="${foodItem.priceTypes}">
								<td>${priceType.foodtymPrice}</td>
							</c:forEach>
						</tr>
						<tr>
							<td class="text-danger"><i class="fas fa-rupee-sign"></i>
								Restaurant</td>
							<c:forEach var="priceType" items="${foodItem.priceTypes}">
								<td>${priceType.restaurantPrice}</td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-5 offset-7">
					<div class="btn-group">
						<button class="btn btn-success btn-sm">Edit</button>
						<button class="btn btn-outline-danger btn-sm">Remove</button>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${foodCount.count mod 2 == 0}">
			</div> <%--End the row --%>
		</c:if>
	</c:forEach>
</div>