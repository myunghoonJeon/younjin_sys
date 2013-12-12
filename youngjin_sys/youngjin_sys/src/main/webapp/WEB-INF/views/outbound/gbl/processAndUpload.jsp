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
<title>Select</title>

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
	<div class="gbl_process_and_upload_div">
		<div class="gbl_process" data-seq="${seq}">
			<ul>
				<li style="background-color: #4D8E58; ">GBL INPUT</li>
				<li class="gbl_process_preperation" style="background-color: ${process.preperation eq 0 ? '#FFD455' : 'white'};">GBL PREPERATION</li>
				<li class="gbl_process_delivery"style="background-color: ${process.preperation eq 1 and process.delivery eq 0 ? '#FFD455' : 'white' };">GBL DELIVERY</li>
				<li style="background-color: ${process.delivery eq 1 and process.invoice eq 0 ? '#FFD455' : 'white' };">GBL INVOICE</li>
			</ul>
		</div>
		<div class="gbl_upload">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="60%" />
					<col width="20%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>TITLE</th>
						<th>DATE</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fileList eq '[]' }">
						<tr>
							<td colspan="4">등록된 파일이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="file" items="${fileList }" varStatus="i">
						<c:choose>
							<c:when test="${file.gblFileNo eq 1 }">
								<tr class="selectFileTr" data-no="1">
									<td>${i.count }</td>						
									<td>GBL 관련 전체문서</td>
									<td>${file.gblUpdateDate }</td>
									<td><input type="radio" value="${file.seq }" name="selectFileList"></td>
								</tr>
							</c:when>
							<c:when test="${file.gblFileNo eq 2 }">
								<tr class="selectFileTr" data-no="2">
									<td>${i.count }</td>
									<td>GBL 관련 추가문서</td>
									<td>${file.gblUpdateDate }</td>
									<td><input type="radio" value="${file.seq }" name="selectFileList"></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td id="upload_tfoot" colspan="4" data-seq="${seq }">
							<input class="document_upload_button" type="button" value="Document Upload" />
							<input class="document_view" type="button" value="Document Download" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>