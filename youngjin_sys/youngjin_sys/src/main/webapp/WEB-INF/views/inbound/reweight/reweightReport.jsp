<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setLocale value="en_us" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body onload="window.print();">
<center>
	<table cellspacing="0" border="0">
		<tr>
			<td style="width: 20cm; height:3cm;font-weight: bold;font-size: 25px;text-align: center;">YOUNGJIN TRADE & TRANSPORATION CO.,LTD</td>
		</tr>
	</table>
	<table cellspacing="0" border="0">
		<tr>
			<td style="width:12cm;height: 2cm;font-size:12px; text-align:left;font-weight: bold;">SUBJECT : ${reweightBasicInfo.reweightName }</td>
			<td style="width:9cm;height: 2cm;font-size:12px; text-align:center;font-weight: bold;">REPORT DATE : ${fn:substring(reweightBasicInfo.reweightDate, 0, 4) }. ${fn:substring(reweightBasicInfo.reweightDate, 4, 6) }. ${fn:substring(reweightBasicInfo.reweightDate, 6, 8) }</td>
		</tr>
	</table>
	<table cellspacing="0" border="0">
		<tr>
			<td style="width:20cm;height: 1cm;font-size:12px; text-align:left;font-weight: bold;">
			1.　${fn:substring(reweightReportingList[0].deliDate, 0, 4) }.
			${fn:toUpperCase(reweightReportingList[0].month) } / ${reweightReportingList[0].codeStr }　REWEIGH INFORMATION</td>
		</tr>
	</table>
	<table cellspacing="0" border="1">
		<!-- column title -->
		<tr>
			<td style="width:1cm; height:1cm;font-size:11px; font-weight:bold;text-align:center;">DELI<br>DATE</td>
			<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;">ORIGIN<br>GBLOCK</td>
			<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;">SCAC<br>CODE</td>
			<td style="width:3cm; font-size:11px; font-weight:bold;text-align:center;">GBL NO</td>
			<td style="width:6cm; font-size:11px; font-weight:bold;text-align:center;">FULL NAME</td>
			<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;">O/WT<br>(LBS)</td>
			<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;">R/WT<br>(LBS)</td>
			<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;">S/WT</td>
			<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;">DENTN</td>
			<td style="width:2cm; font-size:11px; font-weight:bold;text-align:center;">RATE<br>GBL.31</td>
		</tr>
		<!-- 여기가 내용들어가는 부분 vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->
		<c:forEach var="reweightReport" items="${reweightReportingList }" varStatus="i"><!-- 임의로 변수명 설정 -->
			<tr>
				<td style="width:1cm; height:0.4cm;font-size:11px; font-weight:bold;text-align:center;" name="delidate">${fn:substring(reweightReport.deliDate, 4, 6) }/${fn:substring(reweightReport.deliDate, 6, 8) }</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="origingblock">${reweightReport.originGblock }</td>
				<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;"name="scaccode">${reweightReport.scacCode }/${reweightReport.code }</td>
				<td style="width:3cm; font-size:11px; font-weight:bold;text-align:center;"name="gblno">${reweightReport.gblNo }</td>
				<td style="width:6cm; font-size:11px; font-weight:bold;text-align:center;"name="fullname">${reweightReport.fullName }</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="owt">${reweightReport.oWt }</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="rwt">${reweightReport.rWt }</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="swt">　</td>
				<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;"name="dentn">${reweightReport.dentn }</td>
				<td style="width:2cm; font-size:11px; font-weight:bold;text-align:center;"name="rate">$${reweightReport.rateGbl31 }</td>			
			</tr>
		</c:forEach>
		<!-- 나머지 부분 처리 (내가 나머지는 채워넣을게)-->
		<tr>
				<td style="width:1cm; height:0.4cm;font-size:11px; font-weight:bold;text-align:center;" name="delidate">X</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="origingblock">X</td>
				<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;"name="scaccode">X</td>
				<td style="width:3cm; font-size:11px; font-weight:bold;text-align:center;"name="gblno">X</td>
				<td style="width:6cm; font-size:11px; font-weight:bold;text-align:center;"name="fullname">X</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="owt">X</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="rwt">X</td>
				<td style="width:1cm; font-size:11px; font-weight:bold;text-align:center;"name="swt">X</td>
				<td style="width:1.5cm; font-size:11px; font-weight:bold;text-align:center;"name="dentn">X</td>
				<td style="width:2cm; font-size:11px; font-weight:bold;text-align:center;"name="rate">X</td>			
			</tr>
	</table>
	<table>
		<tr>
			<td style="padding-top:1cm;width:21cm;height:0.5cm; font-size:13px; font-weight:bold;text-align:left;">주식회사 영진상운(전화 : 795-7176)</td>
		</tr>
		<tr>
			<td style="width:21cm;height:0.5cm; font-size:13px; font-weight:bold;text-align:left;">담당: 엄혜정　　　　　서명:</td>
		</tr>
	</table>
	</center>
</body>
</html>