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
<title>SELECT SEPERATE</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">
<link rel="stylesheet" href="${cp }/resources/jquery/jquery-ui-1.10.3.custom.min.css">

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
	var addError = false;
	if (typeof youngjin == 'undefined') {
		youngjin = {};
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>

</head>
<body>
	<div class="truck_seperate_setting_wrap">		
		<div class="truck_seperate_addButton_wrap">
			<ul class="truck_seperate_button_list">
				<li>
					<span class="yj_button truck_seperate_back">back</span>
				</li>
				<li>
					<span class="yj_button truck_seperate_addButton" data-seq="${seq }">seperate</span>
				</li>
			</ul>
		</div>
			<table class="truck_seperate_table">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">PIECE</th>
					<th rowspan="2">TYPE</th>
					<th rowspan="2">STATUS</th>
					<th colspan="2">GROSS</th>
					<th rowspan="2">TARE</th>
					<th rowspan="2">NET</th>
					<th rowspan="2">CUFT</th>
					<th rowspan="2">REMARK</th>
					<th rowspan="2"></th>
				</tr>
				<tr>
					<th>KG</th>
					<th>LBS</th>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="weightcertificate" items="${weightCertificateList }" varStatus="i">
					<tr>	
						<td class="piece_td">${weightcertificate.piece }</td>
						<td class="type_td">${weightcertificate.type }</td>
						<td class="status_td">${weightcertificate.status }</td>
						<td class="gross_td">${weightcertificate.grossKg }</td>
						<td class="gross_td">${weightcertificate.gross }</td>
						<td class="tare_td">${weightcertificate.tare }</td>
						<td class="net_td">${weightcertificate.net }</td>
						<td class="cuft_td">${weightcertificate.cuft }</td>
						<td class="remark_td">${weightcertificate.remark }</td>
						<td><input type="checkbox" name="checkSeperateWeight" value="${weightcertificate.seq }" /></td>
					</tr>
				</c:forEach>				
			</tbody>
		</table>
	</div>
</body>
</html>