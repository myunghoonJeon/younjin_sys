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
	<div class="title">
		<h1>TRUCK MANIFAST</h1>
	</div>

	<c:set var="branchList" value="${filterMap['branchList'] }" />
	
	<div class="gbl_filter">	
		<form:form commandName="outboundFilter" method="get">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<form:select path="branch">
					<form:option value="">All</form:option>
					<c:forEach var="branch" items="${branchList }">
						<c:if test="${branch.codeName ne 'None' }" >
							<form:option value="${branch.codeEtc }">${branch.codeName }</form:option>
						</c:if>
					</c:forEach>
				</form:select>
			</sec:authorize>
			<form:input path="startPud"/> ~ <form:input path="endPud"/>
			<form:hidden path="page" value="${pagination.currentPage}"/>
		</form:form>
	</div>
	
	<div class="truck_addButton user_addButton">
		<span >add</span>
	</div>	
	
	<div>
		<table class="yj_table">		
			<thead>
				<tr>
					<th>NO</th>
					<th>BRANCH</th>
					<th>CODE</th>
					<th>DATE</th>
				</tr>
			</thead>
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
			<tbody>
				<c:if test="${truckList eq '[]' or truckList eq null }">
					<tr>
						<td colspan="5">등록된 정보가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="truck" items="${truckList }" varStatus="i">
					<tr>
						<td>${i.count }</td>
						<td>${truck.branch }</td>
						<td>${truck.code }</td>
						<td>${truck.date }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
	
<%@ include file="../../../layout/foot.jspf"%>