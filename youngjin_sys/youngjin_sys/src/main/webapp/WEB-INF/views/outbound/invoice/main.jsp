<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../layout/head.jspf"%>	
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

	<div class="invoice_add_button_wrap">
		<span class="invoice_add_button yj_button" >add</span>
	</div>	
	
	<div>
		<table class="yj_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>TSP</th>
					<th>START DATE</th>
					<th>END DATE</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${invoiceList eq '[]' }">
					<tr>
						<td colspan="5">등록된 invoice가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="invoice" items="${invoiceList }" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>${invoice.tsp }</td>
						<td>${invoice.startDate }</td>
						<td>${invoice.endDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../../layout/foot.jspf"%>