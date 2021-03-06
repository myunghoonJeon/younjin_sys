<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="" xml:lang="">
<head>
<title>DD Form 619, Statement of Accessorial Services Performed, 20080320 draft</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="generator" content="pdftohtml 0.36"/>
<meta name="author" content="WHS/ESD/IMD"/>
<meta name="date" content="2008-05-08T11:30:32+00:00"/>
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
<STYLE type="text/css">
<!--
	p {margin: 0; padding:0; font-family: Helvetica;}
	.q-text {font-family:arial; font-size:13px; border:0px dashed gray; font-weight:bolder;}
	.q-multiline {font-family:arial; font-weight:bold; font-size:14px; border:0px dashed gray;}
	.q-center {text-align:center;}
	textarea{resize:none; border: none; overflow-y: hidden;}
	.q-right  {text-align:right;}
	input{}
/*
	.ft10{font-size:18px;font-family:Helvetica;color:#000000;}
	.ft11{font-size:12px;font-family:Times;color:#000000;}
	.ft12{font-size:12px;font-family:Helvetica;color:#000000;}
	.ft13{font-size:12px;font-family:Helvetica;color:#000000;}
	.ft14{font-size:10px;font-family:Helvetica;color:#000000;}
	.ft15{font-size:13px;font-family:Helvetica;color:#000000;}
	.ft16{font-size:10px;font-family:Helvetica;color:#000000;}
	.ft17{font-size:13px;font-family:Helvetica;color:#000000;}
	.ft18{font-size:13px;font-family:Helvetica;color:#000000;}
	.ft19{font-size:13px;font-family:Times;color:#000000;}
	.ft110{font-size:10px;font-family:Helvetica;color:#000000;}
	.ft111{font-size:17px;font-family:Times;color:#000000;}
	.ft112{font-size:18px;font-family:Times;color:#000000;}
	.ft113{font-size:14px;font-family:Helvetica;color:#000000;}
	.ft114{font-size:12px;line-height:21px;font-family:Helvetica;color:#000000;}
	.ft115{font-size:12px;line-height:17px;font-family:Helvetica;color:#000000;}
	.ft116{font-size:12px;line-height:14px;font-family:Helvetica;color:#000000;}
	.ft117{font-size:12px;line-height:14px;font-family:Helvetica;color:#000000;}
	.ft118{font-size:13px;line-height:21px;font-family:Helvetica;color:#000000;}
	.ft119{font-size:13px;line-height:18px;font-family:Helvetica;color:#000000;}
	.ft120{font-size:13px;line-height:18px;font-family:Helvetica;color:#000000;}
	.ft121{font-size:13px;line-height:20px;font-family:Helvetica;color:#000000;}
	.ft122{font-size:10px;line-height:12px;font-family:Helvetica;color:#000000;} */
	${dd619.orderingActivityName }</textarea><input type="text" name="q-6a" class="q-text" style="width:243px;height:22px; font-size: 13px;" value="${dd619.orderingActivityName }"
