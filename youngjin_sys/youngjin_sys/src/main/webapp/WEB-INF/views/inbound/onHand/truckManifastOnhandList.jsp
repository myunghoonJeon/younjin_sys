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
<title>Truck GBL List</title>

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
	<c:if test="${end eq true }">
		<script type="text/javascript">
			parent.location.href=contextPath + '/inbound/truckManifastOnhandList/';
			parent.$.smartPop.close();
		</script>
	</c:if>
	<c:set var="branchList" value="${filterMap['branchList'] }" />
	<c:set var="carrierList" value="${filterMap['carrierList'] }" />
	<c:set var="codeList" value="${filterMap['codeList'] }" />
		
	<div class="truck_gbl_list_wrap">
		<div class="pop_title_line">
			<span>GBL LIST</span>
		</div>	
		
		<div class="gbl_filter">
			<ul class="freight_filter_wrap">
				<form:form commandName="inboundFilter" method="get">
					<sec:authorize access="hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2')">
						<li>	
							<form:select path="branch">
								<form:option value="">All</form:option>
								<c:forEach var="branch" items="${branchList }">
									<c:if test="${branch.codeName ne 'None' }" >
										<form:option value="${branch.codeEtc }">${branch.codeName }</form:option>
									</c:if>
								</c:forEach>
							</form:select>
						</li>
					</sec:authorize>
						<li>
							<form:select path="code">
								<form:option value="">All</form:option>
								<c:forEach var="code" items="${codeList }">
									<form:option value="${code.subCode }">${code.subCode }</form:option>
								</c:forEach>
							</form:select>
						</li>
					<li>
					<form:select path="searchTitle">
						<form:option value="name">NAME</form:option>
						<form:option value="ssn">SSN</form:option>
						<form:option value="gblNo">GBL NO</form:option>
					</form:select>
					<form:input path="searchContent"/>
						<span class="inbound_search yj_button" >search</span>
					</li>	
					<form:hidden path="page" value="${pagination.currentPage}"/>
					</form:form>			
				<li>Truck Manifast Date : <input type="text" id="truck_manifast_date" /></li>	
				<li>AREA : <input type="text" id="truck_manifast_area"/></li>
				<li>
					<span class="truck_gbl_addButton truck_gbl_onHand_addbutton yj_button" >add</span>
				</li>
			</ul>
		</div>
		<div>
			<table class="yj_table">
				<thead>
					<tr>
						<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null}">
							<th>CODE</th>
						</c:if>
						<th>PUD</th>
						<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
							<th>SCAC</th>
						</c:if>
						<th>GBL_NO</th>
						<th>RANK</th>
						<th>NAME</th>
						<th>PCS</th>
						<th>LBS</th>
						<th>CUFT</th>
						<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
							<th>BRANCH</th>
						</c:if>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${onHandGblList eq '[]' or onHandGblList eq null or onHandGblList eq '' }">
						<tr>
							<td colspan="13">GBL이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="gbl" items="${onHandGblList }">
						<fmt:parseDate var="parsePud" value="${gbl.pud}" pattern="yyyyMMdd"/>
						<c:set var="pud" value="${parsePud }" />
						<tr class="booking_gbl_list_tr" data-seq="${gbl.seq }" data-ws="1">
							<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null }">
								<td>${gbl.code }</td>
							</c:if>
							<td>
								${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }
							</td>
							<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
								<td>${gbl.tsp }</td>
							</c:if>
							<td>${gbl.gblNo }</td>
							<td>${gbl.rank }</td>
							<td>${gbl.shipperName }</td>
							<td>${gbl.pcs }</td>
							<td>${gbl.lbs }</td>
							<td>${gbl.cuft }</td>
							<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
								<td>${gbl.areaLocal }</td>
							</c:if>
							<td><input value="${gbl.onHandContentSeq }" type="checkbox" /></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
				<tr>
					<td colspan="11">
						<a href="javascript:void(goToPage(1))">FIRST</a>
						<a href="javascript:void(goToPreviousPages())">PREV</a>
						<c:forEach var="i" begin="${pagination.pageBegin}" end="${pagination.pageEnd}">
							<c:if test="${i == pagination.currentPage}">
								<a class="page_now" style="font-size: 13pt;color: red;">${i}</a>
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
			</table>
		</div>
	</div>
</body>
</html>