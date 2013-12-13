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
<title>Weight Certificate</title>

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
	<div class="weightcertificate_head">
		<h1>YOUNGJIN TRADE & TRAN. CO. LTD.</h1>
	</div>
	<div id="weightcertificate_sub_head">
		<p>RM 901 Palase Bldg Seocho 4 dong, Seocho Gu, Seoul, Korea (82-2) 825-6345</p>
		<p>E-mail : youngjin@yttc.co.kr								 (82-2) 825-6344</p>
	</div>
	<div id="weightcertificate_write" data-seq="${seq }">
		<span class="yj_button ${(weightcertificateList ne '[]') ? 'weightcertificate_update' : 'weightcertificate_write' }">${(weightcertificateList ne '[]') ? 'update' : 'write' }</span>
	</div>
	<div id="weightcertificate_wrap">
		<h1 id="weightcertificate_title">CERTIFIED WEIGHT TICKET</h1>
		<div class="weightcertificate_content">
			<div class="weightcertificate_base_info">
				<ul>
					<li class="weightcertificate_column1">RANK / NAME OF OWNER</li>				
					<li class="weightcertificate_column2"><input type="text" value="${gbl.rank } ${gbl.customerName}" /></li>
					<li class="weightcertificate_column3">DATE</li>				
					<li class="weightcertificate_column4"><input id="weightcertificate_date" type="text" /></li>
				</ul>
		
				<ul>
					<li class="weightcertificate_column1">ORIGIN</li>				
					<li class="weightcertificate_column2"><input type="text" /></li>
					<li class="weightcertificate_column3">GBL NO</li>				
					<li class="weightcertificate_column4"><input type="text" value="${gbl.no }" /></li>
				</ul>
		
				<ul>
					<li class="weightcertificate_column1">DESTINATION</li>				
					<li class="weightcertificate_column2"><input type="text" /></li>
					<li class="weightcertificate_column3">CODE</li>				
					<li class="weightcertificate_column4"><input type="text" value="${gbl.code }" /></li>
				</ul>

				<ul>
					<li class="weightcertificate_column1">CARRIER</li>				
					<li class="weightcertificate_column2"><input type="text" value="${gbl.scac }" /></li>
					<li class="weightcertificate_column3">RDD</li>				
					<li class="weightcertificate_column4"><input type="text" value="${gbl.rdd }" /></li>
				</ul>
			</div>
			<div id="weightcertificate_add_button" data-count="0">
				<span class="yj_button weightcertificate_add">add</span>
			</div>
			<div class="weightcertificate_table_wrap">
				<form id="weightcertificate_form" name="weightcertificate_form">
					<table>
						<colgroup>
							<col width="12%">
							<col width="9%">
							<col width="12%">
							<col width="10%">
							<col width="13%">
							<col width="12%">
							<col width="12%">
							<col width="20%">
						</colgroup>
						<thead>
							<tr>
								<th>PIECE</th>
								<th>TYPE</th>
								<th>STATUS</th>
								<th>GROSS</th>
								<th>TARE</th>
								<th>NET</th>
								<th>CUFT</th>
								<th>REMARK</th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach var="weightcertificate" items="${weightcertificateList }">
								<tr>	
									<td>${weightcertificate.piece }</td>
									<td>${weightcertificate.type }</td>
									<td>${weightcertificate.status }</td>
									<td>${weightcertificate.gross }</td>
									<td>${weightcertificate.tare }</td>
									<td>${weightcertificate.net }</td>
									<td>${weightcertificate.cuft }</td>
									<td>${weightcertificate.remark }</td>
								</tr>
							</c:forEach>				
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">/x/x/x/x/x/xx/x/x/x/x/x/x/x/x/x/LAST ITEMx/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/</td>
							</tr>	
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>							
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td id="weightcertificate_progear"><input type="text" ></td>
								<td id="weightcertificate_seal_no"><input type="text" ></td>
								<td id="weightcertificate_lbs"><input type="text" ></td>
								<td></td>
								<td></td>							
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>							
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>							
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>							
							</tr>
						</tfoot>					
					</table>
				</form>
			</div>
			<div class="weightcertificate_foot">
				
			</div>
		</div>
	</div>
</body>
</html>