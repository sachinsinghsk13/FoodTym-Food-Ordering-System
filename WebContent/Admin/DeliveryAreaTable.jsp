<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table" id="areas-table">
	<thead style="font-weight: bold;">
		<tr>
			<td>S. No.</td>
			<td>Delhi-NCR Region</td>
			<td>Locality</td>
			<td>Action</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="area" items="${requestScope.areas.deliveryAreas}" varStatus="areaCount">
		<tr>
			<td>${areaCount.count}</td>
			<td>${area.locality.ncrRegionName}</td>
			<td>${area.locality.localityName}</td>
			<td>
				<button class="btn btn-sm btn-danger" data-delivery-area-id="${area.id}">Remove</button>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>