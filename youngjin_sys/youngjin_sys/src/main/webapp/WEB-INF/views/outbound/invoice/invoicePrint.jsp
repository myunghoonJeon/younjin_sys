<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		font-size: 14pt;
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
</HEAD>
<BODY>
	<table border="0" align="center" style="width: 17cm" cellspacing="0">
		<tr>
			<td id='title'colspan="4">YOUNJIN TRADE & TRANSPORTATION, Co., LTD</td>
		</tr>
		<tr>
			<td id='title-side'>　</td>
			<td colspan="2" id='address'><input style="width: 9cm; border: none; text-align: center; font-size: 11pt;font-weight: bold;" type="text" value="475 Sangdo-1-dong, Dongjak-gu, Seoul, Korea."></input></td>
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
			<td id='totd' rowspan="3">TO: </td><td id='to' rowspan="3">${scac.scacFullName }<br/>${scac.address }</td>
			<td id='date' style="width:2.5cm;">DATE: </td > <td id='date'><%out.println(date); %></td>
		</tr>
		<tr>
			<td id='invoiceno' style="width:2.5cm;">INVOICE NO: </td> <td id='invoiceno'><%out.println(invoiceno); %></td>
		</tr>
		<tr>
			<td id='code'style="width:2.5cm;">CODE: </td> <td id='code'>${fn:toUpperCase(invoice.process) }#</td>
		</tr>		
	</table>
	<table id='test'border="0" align="center" style="width : 17cm;" cellspacing="0">
		<tr style="font-size: 9pt; ">
			<td id='no'>NO</td>
			<td id='route'>ROUTE</td>
			<td id='gblno'>GBL-NO</td>
			<td id='rank'>RANK</td>
			<td id='shipper'>SHIPPER</td>
			<td id='weight'>WEIGHT</td>
			<td id='ratecwt'>RATE/CWT</td>
			<td id='amount'>AMOUNT</td>
		</tr>
		<c:forEach var="invoiceGbl" items="${invoiceGblList }" varStatus="i">
			<tr>	
				<td>${i.count }</td>
				<td></td>
				<td>${invoiceGbl.gblNo }</td>
				<td>${invoiceGbl.rank }</td>
				<td>${invoiceGbl.name }</td>
				<td>${i.count }</td>
				<td>${i.count }</td>
				<td>${invoiceGbl.amount }</td>
			</tr>
		</c:forEach>
	</table>
</BODY>
</HTML>

