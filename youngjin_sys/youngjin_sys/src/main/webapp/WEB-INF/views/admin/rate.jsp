<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
<div class="rate_wrapper">
	<div>
		<form:form commandName="rate">
			<form:select path="writeYear" class="rate_year_change">
				<c:forEach var="year" items="${yearList }">
					<form:option value="${year }">${year }년</form:option>
				</c:forEach>
			</form:select>
		</form:form>
	</div>
	<div>
		<p>1. Destination service Charge(inbound) & Packing charge(outbound) GBL RATE schedule</p>
		<table class="rate_table">
			<thead>
				<tr>
					<th rowspan="4">TSP</th>
					<th colspan="${fn:length(codeList) }">inbound(destination service charge rate)</th>
					<th colspan="${fn:length(codeList) + 4 }">outbound(packing service charge rate)</th>
					<th colspan="3">outbound(container)</th>
				</tr>
				<tr>
					<c:forEach var="codeName" items="${codeList }">
						<th>${codeName.codeEtc }</th>
					</c:forEach>
					<c:forEach var="codeName" items="${codeList }">
						<c:choose>
							<c:when test="${codeName.subCode eq '01' or codeName.subCode eq '02' or codeName.subCode eq '03' or codeName.subCode eq '04' }">
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
							<c:when test="${codeValue.subCode eq '01' or codeValue.subCode eq '02' or codeValue.subCode eq '03' or codeValue.subCode eq '04' }">
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
					<c:forEach begin="0" end="${fn:length(codeList) + 3 }" varStatus="i">
						<c:choose>
							<c:when test="${i.count % 2 eq 1 and i.count lt 9}">
								<th>typeII</th>
							</c:when>
							<c:when test="${i.count % 2 eq 0 and i.count lt 9}">
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
							<c:set var="inboundMap" value="${basicMap['inbound'] }" />
							<td class="basic_rate_td" data-tsp="${tspRate.codeName }" data-code="${codeList[j.index].codeName }" data-process="inbound" data-writeYear="${(inboundMap[tspRate.codeName][codeList[j.index].codeName].writeYear eq null) ? rate.writeYear : inboundMap[tspRate.codeName][codeList[j.index].codeName].writeYear }"><input class="basic_rate_input" type="text" value="${inboundMap[tspRate.codeName][codeList[j.index].codeName].rate }" /></td>
						</c:forEach>
						<c:forEach begin="0" end="${fn:length(codeList) + 3 }" varStatus="k">
							<c:set var="outboundMap" value="${basicMap['outbound'] }" />
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
								<c:when test="${k.index gt 5 and k.index lt 8}">
									<c:set var="index" value="${k.count - k.index + 2 }" />									
								</c:when>
								<c:otherwise>
									<c:set var="index" value="${k.count - 5}" />
								</c:otherwise>								
							</c:choose>
							<c:choose>
								<c:when test="${k.count % 2 eq 1 and k.count lt 9 }">
									<c:set var="codeVal" value="${codeList[index].codeName }typeII" />
									<td class="basic_rate_td" data-tsp="${tspRate.codeName }" data-code="${codeList[index].codeName }" data-process="outbound" data-obType="typeII" data-writeYear="${(outboundMap[tspRate.codeName][codeVal].writeYear eq null or outboundMap[tspRate.codeName][codeVal].writeYear eq '') ? rate.writeYear : outboundMap[tspRate.codeName][codeVal].writeYear }"><input class="basic_rate_input" type="text" value="${outboundMap[tspRate.codeName][codeVal].rate}" /></td>
								</c:when>
								<c:when test="${k.count % 2 eq 0 and k.count lt 9 }">
									<c:set var="codeVal" value="${codeList[index].codeName }O/F" />
									<td class="basic_rate_td" data-tsp="${tspRate.codeName }" data-code="${codeList[index].codeName }" data-process="outbound" data-obType="O/F" data-writeYear="${(outboundMap[tspRate.codeName][codeVal].writeYear eq null or outboundMap[tspRate.codeName][codeVal].writeYear eq '') ? rate.writeYear : outboundMap[tspRate.codeName][codeVal].writeYear }"><input class="basic_rate_input" type="text" value="${outboundMap[tspRate.codeName][codeVal].rate}" /></td>
								</c:when>
								<c:otherwise>
									<c:set var="codeVal" value="${codeList[index].codeName}" />									
									<td class="basic_rate_td" data-tsp="${tspRate.codeName }" data-code="${codeList[index].codeName }" data-process="outbound" data-writeYear="${(outboundMap[tspRate.codeName][codeVal].writeYear eq null or outboundMap[tspRate.codeName][codeVal].writeYear eq '') ? rate.writeYear : outboundMap[tspRate.codeName][codeVal].writeYear }"><input class="basic_rate_input" type="text" value="${outboundMap[tspRate.codeName][codeVal].rate}" /></td>	
								</c:otherwise>
							</c:choose>						
						</c:forEach>
						<td class="container_rate_td" data-tsp="${tspRate.codeName }" data-status="new" data-writeYear="${(containerMap[tspRate.codeName]['new'].writeYear eq null) ? rate.writeYear : containerMap[tspRate.codeName]['new'].writeYear }"><input class="container_rate_input" type="text" value="${containerMap[tspRate.codeName]['new'].containerRate }"/></td>
						<td class="container_rate_td" data-tsp="${tspRate.codeName }" data-status="used" data-writeYear="${(containerMap[tspRate.codeName]['new'].writeYear eq null) ? rate.writeYear : containerMap[tspRate.codeName]['new'].writeYear }"><input class="container_rate_input" type="text" value="${containerMap[tspRate.codeName]['used'].containerRate }"/></td>
						<td class="container_rate_td" data-tsp="${tspRate.codeName }" data-status="repair" data-writeYear="${(containerMap[tspRate.codeName]['new'].writeYear eq null) ? rate.writeYear : containerMap[tspRate.codeName]['new'].writeYear }"><input class="container_rate_input" type="text" value="${containerMap[tspRate.codeName]['repair'].containerRate }"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<p>2. company charge Schedule 1</p>
		<table class="rate_table">
			<tbody>
				<tr>
					<td>company rate1</td>
					<td>comprate1</td>
					<td><input class="comprate_input" data-title="comprate1" data-writeYear="${(etcMap['comprate1'].writeYear eq null) ? rate.writeYear : etcMap['comprate1'].writeYear }" type="text" value="${etcMap['comprate1'].rate }" /></td>
				</tr>
				<tr>
					<td>company rate2</td>
					<td>comprate2</td>
					<td><input class="comprate_input" data-title="comprate2" data-writeYear="${(etcMap['comprate1'].writeYear eq null) ? rate.writeYear : etcMap['comprate1'].writeYear }" type="text" value="${etcMap['comprate2'].rate }" /></td>
				</tr>
				<tr>
					<td>company rate3</td>
					<td>comprate3</td>
					<td><input class="comprate_input" data-title="comprate3" data-writeYear="${(etcMap['comprate1'].writeYear eq null) ? rate.writeYear : etcMap['comprate1'].writeYear }" type="text" value="${etcMap['comprate3'].rate }" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div>
		<p>3. SIT Charge Rate schedule</p>
		<table class="sit_rate_table">
			<thead>
				<tr>
					<th colspan="2">INBOUND</th>
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
						<td data-seq="${ubList.seq }"><input class="sit_rate_input" type="text" value="${ubList.rate }"/></td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${inboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td data-seq="${hhgList.seq }"><input class="sit_rate_input" type="text" value="${hhgList.rate }" /></td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
		
		<table class="sit_rate_table">
			<thead>
				<tr>
					<th colspan="2">OUTBOUND</th>
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
						<td data-seq="${ubList.seq }"><input class="sit_rate_input" type="text" value="${ubList.rate }"/></td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${outboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td data-seq="${hhgList.seq }"><input class="sit_rate_input" type="text" value="${hhgList.rate }"/></td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
	</div>
	
	<div>
		<p>4. Other charge Schedule 1</p>
		
		<table class="rate_table">
			<thead>
				<tr>
					<th colspan="2">INBOUND</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>UB</td>
				</tr>
				<c:set var="inboundMap" value="${otherMap['INBOUND'] }"/>
				<c:forEach var="ubList" items="${inboundMap['UB'] }">
					<tr>
						<td>${ubList.title }</td>
						<td data-seq="${ubList.seq }"><input class="other_rate_input" type="text" value="${ubList.rate }"/></td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${inboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td data-seq="${hhgList.seq }"><input class="other_rate_input" type="text" value="${hhgList.rate }"/></td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
		
		<table class="rate_table">
			<thead>
				<tr>
					<th colspan="2">OUTBOUND</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>UB</td>
				</tr>
				<c:set var="outboundMap" value="${otherMap['OUTBOUND'] }"/>
				<c:forEach var="ubList" items="${outboundMap['UB'] }">
					<tr>
						<td>${ubList.title }</td>
						<td data-seq="${ubList.seq }"><input class="other_rate_input" type="text" value="${ubList.rate }"/></td>
					</tr>
				</c:forEach>				
				<tr>
					<td></td>
					<td>HHG</td>
				</tr>
				<c:forEach var="hhgList" items="${outboundMap['HHG'] }">
					<tr>
						<td>${hhgList.title }</td>
						<td data-seq="${hhgList.seq }"><input class="other_rate_input" type="text" value="${hhgList.rate }"/></td>
					</tr>
				</c:forEach>			
			</tbody>
		</table>
	</div>
</div>	
<%@ include file="../../layout/foot.jspf"%>