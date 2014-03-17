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
<title>DD619 MODIFY</title>

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
	<div id="inbound_dd619_add_div">
		<div class="pop_title_line">
			<span>DD619-1 MODIFY</span>
		</div>	
		
		<div class="yj_button_wrap">
			<ul class="yj_button_list inbound_dd619_back_wrap">
				<li>
					<span class="yj_button inbound_dd619_form_back">back</span>
				</li>
				<li>
					<span class="yj_button inbound_dd619_form_print">print</span>
				</li>
			</ul>
		</div>
		
		<form:form commandName="dd619">
			<form:hidden path="writeUser" value="${user.name }"/>
		<table class="inbound_dd619_add_table" data-seq="${seq }" data-dd619Seq="${dd619.seq }", data-memorandumSeq="${dd619.memorandumListSeq }">
			<tr>
				<th>GBL NO</th>
				<td><form:input path="gblNo"/></td>
				<th>DATE</th>
				<td><form:input path="date" value="${gbl.pud }"/></td>
			</tr>	
			<tr>
				<th>NAME</th>
				<td><form:input path="name" value="${gbl.shipperName }"/></td>
				<th>SSN</th>
				<td>XXX - XX - <form:input path="ssn" style="width: 50px;" value="${fn:substring(dd619.ssn, 5, 9) }"/></td>
			</tr>
			<tr>
				<th>RANK</th>
				<td><form:input path="rank"/></td>
				<th>ORIGIN OF SHIPMENT</th>
				<td><form:input path="originOfShipment" value="${gbl.gbloc }"/></td>
			</tr>		
			<tr>
				<th>DESTINATION</th>
				<td><form:input path="destination" value="${gbl.areaLocal }"/></td>
				<th>ORDERING ACTIVITY NAME</th>
				<td><form:input path="orderingActivityName" value="${branch.itoAddress }"/></td>
			</tr>		
			<tr>
				<th>CARRIER NAME</th>
				<td><form:input path="carrierName" value="${gbl.tsp }" /></td>
				<th>AGENT NAME</th>
				<td><form:input path="agentName" value="${company.companyNameAcronym }"/></td>
			</tr>		
			<tr>
				<th>SINGNATURE</th>
				<c:choose>
					<c:when test="${user.areaStr eq 'YongSan'}">
						<c:set var="signature" value="DOO S SHIN / BRANCH MANAGER" />
					</c:when>
					<c:when test="${user.areaStr eq 'PyungTaek' or user.areaStr eq 'Osan'}">
						<c:set var="signature" value="PAK,OK MAN / BRANCH MANAGER" />
					</c:when>
					<c:when test="${user.areaStr eq 'TongDooChun' or user.areaStr eq 'UyJungBu'}">
						<c:set var="signature" value="SON-YOUN-A / BRANCH MANAGER" />
					</c:when>
					<c:when test="${user.areaStr eq 'DaeGu' or user.areaStr eq 'Busan'}">
						<c:set var="signature" value="YI, CHIN HUN / BRANCH MANAGER" />
					</c:when>								
				</c:choose>
				<td><form:input path="signature" value="${signature }" /></td>
				<th>CARRIER'S SHIPMENT REFER</th>
				<td><form:input path="carrierShipmentReference"/></td>
			</tr>		
			<tr>
				<th>CODE</th>
				<td><form:input path="code"/></td>
				<th>OTHER</th>
				<c:choose>
					<c:when test="${dd619.other ne null and dd619.other ne '' }">
						<td><input id="other1" value="${ dd619.otherArray[0]}"/><input id="other2" value="${ dd619.otherArray[1]}"/><input id="other3" value="${ dd619.otherArray[2]}"/></td>
					</c:when>
					<c:otherwise>
						<td><input id="other1"/><input id="other2"/><input id="other3"/></td>
					</c:otherwise>
				</c:choose>
			</tr>		
			<tr class="dd619_total">
				<th>TOTAL</th>
				<c:choose>
					<c:when test="${dd619.total ne null and dd619.total ne '' }">
						<td colspan="3"><input id="total1" value="${ dd619.totalArray[0]}" /><input id="total2" value="${ dd619.totalArray[0]}"/><input id="total3" value="${ dd619.totalArray[0]}"/></td>
					</c:when>
					<c:otherwise>
						<td colspan="3"><input id="total1"/><input id="total2"/><input id="total3"/></td>
					</c:otherwise>
				</c:choose>
			</tr>		
			<tr>				
				<th colspan="4">STATEMENT Of OWNER, MILITARY INSPECTOR/TRANSPORTATION OFFICER</th>
			</tr>
			<tr>
				<th>MATERIALS WERE FURNISHED/ACCESSORIAL SERVICES WERE PERFORMED</th>
				<td><form:input path="officerMaterial" value="ORIGIN"/></td>				
				<th>SIGNATURE</th>
				<td><form:input path="officerSignature" value="${gbl.rank } ${gbl.shipperName }"/></td>				
			</tr>
			<tr>							
				<th colspan="2">DATE SIGNED</th>
				<td colspan="2"><form:input path="officerDate" /></td>
			</tr>	
			<tr>
				<th colspan="4">
					TRANSPORTAION OFFICER CERTIFICATION. I CERTIFY THAT SHIPMENT SERVICES WERE ACCOMPLISHED AS SHOWN BELOW.
				</th>
			</tr>
			<tr>
				<th>SIGNATURE OF TRANSPORTATION OFFICER</th>
				<td><form:input path="rankAndName" value="${gbl.rank } ${gbl.shipperName }"/></td>
				<th>DATE SIGNED</th>
				<td><form:input path="transportationDate" /></td>
			</tr>		
			<tr>
				<th colspan="4">REMARK</th>
			</tr>
			<tr>
				<td id="dd619_remark" colspan="4">
					<c:forEach var="remark" items="${remarkList }">
						<c:choose>
							<c:when test="${remark.type eq '01' }">
								<input type="hidden" id="dd619Count" value="1"/>
								<form:input path="invoiceMemorandumType" value="LOWERING EQIPMENT"/> : 
								<form:input path="invoiceMemorandumValue" value="${remarkValue['LOWERING EQIPMENT'] }" />
							</c:when>
							<c:when test="${remark.type eq '02' }">
								<c:forEach var="article" items="${remark.articleList }"> 	
									<input type="hidden" id="dd619Count" value="${fn:length(remark.articleList) }"/>
									<form:input path="invoiceMemorandumType" value="${article }"/> : 
									<form:input path="invoiceMemorandumValue" value="${remarkValue[article] }" />
								</c:forEach>
							</c:when>
							<c:when test="${remark.type eq '03' }">
								<input type="hidden" id="dd619Count" value="3"/>
								<form:input path="invoiceMemorandumType" value="MOTO CYCLE"/> :
								<form:input path="invoiceMemorandumValue" value="${remarkValue['MOTO CYCLE'] }" /> 
								<form:input path="invoiceMemorandumType" value="FABRICATED ONE CRATE"/> :
								<form:input path="invoiceMemorandumValue" value="${remarkValue['FABRICATED ONE CRATE'] }" /> 
								<form:input path="invoiceMemorandumType" value="TOTAL AMOUNT"/> :
								<form:input path="invoiceMemorandumValue" value="${remarkValue['TOTAL AMOUNT'] }" /> 								
							</c:when>
						</c:choose>	
					</c:forEach>
				</td>
			</tr>	
			<tfoot>
				<tr>
					<td colspan="4">
						<div>
							<span class="inbound_dd619_modify_submit_button yj_button">modify</span>
						</div>
					</td>
				</tr>
			</tfoot>				
		</table>
		</form:form>
	</div>

</body>
</html>