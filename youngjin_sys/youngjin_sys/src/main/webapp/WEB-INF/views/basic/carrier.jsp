<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="basicWrapper">
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>	
					<span class="basic_carrier_add yj_button" >add</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="yj_table carrier_table">
				<thead>
					<tr>
						<th>SCAC(TSP)</th>
						<th>FULL NAME</th>
						<th>ADDRESS</th>
						<th>TEL</th>
						<th>FAX</th>
						<th>PRESIDENT</th>
						<th>MANAGER</th>
						<th>E-MAIL(MAININ)</th>
						<th>E-MAIL(TRAFFIC MANAGER)</th>
						<th>AGENT</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${carrierList eq '[]' or carrierList eq null or carrierList eq '' }">
						<tr>
							<td colspan="10">SCAC 가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="carrier" items="${carrierList }">
						<tr data-seq="${carrier.seq }">
							<td>${carrier.scac }</td>
							<td>${carrier.scacFullName }</td>
							<td>${carrier.address }</td>
							<td>${carrier.telNo}</td>
							<td>${carrier.faxNo }</td>
							<td>${carrier.president }</td>
							<td>${carrier.manager }</td>
							<td>${carrier.mainEmail }</td>
							<td>${carrier.trafficEmail }</td>
							<td>${carrier.agent }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>	
<%@ include file="../../layout/foot.jspf"%>