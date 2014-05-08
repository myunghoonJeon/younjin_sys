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
	<div class="booking_addButton_wrap">
		<span class="booking_addButton yj_button" >add</span>
	</div>	
	
	<div>
		<table class="yj_table">
			<colgroup>
				<col width="5%">
				<col width="10%">
				<col width="40%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="5%">
			</colgroup>		
			<thead>
				<tr>
					<th>NO</th>
					<th>WRITE DATE</th>
					<th>GBL LIST</th>
					<th>POWER OF ATTORNY</th>
					<th>BOOKINGLIST</th>
					<th>DECLARATION</th>
					<th></th>
				</tr>
			</thead>
					
			<tbody>
				<c:if test="${bookingList eq '[]' or bookingList eq null }">
					<tr>
						<td colspan="7">등록된 정보가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="book" items="${bookingList }" varStatus="i">
					<tr data-bookSeq="${book.seq }">
						<td>${i.count }</td>
						<td>${book.writeDate }</td>
						<td style="font-size: 5pt;">${book.gblList }</td>
						<td><button  class="powerofattorny_list_content yj_button">print</button></td>
						<td><button  class="booking_list_content yj_button">print</button></td>
						<td><button  class="declaration_list_content yj_button">print</button></td>
						<td class="booking_deleteButton"><img src="${cp }/resources/images/gbl/memorandum_delete.png" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
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