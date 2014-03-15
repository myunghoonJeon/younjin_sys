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
		$("form#outboundFilter").submit();
	}
	
	function goToPreviousPages() {
		goToPage(Math.max(1, page - numPagesPerScreen));
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="inbound_invoice_gbl_list_wrap">
		<div class="pop_title_line">
			<span>INBOUND INVOICE ADD</span>
		</div>		
			
		<c:set var="branchList" value="${filterMap['branchList'] }" />
		<c:set var="carrierList" value="${filterMap['carrierList'] }" />
		<c:set var="codeList" value="${filterMap['codeList'] }" />
		
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<form:form commandName="inboundFilter" method="get">
					<sec:authorize access="hasRole('ROLE_LEVEL4')">
						<li style="font-size: 10pt;">
							<form:select path="branch">
								<form:option value="">Branch(All)</form:option>
								<c:forEach var="branch" items="${branchList }">
									<c:if test="${branch.codeName ne 'None' }" >
										<form:option value="${branch.codeEtc }">${branch.codeName }</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</li>
					</sec:authorize>
					<li>
						<form:select path="carrier">
							<form:option value="">Carrier(All)</form:option>
							<c:forEach var="carrier" items="${carrierList }">
								<form:option value="${carrier.subCode }">${carrier.subCode }</form:option>
							</c:forEach>
						</form:select>
					</li>
					<li>
						<form:select path="code">
							<form:option value="">Code(All)</form:option>
							<c:forEach var="code" items="${codeList }">
								<form:option value="${code.subCode }">${code.subCode }</form:option>
							</c:forEach>
						</form:select>
					</li>
					<li>
						<form:input path="startPud"/> ~ <form:input path="endPud"/>
					</li>
					<li>
						<form:hidden path="page" value="${pagination.currentPage}"/>
					</li>
					<li>	
						<span class="inbound_invoice_add yj_button" >add</span>
					</li>
				</form:form>
			</ul>	
		</div>
	
		<div class="gbl_list_filter_title">
			<ul>
				<c:if test="${inboundFilter.branch ne '' and inboundFilter.branch ne null}">
					<li>[BRANCH : ${inboundFilter.branch  }] </li>
				</c:if>
				<c:if test="${inboundFilter.carrier ne '' and inboundFilter.carrier ne null}">
					<li>[CARRIER : ${inboundFilter.carrier }] </li>
				</c:if>
				<c:if test="${inboundFilter.code ne '' and inboundFilter.code ne null}">
					<li>[CODE : ${inboundFilter.code }]</li>
				</c:if>
			</ul> 	
		</div>	
		
		<div>
			<table class="yj_table">
				<tfoot>
					<tr>
						<td colspan="13">
							<a href="javascript:void(goToPage(1))">FIRST</a>
							<a href="javascript:void(goToPreviousPages())">PREV</a>
							<c:forEach var="i" begin="${pagination.pageBegin}" end="${pagination.pageEnd}">
								<c:if test="${i == pagination.currentPage}">
									<a class="page_now">${i}</a>
								</c:if>
								<c:if test="${i != pagination.currentPage}">
									<a href="javascript:void(goToPage(${i}))">${i}</a>
								</c:if>
							</c:forEach>
							<a href="javascript:void(goToNextPages())">NEXT</a>
							<a href="javascript:void(goToPage(${pagination.numPages}))">LAST</a>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th>GBL NO</th>
						<th>NAME</th>
						<th>SSN</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${gblList eq '[]' or gblList eq null or gblList eq '' }">
						<tr>
							<td colspan="14">GBL이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="gbl" items="${gblList }">
						<tr class="inbound_invoice_gbl_list" data-seq="${gbl.seq }">
							<td>${gbl.gblNo }</td>
							<td>${gbl.shipperName }</td>
							<td>XXX-XX-${fn:substring(gbl.ssn, 5, 9) }
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
</body>
</html>