-->
</STYLE>
</head>
<strong>
<BODY bgcolor="#A0A0A0" vlink="blue" link="blue" style="margin:0px;">
<!-- Page 1 -->
<a name="1"></a>
<fmt:parseDate value="${memorandumMap['06'].sitStartDate }" var="sitStartDate" pattern="yyyyMMdd"/>
<fmt:parseDate value="${memorandumMap['07'].sitEndDate }" var="sitEndDate" pattern="yyyyMMdd"/>
<DIV id="page1-div" style="position:relative;width:1224px;height:1584px;"> 
<IMG width="1224px" height="1584px" src="<c:url value='/resources/images/gbl/DDForm6191001.png' />" alt="background image"/>
<P style="position:absolute;top:324px;left:79px;white-space:nowrap" class="ft115"><input type="text" name="q-1" class="q-text q-center" style="width:243px;height:20px;" value="${gbl.gblNo }"></input></P>
<P style="position:absolute;top:324px;left:343px;white-space:nowrap" class="ft115"><input type="text" name="q-2" class="q-text q-center" style="width:268px;height:20px;" value="${gbl.pud }"></input></P>
<P style="position:absolute;top:378px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-3a" class="q-text" style="width:533px;height:18px;" value="${gbl.shipperName }"></input></P>
<P style="position:absolute;top:425px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-3b" class="q-text q-center" style="width:243px;height:20px;" value="XXX-XX-${fn:substring(gbl.ssn, 5, 9) }"></input></P>
<P style="position:absolute;top:425px;left:343px;white-space:nowrap" class="ft12"><input type="text" name="q-3c" class="q-text q-center" style="width:268px;height:20px;" value="${gbl.rank }"></input></P>
<P style="position:absolute;top:473px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-4" class="q-text q-center" style="width:243px;height:20px;" value="${gblock.remark }"></input></P>
<P style="position:absolute;top:473px;left:343px;white-space:nowrap" class="ft12"><input type="text" name="q-5" class="q-text q-center" style="width:268px;height:20px;" value="${gbl.fright }"></input></P>
<P style="position:absolute;top:537px;left:79px;white-space:nowrap" class="ft114"><textarea name="q-6a" style="width:243px; font-size:10pt;height:32px;font-weight:bolder;font-family: arial">${dd619.orderingActivityName }</textarea></P>
<P style="position:absolute;top:521px;left:343px;white-space:nowrap" class="ft12"><input name="q-6b" class="q-text" style="width:276px;height:50px;" value="" /></P>
<P style="position:absolute;top:593px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-7a" class="q-text" style="width:243px;height:18px;" value="${ dd619.carrierName}"></input></P>
<P style="position:absolute;top:593px;left:343px;white-space:nowrap" class="ft12"><input type="text" name="q-7b" class="q-text" style="width:268px;height:18px;" value="${dd619.agentName }"></input></P>
<P style="position:absolute;top:642px;left:79px;white-space:nowrap" class="ft12"><textarea name="q-8" class="q-text q-center" style="width:370px;height:40px;">${dd619.signature }</textarea></P>
<P style="position:absolute;top:662px;left:475px;white-space:nowrap" class="ft115"><input type="text" name="q-9" class="q-text q-center" style="width:135px;height:28px;" value="${fn:substring(sitEndDate, 8, 10) }-${ fn:substring(sitEndDate, 4, 7)}-${ fn:substring(sitEndDate, 26, 28) }"></input></P>
<P style="position:absolute;top:713px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-10" class="q-text q-center" style="width:293px;height:18px;" value="${gbl.tsp } # ${gbl.code}"></input></P>
<P style="position:absolute;top:713px;left:391px;white-space:nowrap" class="ft12"><input type="text" name="q-11" class="q-text q-center" style="width:220px;height:18px;" value="${gbl.pcs }/${(gbl.code eq '3' or gbl.code eq'4' or gbl.code eq'5' or gbl.code eq 'T') ? gbl.netWeight : gbl.grossWeight}/${gbl.cuft}"></input></P>
<P style="position:absolute;top:773px;left:79px;white-space:nowrap" class="ft15"><textarea name="q-12" class="q-text" style="width: 520px;height:200px; border: none;">${dd619.remark }</textarea></P>
<P style="position:absolute;top:333px;left:631px;white-space:nowrap" class="ft12"><input type="text" name="q-13a" class="q-text q-center" style="width:244px;height:42px;" value="${gbl.storedAt }"></input></P>
<P style="position:absolute;top:363px;left:894px;white-space:nowrap" class="ft12"><input type="checkbox" name="q-13b" class="q-radio" value="destination"></input></P>
<P style="position:absolute;top:363px;left:1039px;white-space:nowrap" class="ft12"><input type="checkbox" name="q-13b" class="q-radio" value="other"></input></P>


<c:if test="${sitStartDate ne '' }">
	<P style="position:absolute;top:402px;left:631px;white-space:nowrap" class="ft115"><input type="text" name="q-13c" class="q-text q-center" style="width:136px;height:44px;" value="${fn:substring(sitStartDate, 8, 10) }-${ fn:substring(sitStartDate, 4, 7)}-${ fn:substring(sitStartDate, 26, 28) }"></input></P>
	<fmt:formatDate value="${sitStartDate }" pattern="D" var="julianStartDate" />
</c:if>
<c:if test="${sitEndDate ne '' }">
	<P style="position:absolute;top:402px;left:787px;white-space:nowrap" class="ft115"><input type="text" name="q-13d" class="q-text q-center" style="width:126px;height:44px;" value="${fn:substring(sitEndDate, 8, 10) }-${ fn:substring(sitEndDate, 4, 7)}-${ fn:substring(sitEndDate, 26, 28) }" /> </P>
	<fmt:formatDate value="${sitEndDate }" pattern="D" var="julianEndDate" />
</c:if>

<c:choose>
	<c:when test="${diffSitYearJulian ne '' }">
		<c:set var="number13" value="${diffSitYearJulian}" />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${sitStartDate ne null and sitStartDate ne '' and sitEndDate ne null and sitEndDate ne ''}">
				<c:set var="number13" value="${julianEndDate - julianStartDate + 1 }" />
			</c:when>
			<c:otherwise>
				<c:set var="number13" value=" "/>
			</c:otherwise>
		</c:choose>
		
	</c:otherwise>
</c:choose>


