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
<title>INBOUND INVOICE ADD</title>

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
	<div class="on_hand_list_add_setting_wrap">
		<div class="pop_title_line">
			<span>ON HAND LIST ADD SETTING</span>
		</div>
		<div class="yj_button_wrap">
			<ul class="yj_button_list on_hand_list_add_button_list">
				<li>
					<span class="yj_button on_hand_list_add_next" data-seq="${gblSeq }">next</span>
				</li>
			</ul>
		</div>
		
		<table class="on_hand_list_add_table">
			<thead>
				<tr>
					<th>ON HAND DATE</th>
					<th>FIRST ARRIVALABLE DELIVER DATE</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="on_hand_list_add_onHandDate" placeholder="ex) YYYY-MM-DD"/></td>
					<td><input type="text" class="on_hand_list_add_firstDeliverDate" placeholder="ex) YYYY-MM-DD"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>