<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="dp" items="${requestScope.deliverypersons}">
	<div class="container-fluid result-item shadow p-2 bg-light border"
		data-id="${dp.id}">
		<div class="row">
			<div class="col-lg-12">
				<span class="text-danger h5">${dp.firstName} ${dp.lastName}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<strong>ID: </strong><span>${dp.id}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<strong>Father Name: </strong><span>${dp.fatherName}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<strong>Ph: </strong> <span class="h6 text-primary">${dp.mobileNo}</span>
			</div>
		</div>
	</div>

</c:forEach>