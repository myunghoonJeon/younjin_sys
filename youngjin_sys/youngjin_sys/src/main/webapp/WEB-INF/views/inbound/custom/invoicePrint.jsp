<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="inboundInsuranceRate" value="0.6"/>
<c:set var="dollorPerCbm" value="85" />
<html xmlns="http://www.w3.org/1999/xhtml" lang="" xml:lang="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<style type="text/css"> 
<!--
	p {margin: 0; padding: 0;}	
	.ft10{font-size:18px; font-weight: bold; font-family:arial;color:#000000; }
	.ft11{font-size:18px; font-weight:bold; font-family:arial;color:#000000;}
	.ft12{font-size:21px;font-family:Times;color:#000000;}
-->
</style>
</head>

<body bgcolor="#A0A0A0" vlink="blue" link="blue" onload="window.print();" >
<div id="page1-div" style="position:relative;width:892px;height:1262px;">
<img width="892" height="1262" src="<c:url value='/resources/images/freight/inboundinvoice2.jpg'/>" alt="background image"/>


<!------------------------------------------------------------------------------------------------------------------------------------------------> 
<p style="position:absolute;top:209px;left:665px;white-space:nowrap" class="ft11">${ fn:substring(inboundInvoiceBasicInfo.invoiceDate, 0, 4)}-${fn:substring(inboundInvoiceBasicInfo.invoiceDate, 4, 6)}-${fn:substring(inboundInvoiceBasicInfo.invoiceDate, 6, 8)}</p>
<p style="position:absolute;top:272px;left:281px;white-space:nowrap" class="ft10">${ inboundInvoiceBasicInfo.name }</p>
<p style="position:absolute;top:323px;left:281px;white-space:nowrap" class="ft10">${ inboundInvoiceBasicInfo.gblNo }</p>
<p style="position:absolute;top:374px;left:281px;white-space:nowrap" class="ft10">${ inboundInvoiceBasicInfo.piece }</p>
<p style="position:absolute;top:425px;left:281px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${ inboundInvoiceBasicInfo.cuft }" type="number" /></p>
<p style="position:absolute;top:476px;left:281px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${ inboundInvoiceBasicInfo.grossWt }" type="number" /></p>
<p style="position:absolute;top:527px;left:281px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${ inboundInvoiceBasicInfo.netWt }" type="number" /></p>

<p style="position:absolute;top:618px;left:480px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${inboundInsuranceRate * 100 }" pattern="0" /></p>

<p style="position:absolute;top:642px;left:225px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${ inboundInvoiceBasicInfo.netWt }" type="number" /></p>
<p style="position:absolute;top:642px;left:390px;white-space:nowrap" class="ft10">${inboundInsuranceRate }</p>
<fmt:formatNumber var="lbsus" value="${inboundInvoiceBasicInfo.netWt * inboundInsuranceRate }" pattern="0" />
<p style="position:absolute;top:642px;left:556px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${lbsus }" type="number" /></p>

<p style="position:absolute;top:667px;left:285px;white-space:nowrap" class="ft10">${dollorPerCbm }</p>

<p style="position:absolute;top:692px;left:225px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${ inboundInvoiceBasicInfo.cbm }" type="number" pattern=".###" /></p>
<p style="position:absolute;top:692px;left:390px;white-space:nowrap" class="ft10">${dollorPerCbm }</p>
<fmt:formatNumber var="cbmus" value="${inboundInvoiceBasicInfo.cbm * dollorPerCbm }" pattern="0"/>
<p style="position:absolute;top:692px;left:556px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${cbmus }" type="number" /></p>

<p style="position:absolute;top:755px;left:575px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${cbmus + lbsus }" type="number" /></p>

<p style="position:absolute;top:821px;left:270px;white-space:nowrap" class="ft10">
	<c:choose>
		<c:when test="${inboundInvoiceBasicInfo.code eq '8' }">
			FRIGHT
		</c:when>
		<c:otherwise>
			VESSEL
		</c:otherwise>
	</c:choose>
</p>
<p style="position:absolute;top:821px;left:480px;white-space:nowrap" class="ft10">
	<c:choose>
		<c:when test="${inboundInvoiceBasicInfo.code eq '8' }">
			${inboundInvoiceBasicInfo.fright }
		</c:when>
		<c:otherwise>
			${inboundInvoiceBasicInfo.vessle }
		</c:otherwise>
	</c:choose>
</p>

<p style="position:absolute;top:871px;left:280px;white-space:nowrap" class="ft10">
	<c:choose>
		<c:when test="${inboundInvoiceBasicInfo.code eq '8' }">
			AWB
		</c:when>
		<c:otherwise>
			OBL
		</c:otherwise>
	</c:choose>	
</p>
<p style="position:absolute;top:871px;left:480px;white-space:nowrap" class="ft10">
	<c:choose>
		<c:when test="${inboundInvoiceBasicInfo.code eq '8' }">
			${inboundInvoiceBasicInfo.awbNo }
		</c:when>
		<c:otherwise>
			${inboundInvoiceBasicInfo.oblNo }
		</c:otherwise>
	</c:choose>
</p>

<p style="position:absolute;top:920px;left:480px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${inboundInvoiceBasicInfo.grossWtKg }" type="number" pattern="#,###"/></p>
<p style="position:absolute;top:968px;left:480px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${inboundInvoiceBasicInfo.netWtKg }" type="number" pattern="#,###"/></p>

<p style="position:absolute;top:1020px;left:480px;white-space:nowrap" class="ft10"><fmt:formatNumber value="${inboundInvoiceBasicInfo.itemsPieces }" type="number" pattern="#,###"/></p>
<!------------------------------------------------------------------------------------------------------------------------------------------------> 






</div>
</body>
</html>