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
	<c:if test="${end eq true }">
		<script type="text/javascript">
			parent.location.href=contextPath + '/outbound/gblList/';
			parent.$.smartPop.close();
		</script>
	</c:if>
	<form:form commandName="outboundFilter" method="get">
		<sec:authorize access="hasRole('ROLE_LEVEL4')">
			<li>
				<form:hidden path="page" value="${pagination.currentPage}"/>
			</li>
		</sec:authorize>
	</form:form>
	<div class="house_addButton_wrap">
		<span class="house_button yj_button" >add</span>
	</div>	
	
	<div>
		<table class="yj_table">
			<thead>
				<tr>
					<th style="width:5%">NO</th>
					<th>CONT NO</th>
					<th>SEAL NO</th>
					<th style="width:5%;"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${houseList eq '[]' or houseList eq null }">
					<tr>
						<td colspan="4">등록된 정보가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="house" items="${houseList }" varStatus="i">
					<tr class="house_list_tr" data-seq="${house.seq }">
						<td>${i.count }</td>
						<td>${house.contNo }</td>
						<td>${house.sealNo }</td>
						<td class="house_delete" ><img style="width : 15px;height:15px;"src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
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
		</table>
	</div>	
		
	
<%@ include file="../../../layout/foot.jspf"%>