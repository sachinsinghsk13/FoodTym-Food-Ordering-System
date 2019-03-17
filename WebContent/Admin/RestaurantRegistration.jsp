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
<script src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/script.js"></script>
<title>New Restaurant</title>
</head>
<body>
	<c:import url="jspinclude/adminheader.jsp"></c:import>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>New Restaurant</h3>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/Admin/Restaurants"
			method="POST" enctype="multipart/form-data">
			<div class="row">
				<legend>Restaurant Details</legend>
				<div class="col-md-6">
					<div class="form-group">
						<label for="restName">Restaurant Name: </label> <input type="text"
							class="form-control" id="restName" name="restName"
							placeholder="Restaurant Name" required="required">

					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="restNcrRegion">Delhi-NCR Region: </label>
						<div class="autocomplete-box">

							<input type="text" class="form-control" id="restNcrRegion"
								name="restNcrRegion" placeholder="Delhi-NCR Region"
								required="required">

						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="restLocality">Locality: </label>
						<div class="autocomplete-box">
							<input type="text" class="form-control" id="restLocality"
								name="restLocality" placeholder="Locality" required="required">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="restAddress">Address:</label>
						<textarea class="form-control" id="restAddress" name="restAddress"
							placeholder="Full Address" required="required"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="restFssai">FSSAI Registraion No.</label> <input
							type="number" name="restFssai" id="restFssai"
							class="form-control" placeholder="FSSAI No">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="restMobile">Mobile No.</label> <input type="text"
							name="restMobile" id="restMobile" class="form-control"
							placeholder="PayTm & Login Mobile No." required="required">
						<p class="form-text text-muted">This Mobile No. must have a
							assosiated PayTm account.</p>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="restEmail">Email</label> <input type="email"
							name="restEmail" id="restEmail" class="form-control"
							placeholder="Restaurant Email" required="required">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label for="restRegAmount">Registraion Amount:</label> <input
							type="text" class="form-control" id="restRegAmount"
							name="restRegAmount" placeholder="Registration Amount"
							value="0.0">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label for="restBanner">Restaurant Banner</label>
						<div class="custom-file">
							<label for="restBanner" class="custom-file-label">Restaurant
								Banner</label> <input type="file" name="restBanner" id="restBanner"
								class="custom-file-input" placeholder="Banner">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="restOpenTime">Open Time : </label> <input type="time"
							name="restOpenTime" id="restOpenTime" class="form-control"
							placeholder="FSSAI No" required="required">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="restCloseTime">Close Time.</label> <input type="time"
							name="restCloseTime" id="restCloseTime" class="form-control"
							placeholder="FSSAI No" required="required">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="restAbout">About:</label>
						<textarea name="restAbout" id="restAbout" class="form-control"
							placeholder="Write Something About This Restaurant"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="restPassword">Password:</label> <input type="password"
							class="form-control" id="restPassword" name="restPassword"
							placeholder="Password" required="required">
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
				<legend>Owner Details</legend>
				<div class="col-md-4">
					<div class="btn-group">
						<button type="button" class="btn btn-outline-success active"
							id="newOwnerBtn">New Owner</button>
						<button type="button" class="btn btn-outline-danger"
							id="existOwnerBtn">Existing Owner</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6" id="ownerIdDiv" style="display: none">
					<div class="form-group">
						<label for="">Owner ID:</label> <input type="text"
							class="form-control" id="ownerId" name="ownerId"
							placeholder="Owner ID" value="-1">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="ownerFirstName">First Name:</label> <input type="text"
							class="form-control ownerDetails" name="ownerFirstName"
							id="ownerFirstName" placeholder="First Name" required="required">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="ownerLastName">Last Name:</label> <input type="text"
							class="form-control ownerDetails" name="ownerLastName"
							id="ownerLastName" placeholder="Last Name" required="required">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group gender-form-group">
						<label for="ownerGender">Gender:</label><br>
						<div class="form-check-inline">
							<label for="" class="form-check-label"> <input
								type="radio" name="ownerGender"
								class="form-check-input ownerDetails" value="MALE"
								checked="checked">Male
							</label>
						</div>
						<div class="form-check-inline">
							<label for="" class="form-check-label ownerGender"> <input
								type="radio" name="ownerGender" value="FEMALE"
								class="form-check-input">Female
							</label>
						</div>
					</div>
				</div>
				<div class="offset-2 col-md-4">
					<div class="form-group">
						<label for="ownerDob">Date of Birth:</label> <input type="date"
							class="form-control ownerDetails" name="ownerDob" id="ownerDob"
							required="required">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="ownerMobile">Mobile No.</label> <input type="text"
							name="ownerMobile" id="ownerMobile"
							class="form-control ownerDetails" placeholder="Owner Mobile No."
							required="required">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="ownerEmail">Email</label> <input type="email"
							name="ownerEmail" id="ownerEmail"
							class="form-control ownerDetails" placeholder="Owner Email"
							required="required">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="ownerAddress">Address:</label>
						<textarea class="form-control ownerDetails" id="ownerAddress"
							name="ownerAddress" placeholder="Owner Address"
							required="required"></textarea>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="restBanner">Owner Picture</label>
						<div class="custom-file">
							<label for="restBanner" class="custom-file-label">Owner Picture</label> <input type="file" name="ownerPicture" id="ownerPicture"
								class="custom-file-input" placeholder="Onwer Picture">
						</div>
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