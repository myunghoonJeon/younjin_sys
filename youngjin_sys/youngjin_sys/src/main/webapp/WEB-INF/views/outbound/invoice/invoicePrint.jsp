<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<!DOCTYPE html>
<HTML>
<style>
	#in_td{
		border : thin;
		border-style: solid;
		text-align: center;
		font-family: arial;
		font-size: 10pt;
	}
	#title{
		text-align: center;
		font-size: 19pt;
		font-weight: bold;
	}
	#title-side{
		width: 4.5cm;
	}
	#address{
		font-weight:bold;
		text-align: center;
	}
	#tel, #fax{
		font-weight:bold;
		font-size : 10pt;
		text-align: center;
	}
	#invoice{
		padding-top: 1cm;
		padding-bottom: 0.5cm;
		text-align: center;
	}
	#invoice_input{
		font-weight:bold; 
		text-decoration: underline;
		font-size: 14pt;
	}
	#to{
		 height: 2.5cm; vertical-align: text-top; font-weight: bold;
		font-size: 10pt;
		font-family:arial;
	}
	#totd{
		width:1cm; height: 2.5cm; vertical-align: text-top; font-weight: bold;
		font-size: 9pt;
		font-family:arial;
	}
	#date,#invoiceno,#code{
		font-size: 9pt;
		font-weight: bold;
		vertical-align: text-top;
		font-family:arial;
	}
	
	#test tr td{
		border-top:1px solid;
		border-bottom:1px solid;
		padding-top: 0.2cm;
		padding-bottom: 0.2cm;
		text-align:center;
		font-weight: bold;
		font-family: arial;
		font-size: 11pt;
	}
	
	#no{
		width : 1cm;
	}
	#route{
		font-weight: bold;
		width : 1.5cm;
	}
	#gblno{
		font-weight: bold;
		width : 2.5cm; 
	}
	#rank{
		font-weight: bold;
		width : 1.5cm;
	}
	#shipper{
		font-weight: bold;
		width : 4cm;
	}
	#weight{
		font-weight: bold;
		width : 1.5cm;
	}
	#ratecwt{
		font-weight: bold;
		width : 3cm;
	}
	#amount{
		font-weight: bold;
		width : 3.5cm;
	}
	
	.invoice_gbl_content_table tr th{
		background-color: white;
		color: black;
	}
</style>
<%
	String tel = "82-2-825-6345";
	String fax = "82-2-825-6345,";
	String to = "";
	String date = "05-16-13";
	String invoiceno="CRWV-#4-OH-0516";
	String code="OUTBOUND #4";
%>
<HEAD>
	<link rel="stylesheet" href="${cp }/resources/css/common.css">
