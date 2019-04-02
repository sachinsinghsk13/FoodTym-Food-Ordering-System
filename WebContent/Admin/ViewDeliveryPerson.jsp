<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cus"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<cus:bootstrapcss/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
<cus:bootstrapjs/>
<script src="${pageContext.request.contextPath}/static/js/delivery_person_view.js"></script>
<title>${requestScope.restaurant.name}</title>
</head>
<body>
	<!-- This Div stores the delivery person id .. to use in javascript and ajax calls -->
	<div style="display:none" data-id="${requestScope.deliveryPerson.id}" id="delivery-person-id"></div>
	
	<cus:adminheader activelink="deliveryperson"/>
	<ul class="nav nav-tabs" role="tabs-list">
		<li class="nav-item"><a href="#delivery-person-tab"
			data-toggle="tab" class="nav-link active">Delivery Person</a></li>
		<li class="nav-item"><a href="#delivery-areas-tab"
			data-toggle="tab" class="nav-link">Delivery Areas</a></li>
	</ul>

	<!--Tabs Content Panes-->

	<div class="tab-content">

		<!-- Restaurant Info Tab-->
		<div id="delivery-person-tab" class="tab-pane container-fluid active">
			<div class="row">
				<div class="col-md-6 text-capitalize px-3 text-dark">
					<h1>${requestScope.deliveryPerson.firstName}
						${requestScope.deliveryPerson.lastName}</h1>
				</div>
				<div class="col-md-4 p-3">
					<a class="btn btn-info" href="#">Edit Details</a> <a
						class="btn btn-danger" href="#">Remove</a>
				</div>

			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<tr>
							<td>
								<h4>Delivery Person ID :</h4>
							</td>
							<td colspan="3">
								<h5>${requestScope.deliveryPerson.id}</h5>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Date of Birth</h5>
							</td>
							<td>
								<h6>${requestScope.deliveryPerson.dob}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Gender</h5>
							</td>
							<td>
								<h6>${requestScope.deliveryPerson.gender}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Address:</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.deliveryPerson.address}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Mobile:</h5>
							</td>
							<td>
								<h6>${requestScope.deliveryPerson.mobileNo}</h6>
							</td>
						</tr>
						<tr>

							<td>
								<h5>Email:</h5>
							</td>
							<td>
								<h6>${requestScope.deliveryPerson.email}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Vehical No.</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.deliveryPerson.vehicalNo}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Join Date</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.deliveryPerson.joinDate}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Salary</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.deliveryPerson.salary}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Commission Salary</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.deliveryPerson.commSalary}per order</h6>
							</td>
						</tr>

						<tr>
							<td>
								<h5>About:</h5>
							</td>
							<td>
								<h6>${requestScope.deliveryPerson.note}</h6>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<!-- Delivery Area Tab-->
		<div id="delivery-areas-tab" class="tab-pane container-fluid">
			<div class="row p-3">
				<button class="btn btn-success" id="show-assign-area">Assign
					a New Delivery Area</button>
			</div>
			<div class="row p-3" id="area-selection-div" style="display: none">
				<div class="col-md-4">
					<div class="form-group">
						<div class="autocomplete-box">
							<input type="text" class="form-control" id="ncrRegions-input"
								placeholder="Delhi-NCR Region">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<div class="autocomplete-box">
							<input type="text" class="form-control" id="locality-input" placeholder="Locality">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<button class="btn btn-primary btn-block" id="assign-area-submit">Assign
							Delivery Area</button>
					</div>
				</div>
			</div>
			<div class="row" id="area-table-row">
				
			</div>
		</div>
	</div>
	</div>

	<c:import url="jspinclude/footer.jsp"></c:import>
</body>
</html>