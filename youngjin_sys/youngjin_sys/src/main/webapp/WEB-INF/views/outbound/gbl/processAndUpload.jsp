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
		<div class="gbl_process">
			<ul>
				<li style="background-color: #4D8E58; ">GBL INPUT</li>
				<li style="background-color: ${process.preperation eq 0 ? '#FFD455' : 'white'};">GBL PREPERATION</li>
				<li style="background-color: ${process.preperation eq 1 and process.delivery eq 0 ? '#FFD455' : 'white' };">GBL DELIVERY</li>
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
					<tr>
						<td>1</td>						
						<td>GBL 관련 전체문서</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>2</td>
						<td>GBL 관련 추가문서</td>
						<td></td>
						<td></td>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<input type="button" value="Document Upload" />
							<input type="button" value="Print" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>