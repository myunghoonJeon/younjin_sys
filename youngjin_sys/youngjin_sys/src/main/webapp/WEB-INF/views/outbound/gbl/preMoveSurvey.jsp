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
<title>PreMoveSurvey</title>

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
	<div class="pre_move_survey_wrap">
		<div class="title">
			<h1>PRE MOVE SURVEY</h1>
		</div>
		<form:form commandName="preMoveSurvey">
			<form:hidden path="seq" value="${seq }" />
			<table class="pre_move_survey_table">
				<tr>
					<th>Estimate Weight</th>
					<td><form:input path="estimateWeight" /></td>
				</tr>
				<tr>
					<th>Special Item</th>
					<td><form:input path="specialItem" /></td>
				</tr>
				<tr>
					<th>E/S Container</th>
					<td><form:input path="esContainer" /></td>
				</tr>
				<tr>
					<th>3rd Party</th>
					<td><form:input path="thirdParty" /></td>
				</tr>
				<tr>
					<th>FireArms</th>
					<td><form:input path="fireArms" /></td>
				</tr>
				<tr>
					<th>Remark</th>
					<td><form:input path="remark" /></td>
				</tr>
				<tfoot>
					<tr>
						<td colspan="2"><input class="${preMoveSurvey ne null ? 'preMoveSurveyEditButton' : 'preMoveSurveyAddButton'}" type="button" value="${preMoveSurvey ne null ? 'edit' : 'add' }"/></td>
					</tr> 
				</tfoot>
			</table>			
		</form:form>
	</div>
</body>
</html>