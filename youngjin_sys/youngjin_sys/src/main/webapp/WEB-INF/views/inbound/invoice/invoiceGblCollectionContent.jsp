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
<title>CONTENT</title>

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
	<div class="invoice_gbl_content_wrap">
		<div class="pop_title_line">
			<span>INVOICE GBL</span>
		</div>
		
		<div class="yj_button_wrap invoice_gbl_list_button_wrap">
			<ul class="yj_button_list invoice_gbl_content_list_button_list">
				<li>
					<span class="yj_button invoice_gbl_content_list_pdf" data-gblSeq="${gblSeq }" data-invoiceGblSeq="${invoiceGblSeq }" data-seq="${invoiceGblContentInfo.invoiceListSeq }">pdf</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="invoice_gbl_content_table">
				<tr>
					<th>TSP</th>
					<td>${invoiceGblContentInfo.invoice.tsp }</td>
					<th>IN/OUT</th>
					<td>${invoiceGblContentInfo.invoice.process }</td>
					<th>CODE</th>
					<td colspan="2">${invoiceGblContentInfo.code }</td>
				</tr>
				<tr>
					<th>GBL NO</th>
					<td>${invoiceGblContentInfo.gblNo }</td>
					<th>RANK</th>
					<td>${invoiceGblContentInfo.rank }</td>
					<th>NAME</th>
					<td colspan="2">${invoiceGblContentInfo.name }</td>
				</tr>
				<tr>
					<td colspan="7"></td>
				</tr>
				<tr>
					<th colspan="5">
						CHARGING ITEMS
					</th>
					<th>
						QUANTITY
					</th>
					<th>
						AMOUNTS
					</th>
				</tr>
				<c:forEach var="invoiceGblContent" items="${invoiceGblContentList }">
					<tr>
						<td colspan="5">${invoiceGblContent.chargingItem }</td>
						<td>${invoiceGblContent.quantity }</td>
						<td>${invoiceGblContent.amount }</td>
					</tr>
				</c:forEach>
				<tfoot>
					<tr>
						<th colspan="2" class="collection_remark_th">REMARK</th>
						<td colspan="5">
							<textarea class="collection_remark">${invoiceGblContentInfo.remark }</textarea>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>