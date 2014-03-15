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
<title>DD619 LIST</title>

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
	<div id="dd619_add_div">
		<div class="pop_title_line">
			<span>DD619-1 INPUT</span>
		</div>	
		
		<div class="yj_button_wrap">
			<ul class="yj_button_list dd619_back_wrap">
				<li>
					<span class="yj_button dd619_form_back">back</span>
				</li>
			</ul>
		</div>
		
		<form:form commandName="dd619">
			<form:hidden path="writeUser" value="${user.name }"/>
		<table class="dd619_add_table" data-seq="${seq }">
			<tr>
				<th>GBL NO</th>
				<td><form:input path="gblNo" value="${gbl.no }"/></td>
				<th>DATE</th>
				<td><form:input path="date"/></td>
			</tr>	
			<tr>
				<th>NAME</th>
				<td><form:input path="name" value="${gbl.customerName }"/></td>
				<th>SSN</th>
				<td>XXX - XX - <form:input path="ssn" value="${gbl.ssn }" style="width: 50px;"/></td>
			</tr>
			<tr>
				<th>RANK</th>
				<td><form:input path="rank" value="${gbl.rank }"/></td>
				<th>ORIGIN OF SHIPMENT</th>
				<td><form:input path="originOfShipment"/></td>
			</tr>		
			<tr>
				<th>DESTINATION</th>
				<td><form:input path="destination"/></td>
				<th>ORDERING ACTIVITY NAME</th>
				<td><form:input path="orderingActivityName" /></td>
			</tr>		
			<tr>
				<th>CARRIER NAME</th>
				<td><form:input path="carrierName" /></td>
				<th>AGENT NAME</th>
				<td><form:input path="agentName" value="YOUNGJIN T&T CO.LTD"/></td>
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
				<td><form:input path="carrierShipmentReference" value="${gbl.scac }"/></td>
			</tr>		
			<tr>
				<th>CODE</th>
				<td><form:input path="code" value="${gbl.code }"/></td>
				<th>OTHER</th>
				<td><input id="other1"/><input id="other2"/><input id="other3"/></td>
			</tr>		
			<tr class="dd619_total">
				<th>TOTAL</th>
				<td colspan="3"><input id="total1" /><input id="total2" /><input id="total3" /></td>
			</tr>		
			<tr>				
				<th colspan="4">STATEMENT Of OWNER, MILITARY INSPECTOR/TRANSPORTATION OFFICER</th>
			</tr>
			<tr>
				<th>MATERIALS WERE FURNISHED/ACCESSORIAL SERVICES WERE PERFORMED</th>
				<td><form:input path="officerMaterial" value="ORIGIN"/></td>				
				<th>SIGNATURE</th>
				<td><form:input path="officerSignature" value="${gbl.rank } ${gbl.customerName }"/></td>				
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
				<td><form:input path="rankAndName" value="${gbl.rank } ${gbl.customerName }"/></td>
				<th>DATE SIGNED</th>
				<td><form:input path="transportationDate" /></td>
			</tr>		
			<tr>
				<th colspan="4">REMARK</th>
			</tr>
			<tr>
				<td id="dd619_remark" colspan="4">
					<c:forEach var="remark" items="${remarkList }">
						<c:if test="${remark.type eq '01' }">
							<c:set var="remarkContent1" value="THE SERVICE OF A THIRD PARTY FOR HOISTIONG OF THIS SHIPMENT WAS AUTHORIZED FOR
SAFE TRANSPORTATION COMPLY WITH DPS INTERNATIONAL TENDER 223B(3)
(PAID FOR :                        )"/>
						</c:if>
						<c:if test="${remark.type eq '03' }">
							<c:set var="remarkContent3" value="SPECIAL SERVICE WAS REQUIRED(INTERNATIONAL TENDER ITEM#508C) TO MOTOR-CYCLE	
UTILIZED SAFE TRANSPORTAION AT ORIGIN.	
FABRICATED ONE CRATE :  	
TOTAL AMOUNT :       "/>
						</c:if>
					</c:forEach>
					<textarea id="remark">${remarkContent1}
${remarkContent3 }</textarea>
				</td>
			</tr>	
			<tfoot>
				<tr>
					<td colspan="4">
						<div>
							<span class="dd619_add_submit_button yj_button">add</span>
						</div>
					</td>
				</tr>
			</tfoot>				
		</table>
		</form:form>
	</div>

</body>
</html>