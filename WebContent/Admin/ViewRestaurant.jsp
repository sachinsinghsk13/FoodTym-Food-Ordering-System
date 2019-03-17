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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/all.min.css">
<script src="${pageContext.request.contextPath}/static/jquery.min.js""></script>
<script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/script.js"></script>
	<script
	src="${pageContext.request.contextPath}/static/js/foodItems.js"></script>
<title>${requestScope.restaurant.name}</title>
<style>
.price_div , .price_type_checkboxes ,.price_type_row {
    display: none;
}
#food-menu-tab > .col-md-4{
    background-color: rgb(255, 251, 251);
    margin: 15px 0px;
    box-shadow: 2px 2px 10px grey;
}
</style>
</head>
<body>
	<c:import url="jspinclude/adminheader.jsp"></c:import>

	<ul class="nav nav-tabs" role="tabs-list">
		<li class="nav-item"><a href="#restaurant-tab" data-toggle="tab"
			class="nav-link active">Restaurant</a></li>
		<li class="nav-item"><a href="#owner-tab" data-toggle="tab"
			class="nav-link">Owner</a></li>
		<li class="nav-item"><a href="#food-menu-tab" data-toggle="tab"
			class="nav-link">Food Menu</a></li>
        <li class="nav-item">
            <a href="#add-food-menu-tab" data-toggle="tab" class="nav-link">Add Food Items</a>
        </li>
	</ul>

	<!--Tabs Content Panes-->

	<div class="tab-content">

		<!-- Restaurant Info Tab-->
		<div id="restaurant-tab" class="tab-pane container-fluid active">
			<div class="row">
				<div class="col-md-2 text-capitalize px-3 text-dark">
					<h1>${requestScope.restaurant.name}</h1>
				</div>
				<div class="col-md-4 p-3">
					<a class="btn btn-info" href="#">Edit Details</a> <a
						class="btn btn-danger" href="#">Remove</a>
				</div>
				<div class="col-md-6">
					<img src="${pageContext.request.contextPath}/Admin/Restaurants/Banners?restaurantId=${requestScope.restaurant.id}" id="banner-img" alt=""
						class="img-fluid">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<tr>
							<td>
								<h4>Restaurant ID :</h4>
							</td>
							<td colspan="3">
								<h5>${requestScope.restaurant.id}</h5>
							</td>
						</tr>
						<tr>
							<td>
								<h5>NCR Region:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.locality.ncrRegionName}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Locality:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.locality.localityName}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Address:</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.restaurant.address}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Mobile:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.mobileNo}</h6>
							</td>
						</tr>
						<tr>

							<td>
								<h5>Email:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.email}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>FSSAI Reg. No.</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.restaurant.fssaiNo}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Registration Date</h5>
							</td>
							<td colspan="3">
								<h6>${requestScope.restaurant.regDate}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Registration Amount</h5>
							</td>
							<td colspan="3">
								<h6>Rs. ${requestScope.restaurant.regAmount}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>Open Time:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.openTime}</h6>
							</td>

						</tr>
						<tr>
							<td>
								<h5>Close Time:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.closeTime}</h6>
							</td>
						</tr>
						<tr>
							<td>
								<h5>About:</h5>
							</td>
							<td>
								<h6>${requestScope.restaurant.note}</h6>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<!-- Owner Info Tab-->
		<div id="owner-tab" class="tab-pane container-fluid">
			<div class="row">
				<div class="col-md-4 text-capitalize px-3 text-dark">
					<h1>Sachin Singh</h1>
				</div>
				<div class="col-md-4 p-1">
					<a class="btn btn-info" href="#">Edit Details</a>
				</div>
			</div>
			<div class="row">
				<table class="table">
					<tr>
						<td><h4>Owner ID:</h4></td>
						<td><h5>${requestScope.restaurant.owner.id}</h5></td>
					</tr>
					<tr>
						<td>
							<h5>Gender:</h5>
						</td>
						<td>
							<h6>${requestScope.restaurant.owner.gender}</h6>
						</td>
					</tr>
					<tr>
						<td>
							<h5>Date of Birth</h5>
						</td>
						<td>
							<h6>${requestScope.restaurant.owner.dob}</h6>
						</td>
					</tr>
					<tr>
						<td>
							<h5>Mobile:</h5>
						</td>
						<td>
							<h6>${requestScope.restaurant.owner.mobileNo}</h6>
						</td>

					</tr>
					<tr>
						<td>
							<h5>Email:</h5>
						</td>
						<td>
							<h6>${requestScope.restaurant.owner.email}</h6>
						</td>
					</tr>
					<tr>
						<td>
							<h5>Address:</h5>
						</td>
						<td>
							<h6>${requestScope.restaurant.owner.address}</h6>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="food-menu-tab" class="tab-pane container-fluid">
           
        </div>
        <div id="add-food-menu-tab" class="tab-pane container-fluid">
            <div class="container">
                <h3 class="m-3 text-center">Add New Food Item</h3>
                <div style="display:none" id="restaurantId" data-restaurant-id="${requestScope.restaurant.id}"></div>
                <h4>General Details</h4>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="">Food Category</label>
                        <select name="" id="food_category" class="custom-select">
                            <option value="Snanks">None</option>
                        </select>
                        <small class="form-text text-muted">Choose a Food Category</small>
                    </div>
                    <div class="form-group col-md-8">
                        <label for="">Food Sub Category</label>
                        <div class="autocomplete-box">
                        	<input type="text" class="form-control" id="food_subcategory" placeholder="Subcategory">
                        </div>
                        <small class="form-text text-muted">Restaurant can specify their own category as subcategory</small>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="">Food Title</label>
                        <input type="text" class="form-control" id="food_title" placeholder="Title of Food Item">
                    </div>
                    <div class="form-group col-md-8">
                        <label for="">Description</label>
                        <input type="text" class="form-control" id="food_desc" placeholder="A breif note about this food item">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="">Prepare Time</label>
                        <input type="number" class="form-control" id="food_prepare_time" placeholder="Avarage Prepare Time">
                        <small class="form-text text-muted">
                            How much time this food item takes to prepare? (In Minutes)
                        </small>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="">Type</label>
                        <input type="radio" value="veg" class="food_type" name="type" checked>Veg
                        <input type="radio" value="nonveg" class="food_type" name="type">Non-Veg
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4 custom-file">
                        <input type="file" class="custom-file-input" id="food_thumb">
                        <label class="custom-file-label">Thumbnail Image</label>
                        <small class="form-text text-muted">
                            An optional image can be attached with this food item.
                        </small>
                    </div>
                </div>
                <h4 class="my-5">Price Details</h4>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <select name="" id="price_basis" class="custom-select">
                            <option value="half_full">Half/Full</option>
                            <option value="pcs">Pcs.</option>
                            <option value="kg">Kg</option>
                        </select>
                        <small class="form-text text-muted">
                            Choose a price basis for this food item.
                        </small>
                    </div>
                    <div class="col-md-8">
                        <div class="price_type_checkboxes" id="half_full_checkbox" style="display:block">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="qtr"> Qtr
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="half"> Half
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="full"> Full
                            </label>
                        </div>
                        <div class="price_type_checkboxes" id="pcs_checkbox">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="pcs"> Pcs
                            </label>
                        </div>
                        <div class="price_type_checkboxes" id="kg_checkbox">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="half_kg"> 500gm
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="full_kg"> 1Kg
                            </label>
                        </div>
                    </div>
                </div>
                <div class="price_div" id="half_full_div" style="display:block">
                    <div class="price_type_row" id="price_type_qtr">
                        <h5>#Quater</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="qtr_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="qtr_restaurant_price">
                            </div>
                        </div>
                    </div>
                    <div class="price_type_row" id="price_type_half">
                        <h5>#Half</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="half_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="half_restaurant_price">
                            </div>
                        </div>
                    </div>
                    <div class="price_type_row" id="price_type_full">
                        <h5>#Full</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="full_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="full_restaurant_price">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="price_div" id="pcs_div">
                    <div class="price_type_row" id="price_type_pcs">
                        <h5>#Pcs</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">Pcs</label>
                                <input type="number" class="form-control" id="pcs_pcs">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="pcs_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="pcs_restaurant_price">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="price_div" id="kg_div">
                    <div class="price_type_row" id="price_type_half_kg">
                        <h5>#500gm</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="half_kg_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="half_kg_restaurant_price">
                            </div>
                        </div>
                    </div>
                    <div class="price_type_row" id="price_type_full_kg">
                        <h5>#1 Kg</h5>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="">FoodTym Price</label>
                                <input type="number" class="form-control" id="full_kg_foodtym_price">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="">Restaurant Price</label>
                                <input type="number" class="form-control" id="full_kg_restaurant_price">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-row m-2">
                    <div class="col-md-12">
                        <button class="btn btn-block btn-danger" id="addFoodItemBtn">Add To Menu</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
	</div>
	<c:import url="jspinclude/footer.jsp"></c:import>
</body>

</html>