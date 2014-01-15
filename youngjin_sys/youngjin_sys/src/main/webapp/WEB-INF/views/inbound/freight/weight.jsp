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
		<form:form commandName="weightIb">
			<table>
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
					<tr>
						<td><form:input path="piece" /></td>
						<td><form:input path="type" /></td>
						<td><form:input path="gross" /></td>
						<td><form:input path="grossKg" /></td>
						<td><form:input path="tare" /></td>
						<td><form:input path="net" /></td>
						<td><form:input path="cuft" /></td>
						<td><form:input path="reweight" /></td>
						<td class="weight_remark"><form:input path="remark" /></td>
					</tr>
				</tbody>					
			</table>
		</form:form>
	</div>
</body>
</html>