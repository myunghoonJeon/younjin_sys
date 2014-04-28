<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- inbound truck manifast -->
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
	<c:set var="branchList" value="${filterMap['branchList'] }" />
	
	<div class="gbl_filter">	
		<ul>
			<form:form commandName="inboundFilter" method="get"><%-- 
				<sec:authorize access="hasRole('ROLE_LEVEL4')">
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
						<form:input path="startPud"/> ~ <form:input path="endPud"/>
					</li>
					<li>
						<form:hidden path="page" value="${pagination.currentPage}"/>
					</li> --%>
					<li>
						<span class="truck_addButton inbound_truck_addButton yj_button" >add</span>
					</li>
			</form:form>
		</ul>
	</div>
	
	<div>
		<table class="yj_table">		
			<thead>
				<tr>
					<th>NO</th>
					<th>TRUCKMANIFAST DATE</th>
					<th>AREA</th>
					<th></th>
				</tr>
			</thead><%-- 
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
			</tfoot> --%>			
			<tbody>
				<c:if test="${truckList eq '[]' or truckList eq null }">
					<tr>
						<td colspan="5">등록된 정보가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="truck" items="${truckList }" varStatus="i">
					<tr class="inbound_truck_manifast_form" data-seq="${truck.seq }">
						<td>${i.count }</td>
						<td>${truck.truckManifastDate }</td>
						<td>${truck.area }</td>
						<td  class="truck_manifast_deleteButton inbound_truck_manifast_deleteButton"><img src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
	
<%@ include file="../../../layout/foot.jspf"%>