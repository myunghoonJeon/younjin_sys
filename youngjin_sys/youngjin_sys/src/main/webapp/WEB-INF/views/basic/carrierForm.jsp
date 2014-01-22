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

<%@ include file="../../layout/include_script.jspf" %>

<c:if test="${check eq 'yes' }">
	<script type="text/javascript">
	
	parent.location.reload();
	
	parent.$.smartPop.close();
	</script>
</c:if>

</head>
<body>
	<div class="carrier_add_div">
		<div class="pop_title_line">
			<span>CARRIER ${(flag eq 'add') ? 'ADD' : 'MODIFY' }</span>
		</div>	
		<form:form commandName="carrier">
		<form:hidden path="seq"/>
		<table class="gbl_add_table">
			<tbody>
				<tr>
					<th>SCAC</th>
					<td><form:input path="scac"/></td>
				</tr>
				<tr>
					<th>SCAC FULL NAME</th>
					<td><form:input path="scacFullName"/></td>
				</tr>
				<tr>
					<th>ADDRESS</th>
					<td><form:input path="address"/></td>
				</tr>
				<tr>
					<th>TEL NO</th>
					<td><form:input path="telNo"/></td>
				</tr>
				<tr>
					<th>FAX NO</th>
					<td><form:input path="faxNo"/></td>
				</tr>
				<tr>
					<th>PRESIDENT</th>
					<td><form:input path="president"/></td>
				</tr>
				<tr>
					<th>MANAGER</th>
					<td><form:input path="manager"/></td>
				</tr>
				<tr>
					<th>E-MAIL(MAININ)</th>
					<td><form:input path="mainEmail"/></td>
				</tr>
				<tr>
					<th>E-MAIL(TRAFFIC MANAGER)</th>
					<td><form:input path="trafficEmail"/></td>
				</tr>
				<tr>
					<th>AGENT</th>
					<td><form:input path="agent"/></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div><span class="yj_button ${(flag eq 'add') ? 'basic_carrier_add_submit' : 'basic_carrier_modify_submit' }">${(flag eq 'add') ? 'add' : 'modify' }</span></div>
					</td>
				</tr>
			</tfoot>		
		</table>
		</form:form>
	</div>
</body>
</html>