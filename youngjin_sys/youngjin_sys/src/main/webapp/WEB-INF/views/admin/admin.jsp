<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
<div>
	<div class="title">
		<h1>All User Management</h1>
	</div>
	
	<div class="admin_user_addButton user_addButton">
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
						<td class="admin_user_list_password">${fn:substring(user.password, 0, 10) }...</td>
						<td class="admin_user_list_firstName">${user.firstName }</td>
						<td class="admin_user_list_familyName">${user.familyName }</td>
						<td class="admin_user_list_auth">
							<select class="admin_user_auth_list_select">
								<c:forEach var="auth" items="${authList }">
									<option value="${auth.subCode }" ${auth.subCode == user.auth ? 'selected=selected' : '' }>${auth.codeName }</option>
								</c:forEach>
							</select>
						</td>
						<td class="admin_user_list_area">
							<select class="admin_user_area_list_select">
								<c:forEach var="area" items="${areaList }">
									<option value="${area.subCode }" ${area.subCode == user.area ? 'selected=selected' : '' }>${area.codeName }</option>
								</c:forEach>
							</select>						
						</td>
						<td class="admin_user_list_enabled">
							<select class="admin_user_enabled">
								<option value="0" ${user.enabled eq 'false' ? 'selected=selected' : '' }>false</option>
								<option value="1" ${user.enabled eq 'true' ? 'selected=selected' : '' }>true</option>
							</select>
						</td>
						<td class="admin_user_list_lastUpdate">${user.lastUpdate }</td>
						<td class="admin_user_list_lastUpdateBy">${user.lastUpdateBy }</td>
						<td class="admin_user_list_delete"><a href="#">x</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>
<%@ include file="../../layout/foot.jspf"%>