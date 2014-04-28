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
				<span class="declaration_list_add yj_button" >add</span>
			</li>
		</ul>	
	</div>
	
	<div>
		<table class="yj_table declarationList_table">
			<tfoot>
				<tr>
					<td colspan="13">
					<!-- 
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
						 -->
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
					<th>NO</th>
					<th>INBOUND INVOICE DATE</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${declarationList eq '[]' or declarationList eq null or declarationList eq '' }">
					<tr>
						<td colspan="3">DECLARATION LIST 가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="declaration" items="${declarationList }" varStatus="i">
					<tr data-seq="${declaration.seq }">
						<td>${i.count }</td>
						<td>${declaration.invoiceDate }</td>
						<td><img class="declaration_list_delete" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>
