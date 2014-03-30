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
		font-size: 15pt;
	}
	#to{
		width:9cm;
		height:2.5cm;
	}
	#totd{
		width:1cm; height: 2.5cm; vertical-align: text-top; font-weight: bold;
		font-size: 10pt;
	}
	#date,#invoiceno{
		font-size: 9pt;
		font-weight: bold;
		vertical-align: text-top;
	}
	#code{
		font-size: 9pt;
		font-weight: bold;
		vertical-align: text-top;
		padding-top: 0.5cm;
	}
	
	#test tr td{
		border-top:1px solid;
		border-bottom:1px solid;
		padding-top: 0.2cm;
		padding-bottom: 0.2cm;
		text-align:center;
		font-weight: bold;
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
	
	.invoice_gbl_content_print_table{ width: 80%; margin: 10px auto; text-align: center; }
	.invoice_gbl_title_print_table{ width: 80%; margin: 10px auto; text-align: center; }
	.invoice_gbl_title_td_lfet{style="border-bottom: thin; border-left: thin; border-top: thin; border-style: solid;}
	.invoice_gbl_title_td_right{style="border-bottom: thin; border-left: thin; border-top: thin; border-style: solid;}
	.invoice_gbl_content_print_table tr th{ font-size: 12px; padding: 3px 5px; color: #000; border: 1px solid #000; }
	.invoice_gbl_content_print_table tr td{ padding: 10px 0; font-size: 10px; color: #000; border: 1px solid #000; text-align: center; vertical-align: middle;}
	.invoice_gbl_content_print_table tr{ background-color: white; cursor: pointer;  }
	
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
<BODY onload="window.print();">
	<div style="height: 1000px;">
		<table border="0" align="center" style="width: 17cm" cellspacing="0">
			<tr>
				<td id='title'colspan="4">YOUNJIN TRADE & TRANSPORTATION, Co., LTD</td>
			</tr>
			<tr>
				<td id='title-side'>　</td>
				<td colspan="2" id='address'><div style="width: 9cm; border: none; text-align: center; font-size: 10pt;font-weight: bold;" type="text">${company.address }</div></td>
				<td id='title-side'>　</td>
			</tr>
			<tr>
				<td id='title-side'>　</td>
				<td id='tel'>TEL : <input style="width: 3cm; border: none; font-weight: bold;" type="text" value="<%out.println(tel);%>" ></input></td>
				<td id='fax'>FAX : <input style="width: 3cm; border: none; font-weight: bold;" type="text" value="<%out.println(fax);%>"></input></td>
				<td id='title-side'>　</td>
			</tr>
			<tr>
				<td colspan="4" id='invoice'><font id='invoice_input'>I N V O I C E</font></td>
			</tr>
		</table>
		<table border="0" align="center" style="width : 17cm;" cellspacing="0">
			<tr>
				<td id='totd' rowspan="3">TO:</td><td style="vertical-align:top; font-size: 9pt; width:9.5cm;" id='to' rowspan="3">${scac.scacFullName }<br/>${scac.address }</td>
				<fmt:parseDate value="${invoice.invoiceDate}" pattern="yyyyMMdd" var="invoiceDate"  />
				<td id='date' style="width:2.5cm;">DATE: </td > <td id='date'><fmt:formatDate value="${invoiceDate }" pattern="dd-MM-yy" /></td>
			</tr>
			<tr>
				<td id='invoiceno' style="width:2.5cm; font-weight: bold;">INVOICE NO: </td> <td id='invoiceno'>${invoice.invoiceNo }</td>
			</tr>
			<tr>
				<td id='code'style="width:2.5cm; font-weight: bold;">CODE: </td> <td id='code'>${fn:toUpperCase(invoice.process) } #${invoiceGblList[0].code }</td>
			</tr>		
		</table>
		<table id='test' border="0" align="center" style="width : 17cm; " cellspacing="0">
			<tr style="font-size: 8pt; ">
				<td id='no'>NO</td>
				<td id='route'>ROUTE</td>
				<td id='gblno'>GBL-NO</td>
				<td id='rank'>RANK</td>
				<td id='shipper'>SHIPPER</td>
				<td id='amount'>AMOUNT</td>
			</tr>
			<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
				<tr>	
					<td style="font-size: 7pt;">${i.count }</td>
					<td style="font-size: 7pt;"></td>
					<td style="font-size: 7pt;">${invoiceGbl.gblNo }</td>
					<td style="font-size: 7pt;">${invoiceGbl.rank }</td>
					<td style="font-size: 7pt;">${invoiceGbl.name }</td>
					<td style="font-size: 7pt;">${invoiceGbl.amount }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
		<div>			
			<div>
				<table class="invoice_gbl_title_print_table"border="1"><!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  -->
					<tr>
						<td class="invoice_gbl_title_td_lfet">TSP</td><td>${invoice.tsp }</td>
						<td>IN/OUT</td>
						<td>
							<c:choose>
								<c:when test="${invoice.process eq 'inbound' }">
									TO KOREA
								</c:when>
								<c:otherwise>
									FROM KOREA
								</c:otherwise>
							</c:choose>
						</td>
						<td>CODE</td><td>${invoiceGbl.code }</td>
					</tr>
					<tr>
						<td>GBL NO</td><td>${invoiceGbl.gblNo }</td>
						<td>RANK</td><td>${invoiceGbl.rank }</td>
						<td>NAME</td><td>${invoiceGbl.name }</td>
					</tr>
				</table>
				<table class="invoice_gbl_content_print_table">
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
</BODY>
</HTML>

