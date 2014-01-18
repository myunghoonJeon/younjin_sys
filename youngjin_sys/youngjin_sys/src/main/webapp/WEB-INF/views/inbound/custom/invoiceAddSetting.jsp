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
	<div class="inbound_invoice_add_setting_wrap">
		<div class="pop_title_line">
			<span>INBOUND INVOICE ADD SETTING</span>
		</div>
		<div class="yj_button_wrap">
			<ul class="yj_button_list inbound_invoice_add_button_list">
				<li>
					<span class="yj_button inbound_invoice_add_back">back</span>
				</li>
				<li>
					<span class="yj_button inbound_invoice_add_next" data-seq="${gblSeq }">next</span>
				</li>
			</ul>
		</div>
		
		<table class="inbound_invoice_add_table">
			<thead>
				<tr>
					<th>GBL NO</th>
					<th>DATE</th>
					<th>INBOUND INVOICE NO</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" id="inbound_invoice_add_gblNo" value="${settingValueMap['gblNo'] }"/></td>
					<td><input type="text" id="inbound_invoice_add_date"/></td>
					<td><input type="text" id="inbound_invoice_add_no" value="${settingValueMap['inboundInvoiceNo'] }"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>