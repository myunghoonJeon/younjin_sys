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
	
	<div class="delivery_addButton_wrap">
		<span class="delivery_addButton yj_button" >add</span>
	</div>	
	
	<div>
		<table class="yj_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>CONTAINER COUNT</th>
					<th>CONTAINER TYPE</th>
					<th>TRUCKING DATE</th>
					<th>BOOKING DATE</th>
					<th>PORT AGENT</th>
					<th>POD</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${deliveryList eq '[]' or deliveryList eq null }">
					<tr>
						<td colspan="7">등록된 정보가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>	
	
<%@ include file="../../../layout/foot.jspf"%>