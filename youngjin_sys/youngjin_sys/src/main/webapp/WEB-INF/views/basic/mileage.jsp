<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="title">
		<h1>MILEAGE</h1>
	</div>
	
	<div class="mileage_addButton_wrap">
		<span class="mileage_addButton yj_button" >add</span>
	</div>	
	
	<div>
		<table class="mileage_table yj_table">
			<colgroup>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">DD619-1 13-a<br/>(GBL BLOCK 18)</th>
					<th rowspan="2">배달지역</th>
					<th rowspan="2">Miles</th>
					<th colspan="4">LBS</th>
				</tr>
				<tr>
					<th>WEIGHT</th>
					<th>RATE</th>
					<th>WEIGHT</th>
					<th>RATE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mileage" items="${mileageList }">
					<tr data-seq="${mileage.seq }">
						<td class="mileage_storedAt"><input type="text" name="storedAt" placeholder="storedAt" value="${mileage.storedAt }" /></td>
						<td class="mileage_destination"><input type="text" name="destination" placeholder="destination" value="${mileage.destination }"/></td>
						<td class="mileage_miles"><input type="text" name="miles" placeholder="miles" value="${mileage.miles }"/></td>
						<td class="mileage_minWeight"><input type="text" name="minWeight" placeholder="minWeight" value="${mileage.minWeight }"/></td>
						<td class="mileage_minRate"><input type="text" name="minRate" placeholder="minRate" value="${mileage.minRate }"/></td>
						<td class="mileage_maxWeight"><input type="text" name="maxWeight" placeholder="maxWeight" value="${mileage.maxWeight }"/></td>
						<td class="mileage_maxRate"><input type="text" name="maxRate" placeholder="maxRate" value="${mileage.maxRate }"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../layout/foot.jspf"%>