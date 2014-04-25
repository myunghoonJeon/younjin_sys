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
	<div class="inbound_invoice_gbl_list_wrap">
		<div class="pop_title_line">
			<span>ON HAND LIST ADD</span>
		</div>		
		
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>	
					<span data-onHandListseq="${onHandListSeq }" class="on_hand_list_content_add yj_button" >add</span>
				</li>
			</ul>	
		</div>
		
		<div>
			<table class="yj_table">
				<thead>
					<tr>
						<th>GBL NO</th>
						<th>RANK</th>
						<th>NAME</th>
						<th>SSN</th>
						<th>CODE</th>
						<th>BY</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${inboundInvoiceList eq '[]' or inboundInvoiceList eq null or inboundInvoiceList eq '' }">
						<tr>
							<td colspan="14">통관된 GBL이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="inboundInvoice" items="${inboundInvoiceList }">
						<tr class="on_hand_list_content_select_tr" data-onHandListContentSeq="${ inboundInvoice.onHandListContentSeq}" data-onHandListseq="${onHandListSeq }" data-seq="${inboundInvoice.seq }" data-gblSeq="${inboundInvoice.gblSeq }">
							<td>${inboundInvoice.gblNo }</td>
							<td>${inboundInvoice.rank }</td>
							<td>${inboundInvoice.name }</td>
							<td>XXXX-XX-${fn:substring(inboundInvoice.ssn, 8, 12) }
							<td>${inboundInvoice.code }</td>	
							<td class="on_hand_list_content_select_select">
								<select>
									<option value="nextWeek">다음주 예정</option>
									<option value="email">E-MAIL</option>
									<option value="call">CALL</option>
								</select>
							</td>
							<td><input class="on_hand_list_content_select_check" type="checkbox" value="${inboundInvoice.seq }" disabled="disabled"/></td>						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
</body>
</html>