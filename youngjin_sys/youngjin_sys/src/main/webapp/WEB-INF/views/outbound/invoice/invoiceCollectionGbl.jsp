<%@page import="org.youngjin.net.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
	var todayDate = <%=DateUtil.getToday("YYYYMMDD")%>
</script>

<%@ include file="../../../layout/include_script.jspf" %>

</head>
<body>
	<div class="invoice_collection_gbl_list_wrap">
		<div class="pop_title_line">
			<span>INVOICE GBL COLLECTION</span>
		</div>	<!-- 
		
		<div class="yj_button_wrap invoice_gbl_list_button_wrap">
			<ul class="yj_button_list invoice_gbl_list_button_list">
				<li>
					<span class="yj_button invoice_gbl_print">print</span>
				</li>
			</ul>
		</div> -->
		
		<div>
			<table class="invoice_gbl_collection_list_table" data-seq="${invoicSeq }" data-process="outbound">
				<colgroup>
					<col width="8%">
					<col width="11%">
					<col width="9%">
					<col width="8%">
					<col width="64%">
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>AMOUNT</th>
						<th>NET</th>
						<th>STATE</th>
						<th>FLOW</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="invoiceGbl" items="${invoiceGblList }">
						<c:set var="collectionGblMap" value="${invoiceCollectionGblMap[invoiceGbl.seq] }" />
						<tr data-gblSeq="${invoiceGbl.gblSeq }" data-invoiceGblSeq="${invoiceGbl.seq }">
							<td>${invoiceGbl.gblNo }</td>
							<td style="font-size: 8pt;"class="invoice_amount"><input style="width:70px;text-align: center;"class="invoice_amountValue" value="${invoiceGbl.amount }" readonly="readonly"/>$</td>
							<c:choose>
								<c:when test="${collectionGblMap.net eq null}">
									<c:set var="tempNet" value="0"></c:set>
									<c:set var="tempState" value="RESENT"></c:set>
									<c:set var="tempDifference" value="${invoiceGbl.amount }"/>
								</c:when>	
								<c:otherwise>
									<c:set var="tempNet" value="${collectionGblMap.net}"></c:set>
									<c:set var="tempState" value="${collectionGblMap.state }"></c:set>
									<c:set var="tempDifference" value="${collectionGblMap.difference }"/>
								</c:otherwise>
								</c:choose>
							<td><input style="width:70px; text-align: center;"class="collection_net" name="collection_net" type="text" value="${tempNet }" readonly="readonly" />$</td>
							<td>
								<c:choose>
									<c:when test="${collectionGblMap.state eq 'COMPLETE' }">
										${collectionGblMap.state }	
									</c:when>
									<c:when test="${collectionGblMap.state eq 'RESENT' }">
										<%-- <font color="red">${collectionGblMap.state }</font><br />
										<font color="red"><fmt:formatNumber pattern="##,###.00" value="${collectionGblMap.difference }"/>$</font> --%>
										<font color="red">${tempState }</font><br/>
										<font color="red"><fmt:formatNumber pattern="##,###.00" value="${tempDifference }"/></font>
									</c:when>
									<c:otherwise>
										<font color="red">${tempState }</font><br/>
										<font color="red"><fmt:formatNumber pattern="##,###.00" value="${tempDifference }"/></font>
									</c:otherwise>
								</c:choose>								
							</td>
							<td class="collection_gbl_flow_wrap">
								<ul>
									<c:forEach var="flow" items="${collectionGblMap.invoiceColltionFlowList }" varStatus="i">
										<li>
											<table class="collection_flow_table" data-flowSeq="${flow.seq }" data-collectionSeq="${flow.invoiceCollectionSeq }" data-count="${i.count }">
												<tr>
													<td colspan="2">
														<select name="flow_state" disabled="disabled">
															<option value="DEPOSIT" ${flow.state eq 'DEPOSIT' ? 'selected=selected' : '' }>DEPOSIT</option>
															<option value="ACCEPT" ${flow.state eq 'ACCEPT' ? 'selected=selected' : '' }>ACCEPT</option>
															<option value="CLAIM" ${flow.state eq 'CLAIM' ? 'selected=selected' : '' }>CLAIM</option>
														</select>
													</td>
													<td>DATE</td>
													<td class="collection_date" path="flow_date">${flow.date }</td>
													<td>AMOUNT</td>
													<td class="collection_amount">${flow.amount }</td>
													<td>REMARK</td>
													<td>${flow.remark }</td>
												</tr>
											</table>
										</li>
									</c:forEach>
									<li class="collection_button">																
										<c:if test="${collectionGblMap.state ne 'COMPLETE' }">
											<div class="collection_gbl_plus"><img src="${cp }/resources/images/collection_plus.png" /></div>
											<c:if test="${collectionGblMap.invoiceColltionFlowList ne '[]' and collectionGblMap.invoiceColltionFlowList ne null }">
												<div class="collection_gbl_delete"><img src="${cp }/resources/images/collection_delete.png" /></div>
											</c:if>
										</c:if>
										<c:if test="${collectionGblMap.state eq 'COMPLETE'}">
											<div class="collection_gbl_delete"><img src="${cp }/resources/images/collection_delete.png" /></div>
										</c:if>
									</li>											
								</ul>
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>
</body>
</html>