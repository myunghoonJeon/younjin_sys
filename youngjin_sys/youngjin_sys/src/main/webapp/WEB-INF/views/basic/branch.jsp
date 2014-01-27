<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>	
	<div class="basicWrapper">
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>	
					<span class="basic_branch_add yj_button" >add</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="yj_table branch_table">
				<thead>
					<tr>
						<th>ACRONYM</th>
						<th>BRANCH</th>
						<th>MANAGER</th>
						<th>TEL</th>
						<th>FAX</th>
						<th>EMAIL</th>
						<th>ITO TEL</th>
						<th>ITO DSN</th>
						<th>ITO CHIEF</th>
						<th>ITO ADDRESS</th>
						<th>D GBLOCK</th>
						<th>DODAC</th>			
					</tr>
				</thead>
				<tbody>
					<c:if test="${branchList eq '[]' or branchList eq null or branchList eq '' }">
						<tr>
							<td colspan="14">BRANCH 가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="branch" items="${branchList }">
						<tr data-seq="${branch.seq }">
							<td>${branch.branchAcronym }</td>
							<td>${branch.branch }</td>
							<td>${branch.branchManager }</td>
							<td>${branch.telNo}</td>
							<td>${branch.faxNo }</td>
							<td>${branch.eMailAddress }</td>
							<td>${branch.itoTelNo }</td>
							<td>${branch.itoDsnNo }</td>
							<td>${branch.itoChief }</td>
							<td>${branch.itoAddress }</td>
							<td>${branch.dGbloc }</td>
							<td>${branch.dodac }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>	
<%@ include file="../../layout/foot.jspf"%>