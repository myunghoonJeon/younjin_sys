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
	<div>	
		<div class="truck_weight_addButton user_addButton">
			<span >add</span>
		</div>
		<table>
			<thead>
				<tr>
					<th>no</th>
					<th>piece</th>
					<th>type</th>
					<th>status</th>
					<th>gross</th>
					<th>tare</th>
					<th>net</th>
					<th>cuft</th>
					<th>remark</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="weightcertificate" items="${weightList }" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>${weightcertificate.piece }</td>
						<td>${weightcertificate.type }</td>
						<td>${weightcertificate.status }</td>
						<td>${weightcertificate.gross }</td>
						<td>${weightcertificate.tare }</td>
						<td>${weightcertificate.net }</td>
						<td>${weightcertificate.cuft }</td>
						<td>${weightcertificate.remark}</td>
						<td><input class="truck_weight_seq" value="${weightcertificate.seq }" type="checkbox" ${(weightcertificate.truckCheck eq true) ? 'checked=checked' : '' } /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>