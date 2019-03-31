<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="restaurant" items="${requestScope.restaurants}">
	<div class="container-fluid result-item shadow p-2 bg-light border" data-id="${restaurant.id}">
        <div class="row">
            <div class="col-lg-12">
                <p class="text-success"><span class="text-danger h5">${restaurant.name}</span> &middot; ${restaurant.owner.firstName} ${restaurant.owner.lastName }</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <strong>ID: </strong><span>${restaurant.id}</span>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <strong>Area: </strong> <span class="h6 text-primary">${restaurant.locality.ncrRegionName }</span> &middot;<span
                    class="text-dark h6">
                    ${restaurant.locality.localityName}</span>
            </div>
        </div>
    </div>

</c:forEach>