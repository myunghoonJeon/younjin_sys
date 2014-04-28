<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../layout/head.jspf"%>
<%-- Page 처리 Script --%>
<c:set var="pagination" value="${inboundFilter.pagination }"/>

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
		$("form#inboundFilter").submit();
	}
	
	function goToPreviousPages() {
		goToPage(Math.max(1, page - numPagesPerScreen));
	}
</script>	
	<c:if test="${end eq true }">
		<script type="text/javascript">
			parent.location.href=contextPath + '/inbound/freightList/';
			parent.$.smartPop.close();
		</script>
	</c:if>
	
	<form:form commandName="inboundFilter" method="get">
		<form:hidden path="page" value="${pagination.currentPage}"/>
	</form:form>
	
	<div class="gbl_filter">	
		<ul class="freight_filter_wrap">
			<li>	
				<span class="inbound_invoice_add_button yj_button" >add</span>
			</li>
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
					<th>INBOUND INVOICE NO</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${inboundInvoiceList eq '[]' or inboundInvoiceList eq null or inboundInvoiceList eq '' }">
					<tr>
						<td colspan="14">invoice 가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="inboundInvoice" items="${inboundInvoiceList }">
					<tr class="inbound_invoice_tr" data-inboundInvoiceSeq="${inboundInvoice.seq }" data-gblSeq="${inboundInvoice.gblSeq }">
						<td>${inboundInvoice.gblNo }</td>
						<td>${inboundInvoice.name }</td>
						<td>XXX-XX-${fn:substring(inboundInvoice.ssn, 5, 9)}</td>
						<td>							
							<c:choose>
								<c:when test="${fn:length(inboundInvoice.inboundInvoiceNo) eq 1 }">
									${fn:substring(inboundInvoice.invoiceDate, 2, 4)}-0000${inboundInvoice.inboundInvoiceNo }
								</c:when>
								<c:when test="${fn:length(inboundInvoice.inboundInvoiceNo) eq 2 }">
									${fn:substring(inboundInvoice.invoiceDate, 2, 4)}-000${inboundInvoice.inboundInvoiceNo }
								</c:when>
								<c:when test="${fn:length(inboundInvoice.inboundInvoiceNo) eq 3 }">
									${fn:substring(inboundInvoice.invoiceDate, 2, 4)}-00${inboundInvoice.inboundInvoiceNo }
								</c:when>
								<c:when test="${fn:length(inboundInvoice.inboundInvoiceNo) eq 4 }">
									${fn:substring(inboundInvoice.invoiceDate, 2, 4)}-0${inboundInvoice.inboundInvoiceNo }
								</c:when>
								<c:when test="${fn:length(inboundInvoice.inboundInvoiceNo) eq 5 }">
									${fn:substring(inboundInvoice.invoiceDate, 2, 4)}-${inboundInvoice.inboundInvoiceNo }
								</c:when>
							</c:choose>
						</td>
						<td><img class="inbound_invoice_list_delete" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>