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
<title>memorandum_all</title>

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
	<div class="memorandum_all_list_wrap" data-seq="${seq}">
		<div class="pop_title_line">
			<span>MEMORANDUM LIST</span>
		</div>	
		<div id="memorandum_all_addButton">
			<ul id="memorandum_all_button_list">
				<li>
					<span class="yj_button memorandum_all_back">back</span>
				</li>
				<li>
					<span class= "yj_button memorandum_all_addButton">add</span>
				</li>
			</ul>
		</div>
		<div>
			<table class="yj_table memorandum_all_table" data-seq="${seq }">
				<thead>
					<tr>
						<th>
							NO
						</th>
						<th>
							WRITE DATE
						</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${memroandumList eq '[]' or memroandumList eq null }">
						<tr class="memorandum_list_none">
							<td colspan="3">등록 된 문서가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="memorandum" items="${memroandumList }" varStatus="i">
						<tr data-list="${memorandum.seq }">
							<td class="memorandum_list_count">${i.count }</td>
							<td>${memorandum.writeDate }</td>
							<td data-check="delete"><img class="memorandum_icon memorandum_icon memorandum_list_delete" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>