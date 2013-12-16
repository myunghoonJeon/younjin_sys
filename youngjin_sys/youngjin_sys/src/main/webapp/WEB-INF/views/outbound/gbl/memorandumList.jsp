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
<link rel="stylesheet" href="${cp }/resources/css/font.css">
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
		<div>
			<table class="memorandum_table" data-seq="${gbl.seq }">
				<thead>
					<tr>
						<th>type</th>
						<th>check</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="memorandum" items="${memorandumList }">
						<tr>
							<td class="memorandum_name">
								${memorandum.codeName } 
								<c:if test="${memorandum.subCode eq '02' }">
									<input type="text" value="${articles ne null ? articles : '' }"/>
								</c:if>
							</td>
							<td class="memorandum_type">
								<c:choose>
									<c:when test="${checkMemorandumMap ne '{}' }">
										<input type="checkbox" value="${memorandum.subCode }" ${checkMemorandumMap[memorandum.subCode] ne null ? 'checked=checked' : '' } />
									</c:when>
									<c:otherwise>
										<input type="checkbox" value="${memorandum.subCode }" />
									</c:otherwise>
								</c:choose>
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