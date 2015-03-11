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

	<c:set var="branchList" value="${filterMap['branchList'] }" />
	<c:set var="carrierList" value="${filterMap['carrierList'] }" />
	<c:set var="codeList" value="${filterMap['codeList'] }" />
	
	<div class="gbl_filter">	
		<ul>
			<form:form commandName="outboundFilter" method="get">
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
						<form:option value="gblNo">GBL NO</form:option>
						<form:option value="name">NAME</form:option>
						<form:option value="ssn">SSN</form:option>
					</form:select>
					<form:input path="searchContent"/>
					<span class="outbound_search yj_button" >search</span>
				</li>
				<li>
						<form:hidden path="page" value="${pagination.currentPage}"/>
					</li>
				<li>	
					<span class="gbl_addButton yj_button" >add</span>
				</li>
			</form:form>
		</ul>	
	</div>
	
	<div class="gbl_list_filter_title">
		<ul>
			<c:if test="${outboundFilter.branch ne '' and outboundFilter.branch ne null}">
				<li>[BRANCH : ${outboundFilter.branch  }] </li>
			</c:if>
			<c:if test="${outboundFilter.carrier ne '' and outboundFilter.carrier ne null}">
				<li>[CARRIER : ${outboundFilter.carrier }] </li>
			</c:if>
			<c:if test="${outboundFilter.code ne '' and outboundFilter.code ne null}">
				<li>[CODE : ${outboundFilter.code }]</li>
			</c:if>
		</ul>		
	</div>
	
	<div>
		<table class="yj_table">
		<colgroup>
				<col width="2%"/>
				<col width="5%"/>
				<col width="3%"/>
				<col width="3%"/>
				<col width="10%"/>
				<col width="2%"/>
				<col width="10%"/>
				<col width="2%"/>
				<col width="3%"/>
				<col width="3%"/>
				<col width="3%"/>
				<col width="10%"/>
				<col width="3%"/>
				<col width="3%"/>
				<col width="10%"/>
				<col width=""/>
			</colgroup>
			<tfoot>
				<tr>
					<td colspan="16">
						<a href="javascript:void(goToPage(1))">FIRST</a>
						<a href="javascript:void(goToPreviousPages())">PREV</a>
						<c:forEach var="i" begin="${pagination.pageBegin}" end="${pagination.pageEnd}">
							<c:if test="${i == pagination.currentPage}">
								<a style="font-weight: bold; font-size: 13pt; color: red;" class="page_now">${i}</a>
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
					<th>STATUS</th>
					<th>PUD</th>
					<th>SCAC</th>
					<th>GBL NO</th>
					<th>RANK</th>
					<th>NAME</th>
					<th>PCS</th>
					<th>LBS</th>
					<th>CUFT</th>
					<th>US_NO</th>
					<th>BRANCH</th>
					<th>ETD</th>
					<th>ETA</th>
					<th>BL NO1</th>
					<th>POD</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${gblList eq '[]' or gblList eq null or gblList eq '' }">
					<tr>
						<td colspan="16">GBL이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gbl" items="${gblList }">
					<c:choose>
						<c:when test="${fn:length(gbl.pud) eq 8 }">
							<fmt:parseDate var="parsePud" value="${gbl.pud}" pattern="yyyyMMdd"/>
							<c:set var="pud" value="${parsePud }" />
						</c:when>
						<c:otherwise>
							<c:set var="pud" value="${gbl.pud }"/>
						</c:otherwise>
					</c:choose>
					<tr class="gbl_list" data-seq="${gbl.seq }">
						<c:if test="${outboundFilter.code eq '' or outboundFilter.code eq null }">
							<td>${gbl.code }</td>
						</c:if>
						<td>${gblStatus[gbl.no] }</td>
						<td>
							${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }
						</td>
						<c:if test="${outboundFilter.carrier eq '' or outboundFilter.carrier eq null }">
							<td>${gbl.scac }</td>
						</c:if>
						<td>${gbl.no }</td>
						<td>${gbl.rank }</td>
						<td>${gbl.customerName }</td>
						<td>${gbl.pcs }</td>
						<c:choose>
							<c:when test="${gbl.code eq '3' or gbl.code eq '4' or gbl.code eq '5' or gbl.code eq 'T'}">
								<td>${gbl.netWeight }</td>
							</c:when>
							<c:otherwise>
								<td>${gbl.lbs }</td>
							</c:otherwise>
						</c:choose>
						
						<td>${gbl.cuft }</td>
						<td>${gbl.usNo }</td>
						<c:if test="${outboundFilter.branch eq '' or outboundFilter.branch eq null }">
							<td>${gbl.areaLocal }</td>
						</c:if>
						<td>${gbl.etd }</td>
						<td>${gbl.eta }</td>
						<td>${gbl.blNo }</td>
						<td>${gbl.pod }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
<%@ include file="../../../layout/foot.jspf"%>