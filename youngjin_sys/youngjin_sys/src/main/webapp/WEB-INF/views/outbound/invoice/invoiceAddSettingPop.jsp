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
<title>Add</title>

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
	<div class="invoice_add_setting_wrap">		
		<div class="pop_title_line">
			<span>INVOICE ADD SETTING</span>
		</div>
		
		<div>
			<table class="invoice_add_table">
				<thead>
					<tr>
						<th>TSP</th>
						<th>PUD</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<select id="invoice_add_select">
								<c:forEach var="tsp" items="${tspList }">
									<option value="${tsp.codeName }">	
										${tsp.codeName }
									</option>
								</c:forEach>					
							</select>
						</td>
						<td><input id="invoice_add_start_date" type="text" /> ~ <input id="invoice_add_end_date" type="text" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="invoice_add_submit_button_wrap">
								<span class="invoice_add_submit_button yj_button" >create</span>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>	
	</div>
</body>
</html>