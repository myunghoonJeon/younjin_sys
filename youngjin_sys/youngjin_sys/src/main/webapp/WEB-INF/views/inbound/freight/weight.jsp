<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>
<title>Weight Certificate</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">
<link rel="stylesheet" href="${cp }/resources/jquery/jquery-ui-1.10.3.custom.min.css">

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
	var containerList = new Array();
	
	'<c:forEach var="container" items="${containerList}"  varStatus = "i">';
		var container = {
			'status' : '<c:out value="${container.status}"/>',
			'count' : '<c:out value="${container.count}" />',
			'remark' : '<c:out value="${container.remark}" />'
		};
		
		containerList.push(container);
	'</c:forEach>';	
	
	var addError = false;
	if (typeof youngjin == 'undefined') {
		youngjin = {};
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="pop_title_line">
		<span>WEIGHT ADD</span>
	</div>
	<div id="weight_add_wrap">
		<div class="weight_add_button_wrap">
			<ul class="weight_add_button_list">
				<li>
					<span class="yj_button weight_addButton">add</span>
				</li>
			</ul>
		</div>
		<form:form commandName="weightIb">
			<table class="weight_table" data-count="${ fn:length(weightList)}">
				<colgroup>
					<col width="10%">
					<col width="10%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="9%">
					<col width="26%">
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">PIECE</th>
						<th rowspan="2">TYPE</th>
						<th colspan="2">GROSS</th>
						<th rowspan="2">TARE</th>
						<th rowspan="2">NET</th>
						<th rowspan="2">CUFT</th>
						<th rowspan="2">REWEIGHT</th>
						<th rowspan="2">REMARK</th>
					</tr>
					<tr>
						<th>LBS</th>
						<th>KG</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${weightList eq '[]' }">
						<tr>
							<form:hidden path="gblSeq" value="${seq}"/>		
							<td class="weight_piece_td"><form:input path="piece" /></td>
							<td><form:input path="type" /></td>
							<td class="weight_gross_td"><form:input path="gross" /></td>
							<td><form:input path="grossKg"/></td>
							<td class="weight_tare_td"><form:input path="tare" /></td>
							<td><form:input path="net" readonly="readonly"/></td>
							<td class="weight_cuft_td"><form:input path="cuft" /></td>
							<td><form:input path="reweight" /></td>
							<td class="weight_remark"><form:input path="remark" /></td>
							<td class="weight_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_plus_Box"></div></td>
						</tr>
					</c:if>
					
					<c:set var="totalPcs" value="0" />
					<c:set var="totalGross" value="0" />
					<c:set var="totalGrossKg" value="0" />
					<c:set var="totalTare" value="0" />
					<c:set var="totalNet" value="0" />
					<c:set var="totalCuft" value="0" />
					
					<c:forEach var="weight" items="${weightList }" varStatus="i">
						<c:set var="totalPcs" value="${i.count }" />
						<c:set var="totalGross" value="${totalGross + weight.gross}" />
						<c:set var="totalGrossKg" value="${totalGrossKg + weight.grossKg}" />
						<c:set var="totalTare" value="${totalTare + weight.tare }" />
						<c:set var="totalNet" value="${totalNet + weight.net }" />
						<c:set var="totalCuft" value="${totalCuft + weight.cuft }" />
						<tr>
							<td class="weight_piece_td"><input type="text" name="piece" id="piece" value="${weight.piece }" readonly="readonly"/></td>
							<td><input type="text" name="type" id="type" value="${weight.type }" /></td>
							<td class="weight_gross_td"><input type="text" name="gross" id="gross" value="${weight.gross }" /></td>
							<td><input type="text" name="grossKg" id="grossKg" value="${weight.grossKg } "/></td>
							<td class="weight_tare_td"><input type="text" name="tare" id="tare" value="${weight.tare }" /></td>
							<td><input type="text" name="net" id="net" value="${weight.net }" /></td>
							<td class="weight_cuft_td"><input type="text" name="cuft" id="cuft" value="${weight.cuft }" /></td>
							<td><input type="text" name="reweight" id="reweight" value="${weight.reweight }" /></td>
							<td><input type="text" name="remark" id="remark" value="${weight.remark }" /></td>
							<c:if test="${fn:length(weightList) ==  i.count}">
								<td class="gbl_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_plus_Box"></div></td>
							</c:if>
						
						</tr>					
					</c:forEach>
				</tbody>			
				<tfoot>
					<tr>
						<td>Total</td>
						<td class="total_piece_td">${ fn:length(weightList) + 1 }</td>
						<td class="total_gross_td">${totalGross }</td>
						<td class="total_grossKg_td">${totalGrossKg }</td>
						<td class="total_tare_td">${totalTare }</td>
						<td class="total_net_td">${totalNet }</td>
						<td class="total_cuft_td">${totalCuft }</td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>		
			</table>
		</form:form>
	</div>
</body>
</html>