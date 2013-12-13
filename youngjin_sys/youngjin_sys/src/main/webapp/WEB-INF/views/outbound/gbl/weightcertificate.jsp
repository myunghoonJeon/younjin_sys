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
<title>Weight Certificate</title>

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
	<div id="weightcertificate_wrap">
		<h1 id="weightcertificate_title">CERTIFIED WEIGHT TICKET</h1>
		<div class="weightcertificate_content">
			<ul>
				<li>
					<ul>
						<li></li>				
						<li></li>
						<li></li>				
						<li></li>
					</ul>
				</li>
				<li>			
					<ul>
						<li></li>				
						<li></li>
						<li></li>				
						<li></li>
					</ul>
				</li>
				<li>			
					<ul>
						<li></li>				
						<li></li>
						<li></li>				
						<li></li>
					</ul>
				</li>
				<li>
					<ul>
						<li></li>				
						<li></li>
						<li></li>				
						<li></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>