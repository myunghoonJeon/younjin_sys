<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../layout/head.jspf"%>	
<%-- Page 처리 Script --%>
<c:set var="pagination" value="${invoiceFilter.pagination }"/>
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
					GBL NO : <form:input path="gblNo" />
				</li>
				<li>
					<form:hidden path="page" value="${pagination.currentPage}"/>
				</li>
			</form:form>
		</ul>	
	</div>
		
	<div>
		<table class="yj_table invoice_collection_table">
			<!-- <colgroup>
				<col width="8%">
				<col width="6%">
				<col width="6%">
				<col width="8%">
				<col width="72%">
			</colgroup> -->
			<thead>
				<tr>
					<th>NO</th>
					<th>AMOUNT</th>
					<th>NET</th>
					<th>STATE</th>
					<!-- <th>FLOW</th> -->
				</tr>
			</thead>
			<tbody>
				<c:if test="${invoiceList eq '[]' }">
					<tr>
						<td colspan="5">등록된 invoice가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="invoice" items="${invoiceList }" varStatus="i">
					<c:choose>
						<c:when test="${invoice.amount ne '' and invoice.amount ne null }">
							<c:set var="collectionMap" value="${invoiceCollectionMap[invoice.seq] }" />
							<tr data-seq="${invoice.seq }">
								<td>${invoice.invoiceNo }</td>
								<td class="invoice_amount"><input class="invoice_amountValue" value="${invoice.amount }" readonly="readonly"/>$</td>
								<td><input class="collection_net" name="collection_net" type="text" value="${collectionMap.net }" readonly="readonly" />$</td>
								<td>
									<c:choose>
										<c:when test="${collectionMap.state eq 'COMPLETE' }">
											${collectionMap.state }	
										</c:when>
										<c:when test="${collectionMap.state eq 'RESENT' }">
											<font color="red">${collectionMap.state }</font><br />
											<font color="red">${collectionMap.difference }$</font>
										</c:when>
									</c:choose>								
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>	
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../../layout/foot.jspf"%>