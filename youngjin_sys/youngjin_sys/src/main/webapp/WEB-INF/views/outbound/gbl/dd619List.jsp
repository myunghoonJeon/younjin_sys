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
	<div class="dd619_list_wrap" data-seq="${seq}">
		<div class="title">
			<h1>DD619 LIST</h1>
		</div>	
		<div id="dd619_addButton">
			<ul id="dd619_button_list">
				<li>
					<span class="yj_button dd619_back">back</span>
				</li>
				<li>
					<span class= "yj_button dd619_addButton">add</span>
				</li>
			</ul>
		</div>
		<div>
			<table class="yj_table">
				<thead>
					<tr>
						<th>
							NO
						</th>
						<th>
							GBL NO
						</th>
						<th>
							WRITE DATE
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${dd619List eq '[]' or dd619List eq null }">
						<tr>
							<td colspan="3">등록 된 문서가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="dd619" items="${dd619List }" varStatus="i">
						<tr>
							<td>${i.count }</td>
							<td>${dd619.gblNo }</td>
							<td>${dd619.writeDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>