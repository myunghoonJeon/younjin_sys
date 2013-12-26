<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
<div class="rate_wrapper">
	<div>
		<p>1. Destination service Charge(inbound) & Packing charge(outbound) GBL RATE schedule</p>
		<table class="rate_table">
			<thead>
				<tr>
					<th rowspan="4">TSP</th>
					<th colspan="${fn:length(codeList) }">inbound(destination service charge rate)</th>
					<th colspan="${fn:length(codeList) + 3 }">outbound(packing service charge rate)</th>
					<th colspan="3">outbound(container)</th>
				</tr>
				<tr>
					<c:forEach var="codeName" items="${codeList }">
						<th>${codeName.codeEtc }</th>
					</c:forEach>
					<c:forEach var="codeName" items="${codeList }">
						<c:choose>
							<c:when test="${codeName.subCode eq '01' or codeName.subCode eq '02' or codeName.subCode eq '03' }">
								<th colspan="2">${codeName.codeEtc }</th>
							</c:when>
							<c:otherwise>
								<th>${codeName.codeEtc }</th>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<th colspan="3">container</th>
				</tr>
				<tr>
					<c:forEach var="codeValue" items="${codeList }">
						<th>${codeValue.codeName }</th>
					</c:forEach>					
					<c:forEach var="codeValue" items="${codeList }">
						<c:choose>
							<c:when test="${codeValue.subCode eq '01' or codeValue.subCode eq '02' or codeValue.subCode eq '03' }">
								<th colspan="2">${codeValue.codeName }</th>
							</c:when>
							<c:otherwise>
								<th>${codeValue.codeName }</th>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:forEach begin="1" end="3">
						<th></th>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="blankValue" items="${codeList }">
						<th></th>
					</c:forEach>
					<c:forEach begin="0" end="${fn:length(codeList) + 2 }" varStatus="i">
						<c:choose>
							<c:when test="${i.count % 2 eq 1 and i.count lt 7}">
								<th>typeII</th>
							</c:when>
							<c:when test="${i.count % 2 eq 0 and i.count lt 7}">
								<th>O/F</th>
							</c:when>
							<c:otherwise>
								<th></th>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<th>new</th>
					<th>used</th>
					<th>repair</th>			
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tspRate" items="${tspList }" varStatus="i">
					<tr>
						<td>${tspRate.codeName }</td>
						<c:forEach items="${codeList }" varStatus="j">
							<td data-tsp="${tspRate.codeName }" data-code="${codeList[j.index].codeName }" data-process="inbound"></td>
						</c:forEach>
						<c:forEach begin="0" end="${fn:length(codeList) + 2 }" varStatus="k">
							<c:choose>
								<c:when test="${k.index eq 1 or k.index eq 0 }">
									<c:set var="index" value="0" />
								</c:when>
								<c:when test="${k.index gt 1 and k.index lt 4}">
									<c:set var="index" value="${k.count - k.index}" />
								</c:when>
								<c:when test="${k.index gt 3 and k.index lt 6}">
									<c:set var="index" value="${k.count - k.index + 1}" />									
								</c:when>
								<c:otherwise>
									<c:set var="index" value="${k.count - 3}" />
								</c:otherwise>								
							</c:choose>
								<td data-tsp="${tspRate.codeName }" data-code="${codeList[index].codeName }" data-process="outbound"></td>
						</c:forEach>
						<td data-tsp="${tspRate.codeName }" data-status="new"></td>
						<td data-tsp="${tspRate.codeName }" data-status="used"></td>
						<td data-tsp="${tspRate.codeName }" data-status="repair"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<p>2. company charge Schedule 1</p>
		<table>
			<tr>
				<td>company rate1</td>
				<td>comprate1</td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<div>
		<p>3. SIT Charge Rate schedule</p>
		<table class="rate_table">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>INBOUND</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>UB</td>
				</tr>
				<c:set var="inboundMap" value="${sitMap['INBOUND'] }"/>
				<c:forEach var="ubList" items="${inboundMap['UB'] }">
					<tr>
						<td>${ubList.title }</td>
						<td>${ubList.rate }</td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${inboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td>${hhgList.rate }</td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
		
		<table class="rate_table">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>OUTBOUND</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>UB</td>
				</tr>
				<c:set var="outboundMap" value="${sitMap['OUTBOUND'] }"/>
				<c:forEach var="ubList" items="${outboundMap['UB'] }">
					<tr>
						<td>${ubList.title }</td>
						<td>${ubList.rate }</td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${outboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td>${hhgList.rate }</td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
	</div>
</div>	
<%@ include file="../../layout/foot.jspf"%>