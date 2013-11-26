<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
<div>
	<div class="title">
		<h1>All User Management</h1>
	</div>
	
	<div id="admin_user_addButton">
		<span >add</span>
	</div>
	
	<div id="admin_user_managementDiv">
		<table class="yj_table">
			<thead>
				<tr>
					<th>No</th>
					<th>Id</th>
					<th>Password</th>
					<th>First Name</th>
					<th>Family Name</th>
					<th>Auth</th>
					<th>Area</th>
					<th>Enable</th>
					<th>Last Update Date</th>
					<th>Last Update User</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allUserList eq '[]' || allUserList eq null }">
					<tr id="yj_user_none">
						<td colspan="11">등록된 사용자가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="user" items="${allUserList }" varStatus="i">
					<tr class="admin_user_list_tr" data-seq=${user.seq }>
						<td class="admin_user_list_no">${i.count }</td>
						<td class="admin_user_list_username">${user.username }</td>
						<td class="admin_user_list_password">${user.password }</td>
						<td class="admin_user_list_firstName">${user.firstName }</td>
						<td class="admin_user_list_familyName">${user.familyName }</td>
						<td class="admin_user_list_auth">${user.authStr }</td>
						<td class="admin_user_list_area">${user.area }</td>
						<td class="admin_user_list_enabled">${user.enabled }</td>
						<td>${user.lastUpdate }</td>
						<td>${user.lastUpdateBy }</td>
						<td class="admin_user_list_delete"><a href="#">x</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>
<%@ include file="../../layout/foot.jspf"%>