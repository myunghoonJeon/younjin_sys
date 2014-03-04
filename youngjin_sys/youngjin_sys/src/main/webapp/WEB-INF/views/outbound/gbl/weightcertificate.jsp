<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>
<title>Weight Certificate</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/common.css">
<link rel="stylesheet" href="${cp }/resources/jquery/jquery-ui-1.10.3.custom.min.css">

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
	var containerList = new Array();

	'<c:forEach var="container" items="${containerList}"  varStatus = "i">';
		var container = {
			'status' : '<c:out value="${container.status}"/>',
			'count' : '<c:out value="${container.count}" />',
			'remark' : '<c:out value="${container.remark}" />'
		};

		containerList.push(container);
	'</c:forEach>';	

	var addError = false;
	if (typeof youngjin == 'undefined') {
		youngjin = {};
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="pop_title_line">
		<span>WEIGHT CERTIFICATE</span>
	</div>
	<div id="weightcertificate_form_wrap">		
		<div class="weight_button_wrap">
			<ul class="weight_button_list" data-seq="${gbl.seq }">
				<li>
					<span class="yj_button weight_certificate_back">back</span>
				</li>
				<li>
					<span class="yj_button weightcertificate_write">${(weightcertificateList ne '[]') ? 'modify' : 'write' }</span>
				</li>
				<li>
					<span class="yj_button weightcertificate_print">print</span>
				</li>
			</ul>
		</div>

		<div class="weightcertificate_head">
			<h1>YOUNGJIN TRADE & TRAN. CO. LTD.</h1>
		</div>
		<div id="weightcertificate_sub_head">
			<p>RM 901 Palase Bldg Seocho 4 dong, Seocho Gu, Seoul, Korea (82-2) 825-6345</p>
			<p>E-mail : youngjin@yttc.co.kr								 (82-2) 825-6344</p>
		</div>
		<div id="weightcertificate_wrap">
			<h1 id="weightcertificate_title">CERTIFIED WEIGHT TICKET</h1>
			<div class="weightcertificate_content">
				<div class="weightcertificate_base_info">
					<ul>
						<li class="weightcertificate_column1">RANK / NAME OF OWNER</li>				
						<li class="weightcertificate_column2"><input type="text" value="${gbl.rank } ${gbl.customerName}" /></li>
						<li class="weightcertificate_column3">DATE</li>				
						<li class="weightcertificate_column4"><input id="weightcertificate_date" type="text" value="${weightcertificateList[0].date }" /></li>
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
				<div class="weightcertificate_table_wrap" data-count="${fn:length(weightcertificateList) eq 0 ? 0 : fn:length(weightcertificateList) }">
					<form id="weightcertificate_form" name="weightcertificate_form">
						<table>
							<colgroup>
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="9%">
								<col width="9%">
								<col width="9%">
								<col width="9%">
								<col width="9%">
								<col width="25%">
							</colgroup>
							<thead>
								<tr>
									<th rowspan="2">PIECE</th>
									<th rowspan="2">TYPE</th>
									<th rowspan="2">STATUS</th>
									<th colspan="2">GROSS</th>
									<th rowspan="2">TARE</th>
									<th rowspan="2">NET</th>
									<th rowspan="2">CUFT</th>
									<th rowspan="2">REMARK</th>
								</tr>
								<tr>
									<th>LBS</th>
									<th>KG</th>
								</tr>
							</thead>
							<tbody>	

								<c:set var="totalPcs" value="0" />
								<c:set var="totalGross" value="0" />
								<c:set var="totalGrossKg" value="0" />
								<c:set var="totalTare" value="0" />
								<c:set var="totalNet" value="0" />
								<c:set var="totalCuft" value="0" />

								<c:forEach var="weightcertificate" items="${weightcertificateList }" varStatus="i">
									<c:set var="totalPcs" value="${totalPcs + 1 }" />
									<c:set var="totalGross" value="${totalGross + weightcertificate.gross}" />
									<c:set var="totalGrossKg" value="${totalGrossKg + weightcertificate.grossKg}" />
									<c:set var="totalTare" value="${totalTare + weightcertificate.tare }" />
									<c:set var="totalNet" value="${totalNet + weightcertificate.net }" />
									<c:set var="totalCuft" value="${totalCuft + weightcertificate.cuft }" />
									<tr data-weightSeq="${weightcertificate.seq }">	
										<td class="piece_td"><input name="piece" type="text" value="${weightcertificate.piece }" /></td>
										<td class="type_td"><input name="type" type="text" value="${weightcertificate.type }"/></td>
										<td class="status_td">
											<select name="status">
												<option value=""></option>
												<c:forEach var="container" items="${containerList }">
													<option data-count="${container.count }" value="${container.status }" ${weightcertificate.status eq container.status ? 'selected=selected' : ''}>${container.status }</option>																											
												</c:forEach>
											</select>
										</td>
										<td class="gross_td"><input name="gross" type="text" value="${weightcertificate.gross }"/></td>
										<td class="gross_td"><input name="grossKg" type="text" value="${weightcertificate.grossKg }" readonly="readonly" /></td>
										<td class="tare_td"><input name="tare" type="text" value="${weightcertificate.tare }" /></td>
										<td class="net_td"><input name="net" type="text" value="${weightcertificate.net }"/></td>
										<td class="cuft_td"><input name="cuft" type="text" value="${weightcertificate.cuft }" /></td>
										<td class="remark_td"><input name="remark" type="text" value="${weightcertificate.remark }"/></td>
										<td class="gbl_delete_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_delete_Box"></div></td>
										<c:if test="${fn:length(weightcertificateList) ==  i.count}">
											<td class="gbl_plus_Box_td" style="border-left: 0px; border-top: thin; border-bottom: 0; border-right: 0;" data-count="0">
												<div class="gbl_weight_plus_Box"></div>
											</td>
										</c:if>
									</tr>
								</c:forEach>				
							</tbody>
							<tfoot>
								<tr>
									<td colspan="9" style="font-size: 12px;">/x/x/x/x/x/x/x/x/x/xx/x/x/x/x/x/x/x/x/x/x/ LAST ITEM /x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/x/</td>
									<c:if test="${weightcertificateList eq '[]' }">
										<td class="gbl_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_plus_Box"></div></td>
									</c:if>
								</tr>	
								<tr>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td id="weightcertificate_progear" style="font-size: 10px;">PROGEAR:</td>
									<td id="weightcertificate_seal_no" style="font-size: 10px;"><input type="text" name="progear" value="${(weightcertificateList ne '[]') ? weightcertificateList[0].proGear : '' }" ></td>
									<td id="weightcertificate_lbs" style="font-size: 10px;">LBS</td>
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
									<td> </td>						
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
									<td></td>						
								</tr>
								<tr>
									<td style="font-size: 11px;">Total</td>
									<td class="total_piece_td" style="font-size: 11px;">${ fn:length(weightcertificateList) }</td>
									<td></td>
									<td class="total_gross_td" style="font-size: 11px;"><fmt:formatNumber value="${totalGross }" /></td>
									<td class="total_grossKg_td" style="font-size: 11px;"><fmt:formatNumber value="${totalGrossKg }" /></td>
									<td class="total_tare_td" style="font-size: 11px;"><fmt:formatNumber value="${totalTare }"  /></td>
									<td class="total_net_td" style="font-size: 11px;"><fmt:formatNumber value="${totalNet }"  /></td>
									<td class="total_cuft_td" style="font-size: 11px;"><fmt:formatNumber value="${totalCuft }" /></td>
									<td></td>
								</tr>
							</tfoot>			
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
