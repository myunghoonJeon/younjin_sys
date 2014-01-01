<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>
<title>Form 모음</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">

<script src="${cp}/resources/jquery/jquery-1.8.2.min.js"></script>
<script src="${cp}/resources/js/common.js"></script>

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
</script>

</head>
<body>
	<div id="form_pop_div">
		<div class="form_wrap">
			<table>
				<thead>
					<tr>
						<th id="form_no">NO</th>
						<th id="form_title">TITLE</th>
					</tr>
				</thead>
				<tbody>
					<tr id="form_inventory">
						<td>1</td>
						<td>INVENTORY</td>
					</tr>
					<tr id="form_hirisk_inventory">
						<td>2</td>
						<td>HIRISK INVENTORY</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>