<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../layout/head.jspf"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- Page 처리 Script --%>
<c:set var="pagination" value="${outboundFilter.pagination }"/>
<script type="text/javascript">
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
	<c:set var="carrierList" value="${filterMap['tspList'] }" />
	
	<div class="invoice_filter">	
		<ul>
			<form:form commandName="invoiceFilter" method="get">
				<li>
					<form:select path="tsp">
						<form:option value="">Carrier(All)</form:option>
						<c:forEach var="tsp" items="${carrierList }">
							<form:option value="${tsp.subCode }">${tsp.subCode }</form:option>
						</c:forEach>
					</form:select>
				</li>
				<li>
					<form:input path="startDate"/> ~ <form:input path="endDate"/>
				</li>
				<li>
					<form:hidden path="page" value="${pagination.currentPage}"/>
				</li>
				<li>	
					<span class="invoice_add_button yj_button" >add</span>
				</li>
			</form:form>
		</ul>	
	</div>
		
	<div>
		<table class="yj_table invoice_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>INVOICE NO</th>
					<th>START DATE</th>
					<th>END DATE</th>
					<th>AMOUNT</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${invoiceList eq '[]' }">
					<tr>
						<td colspan="6">등록된 invoice가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="invoice" items="${invoiceList }" varStatus="i">
					<tr data-seq="${invoice.seq }">
						<td>${i.count }</td>
						<td>${invoice.invoiceNo }</td>
						<td>${invoice.startDate }</td>
						<td>${invoice.endDate }</td>
						<td><fmt:formatNumber value="${invoice.amount }" type="number"/></td>
						<td class="invoice_all_delete"><img class="memorandum_icon" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../../layout/foot.jspf"%>