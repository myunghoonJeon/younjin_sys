<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="basicWrapper">
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>	
					<span class="basic_company_add yj_button" >add</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="yj_table company_table">
				<thead>
					<tr>
						<th>CODE</th>
						<th>NAME</th>
						<th>NAME ACRONYM</th>
						<th>FULL NAME</th>
						<th>ADDRESS</th>
						<th>PRESIDENT</th>
						<th>MANAGER</th>
						<th>TEL NO</th>
						<th>FAX NO</th>
						<th>E-MAIL ADDRESS</th>
						<th>사업자 등록 번호</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${companyList eq '[]' or companyList eq null or companyList eq '' }">
						<tr>
							<td colspan="11">company 가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="company" items="${companyList }">
						<tr data-seq="${company.seq }">
							<td>${company.companyCode }</td>
							<td>${company.companyName }</td>
							<td>${company.companyNameAcronym }</td>
							<td>${company.companyFullName}</td>
							<td>${company.address }</td>
							<td>${company.president }</td>
							<td>${company.manager }</td>
							<td>${company.telNo }</td>
							<td>${company.faxNo }</td>
							<td>${company.eMailAddress }</td>
							<td>${company.businessLicenceNo }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>	
<%@ include file="../../layout/foot.jspf"%>