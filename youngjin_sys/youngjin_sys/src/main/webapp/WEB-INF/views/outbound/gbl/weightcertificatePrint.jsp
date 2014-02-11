<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="" xml:lang="">
<head>
<title>weight certificate</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="generator" content="pdftohtml 0.36"/>
<meta name="author" content="영진상운"/>
<meta name="date" content="2014-01-07T21:05:52+00:00"/>
<style type="text/css"> 
<!--
.xflip {
    -moz-transform: scaleX(-1);
    -webkit-transform: scaleX(-1);
    -o-transform: scaleX(-1);
    transform: scaleX(-1);
    filter: fliph;
}
.yflip {
    -moz-transform: scaleY(-1);
    -webkit-transform: scaleY(-1);
    -o-transform: scaleY(-1);
    transform: scaleY(-1);
    filter: flipv;
}
.xyflip {
    -moz-transform: scaleX(-1) scaleY(-1);
    -webkit-transform: scaleX(-1) scaleY(-1);
    -o-transform: scaleX(-1) scaleY(-1);
    transform: scaleX(-1) scaleY(-1);
    filter: fliph + flipv;
}
-->
</style>

<style type="text/css"> 
<!--
	p {margin: 0; padding: 0;}	.ft10{font-size:12px;font-family:Times;color:#000000;}
	.ft11{font-size:14px;font-family:Times;color:#000000;}
	.ft12{font-size:12px;font-family:Times;color:#ff0000;}
	.ft13{font-size:34px;font-family:Times;color:#000000;}
	.ft14{font-size:28px;font-family:Times;color:#000000;}
-->
</style>
</head>
<body bgcolor="#A0A0A0" vlink="blue" link="blue">
<!-- Page 1 -->
<a name="1"></a>
<div id="page1-div" style="position:relative;width:892px;height:1262px;">
<img width="892" height="1262" src="<c:url value='/resources/images/weight-certificate.jpg' />" alt="background image"/>

<!--//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-->
<p style="position:absolute;top:300px;left:350px;white-space:nowrap" class="ft11" name="rank">${gbl.rank } ${gbl.customerName }</p>

<p style="position:absolute;top:300px;left:632px;white-space:nowrap" class="ft11" name="date">${weightcertificateList[0].date }</p>

<p style="position:absolute;top:326px;left:350px;white-space:nowrap" class="ft11" name="origin">????</p>

<p style="position:absolute;top:326px;left:632px;white-space:nowrap" class="ft11" name="gblno">${gbl.no }</p>

<p style="position:absolute;top:352px;left:350px;white-space:nowrap" class="ft11" name="destination">?????</p>

<p style="position:absolute;top:352px;left:632px;white-space:nowrap" class="ft11" name="code">${gbl.code }</p>

<p style="position:absolute;top:378px;left:350px;white-space:nowrap" class="ft11" name="carrier">${gbl.scac }</p>

<p style="position:absolute;top:378px;left:632px;white-space:nowrap" class="ft11" name="rdd">${gbl.rdd }</p>
					
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
	<c:set var="top" value="${ 513 + i.index * 26 }" />
	<p style="position:absolute;top:${top}px;left:56px;white-space:nowrap" class="ft10" name="piece${i.count }">${weightcertificate.piece }</p>
	<p style="position:absolute;top:${top}px;left:127px;white-space:nowrap" class="ft10" name="type${i.count }">${weightcertificate.type }</p>
	<p style="position:absolute;top:${top}px;left:202px;white-space:nowrap" class="ft10" name="status${i.count }">${weightcertificate.status }</p>
	<p style="position:absolute;top:${top}px;left:262px;white-space:nowrap" class="ft10" name="kg${i.count }">${weightcertificate.grossKg }</p>
	<p style="position:absolute;top:${top}px;left:302px;white-space:nowrap" class="ft10" name="gross${i.count }">${weightcertificate.gross }</p>
	<p style="position:absolute;top:${top}px;left:380px;white-space:nowrap" class="ft10" name="tare${i.count }">${weightcertificate.tare }</p>
	<p style="position:absolute;top:${top}px;left:465px;white-space:nowrap" class="ft10" name="net${i.count }">${weightcertificate.net }</p>
	<p style="position:absolute;top:${top}px;left:545px;white-space:nowrap" class="ft10" name="cuft${i.count }">${weightcertificate.cuft }</p>
	<p style="position:absolute;top:${top}px;left:636px;white-space:nowrap" class="ft10" name="remark${i.count }">${weightcertificate.remark }</p>
</c:forEach>
<!-- 
<p style="position:absolute;top:513px;left:56px;white-space:nowrap" class="ft10" name="piece1">p1</p>
<p style="position:absolute;top:539px;left:44px;white-space:nowrap" class="ft10" name="piece2">p2</p>
<p style="position:absolute;top:565px;left:44px;white-space:nowrap" class="ft10" name="piece3">p3</p>
<p style="position:absolute;top:590px;left:44px;white-space:nowrap" class="ft10" name="piece4">p4</p>
<p style="position:absolute;top:615px;left:44px;white-space:nowrap" class="ft10" name="piece5">p5</p>

<p style="position:absolute;top:495px;left:127px;white-space:nowrap" class="ft10" name="type1">t1</p>
<p style="position:absolute;top:521px;left:115px;white-space:nowrap" class="ft10" name="type2">t2</p>
<p style="position:absolute;top:547px;left:115px;white-space:nowrap" class="ft10" name="type3">t3</p>
<p style="position:absolute;top:572px;left:115px;white-space:nowrap" class="ft10" name="type4">t4</p>
<p style="position:absolute;top:597px;left:115px;white-space:nowrap" class="ft10" name="type5">t5</p>

<p style="position:absolute;top:495px;left:202px;white-space:nowrap" class="ft10" name="status1">s1</p>
<p style="position:absolute;top:521px;left:182px;white-space:nowrap" class="ft10" name="status2">s2</p>
<p style="position:absolute;top:547px;left:182px;white-space:nowrap" class="ft10" name="status3">s3</p>
<p style="position:absolute;top:572px;left:182px;white-space:nowrap" class="ft10" name="status4">s4</p>
<p style="position:absolute;top:597px;left:182px;white-space:nowrap" class="ft10" name="status5">s5</p>

<p style="position:absolute;top:495px;left:267px;white-space:nowrap" class="ft10" name="kg1">k1</p>
<p style="position:absolute;top:521px;left:267px;white-space:nowrap" class="ft10" name="kg2">k2</p>
<p style="position:absolute;top:547px;left:267px;white-space:nowrap" class="ft10" name="kg3">k3</p>
<p style="position:absolute;top:572px;left:267px;white-space:nowrap" class="ft10" name="kg4">k4</p>
<p style="position:absolute;top:597px;left:267px;white-space:nowrap" class="ft10" name="kg5">k5</p>

<p style="position:absolute;top:495px;left:312px;white-space:nowrap" class="ft10" name="gross1">g1</p>
<p style="position:absolute;top:521px;left:312px;white-space:nowrap" class="ft10" name="gross2">g2</p>
<p style="position:absolute;top:547px;left:312px;white-space:nowrap" class="ft10" name="gross3">g3</p>
<p style="position:absolute;top:572px;left:312px;white-space:nowrap" class="ft10" name="gross4">g4</p>
<p style="position:absolute;top:597px;left:312px;white-space:nowrap" class="ft10" name="gross5">g5</p>

<p style="position:absolute;top:495px;left:380px;white-space:nowrap" class="ft10" name="tare1">t1</p>
<p style="position:absolute;top:521px;left:357px;white-space:nowrap" class="ft10" name="tare2">t2</p>
<p style="position:absolute;top:547px;left:357px;white-space:nowrap" class="ft10" name="tare3">t3</p>
<p style="position:absolute;top:572px;left:357px;white-space:nowrap" class="ft10" name="tare4">t4</p>
<p style="position:absolute;top:597px;left:357px;white-space:nowrap" class="ft10" name="tare5">t5</p>

<p style="position:absolute;top:495px;left:465px;white-space:nowrap" class="ft10" name="net1">n1</p>
<p style="position:absolute;top:521px;left:444px;white-space:nowrap" class="ft10" name="net2">n2</p>
<p style="position:absolute;top:547px;left:444px;white-space:nowrap" class="ft10" name="net3">n3</p>
<p style="position:absolute;top:572px;left:444px;white-space:nowrap" class="ft10" name="net4">n4</p>
<p style="position:absolute;top:597px;left:444px;white-space:nowrap" class="ft10" name="net5">n5</p>

<p style="position:absolute;top:495px;left:545px;white-space:nowrap" class="ft10" name="cuft1">c1</p>
<p style="position:absolute;top:521px;left:521px;white-space:nowrap" class="ft10" name="cuft2">c2</p>
<p style="position:absolute;top:547px;left:521px;white-space:nowrap" class="ft10" name="cuft3">c3</p>
<p style="position:absolute;top:572px;left:521px;white-space:nowrap" class="ft10" name="cuft4">c4</p>
<p style="position:absolute;top:597px;left:521px;white-space:nowrap" class="ft10" name="cuft5">c5</p>

<p style="position:absolute;top:495px;left:636px;white-space:nowrap" class="ft10" name="remark1">r1</p>
<p style="position:absolute;top:521px;left:624px;white-space:nowrap" class="ft10" name="remark2">r2</p>
<p style="position:absolute;top:547px;left:624px;white-space:nowrap" class="ft10" name="remark3">r3</p>
<p style="position:absolute;top:572px;left:624px;white-space:nowrap" class="ft10" name="remark4">r4</p>
<p style="position:absolute;top:597px;left:624px;white-space:nowrap" class="ft10" name="remark5">r5</p> !-->

<p style="position:absolute;top:694px;left:357px;white-space:nowrap" class="ft10" name="lbs">${totalGross }</p>


<p style="position:absolute;top:771px;left:115px;;white-space:nowrap" class="ft10" name="pcs">${ fn:length(weightcertificateList) }</p>
<p style="position:absolute;top:771px;left:267px;white-space:nowrap" class="ft10" name="sum_gross">${totalGross }</p>
<p style="position:absolute;top:771px;left:357px;white-space:nowrap" class="ft10" name="sum_tare">${totalTare }</p>
<p style="position:absolute;top:771px;left:444px;white-space:nowrap" class="ft10" name="sum_net">${totalNet }</p>
<p style="position:absolute;top:771px;left:521px;white-space:nowrap" class="ft10" name="sum_cuft">${totalCuft }</p>
<!--//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-->

</div>
</body>
</html>

    