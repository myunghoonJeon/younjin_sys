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
	
	<div class="gbl_filter">	
		<ul class="freight_filter_wrap">
			<li>	
				<span class="onHand_onHandList_add yj_button" >add</span>
			</li>
		</ul>	
	</div>
	<div>
		<table class="yj_table on_hand_list_table">
			<tfoot>
				<tr>
					<td colspan="13">
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
					<th>ON HAND DATE</th>
					<th>FIRST ARRIVALABLE DELIVER DATE</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${onHandList eq '[]' or onHandList eq null or onHandList eq '' }">
					<tr>
						<td colspan="14">onHandList 가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="onHand" items="${onHandList }" varStatus="i">
					<tr data-seq="${onHand.seq }">
						<td>${i.count }</td>
						<td>${onHand.onHandDate }</td>
						<td>${onHand.firstArrivalableDeliverDate }</td>
						<td><img class="on_hand_list_delete" src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>