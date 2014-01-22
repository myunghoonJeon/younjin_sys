<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="basicWrapper">
		<div class="gbl_filter">	
			<ul class="freight_filter_wrap">
				<li>	
					<span class="basic_pod_add yj_button" >add</span>
				</li>
			</ul>
		</div>
		
		<div>
			<table class="yj_table pod_table">
				<thead>
					<tr>
						<th>ACRONYM</th>
						<th>FULL</th>
						<th>DEST ADDRESS</th>
						<th>AGENT NAME</th>
						<th>REMARKS</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${podList eq '[]' or podList eq null or podList eq '' }">
						<tr>
							<td colspan="5">POD 가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="pod" items="${podList }">
						<tr data-seq="${pod.seq }">
							<td>${pod.podAcronym }</td>
							<td>${pod.podFull }</td>
							<td>${pod.destAddress }</td>
							<td>${pod.podAgentName}</td>
							<td>${pod.remark }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
	</div>	
<%@ include file="../../layout/foot.jspf"%>