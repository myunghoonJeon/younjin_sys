<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 20cm;
	}
	#left{
		width:2.5cm;
	}
	#right{
		width:2.5cm;
	}
	#toptitletd{
		font-weight: bold;
		font-size: 17pt;
		border-bottom: solid;
		border-bottom-style: double;
		text-align: center;
		vertical-align:bottom;
		height: 1cm;
	}
	#date_td{
		padding-top: 0.6cm;
		text-align: right;
		padding-bottom: 0.5cm;
		font-size: 10pt;
	}
	#subject_context_td{
		padding-left : 0.2cm;
		font-size: 10pt;
	}
	#subject_title_td{
		font-size: 10pt;
		width: 1cm;
		vertical-align: top;
	}
	#context_td1{
		padding-top: 0.5cm;
		width: 8cm;
		font-size: 10pt;
	}
	#context_td2{
		padding-top: 0.5cm;
		font-size: 10pt;
	}
	#inaccordance_td{
		padding-top: 1cm;
	}
	#name_td, #rank_td, #ssn_td, #pud_td, #gblno_td, #carrier_td, #code_td, #address_td, #nameofarticle_td, #estimatedwt_td{
		text-decoration : underline;
		font-size: 10pt;
		padding-top:0.5cm;
	}
	#thirdparty, #locality, #approved{
		font-size:10pt;
		padding-top:0.8cm;
		width:8cm;
		height:2cm;
	}
	#localmanager{
		font-size:10pt;
		padding-top:0.8cm;
		width:8cm;
		height:2cm;
	}
	#term{
		width:1cm;
	}
	#taforto{
		font-size:10pt;
		width:8cm;
		height:2cm;
	}
</style>
<%
	String date="";
	String subject="REQUEST FOR AUTHORIZATION OF ADDITIONAL SERVICE<br>FOR LOWERING OF ARTICLES";
	String installation="INSTALLATION TRANSPORTATION OFFICER<BR>403rd Army Field Support Brigade(AFSB)<BR>Logistics Readiness Center Yongsan<BR>UNIT #15802, APO AP 96205-5802";
	String inaccordance="IN accordance with DPS International Tender 223b(4) request compensation to the carrier<BR>for use of lowering equipment for safe transportation of the following listed articles for";
	String name="DEUS, WILLIAM, J.";
	String rank="1LT";
	String ssn="XXX-XX-5019";
	String pud="19 JUL 13";
	String gbl="QXAK0060503";
	String carrier="AALF";
	String code="4";
	String adress="BUNDANG-GU BAKHYUNDONG 542 PRUGIO GURANGBULL 111-701";
	String nameofarticle="USING FOR THE LOWERING EQUIPMENT";
	String estimatedwt="4000LBS";
	String localmanager="SHIN, DOO SIK<BR>YONG SAN BRANCH MANAGER<BR>YOUNGJIN TRAED & CO.LTD";
	String locality="ASKO-LRC-YON-LGT<BR>Unit #15802, APO AP 96205-5802<BR>FOR YOUNGJIN T&T CO.,LTD.";
	String taforto="JOHN F. CUTTER. GS-13,<BR>";
%>
</head>
<body>
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>
		<td id='left'></td>
			<td id='toptitletd' colspan="3">YOUNGJIN TRADE & TRANSPORTATION CO. LTD.</td>
		</tr>
		<tr>
			<td id="date_td" colspan="3">DATE : <%out.print(date);%></td>
			<td></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='subject_title_td'>SUBJECT : </td>
			<td id='subject_context_td'><%out.print(subject);%></td>
			<td id='right'></td>
		</tr>
	</table>
	
	<table  style="font-weight: bold;" align="center" cellspacing="0">
		<tr >
			<td id='left'>　</td>
			<td id="context_td1"><%out.print(installation);%></td>
			<td></td>
		</tr>
		<tr id="context2">
			<td id='left'></td>
			<td id='context_td2'  colspan="2"><%out.print(inaccordance);%></td>
		</tr>
	</table>
	
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>
			<td colspan="5" id='inaccordance_td'></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='name_td'>NAME : <%out.print(name);%></td>
			<td id='rank_td'>RANK : <%out.print(rank);%></td>
			<td id='ssn_td' colspan="2">SSN : <%out.print(ssn);%></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='pud_td'>P/U DATE : <%out.print(pud);%></td>
			<td id='gblno_td'>GBL NO : <%out.print(gbl);%></td>
			<td id='carrier_td'>CARRIER : <%out.print(carrier);%></td>
			<td id='code_td'>CODE : <%out.print(code);%></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='address_td' colspan="4">ADRESS : <%out.print(adress);%></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='nameofarticle_td' colspan="4">NAME OF ARTICLE : <%out.print(nameofarticle);%></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='estimatedwt_td' colspan="4">ESTIMATED WT : <%out.print(estimatedwt);%></td>
		</tr>
	</table>
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>
			<td id='left'></td>
			<td id='thirdparty' >직접입력</td>
			<td id='term'></td>
			<td></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td></td>
			<td></td>
			<td id='localmanager'><%out.print(localmanager);%></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='locality'><%out.print(locality);%></td>
			<td id='term'></td>
			<td></td>
		</tr>
		<tr>
			<td id='left'></td>
			<td id='approved' colspan="4">APPROVED</td>
		</tr>
		<tr>
			<td id='left'></td>
			<td></td>
			<td></td>
			<td id='taforto'><%out.print(taforto);%></td>
		</tr>
	</table>
</body>
</html>