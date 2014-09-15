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
		$("form#invoiceFilter").submit();
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
					<c:if test="${invoice.amount ne '' and invoice.amount ne null }">
						<c:set var="collectionMap" value="${invoiceCollectionMap[invoice.seq] }" />
						<tr data-seq="${invoice.seq }">
							<td>${invoice.invoiceNo }</td>
							<td class="invoice_amount"><input class="invoice_amountValue" value="${invoice.amount }" readonly="readonly"/>$</td>
							<td><input class="collection_net" name="collection_net" type="text" value="${collectionMap.net }" readonly="readonly" />$</td>
							<td>
								<c:choose>
										<c:when test="${collectionMap.state eq 'COMPLETE' }">
											<font color="blue">COMPLETE</font>	
										</c:when>
										<c:when test="${collectionMap.state eq 'RESENT' and collectionMap.net ne '0.0' }">
											<font color="red">PENDING</font>
										</c:when>
										<c:otherwise> </c:otherwise>
									</c:choose>								
							</td>
							<%-- <td class="collection_flow_wrap">
								<ul>
									<c:forEach var="flow" items="${collectionMap.invoiceColltionFlowList }" varStatus="i">
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
													<td>${flow.writeDate }</td>
													<td>AMOUNT</td>
													<td class="collection_amount">${flow.amount }</td>
													<td>REMARK</td>
													<td>${flow.remark }</td>
												</tr>
											</table>
										</li>
									</c:forEach>
									<li class="collection_button">																
										<c:if test="${collectionMap.state ne 'COMPLETE' }">
											<div class="collection_plus"><img src="${cp }/resources/images/collection_plus.png" /></div>
											<c:if test="${collectionMap.invoiceColltionFlowList ne '[]' and collectionMap.invoiceColltionFlowList ne null }">
												<div class="collection_delete"><img src="${cp }/resources/images/collection_delete.png" /></div>
											</c:if>
										</c:if>
										<c:if test="${collectionMap.state eq 'COMPLETE'}">
											<div class="collection_delete"><img src="${cp }/resources/images/collection_delete.png" /></div>
										</c:if>
									</li>											
								</ul>					
							</td> --%>
						</tr>
					</c:if>		
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<a href="javascript:void(goToPage(1))">FIRST</a>
						<a href="javascript:void(goToPreviousPages())">PREV</a>
						<c:forEach var="i" begin="${pagination.pageBegin}" end="${pagination.pageEnd}">
							<c:if test="${i == pagination.currentPage}">
								<a style="font-size: 13pt;color: red;" class="page_now">${i}</a>
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
<%@ include file="../../../layout/foot.jspf"%>