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
	<div id="gbl_add_div">
		<div class="pop_title_line">
			<span>BRANCH ${(flag eq 'add') ? 'ADD' : 'MODIFY' }</span>
		</div>	
		<form:form commandName="branch">
		<form:hidden path="seq"/>
		<table class="gbl_add_table">
			<tbody>
				<tr>
					<th>BRANCH ACRONYM</th>
					<td><form:input path="branchAcronym"/></td>
				</tr>
				<tr>
					<th>BRANCH</th>
					<td><form:input path="branch"/></td>
				</tr>
				<tr>
					<th>BRANCH MANAGER</th>
					<td><form:input path="branchManager"/></td>
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
					<th>EMAIL ADDRESS</th>
					<td><form:input path="eMailAddress"/></td>
				</tr>
				<tr>
					<th>ITO TEL NO</th>
					<td><form:input path="itoTelNo"/></td>
				</tr>
				<tr>
					<th>ITO DSN NO</th>
					<td><form:input path="itoDsnNo"/></td>
				</tr>
				<tr>
					<th>ITO CHIEF</th>
					<td><form:input path="itoChief"/></td>
				</tr>
				<tr>
					<th>ITO ADDRESS</th>
					<td><form:input path="itoAddress"/></td>
				</tr>
				<tr>
					<th>DGBLOC</th>
					<td><form:input path="dGbloc"/></td>
				</tr>
				<tr>
					<th>DODAC</th>
					<td><form:input path="dodac"/></td>
				</tr>	
				<tr>
					<th>영업소 총괄담당 주소</th>
					<td><form:textarea path="responsibility"/>
				</tr>
				<tr>
					<th>직책</th>
					<td><form:input path="position"/></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div><span class="yj_button ${(flag eq 'add') ? 'basic_add_submit' : 'basic_modify_submit' }">${(flag eq 'add') ? 'add' : 'modify' }</span></div>
					</td>
				</tr>
			</tfoot>		
		</table>
		</form:form>
	</div>
</body>
</html>