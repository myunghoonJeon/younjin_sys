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

<%@ include file="../../../layout/include_script.jspf" %>

</head>
<body>
	<div class="invoice_gbl_content_list_wrap">
		<div class="pop_title_line">
			<span>INVOICE GBL LIST</span>
		</div>	
		
		<div class="yj_button_wrap invoice_gbl_list_button_wrap">
			<ul class="yj_button_list invoice_gbl_list_button_list">
				<li>
					<span class="yj_button inbound_invoice_gbl_print">print</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="inbound_invoice_gbl_list_table" data-seq="${invoicSeq }">
				<thead>
					<tr>
						<th>GBL NO</th>
						<th>RANK</th>
						<th>NAME</th>
						<th>AMOUNT</th>
						<th>STATUS</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="invoiceGbl" items="${invoiceGblList }">
						<tr data-gblSeq="${invoiceGbl.gblSeq }" data-invoiceGblSeq="${invoiceGbl.seq }">
							<td>${invoiceGbl.gblNo }</td>
							<td>${invoiceGbl.rank }</td>
							<td>${invoiceGbl.name }</td>
							<td>${invoiceGbl.amount }</td>
							<td>${invoiceGbl.complete }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>