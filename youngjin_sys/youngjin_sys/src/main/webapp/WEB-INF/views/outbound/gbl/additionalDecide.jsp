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
<title>DD619 LIST</title>

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
	<div id="addtional_decide_wrap">
		<div class="pop_title_line">
			<span>ADDTIONAL DECIDE</span>
		</div>	
		<div id="addition_table_wrap">
			<table id="addition_table" data-seq="${seq}">
				<thead>
					<tr>
						<th>LIST</th>
						<th>COST</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${checkMemorandumMap['01'] ne '[]'  or checkMemorandumMap['01'] ne null}">
						<tr>
							<td><input name="type" type="text" value="LOWERING EQIPMENT" readonly="readonly" /></td>
							<td><input name="cost" type="text" /> $</td>
						</tr>
					</c:if>
					<c:if test="${checkMemorandumMap['02'] ne '[]'  or checkMemorandumMap['02'] ne null}">
						<c:forEach var="article" items="${articles}">
							<tr>
								<td><input name="type" type="text" value="${article}" readonly="readonly" /></td>
								<td><input name="cost" type="text" /> $</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${checkMemorandumMap['03'] ne '[]'  or checkMemorandumMap['03'] ne null}">
						<tr>
							<td><input name="type" type="text" value="MOTO CYCLE" readonly="readonly" /></td>
							<td><input name="cost" type="text" /> $</td>
						</tr>
					</c:if>
					<tr>
						<td><input name="type" type="text" /></td>
						<td><input name="cost" type="text" /> $ </td>
						<td class="gbl_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;"><div class="gbl_plus_Box"></div></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div>
								<span class="addition_complete_btn yj_button">complete</span>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>