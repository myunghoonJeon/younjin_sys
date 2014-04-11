<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>
<title>Memorandum</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">
<link rel="stylesheet" href="${cp }/resources/jquery/jquery-ui-1.10.3.custom.min.css">

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
	var addError = false;
	if (typeof youngjin == 'undefined') {
		youngjin = {};
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="memorandum_wrap">
		<div class="pop_title_line">
			<span>MEMORANDUM</span>
		</div>	
		<div class="yj_button_wrap">
			<ul class="yj_button_list">
				<li>
					<span class="yj_button memo_back_button">back</span>
				</li>
			</ul>
		</div>
		<div>
			<table class="memorandum_table" data-seq="${gbl.seq }" data-memorandumSeq="${memorandumSeq }">
				<thead>
					<tr>
						<th></th>
						<th>type</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="memorandum" items="${memorandumList }">
						<tr>
							<td class="memorandum_type">
								<c:choose>
									<c:when test="${checkMemorandumMap ne '{}' }">
										<input type="checkbox" value="${memorandum.subCode }" ${checkMemorandumMap[memorandum.subCode] ne null ? 'checked=checked' : '' } disabled="disabled" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" value="${memorandum.subCode }" disabled="disabled" />
									</c:otherwise>
								</c:choose>
							</td>
							<td class="memorandum_name">
								${memorandum.codeName } 
								<c:choose>
									<c:when test="${memorandum.subCode eq '02' }">
										<input type="text" value="${articles ne null ? articles : '' }"/>
									</c:when>
									
									<c:when test="${memorandum.subCode eq '04' }">
										 : <input type="text" value="${checkMemorandumMap['04'].extraPickUpCharge }"/>
									</c:when>
									<c:when test="${memorandum.subCode eq '05' }">
										 : <input type="text" value="${checkMemorandumMap['05'].sitNo }"/>
									</c:when>
									<c:when test="${memorandum.subCode eq '06' }">
										 : <input type="text" value="${checkMemorandumMap['06'].sitStartDate }"/>
									</c:when>
									<c:when test="${memorandum.subCode eq '07' }">
										 : <input type="text" value="${checkMemorandumMap['07'].sitEndDate }"/>
									</c:when>
									<c:when test="${memorandum.subCode eq '08' }">
										 : <input type="text" value="${checkMemorandumMap['08'].longCarry }"/>
									</c:when>
									<c:when test="${memorandum.subCode eq '09' }">
										 : <input type="text" value="${checkMemorandumMap['09'].termination }"/>
									</c:when>
								</c:choose>
							</td>
							<td class="memorandum_icon_wrap">
								<ul>
									<c:if test="${checkMemorandumMap[memorandum.subCode] eq null}">
										<li><img class="memorandum_icon memorandum_input_subButton" src="${cp }/resources/images/gbl/memorandum_add.png" /></li>
									</c:if>
									<c:if test="${checkMemorandumMap[memorandum.subCode] ne null}">
										<li><img class="memorandum_icon memorandum_modify_subButton" src="${cp }/resources/images/gbl/memorandum_edit.png" /></li>
									</c:if>
									<c:if test="${checkMemorandumMap[memorandum.subCode] ne null and memorandum.subCode ne '04' and memorandum.subCode ne '05' and memorandum.subCode ne '06' and memorandum.subCode ne '07' and memorandum.subCode ne '08' and memorandum.subCode ne '09'}">
										<li><img class="memorandum_icon meomorandum_icon_print" src="${cp }/resources/images/print-icon.png" /></li>
									</c:if>
									<c:if test="${checkMemorandumMap[memorandum.subCode] ne null}">
										<li><img class="memorandum_icon memorandum_delete_subButton" src="${cp }/resources/images/gbl/memorandum_delete.png" /></li>
									</c:if>
								</ul>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>		
		<div class="memorandum_complete">
			<span class="yj_button" >complete</span>
		</div>
	</div>	
</body>
</html>