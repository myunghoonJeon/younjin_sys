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
<title>Preparation</title>

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
	<div class="gbl_preparation_div">
		<div class="pop_title_line">
			<span>PREPARATION</span>
		</div>
		<ul class="gbl_preparation_list" data-seq="${seq }">
			<li class="gbl_preparation_pre_move_survey"><span>PRE-MOVE-SURVEY</span></li>
			<li class="gbl_preparation_memorandum"><span>MEMORANDUM</span></li>
			<li class="gbl_preparation_dd619_write"><span>DD-619 WRITE</span></li>
			<li class="gbl_preparation_weight_certificate"><span>WEIGHT-CERTIFICATE</span></li>
			<li class="gbl_preparation_addtional_decide"><span>CONFIRMATION</span></li>
			<li class="gbl_prearation_complete"><span>COMPLETE</span></li>
		</ul>
	</div>
</body>
</html>