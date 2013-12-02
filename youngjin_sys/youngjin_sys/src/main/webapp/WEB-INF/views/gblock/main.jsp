<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="title">
		<h1>GBLOCK</h1>
	</div>
	
	<div class="gblock_addButton user_addButton">
		<span >add</span>
	</div>
	
	<div>
		<table class="yj_table">
			<colgroup>
				<col width="15%">
				<col width="10%">
				<col width="10%">
				<col width="65%">
			</colgroup>
			<thead>
				<tr>
					<th>DODAAC</th>
					<th>GBLOC</th>
					<th>US NO</th>
					<th>REMARK</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${gblockList eq '[]' or gblockList eq null or gblockList eq '' }">
					<tr>
						<td colspan="5">GBLOCK 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gblock" items="${gblockList }">
					<tr data-seq="${gblock.seq }">
						<td class="gblock_dodaac">${gblock.dodaac }</td>
						<td class="gblock_gblock">${gblock.gblock }</td>
						<td class="gblock_usNo">${gblock.usNo }</td>
						<td class="gblock_remark">${gblock.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../../layout/foot.jspf"%>