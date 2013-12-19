<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
	<div class="title">
		<h1>RATE</h1>
	</div>
	
	<div class="save_button_wrap">
		<span class="save_button yj_button" onclick="alert('저장되었습니다!');" >save</span>
	</div>	
	
	<div>
		<table class="yj_table">
			<thead>
				<tr>
					<th>NO</th>
					<th>TYPE</th>
					<th>NAME</th>
					<th>RATE</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>OUTBOUND</td>
					<td>GBL Rate</td>
					<td><input type="text" value="8" />%</td>				
				</tr>
				<tr>
					<td>2</td>
					<td>OUTBOUND</td>
					<td>Rate</td>
					<td><input type="text" value="3" />%</td>				
				</tr>
				<tr>
					<td>2</td>
					<td>OUTBOUND</td>
					<td>comprate1</td>
					<td><input type="text" value="4" />%</td>				
				</tr>
			
			<%-- 
				<c:if test="${gblockList eq '[]' or gblockList eq null or gblockList eq '' }">
					<tr>
						<td colspan="5">rate 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="gblock" items="${gblockList }">
					<tr data-seq="${gblock.seq }">
						<td class="gblock_dodaac">${gblock.dodaac }</td>
						<td class="gblock_gblock">${gblock.gblock }</td>
						<td class="gblock_usNo">${gblock.usNo }</td>
					</tr>
				</c:forEach> --%>
			</tbody>
		</table>
	</div>
<%@ include file="../../layout/foot.jspf"%>