<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/admin.css">
<script src="${pageContext.request.contextPath}/static/jquery.min.js""></script>
<script src="${pageContext.request.contextPath}/static/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/ViewRestaurants.js"></script>
    <title>Document</title>
</head>

<body>
    <c:import url="jspinclude/adminheader.jsp"></c:import>
    <!-- search box-->
    <div class="container-fluid p-1">
        <h3 class="m-3">View Restaurants</h3>
        <!--search type-->
        <div class="row m-4" id="search-type-box">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="by-locality" name="search-type" class="custom-control-input"
                    checked="checked">
                <label class="custom-control-label" for="by-locality">By Locality</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="by-id" name="search-type" class="custom-control-input">
                <label class="custom-control-label" for="by-id">By ID</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="by-name" name="search-type" class="custom-control-input">
                <label class="custom-control-label" for="by-name">By Name</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="by-recently-added" name="search-type" class="custom-control-input">
                <label class="custom-control-label" for="by-recently-added">Recently Added</label>
            </div>
            
        </div>
        <!-- search by locality-->
        <div class="row m-4 search-type" id="search-by-locality">
            <div class="col-md-4">
                <div class="form-group">
                    <div class="autocomplete-box">
                        <input type="text" class="form-control" placeholder="NCR Region" id="ncrRegions-input">
                    </div>
                    
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <div class="autocomplete-box">
                        <input type="text" class="form-control" placeholder="Locality" id="locality-input">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <button class="btn btn-success btn-block">Search Restaurant</button>
            </div>
        </div>
        <!-- search by ID-->
        <div class="row m-4 search-type" style="display:none" id="search-by-id">
            <div class="col-md-8">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Restaurant ID">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <button class="btn btn-success btn-block">Search Restaurant</button>
                </div>
            </div>
        </div>
        <div class="row m-4 search-type" id="search-by-name" style="display:none">
            <div class="col-md-8">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Restaurant Name">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <button class="btn btn-success btn-block">Search Restaurant</button>
                </div>
            </div>
        </div>
    </div>


    <div class="container-fluid">
        <div class="row my-3">
            <div class="col-lg-5 clearfix food-item border m-3 p-3">
                <div class="row">
                    <div class="col-sm-6">
                        <h5>Restaurant Name</h5>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="text-muted">Locality | NCR Region | Restaurant Owner</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 offset-6">
                        <div class="btn-group text-light">
                            <a class="btn btn-primary btn-sm">View</a>
                            <a class="btn btn-success btn-sm">Edit</a>
                            <a class="btn btn-danger btn-sm">Remove</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5 food-item border border rounded m-3 p-3">
                <div class="row">
                    <div class="col-sm-6">
                        <h5>Restaurant Name</h5>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="text-muted">Locality | NCR Region | Restaurant Owner</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 offset-6">
                        <div class="btn-group text-light">
                            <a class="btn btn-primary btn-sm">View</a>
                            <a class="btn btn-success btn-sm">Edit</a>
                            <a class="btn btn-danger btn-sm">Remove</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>

    <c:import url="jspinclude/footer.jsp"></c:import>
</body>

</html>