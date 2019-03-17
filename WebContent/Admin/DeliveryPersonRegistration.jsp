<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/admin.css">
<script src="${pageContext.request.contextPath}/static/jquery.min.js""></script>
<script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/script.js"></script>
<title>New Delivery Person</title>
</head>
<body>
	<c:import url="jspinclude/adminheader.jsp"></c:import>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>New Delivery Person</h3>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/Admin/DeliveryPersons" method="POST" enctype="multipart/form-data">
			<div class="row">
				<legend>Personal Details</legend>
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpFirstName">First Name: </label> <input type="text"
							required="required" class="form-control" id="dpFirstName"
							name="dpFirstName" placeholder="First Name">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpLastName">Last Name: </label>
						<div class="autocomplete-div">
							<input type="text" class="form-control" id="dpLastName"
								name="dpLastName" placeholder="Last Name">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpFatherName">Father Name: </label>
						<div class="autocomplete-div">
							<input type="text" class="form-control" id="dpFatherName"
								name="dpFatherName" placeholder="Father Name">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpAddress">Address:</label>
						<textarea class="form-control" id="dpAddress"
							name="dpAddress" placeholder="Full Address"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpDob">Date of Birth.</label> <input type="date"
							name="dpDob" id="dpDob" class="form-control"
							placeholder="Date of Birth">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpGender">Gender:</label><br>
						<div class="form-check-inline">
							<label for="" class="form-check-label"> <input
								type="radio" name="dpGender" class="form-check-input"
								checked="checked" value="MALE">Male
							</label>
						</div>
						<div class="form-check-inline">
							<label for="" class="form-check-label ownerGender"> <input
								type="radio" name="dpGender" class="form-check-input" value="FEMALE">Female
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpMobile">Mobile No.</label> <input type="text"
							name="dpMobileNo" id="dpMobileNo" class="form-control"
							placeholder="Mobile No.">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpEmail">Email</label> <input type="email"
							name="dpEmail" id="dpEmail" class="form-control"
							placeholder="Email">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label for="dpSalary">Salary Amount:</label> <input type="number"
							class="form-control" id="dpSalary" name="dpSalary"
							placeholder="Salary">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="dpCommSalary">Commision Salary</label> <input
							type="number" class="form-control" id="dpCommSalary"
							name="dpCommSalary"
							placeholder="Commision Salary Per Order Delivery">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="dpVehicalNo">Vehical Number:</label> <input
							type="text" name="dpVehicalNo" id="dpVehicalNo"
							class="form-control" placeholder="Vehical Number">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="restAbout">About:</label>
						<textarea name="dpAbout" id="dpAbout" class="form-control"
							placeholder="Write Something About Delivery Person"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="dpPassword">Password:</label> <input type="password"
							class="form-control" id="dpPassword" name="dpPassword"
							placeholder="Password">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="">Confirm Password</label>
						<button type="button" class="form-control btn btn-info"
							id="showPasswordBtn">Show Password</button>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<input type="submit" value="Register"
							class="btn btn-primary btn-block form-control">
					</div>
				</div>
			</div>
		</form>
	</div>
	<c:import url="jspinclude/footer.jsp"></c:import>
</body>
</html>