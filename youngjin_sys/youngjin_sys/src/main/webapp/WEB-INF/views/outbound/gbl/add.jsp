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
<title>Form 모음</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/font.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">

<script src="${cp}/resources/jquery/jquery-1.8.2.min.js"></script>
<script src="${cp}/resources/js/common.js"></script>

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
</script>

</head>
<body>
	<div id="gbl_add_div">
		<div style="background-color: white; width: 100%; height: 100%;">
			<div class="title">
				<h1>GBL INPUT</h1>
			</div>
			<form:form commandName="gbl">
			<table>
				<tr>
					<th>GBL_NO</th>
					<td><form:input path="no" /> </td>
				</tr>
				<tr>
					<th>NAME</th>
					<td><form:input path="customerName" /> </td>
				</tr>
				<tr>
					<th>RANK</th>
					<td><form:input path="rank" /> </td>
				</tr>
				<tr>
					<th>GBL_CODE</th>
					<td><form:input path="code" /> </td>
				</tr>
				<tr>
					<th>TSP</th>
					<td><form:input path="tsp" /> </td>
				</tr>
				<tr>
					<th>ADDRESS</th>
					<td><form:input path="originAddress" /> </td>
				</tr>
				<tr>
					<th>SSN</th>
					<td><form:input path="ssn" /> </td>
				</tr>
				<tr>
					<th>AREA</th>
					<td><form:input path="area" /> </td>
				</tr>
				<tr>
					<th>RDD</th>
					<td><form:input path="rdd" /> </td>
				</tr>
				<tr>
					<th>US_NO</th>
					<td><form:input path="usNo" /> </td>
				</tr>
				<tr>
					<th>GBLOCK</th>
					<td><form:input path="gBlock" /> </td>
				</tr>
				<tr>
					<th>PUD</th>
					<td><form:input path="pud" /> </td>
				</tr>
				<tfoot>
					<tr>
						<td><input type="button" value="add"/></td>
					</tr>
				</tfoot>
			</table>
			</form:form>
		</div>
	</div>	
</body>
</html>