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
			<span>FREIGHT ADD</span>
		</div>	
		<form:form commandName="gbl">
		<table class="gbl_add_table">
			<tr>
				<th>GBL NO</th>
				<td><form:input path="gblNo" />
				<br><form:errors path="gblNo" /></td>
				<th>SHIPPER NAME</th>
				<td><form:input path="shipperName"/></td>
			</tr>
			<tr>
				<th>RANK</th>
				<td><form:input path="rank" /> </td>
				<th>GBL CODE</th>
				<td><form:input path="code" /> </td>
			</tr>
			<tr>
				<th>TSP</th>
				<td><form:input path="tsp" /> </td>
				<th>DEST ADDRESS</th>
				<td><form:input path="destAddress" /> </td>
			</tr>
			<tr>
				<th>PUD</th>
				<td><form:input path="pud" /> </td>
				<th>RDD</th>
				<td><form:input path="rdd" /> </td>
			</tr>
			<tr>
			</tr>
			<tr>
				<th>ARRIVE DATE</th>
				<td><form:input path="arriveDate" /> </td>
				<th>AWB NO</th>
				<td><form:input path="awbNo" /> </td>
			</tr>
			<tr>
				<th>SSN</th>
				<td><form:input path="ssn" /> </td>
				<th>PMJ DATE</th>
				<td><form:input path="pmjDate" /> </td>
			</tr>
			<tr>
				<th>AREA</th>
				<td><form:input path="areaLocal" /> </td>
				<th>FRIGHT</th>
				<td><form:input path="fright" /> </td>
			</tr>
			<tr>
				<th>E-MAIL ADDRESS</th>
				<td><form:input path="eMailAddress" /> </td>
				<th>OBL NO</th>
				<td><form:input path="oblNo" /> </td>
			</tr>
			<tr>
				<th>VESSEL</th>
				<td><form:input path="vessle" /> </td>
				<th>BL 분할 업체</th>
				<td><form:input path="blCompany" /> </td>
			</tr>
			<tr>
				<th>REMARK</th>
				<td><form:input path="remark" /> </td>
				<th>ETA</th>
				<td><form:input path="eta" /> </td>
			</tr>
			<tr>
				<th>GBL RATE</th>
				<td><form:input path="rate" /> </td>
				<th>SIT IN</th>
				<td><form:input path="sitIn" /> </td>
			</tr>
			<tr>
				<th>SIT OUT</th>
				<td><form:input path="sitOut" /> </td>
				<th>SIT NO</th>
				<td><form:input path="sitNo" /> </td>
			</tr>
			<tr>
				<th>YJ NO</th>
				<td><form:input path="yjNo" /> </td>
				<th>TOTAL PCS</th>
				<td><form:input path="totalPcs" /> </td>
			</tr>
			<tr>
				<th>PHONE</th>
				<td><form:input path="phone" /> </td>
				<th>ADDRESS</th>
				<td><form:input path="address" /> </td>
			</tr>
			<tr>
				<th>ON HAND DATE</th>
				<td><form:input path="onHandDate" /> </td>
				<th>GBLOC</th>
				<td><form:input path="gbloc" /> </td>
			</tr>
			<tr>
				<th>DESTINATION GBLOC</th>
				<td><form:input path="destinationGbloc" /> </td>
				<th>ITEMS PIECES</th>
				<td><form:input path="itemsPieces" /> </td>
			</tr>
			<tr>
				<th>GROSS WEIGHT</th>
				<td><form:input path="grossWeight" /> </td>
				<th>NET WEIGHT</th>
				<td><form:input path="netWeight" /> </td>
			</tr>
			<tr>
				<th>CUFT</th>
				<td><form:input path="cuft" /> </td>
				<th>STORED AT</th>
				<td><form:input path="storedAt" /> </td>
			</tr>
			<tfoot>
				<tr>
					<td colspan="4"><input class="freight_add_submit_button" type="button" value="add"/></td>
				</tr>
			</tfoot>
		</table>
		</form:form>
	</div>
</body>
</html>