<P style="position:absolute;top:402px;left:931px;white-space:nowrap" class="ft115"><input type="text" name="q-13e" class="q-text q-center" style="width:90px;height:44px;" value="${number13 }"></P>
<P style="position:absolute;top:402px;left:1036px;white-space:nowrap" class="ft12"><input type="text" name="q-13f" class="q-text q-center" style="width:105px;height:44px;" value="<fmt:formatNumber value="${weight.net}" pattern="#,##0"/>"></P>
<P style="position:absolute;top:496px;left:658px;white-space:nowrap" class="ft12"><input type="text" name="q-13g1" class="q-text q-center" style="width:338px;height:24px;" value="${memorandumMap['05'].sitNo }"></P>
<P style="position:absolute;top:531px;left:1014px;white-space:nowrap" class="ft12"><input type="radio" name="q-13g2" class="q-radio" value="yes"></input></P>
<P style="position:absolute;top:531px;left:1087px;white-space:nowrap" class="ft12"><input type="radio" name="q-13g2" class="q-radio" value="no"></input></P>
<P style="position:absolute;top:570px;left:631px;white-space:nowrap" class="ft12"><input type="text" name="q-13h" class="q-text" style="width:364px;height:44px;" value="${branch.itoChief }"></P>
<P style="position:absolute;top:590px;left:1015px;white-space:nowrap" class="ft115"><input type="text" name="q-13i" class="q-text q-center" style="width:125px;height:24px;"></P>
<P style="position:absolute;top:648px;left:800px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.gross}" pattern="#,##0"/></P>
<P style="position:absolute;top:648px;left:1064px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.reGross }" pattern="#,##0"/></P>
<P style="position:absolute;top:672px;left:800px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.tare}" pattern="#,##0"/></P>
<P style="position:absolute;top:672px;left:1064px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.reTare}" pattern="#,##0"/></P>
<P style="position:absolute;top:696px;left:800px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.net}" pattern="#,##0"/></P>
<P style="position:absolute;top:696px;left:1064px;white-space:nowrap" class="ft12"><fmt:formatNumber value="${ weight.reNet}" pattern="#,##0"/></P>
<P style="position:absolute;top:790px;left:631px;white-space:nowrap" class="ft12"><input type="text" name="q-14g1" class="q-text" style="width:364px;height:40px;"></P>
<P style="position:absolute;top:805px;left:1015px;white-space:nowrap" class="ft115"><input type="text" name="q-14g2" class="q-text q-center" style="width:125px;height:24px;"></P>
<P style="position:absolute;top:872px;left:859px;white-space:nowrap" class="ft16"><input type="text" name="q-15a1" class="q-text q-right" style="width:67px;height:28px;"></P>
<P style="position:absolute;top:872px;left:947px;white-space:nowrap" class="ft16"><input type="text" name="q-15a2" class="q-text q-right" style="width:85px;height:28px;"></P>
<P style="position:absolute;top:923px;left:859px;white-space:nowrap" class="ft116"><input type="text" name="q-15b1" class="q-text q-right" style="width:67px;height:24px;"></P>
<P style="position:absolute;top:923px;left:947px;white-space:nowrap" class="ft116"><input type="text" name="q-15b2" class="q-text q-right" style="width:85px;height:24px;"></P>
<P style="position:absolute;top:971px;left:859px;white-space:nowrap" class="ft117"><input type="text" name="q-15c1" class="q-text q-right" style="width:67px;height:24px;"></P>
<P style="position:absolute;top:971px;left:947px;white-space:nowrap" class="ft117"><input type="text" name="q-15c2" class="q-text q-right" style="width:85px;height:24px;"></P>
<P style="position:absolute;top:1104px;left:75px;white-space:nowrap" class="ft12"></P>
<P style="position:absolute;top:1104px;left:855px;white-space:nowrap" class="ft12"><input type="text" name="q-16b-1" class="q-text q-center" style="width:292px;height:42px;"></P>
<P style="position:absolute;top:1152px;left:75px;white-space:nowrap" class="ft12"></P>
<P style="position:absolute;top:1152px;left:855px;white-space:nowrap" class="ft12"><input type="text" name="q-16b-2" class="q-text q-center" style="width:292px;height:42px;"></P>
<P style="position:absolute;top:1200px;left:75px;white-space:nowrap" class="ft12"></P>
<P style="position:absolute;top:1200px;left:855px;white-space:nowrap" class="ft12"><input type="text" name="q-16b-3" class="q-text q-center" style="width:292px;height:42px;"></P>
<P style="position:absolute;top:1248px;left:75px;white-space:nowrap" class="ft12"></P>
<P style="position:absolute;top:1248px;left:855px;white-space:nowrap" class="ft12"><input type="text" name="q-16b-4" class="q-text q-center" style="width:292px;height:42px;"></P>
<P style="position:absolute;top:1315px;left:475px;white-space:nowrap" class="ft12"></P>
<P style="position:absolute;top:1315px;left:727px;white-space:nowrap" class="ft12"><input type="text" name="q-17b" class="q-text" style="width:410px;height:40px;"></input></P>
<P style="position:absolute;top:1410px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-18a" class="q-text" style="width:375px;height:18px;" value="${company.companyNameAcronym }"></input></P>
<P style="position:absolute;top:1410px;left:475px;white-space:nowrap" class="ft12"><input type="text" name="q-18b" class="q-text" style="width:365px;height:18px; font-size:10pt;" value="${gbl.address }"></input></P>
<P style="position:absolute;top:1458px;left:79px;white-space:nowrap" class="ft12"><input type="text" name="q-18c" class="q-text" style="width:760px;height:36px;"></input></P>
<P style="position:absolute;top:1458px;left:859px;white-space:nowrap" class="ft12"><input type="text" name="q-18d" class="q-text q-center" style="width:280px;height:36px;"></input></P>
</DIV>
</BODY>
</strong>
</html>