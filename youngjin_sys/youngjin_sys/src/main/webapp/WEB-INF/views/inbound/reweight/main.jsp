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

	<div class="gbl_filter">	
		<ul class="freight_filter_wrap">
			<form:form commandName="inboundFilter" method="get">
				<%-- <sec:authorize access="hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2')">
					<li style="font-size: 10pt;">
						<form:select path="branch">
							<form:option value="">Branch(All)</form:option>
							<c:forEach var="branch" items="${branchList }">
								<c:if test="${branch.codeName ne 'None' }" >
									<form:option value="${branch.codeEtc }">${branch.codeName }</form:option>
								</c:if>
							</c:forEach>
						</form:select>
					</li>
				</sec:authorize>
				<li>
					<form:select path="carrier">
						<form:option value="">Carrier(All)</form:option>
						<c:forEach var="carrier" items="${carrierList }">
							<form:option value="${carrier.subCode }">${carrier.subCode }</form:option>
						</c:forEach>
					</form:select>
				</li>
				<li>
					<form:select path="code">
						<form:option value="">Code(All)</form:option>
						<c:forEach var="code" items="${codeList }">
							<form:option value="${code.subCode }">${code.subCode }</form:option>
						</c:forEach>
					</form:select>
				</li>
				<li>
					<form:input path="startPud"/> ~ <form:input path="endPud"/>
				</li>
				<li>
					<form:hidden path="page" value="${pagination.currentPage}"/>
				</li> --%>
				<li>	
					<span class="reweight_add yj_button" >add</span>
				</li>
			</form:form>
		</ul>	
	</div>
	
		<div>
		<table class="yj_table">
			<tfoot>
				<tr>
					<td colspan="3">
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
					<th>REWEIGHT NAME</th>
					<th>REWEIGHT DATE</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${reweightList eq '[]' or reweightList eq null or reweightList eq '' }">
					<tr>
						<td colspan="4">Reweight가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="reweight" items="${reweightList }" varStatus="i"> 
					<tr>
						<td>${i.count }</td>
						<td>${reweight.reweightName }</td>
						<td>${reweight.reweightDate }</td>
						<td><img data-seq="${reweight.seq}" class="reweight_delete" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>