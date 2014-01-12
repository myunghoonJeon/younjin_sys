<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>	
	<div class="title">
		<h1>CONTAINER</h1>
	</div>
	
	<div id="container_wrap">
		<table class="yj_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>STATUS</th>
					<th>COUNT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="container" items="${containerList }" varStatus="i">
					<tr class="container_list_tr" data-seq=${container.seq }>
						<td>${i.count }</td>
						<td>${container.status }</td>
						<td class="container_list_count">${container.count }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../layout/foot.jspf"%>