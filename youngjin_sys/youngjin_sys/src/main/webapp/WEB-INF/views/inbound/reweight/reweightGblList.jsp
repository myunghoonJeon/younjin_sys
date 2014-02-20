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
<c:set var="pagination" value="${inboundFilter.pagination }"/>
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
	
	var numPagesPerScreen = <c:out value='${pagination.numPagesPerScreen}'/>;
	var page = <c:out value='${pagination.currentPage}'/>;
	var numPages = <c:out value='${pagination.numPages}'/>;
	
	function goToNextPages() {
		goToPage(Math.min(numPages, page + numPagesPerScreen));
	}
	
	function goToPage(page) {	
		//location.href =  contextPath + '/member/leading/archives/page/'+page;
		$("input#page").val(page);
		$("form#inboundFilter").submit();
	}
	
	function goToPreviousPages() {
		goToPage(Math.max(1, page - numPagesPerScreen));
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="inbound_reweight_gbl_list_wrap">
		<div class="pop_title_line">
			<span>REWEIGHT SELECT</span>
		</div>		
		
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>
					SUBJECT : <input type="text" id="reweight_subject"/>
				</li>
				<li>	
					<span class="inbound_reweight_add yj_button" >add</span>
				</li>
			</ul>	
		</div>
		
		<div>
			<table class="yj_table">
				<thead>
					<tr>
						<th>DELI<br/>DATE</th>
						<th>ORIGIN<br/>GBLOCK</th>
						<th>SCAC<br/>CODE</th>
						<th>GBL NO</th>
						<th>FULL NAME</th>
						<th>O/WT</th>
						<th>R/WT</th>
						<th>S/WT</th>
						<th>DENTN</th>
						<th>RATE<br/>GBL.31</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${reweightGblList eq '[]' or reweightGblList eq null or reweightGblList eq '' }">
						<tr>
							<td colspan="11">조건에 맞는 GBL이 없습니다.</td>
						</tr>
					</c:if>
					
					<c:forEach var="reweight" items="${reweightGblList }" varStatus="i">
						<tr>
							<td>${reweight.deliDate }</td>
							<td>${reweight.originGblock }</td>
							<td>${reweight.scacCode }</td>
							<td>${reweight.gblNo }</td>
							<td>${reweight.fullName }</td>
							<td>${reweight.oWt }</td>
							<td>${reweight.rWt }</td>
							<td></td>
							<td>${reweight.dentn }</td>
							<td>${reweight.rateGbl31 }</td>
							<td><input type="checkbox" value="${reweight.gblSeq }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
</body>
</html>