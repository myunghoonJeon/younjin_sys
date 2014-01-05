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
	<div>
		<table class="yj_table">
			<colgroup>
				<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null }">
					<col width="5%" />
				</c:if>
				<col width="10%"/>
				<col width="8%" />
				<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
					<col width="5%" />
				</c:if>
				<col width="12%" />
				<col width="5%" />
				<col width="25%" />
				<col width="5%" />
				<col width="5%" />
				<col width="5%" />
				<col width="10%" />
				<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
					<col width="5%" />
				</c:if>
				<col width="10%" />
			</colgroup>
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
					<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null}">
						<th>CODE</th>
					</c:if>
					<th>STATUS</th>
					<th>PUD</th>
					<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
						<th>SCAC</th>
					</c:if>
					<th>GBL_NO</th>
					<th>RANK</th>
					<th>NAME</th>
					<th>PCS</th>
					<th>LBS</th>
					<th>CUFT</th>
					<th>US_NO</th>
					<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
						<th>BRANCH</th>
					</c:if>
					<th>DEST_PORT</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${gblList eq '[]' or gblList eq null or gblList eq '' }">
					<tr>
						<td colspan="13">GBL이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gbl" items="${gblList }">
					<fmt:parseDate var="parsePud" value="${gbl.pud}" pattern="yyyyMMdd"/>
					<c:set var="pud" value="${parsePud }" />
					<tr class="gbl_list" data-seq="${gbl.seq }">
						<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null }">
							<td>${gbl.code }</td>
						</c:if>
						<td>${gblStatus[gbl.no] }</td>
						<td>
							${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }
						</td>
						<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
							<td>${gbl.scac }</td>
						</c:if>
						<td>${gbl.no }</td>
						<td>${gbl.rank }</td>
						<td>${gbl.customerName }</td>
						<td>${gbl.pcs }</td>
						<td>${gbl.lbs }</td>
						<td>${gbl.cuft }</td>
						<td>${gbl.usNo }</td>
						<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
							<td>${gbl.areaLocal }</td>
						</c:if>
						<td>${gbl.destPort }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>