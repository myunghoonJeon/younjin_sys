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
<title>Add</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/font.css">
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
	<div id="gbl_add_div">
		<div class="pop_title_line">
			<span>GBL ADD</span>
		</div>	
		<form:form commandName="gbl">
		<table class="gbl_add_table">
			<tr>
				<th>GBL NO</th>
				<td><form:input path="no" />
				<br><form:errors path="no" /></td>
				<th>NAME</th>
				<td><form:input path="customerName"/></td>
			</tr>
			<tr>
				<th>RANK</th>
				<td><form:input path="rank" /> </td>
				<th>GBL CODE</th>
				<td><form:input path="code" /> </td>
			</tr>
			<tr>
				<th>SCAC</th>
				<td><form:input path="scac" /> </td>
				<th>ORIGIN GBLOCK</th>
				<td><form:input path="originGBlock" /> </td>
			</tr>
			<tr>
				<th>DEST GBLOCK</th>
				<td><form:input path="destGBlock" /> </td>
				<th>PUD</th>
				<td><form:input path="pud" /> </td>
			</tr>
			<tr>
				<th>SSN</th>
				<td><form:input path="ssn" /> </td>
				<th>RDD</th>
				<td><form:input path="rdd" /> </td>
			</tr>
			<tr>
				<th>POD</th>
				<td><form:input path="pod" /> </td>
				<th>POE</th>
				<td><form:input path="poe" /> </td>
			</tr>
			<tr>
				<th>AREA</th>
				<td><form:input path="areaLocal" /> </td>
				<th>ADDRESS</th>
				<td><form:input path="originAddress" /> </td>
			</tr>
			<tr>
				<th>US NO</th>
				<td><form:input path="usNo" /> </td>
				<th>DEST PORT</th>
				<td><form:input path="destPort" /> </td>
			</tr>
			<tr>
				<th>ORIGIN PORT</th>
				<td><form:input path="originPort" /> </td>
				<th>ORIGIN CITY </th>
				<td><form:input path="originCity" /> </td>
			</tr>
			<tr>
				<th>MIL SVC</th>
				<td><form:input path="milSVC" /></td>
				<th colspan="2" style="background: none;"></th>
			</tr>
			<tfoot>
				<tr>
					<td colspan="4"><input class="gbl_add_submit_button" type="button" value="add"/></td>
				</tr>
			</tfoot>
		</table>
		</form:form>
	</div>
</body>
</html>