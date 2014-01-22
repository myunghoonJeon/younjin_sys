<%@page import="org.youngjin.net.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<div class="inbound_invoice_select_weight_wrap">
		<div class="pop_title_line">
			<span>INBOUND INVOICE SELECT WEIGHT</span>
		</div>
		<div class="yj_button_wrap">
			<ul class="yj_button_list inbound_invoice_select_weight_list">
				<li>
					<span class="yj_button inbound_invoice_select_weight_back">back</span>
				</li>
				<li>
					<span data-inboundInvoiceSeq="${inboundInvoiceSeq }" class="yj_button inbound_invoice_select_weight_add">add</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="inbound_invoice_select_weight_table">
				<thead>
					<tr>
						<th>PIECE</th>
						<th>TYPE</th>
						<th>GROSS</th>
						<th>TARE</th>
						<th>NET</th>
						<th>CUFT</th>
						<th>GROSS(KG)</th>
						<th>NET(KG)</th>
						<th>CBM</th>
						<th>분할</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="weight" items="${weightList }">
						<tr data-seq="${weight.seq }">
							<td>${weight.piece }</td>
							<td>${weight.type }</td>
							<td>${weight.gross }</td>
							<td>${weight.tare }</td>
							<td>${weight.net }</td>
							<td>${weight.cuft }</td>
							<td>${weight.grossKg }</td>
							<td>${weight.net * 0.45359237 }</td>
							<td>${weight.cuft / 35.315 }</td>
							<td><input name="inbound_invoice_weight_check" type="checkbox" value="${weight.seq }"/></td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>	
</body>
</html>