</HEAD>
<c:set var="totalAmount" value="0"></c:set>
<BODY onload="window.print();">
	<div>
		<table border="0" align="center" style="width: 17cm" cellspacing="0">
			<tr>
				<td id='title'colspan="4" style="font-family: arial;">YOUNJIN TRADE & TRANSPORTATION, Co., LTD</td>
			</tr>
			<tr>
				<td id='title-side'>　</td>
				<td colspan="2" id='address'><input style="width: 15cm; border: none; text-align: center; font-size: 10pt;font-weight: bold;" type="text" value="${company.address }"></input></td>
				<td id='title-side'>　</td>
			</tr>
			<tr>
				<td id='title-side'>　</td>
				<td id='tel'>TEL : <input style="width: 3cm; border: none; font-weight: bold;" type="text" value="<%out.println(tel);%>" ></input></td>
				<td id='fax'>FAX : <input style="width: 3cm; border: none; font-weight: bold;" type="text" value="<%out.println(fax);%>"></input></td>
				<td id='title-side'>　</td>
			</tr>
			<tr>
				<td colspan="4" id='invoice' style="font-family: arial; text-decoration: underline;">I N V O I C E</td>
			</tr>
		</table>
		<table border="0" align="center" style="width : 17cm;" cellspacing="0">
			<tr>
				<td id='totd' rowspan="3">TO: </td><td id='to' rowspan="3">${scac.scacFullName }<br/>${scac.address }</td>
				<fmt:parseDate value="${invoice.invoiceDate}" pattern="yyyyMMdd" var="invoiceDate"  />
				<td id='date' style="width:2.5cm;">DATE: </td > <td id='date'><fmt:formatDate value="${invoiceDate }" pattern="dd-MM-yy" /></td>
			</tr>
			<tr>
				<td id='invoiceno' style="width:2.5cm; font-weight: bold;">INVOICE NO: </td> <td id='invoiceno'>${invoice.invoiceNo }</td>
			</tr>
			<tr>
				<td id='code'style="width:2.5cm;">CODE: </td> <td id='code'>${fn:toUpperCase(invoice.process) } #${invoiceGblList[0].code }</td>
			</tr>		
		</table>
		<table id='test' border="0" align="center" style="width : 17cm;" cellspacing="0">
			<tr style="font-size: 9pt; ">
				<td id='no'>NO</td>
				<td id='gblno'>GBL-NO</td>
				<td id='rank'>RANK</td>
				<td id='shipper'>SHIPPER</td>
				<td id='amount'>AMOUNT</td>
			</tr>
			<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
				<tr>	
					<td>${i.count }</td>
					<td>${invoiceGbl.gblNo }</td>
					<td>${invoiceGbl.rank }</td>
					<td>${invoiceGbl.name }</td>
					<td><fmt:formatNumber value="${invoiceGbl.amount }" pattern="##,###.00"/></td>
					<c:set var="totalAmount" value="${totalAmount + invoiceGbl.amount }"></c:set>
				</tr>
			</c:forEach>
			<tr>	
				<td style="font-size: 9pt;border-bottom: none; font-family: arial;font-weight: bold;">TOTAL</td>
				<td style="font-size: 7pt;border-bottom: none;">　</td>
				<td style="font-size: 7pt;border-bottom: none;">　</td>
				<td style="font-size: 7pt;border-bottom: none;">　</td>
				<td style="font-size: 11pt;border-bottom: none; font-family: arial;font-weight: bold;">${totalAmount }</td>
			</tr>
		</table>
	</div>

	<div style="page-break-after: always"></div>
	
	<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
	<center>
		<div style="padding-top: 1cm;">			
			<div>
				<table style="border: thin; border-style: solid; width: 600px; font-family: arial; font-size: 10pt;"><!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  -->
					<tr>
						<td >TSP : ${invoice.tsp }</td>
						<td >IN/OUT : FROM KOREA</td>
						<td >CODE : ${invoiceGbl.code }</td>
					</tr>
					<tr>
						<td >GBL NO : ${invoiceGbl.gblNo }</td>
						<td >RANK : ${invoiceGbl.rank }</td>
						<td >NAME : ${invoiceGbl.name }</td>
					</tr>
				</table>
				<div style="padding-bottom: 20px;">
				</div>
				<table cellspacing="0" style="width:600px; border: thin; margin:5px; padding-bottom: 30px;">
					<tr >
						<td id="in_td" style="font-weight: bold;">
							<strong>CHARGING ITEMS</strong>
						</td>
						<td id="in_td" style="font-weight: bold;">
							<strong>QUANTITY</strong>
						</td>
						<td id="in_td" style="font-weight: bold;">
							<strong>AMOUNTS</strong>
						</td>
					</tr> 
					<c:forEach var="invoiceGblContent" items="${invoiceGblContentMap[invoiceGbl.seq] }">
						<tr style="border-bottom: solid; border: solid;">
							<td id="in_td">${invoiceGblContent.chargingItem }</td>
							<td id="in_td">${invoiceGblContent.quantity }</td>
							<td id="in_td"><fmt:formatNumber value="${invoiceGblContent.amount }" pattern="##,###.00"/></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>	
		</center>
	</c:forEach>
	<!-- 
	<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
		<div>			
			<div>
				<table class="invoice_gbl_content_table">
					<tr>
						<th colspan="5">
							CHARGING ITEMS
						</th>
						<th>
							QUANTITY
						</th>
						<th>
							AMOUNTS
						</th>
					</tr> 
					<c:forEach var="invoiceGblContent" items="${invoiceGblContentMap[invoiceGbl.seq] }">
						<tr>
							<td colspan="5">${invoiceGblContent.chargingItem }</td>
							<td>${invoiceGblContent.quantity }</td>
							<td>${invoiceGblContent.amount }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>	
	</c:forEach>
	 -->
</BODY>
</HTML>

