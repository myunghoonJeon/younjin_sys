<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../layout/head.jspf"%>
<%-- Page 처리 Script  inbound--%>
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
	
	<c:set var="branchList" value="${filterMap['branchList'] }" />
	<c:set var="carrierList" value="${filterMap['carrierList'] }" />
	<c:set var="codeList" value="${filterMap['codeList'] }" />
	
	<div class="gbl_filter">	
		<ul class="freight_filter_wrap">
			<form:form commandName="inboundFilter" method="get">
				<sec:authorize access="hasRole('ROLE_LEVEL4') or hasRole('ROLE_LEVEL3') or hasRole('ROLE_LEVEL2')">
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
					<form:select path="searchTitle">
						<form:option value="name">NAME</form:option>
						<form:option value="ssn">SSN</form:option>
						<form:option value="gblNo">GBL NO</form:option>
					</form:select>
					<form:input path="searchContent"/>
					<span class="inbound_search yj_button" >search</span>
				</li>
				<li>
					<form:hidden path="page" value="${pagination.currentPage}"/>
				</li>
				<li>	
					<span class="freight_add yj_button" >add</span>
				</li>
			</form:form>
		</ul>	
	</div>

	<div class="gbl_list_filter_title">
		<ul>
			<c:if test="${inboundFilter.branch ne '' and inboundFilter.branch ne null}">
				<li>[BRANCH : ${inboundFilter.branch  }] </li>
			</c:if>
			<c:if test="${inboundFilter.carrier ne '' and inboundFilter.carrier ne null}">
				<li>[CARRIER : ${inboundFilter.carrier }] </li>
			</c:if>
			<c:if test="${inboundFilter.code ne '' and inboundFilter.code ne null}">
				<li>[CODE : ${inboundFilter.code }]</li>
			</c:if>
		</ul> 	
	</div>	
	
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
						<th>TSP</th>
					</c:if>
					<th>GBL_NO</th>
					<th>RANK</th>
					<th>NAME</th>
					<th>PCS</th>
					<th>LBS</th>
					<th>ETA</th>
					<th>CUFT</th>
					<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
						<th>BRANCH</th>
					</c:if>
					<th>ON HAND</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${gblList eq '[]' or gblList eq null or gblList eq '' }">
					<tr>
						<td colspan="14">GBL이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gbl" items="${gblList }">
					<%-- <fmt:parseDate var="parsePud" value="${gbl.pud}" pattern="yyyyMMdd"/>
					<c:set var="pud" value="${parsePud }" /> --%>
					<tr class="freight_list" data-seq="${gbl.seq }">
						<c:if test="${inboundFilter.code eq '' or inboundFilter.code eq null }">
							<td>${gbl.code }</td>
						</c:if>
						<td>${gblStatus[gbl.gblNo] }</td>
						<td>
							${gbl.pud }
						</td>
						<c:if test="${inboundFilter.carrier eq '' or inboundFilter.carrier eq null }">
							<td>${gbl.tsp }</td>
						</c:if>
						<td>${gbl.gblNo }</td>
						<td>${gbl.rank }</td>
						<td>${gbl.shipperName }</td>
						<td>${gbl.totalPcs }</td>
						<c:choose>
							<c:when test="${gbl.code eq '3' or gbl.code eq '4' or gbl.code eq '5' or gbl.code eq 'T'}">
								<td>${gbl.netWeight }</td>
							</c:when>
							<c:otherwise>
								<td>${gbl.grossWeight }</td>
							</c:otherwise>
						</c:choose>
						<td>${gbl.eta }</td>
						<td>${gbl.cuft }</td>
						<c:if test="${inboundFilter.branch eq '' or inboundFilter.branch eq null }">
							<td>${gbl.areaLocal }</td>
						</c:if>
						<td>${gbl.onHandDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>