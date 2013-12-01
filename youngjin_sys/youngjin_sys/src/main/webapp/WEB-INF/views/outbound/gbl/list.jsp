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
		<h1>GBL LIST</h1>
	</div>
	
	<div class="gbl_filter">	
		<form:form commandName="outboundFilter" method="get">
			<form:hidden path="page" value="${pagination.currentPage}"/>
		</form:form>
	</div>
	
	<div class="gbl_addButton user_addButton">
		<span >add</span>
	</div>
	
	<div>
		<table class="yj_table">
			<colgroup>
				<col width="5%" />
				<col width="8%" />
				<c:if test="${carrier eq null }">
					<col width="5%" />
				</c:if>
				<col width="12%" />
				<col width="5%" />
				<col width="25%" />
				<col width="5%" />
				<col width="5%" />
				<col width="5%" />
				<col width="10%" />
				<c:if test="${branch eq null }">
					<col width="5%" />
				</c:if>
				<col width="10%" />
			</colgroup>
			<tfoot>
				<tr>
					<td colspan="12">
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
					<th>CODE</th>
					<th>PUD</th>
					<c:if test="${carrier eq null }">
						<th>SCAC</th>
					</c:if>
					<th>GBL_NO</th>
					<th>RANK</th>
					<th>NAME</th>
					<th>PCS</th>
					<th>LBS</th>
					<th>CUFT</th>
					<th>US_NO</th>
					<c:if test="${branch eq null }">
						<th>BRANCH</th>
					</c:if>
					<th>DEST_PORT</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${gblList eq '[]' or gblList eq null or gblList eq '' }">
					<tr>
						<td colspan="10">GBL이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gbl" items="${gblList }">
					<tr class="gbl_list" data-gblNo="${gbl.seq }">
						<td>${gbl.code }</td>
						<td>${gbl.pud }</td>
						<c:if test="${carrier eq null }">
							<td>${gbl.scac }</td>
						</c:if>
						<td>${gbl.no }</td>
						<td>${gbl.rank }</td>
						<td>${gbl.customerName }</td>
						<td></td>
						<td></td>
						<td></td>
						<td>${gbl.usNo }</td>
						<c:if test="${branch eq null }">
							<td>${gbl.area }</td>
						</c:if>
						<td>${gbl.destPort }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>