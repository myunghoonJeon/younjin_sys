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

	<div class="tcmd_add_button_wrap">
		<span class="tcmd_add_button yj_button" >add</span>
	</div>	
	
	<div>
		<table class="yj_table tcmd_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>WRITE DATE</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${ tcmdList eq '[]' }">
					<tr>
						<td colspan="5">등록된 tcmd가 없습니다.</td>
					</tr>
				</c:if>
				<c:set var="index" value="1" />
				<c:set var="length" value="${fn:length(tcmdList)}" />
				<c:forEach var="tcmd" items="${tcmdList }" varStatus="i">
					<c:choose>
						<c:when test="${length ne i.count and tcmdList[i.index + 1].oneTcmdFlag eq tcmd.oneTcmdFlag}">
							<c:set var="nextCheck" value="R" />
						</c:when>
						<c:when test="${length eq 1 }">
							<c:set var="nextCheck" value="O" />
						</c:when>
						<c:when test="${length ne i.count and tcmdList[i.index + 1].oneTcmdFlag ne tcmd.oneTcmdFlag and nextCheck ne 'N'}">
							<c:set var="nextCheck" value="O" />
						</c:when>
						<c:when test="${length eq i.count and nextCheck ne 'N'}">
							<c:set var="nextCheck" value="O" />
						</c:when>
					</c:choose>
					<tr data-seq="${tcmd.seq }">
						<c:if test="${nextCheck eq'N' }">
							<c:set var="nextCheck" value="T" />
						</c:if>
						<c:if test="${nextCheck eq 'R' }" >
							<td rowspan="2">${index }</td>
							<c:set var="index" value="${index + 1 }" />
							<c:set var="nextCheck" value="N"/>
						</c:if>	
						<c:if test="${nextCheck eq 'O' }">
							<td>${index }</td>
							<c:set var="index" value="${index + 1 }" />
						</c:if>						
						<td>${tcmd.writeDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../../layout/foot.jspf"%>