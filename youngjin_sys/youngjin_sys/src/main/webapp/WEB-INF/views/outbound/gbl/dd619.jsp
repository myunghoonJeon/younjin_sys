<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%	pageContext.setAttribute("enter", "\r\n");%>
<c:set var="lf" value='<%=pageContext.getAttribute("enter") %>' />

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
</head>
<body bgcolor="#A0A0A0" vlink="blue" link="blue">
<!-- Page 1 -->
<a name="1"></a>
<style type="text/css"> 
	#page1-div input{
		border:1px solid white;
	}
	#page1-div textarea{
		border:none;
	}
	#page1-div checkbox{
		border:none;
		background-color: transparent;
	}
	#page1-div radio{
		border:none;;
	}
	input{heigt:5px;font-family:arial; font-size:13px; border:0px dashed gray; font-weight:bolder;}
	.q-text {font-family:arial; font-size:13px; border:0px dashed gray; font-weight:bolder;}
	.q-text input{height:20px; font-size: 12pt; font-family:arial;}
	p {margin: 0; padding: 0;}	.ft10{font-size:7px;font-family:Times;color:#000000;}
	.ft11{font-size:7px;font-family:Times;color:#000000;}
	.ft12{font-size:13px;font-family:Times;color:#000000;}
	.ft13{font-size:9px;font-family:Times;color:#000000;}
	.ft14{font-size:7px;font-family:Times;color:#000000;}
	.ft15{font-size:8px;font-family:Times;color:#000000;}
	.ft16{font-size:9px;font-family:Times;color:#000000;}
	.ft17{font-size:9px;font-family:Times;color:#000000;}
	.ft18{font-size:12px;font-family:Times;color:#000000;}
	.ft19{font-size:13px;font-family:Times;color:#000000;}
	.ft110{font-size:10px;font-family:Times;color:#000000;}
	.ft111{font-size:7px;line-height:11px;font-family:Times;color:#000000;}
	.ft112{font-size:7px;line-height:10px;font-family:Times;color:#000000;}
	.ft113{font-size:8px;line-height:13px;font-family:Times;color:#000000;}
	.ft114{font-size:9px;line-height:15px;font-family:Times;color:#000000;}
	.ft115{font-size:7px;line-height:9px;font-family:Times;color:#000000;}
	.ft116{font-size:12px;line-height:29px;font-family:Times;color:#000000;}
	.ft117{font-size:13px;line-height:26px;font-family:Times;color:#000000;}
	.ft118{font-size:13px;line-height:28px;font-family:Times;color:#000000;}
	.ft119{font-size:12px;line-height:22px;font-family:Times;color:#000000;}
	.ft120{font-size:13px;line-height:22px;font-family:Times;color:#000000;}
</style>
<div id="page1-div" style="position:relative;width:1224px;height:1584px;">
<img width="1224px" height="1584px" src="<c:url value='/resources/images/gbl/DD619.jpg'/>" alt="background image"/>


<!-- ///////////////////////////////////////////input no ga da////////////////////////////////////////////////////////////////////// -->

<p style="position:absolute;top:247px;left:85px;white-space:nowrap" class="q-text"><input id="q-1" value="${dd619.gblNo  }"/></p>
<p style="position:absolute;top:247px;left:470px;white-space:nowrap" class="q-text"><input id="q-2" style="width: 110px;" value="${gbl.pud}"/></p>
<p style="position:absolute;top:293px;left:85px;white-space:nowrap" class="q-text"><input id="q-3a" value="${dd619.name }"/></p>
<p style="position:absolute;top:345px;left:85px;white-space:nowrap" class="q-text"><input id="q-3b" value="XXX-XX-${dd619.ssn }"/></p>
<p style="position:absolute;top:345px;left:350px;white-space:nowrap" class="q-text"><input id="q-3c" value="${gbl.rank }" style="width:100px;text-align: center;"/></p>
<p style="position:absolute;top:395px;left:85px;white-space:nowrap" class="q-text"><input id="q-4" value="${gbl.originAddress }" style="width: "/></p>
<p style="position:absolute;top:395px;left:350px;white-space:nowrap" class="q-text"><input id="q-5" value="${gblock.remark }"/></p>
<p style="position:absolute;top:453px;left:80px;white-space:nowrap"><textarea id="q-6a" cols="37" rows="2" style="font-size: 10pt; font-weight: bold; font-family: arial; border: hidden; resize:none; overflow: hidden;">${dd619.orderingActivityName }</textarea></p>
<p style="position:absolute;top:453px;left:350px;white-space:nowrap" class="q-text"><input id="q-6b" style="width:200px;height:30px;" value=""/></p>
<p style="position:absolute;top:515px;left:85px;white-space:nowrap" class="q-text"><input id="q-7a" style="width:240px; "value="${dd619.carrierName }"/></p>
<p style="position:absolute;top:515px;left:350px;white-space:nowrap" class="q-text"><input id="q-7b" value="${dd619.agentName }"/></p>
<p style="position:absolute;top:565px;left:85px;white-space:nowrap" class="q-text"><input id="q-8" style="width:250px;"value="${dd619.signature }"/></p>
<p style="position:absolute;top:580px;left:495px;white-space:nowrap" class="q-text"><input id="q-9" style="width:80px;"value=""></p>
<p style="position:absolute;top:630px;left:85px;white-space:nowrap" class="q-text"><input id="q-10" value="${dd619.carrierShipmentReference }"/></p>
<p style="position:absolute;top:630px;left:460px;white-space:nowrap" class="q-text"><input id="q-11" value="${dd619.code }" style="width:100px;text-align: center;"/></p>
<!-- 
<p style="position:absolute;top:750px;left:495px;white-space:nowrap" class="q-text"><input name='lbs' style="width: 70px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:795px;left:85px;white-space:nowrap" class="q-text"><input name='13-a' style="width: 125px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:795px;left:202px;white-space:nowrap" class="q-text"><input name='13-2' style="width: 40px; height: 10px; font-size: 7pt;"></p>
 
<p style="position:absolute;top:758px;left:335px;white-space:nowrap" class="q-text"><input type="checkbox" name="13-b" value="ORIGIN" checked="checked"></p>
<p style="position:absolute;top:758px;left:309px;white-space:nowrap" class="q-text"><input type="checkbox" name="13-b" value="DESTINATION"checked="checked"></p>
<p style="position:absolute;top:758px;left:395px;white-space:nowrap" class="ft14"><input type="checkbox" name="13-b" value="OTHER"checked="checked"></p>
 
<p style="position:absolute;top:649px;left:85px;white-space:nowrap" class="q-text"><input name='13-c' style="width: 50px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:649px;left:135px;white-space:nowrap" class="q-text"><input name='13-d' style="width: 70px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:649px;left:221px;white-space:nowrap" class="q-text"><input name='13-e' style="width: 80px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:649px;left:313px;white-space:nowrap" class="q-text"><input name='13-f' style="width: 45px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:649px;left:373px;white-space:nowrap" class="q-text"><input name='13-g' style="width: 65px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:689px;left:85px;white-space:nowrap" class="q-text"><input name='13-h' style="width: 100px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:689px;left:191px;white-space:nowrap" class="q-text"><input name='13-i' style="width: 250px; height: 9px; font-size: 7pt;"></p>


<p style="position:absolute;top:705px;left:347px;white-space:nowrap" class="q-text"><input name="13-j" type="checkbox" value="yes"></p>
<p style="position:absolute;top:705px;left:396px;white-space:nowrap" class="q-text"><input name="13-j" type="checkbox" value="no"></p>
  
<p style="position:absolute;top:724px;left:316px;white-space:nowrap" class="q-text"><input name='14-a' style="width: 120px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:742px;left:160px;white-space:nowrap" class="q-text"><input name='14-b' style="width: 84px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:770px;left:160px;white-space:nowrap" class="q-text"><input name='14-d' style="width: 84px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:778px;left:160px;white-space:nowrap" class="q-text"><input name='14-f' style="width: 84px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:742px;left:360px;white-space:nowrap" class="q-text"><input name='14-c' style="width: 84px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:770px;left:360px;white-space:nowrap" class="q-text"><input name='14-e' style="width: 84px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:748px;left:360px;white-space:nowrap" class="q-text"><input name='14-g' style="width: 84px; height: 9px; font-size: 7pt;"></p>

<p style="position:absolute;top:810px;left:56px;white-space:nowrap" class="q-text"><input name='15-a-1' style="width: 121px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:826px;left:56px;white-space:nowrap" class="q-text"><input name='15-a-2' style="width: 121px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:842px;left:56px;white-space:nowrap" class="q-text"><input name='15-a-3' style="width: 121px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:858px;left:56px;white-space:nowrap" class="q-text"><input name='15-a-4' style="width: 121px; height: 9px; font-size: 7pt;"></p>

<p style="position:absolute;top:810px;left:185px;white-space:nowrap" class="q-text"><input name='15-b-1' style="width: 175px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:826px;left:185px;white-space:nowrap" class="q-text"><input name='15-b-2' style="width: 175px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:842px;left:185px;white-space:nowrap" class="q-text"><input name='15-b-3' style="width: 175px; height: 9px; font-size: 7pt;"></p>
<p style="position:absolute;top:858px;left:185px;white-space:nowrap" class="q-text"><input name='15-b-4' style="width: 175px; height: 9px; font-size: 7pt;"></p>

<p style="position:absolute;top:810px;left:369px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:826px;left:369px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:842px;left:369px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:858px;left:369px;white-space:nowrap" class="q-text"></p>
-->
<p style="position:absolute;top:1180px;left:75px; white-space: pre-line; font-size: 14px; font-weight: bold;" class="q-text"><textarea cols="145" rows="5" style="font-size: 15px; font-weight:bold;font-family: arial; overflow: hidden; resize:none;">${fn:replace(dd619.remark, lf, '<br/>') }</textarea></p>
<!-- 
<p style="position:absolute;top:993px;left:56px;white-space:nowrap" class="q-text"><input name="18-a" type="checkbox" value="atorigin" checked="checked"></p>
<p style="position:absolute;top:1010px;left:56px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:993px;left:164px;white-space:nowrap" class="q-text"></p>
 -->
<p style="position:absolute;top:1340px;left:300px;white-space:nowrap" class="q-text"><input name='18-a' style="width: 235px; height: 20px; "></p>
<p style="position:absolute;top:1330px;left:561px;white-space:nowrap" class="q-text"><input name="18-b" style="width: 350px;" value="${dd619.officerSignature }"/></p>
<p style="position:absolute;top:1340px;left:980px;white-space:nowrap" class="ft111"><input name="18-c" style="width:150px;height:20px;"value="${dd619.officerDate }"/></p>
<!-- 
<p style="position:absolute;top:1056px;left:55px;white-space:nowrap" class="q-text"><input name="19-1" type="checkbox" value="accessorialservice" checked="checked"></p>
<p style="position:absolute;top:1072px;left:55px;white-space:nowrap" class="q-text"><input name="19-2" type="checkbox" value="storageintransit" checked="checked"></p>
<p style="position:absolute;top:1042px;left:293px;white-space:nowrap" class="q-text"><input name="19-3" type="checkbox" value="reweighcertification" checked="checked"></p>
<p style="position:absolute;top:1059px;left:293px;white-space:nowrap" class="q-text"><input name="19-4" type="checkbox" value="thirdpartyservices" checked="checked"></p>
<p style="position:absolute;top:1073px;left:293px;white-space:nowrap" class="q-text"><input name="19-5" type="checkbox" value="bulkyarticlecharge" checked="checked"></p>
<p style="position:absolute;top:1043px;left:455px;white-space:nowrap" class="q-text"><input name="19-6" type="checkbox" value="watingtime" checked="checked"></p>
<p style="position:absolute;top:1058px;left:455px;white-space:nowrap" class="q-text"><input name="19-7" type="checkbox" value="unpackingservice" checked="checked"></p>
<p style="position:absolute;top:1073px;left:455px;white-space:nowrap" class="q-text"><input name="19-8" type="checkbox" value="overtimeloading" checked="checked"></p>
<p style="position:absolute;top:1043px;left:681px;white-space:nowrap" class="q-text"><input name="19-9" type="checkbox" value="other" checked="checked"></p>
 -->
<p style="position:absolute;top:1470px;left:100px;white-space:nowrap" class="q-text"><input name="19-b"style="width  :400px;height:30px;" value="${gbl.rank } ${gbl.customerName }"/></p>
<p style="position:absolute;top:1105px;left:85px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:1105px;left:421px;white-space:nowrap" class="q-text"></p>
<p style="position:absolute;top:1485px;left:990px;white-space:nowrap" class="q-text"><input name="19-d"style="width:150px;height:20px;" value="${dd619.transportationDate }"/></p>
<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!-- //////////////////////////////////////////////////RIGHT SIDE!!!! no ga da//////////////////////////////////////////////////////////////////// -->
<!-- 
<p style="position:absolute;top:225px;left:681px;white-space:nowrap" class="q-text"><input name='16-a-2' style="width: 36px; height: 9px; font-size: 8pt;"></p>
<p style="position:absolute;top:225px;left:724px;white-space:nowrap" class="q-text"><input name='16-a-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:225px;left:794px;white-space:nowrap" class="q-text"><input name='16-a-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:244px;left:681px;white-space:nowrap" class="q-text"><input name='16-b-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:244px;left:724px;white-space:nowrap" class="q-text"><input name='16-b-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:244px;left:794px;white-space:nowrap" class="q-text"><input name='16-b-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:262px;left:681px;white-space:nowrap" class="q-text"><input name='16-c-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:262px;left:724px;white-space:nowrap" class="q-text"><input name='16-c-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:262px;left:794px;white-space:nowrap" class="q-text"><input name='16-c-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:280px;left:681px;white-space:nowrap" class="q-text"><input name='16-d-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:280px;left:724px;white-space:nowrap" class="q-text"><input name='16-d-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:280px;left:794px;white-space:nowrap" class="q-text"><input name='16-d-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:298px;left:681px;white-space:nowrap" class="q-text"><input name='16-e-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:298px;left:724px;white-space:nowrap" class="q-text"><input name='16-e-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:298px;left:794px;white-space:nowrap" class="q-text"><input name='16-e-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:316px;left:681px;white-space:nowrap" class="q-text"><input name='16-f-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:316px;left:724px;white-space:nowrap" class="q-text"><input name='16-f-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:316px;left:794px;white-space:nowrap" class="q-text"><input name='16-f-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:334px;left:681px;white-space:nowrap" class="q-text"><input name='16-g-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:334px;left:724px;white-space:nowrap" class="q-text"><input name='16-g-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:334px;left:794px;white-space:nowrap" class="q-text"><input name='16-g-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:352px;left:681px;white-space:nowrap" class="q-text"><input name='16-h-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:352px;left:724px;white-space:nowrap" class="q-text"><input name='16-h-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:352px;left:794px;white-space:nowrap" class="q-text"><input name='16-h-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:370px;left:681px;white-space:nowrap" class="q-text"><input name='16-i-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:370px;left:724px;white-space:nowrap" class="q-text"><input name='16-i-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:370px;left:794px;white-space:nowrap" class="q-text"><input name='16-i-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:388px;left:681px;white-space:nowrap" class="q-text"><input name='16-j-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:388px;left:724px;white-space:nowrap" class="q-text"><input name='16-j-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:388px;left:794px;white-space:nowrap" class="q-text"><input name='16-j-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:406px;left:681px;white-space:nowrap" class="q-text"><input name='16-k-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:406px;left:724px;white-space:nowrap" class="q-text"><input name='16-k-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:406px;left:794px;white-space:nowrap" class="q-text"><input name='16-k-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:424px;left:681px;white-space:nowrap" class="q-text"><input name='16-l-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:424px;left:724px;white-space:nowrap" class="q-text"><input name='16-l-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:424px;left:794px;white-space:nowrap" class="q-text"><input name='16-l-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:442px;left:681px;white-space:nowrap" class="q-text"><input name='16-m-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:442px;left:724px;white-space:nowrap" class="q-text"><input name='16-m-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:442px;left:794px;white-space:nowrap" class="q-text"><input name='16-m-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:460px;left:681px;white-space:nowrap" class="q-text"><input name='16-n-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:460px;left:724px;white-space:nowrap" class="q-text"><input name='16-n-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:460px;left:794px;white-space:nowrap" class="q-text"><input name='16-n-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:478px;left:681px;white-space:nowrap" class="q-text"><input name='16-o-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:478px;left:724px;white-space:nowrap" class="q-text"><input name='16-o-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:478px;left:794px;white-space:nowrap" class="q-text"><input name='16-o-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:496px;left:681px;white-space:nowrap" class="q-text"><input name='16-p-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:496px;left:724px;white-space:nowrap" class="q-text"><input name='16-p-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:496px;left:794px;white-space:nowrap" class="q-text"><input name='16-p-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:514px;left:681px;white-space:nowrap" class="q-text"><input name='16-q-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:514px;left:724px;white-space:nowrap" class="q-text"><input name='16-q-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:514px;left:794px;white-space:nowrap" class="q-text"><input name='16-q-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:532px;left:681px;white-space:nowrap" class="q-text"><input name='16-r-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:532px;left:724px;white-space:nowrap" class="q-text"><input name='16-r-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:532px;left:794px;white-space:nowrap" class="q-text"><input name='16-r-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:550px;left:622px;white-space:nowrap" class="q-text"><input name='16-s-1' style="width: 45px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:550px;left:681px;white-space:nowrap" class="q-text"><input name='16-s-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:550px;left:724px;white-space:nowrap" class="q-text"><input name='16-s-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:550px;left:794px;white-space:nowrap" class="q-text"><input name='16-s-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>


<p style="position:absolute;top:570px;left:572px;white-space:nowrap" class="q-text"><input name='16-t-1-top' style="width: 75px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:585px;left:555px;white-space:nowrap" class="q-text"><input name='16-t-1-bottom' style="width: 82px; height: 10px; font-size: 7pt;"></p>
<p style="position:absolute;top:575px;left:681px;white-space:nowrap" class="q-text"><input name='16-t-2' style="width: 38px; height: 15px; font-size: 8pt;"></p>
<p style="position:absolute;top:575px;left:724px;white-space:nowrap" class="q-text"><input name='16-t-3' style="width: 65px; height: 15px; font-size: 8pt;"></p>
<p style="position:absolute;top:575px;left:794px;white-space:nowrap" class="q-text"><input name='16-t-4' style="width: 65px; height: 15px; font-size: 8pt;"></p>

<p style="position:absolute;top:610px;left:681px;white-space:nowrap" class="q-text"><input name='16-u-2' style="width: 38px; height: 15px; font-size: 8pt;"></p>
<p style="position:absolute;top:610px;left:724px;white-space:nowrap" class="q-text"><input name='16-u-3' style="width: 65px; height: 15px; font-size: 8pt;"></p>
<p style="position:absolute;top:610px;left:794px;white-space:nowrap" class="q-text"><input name='16-u-4' style="width: 65px; height: 15px; font-size: 8pt;"></p>

<p style="position:absolute;top:640px;left:681px;white-space:nowrap" class="q-text"><input name='16-v-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:640px;left:724px;white-space:nowrap" class="q-text"><input name='16-v-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:640px;left:794px;white-space:nowrap" class="q-text"><input name='16-v-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:658px;left:681px;white-space:nowrap" class="q-text"><input name='16-w-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:658px;left:724px;white-space:nowrap" class="q-text"><input name='16-w-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:658px;left:794px;white-space:nowrap" class="q-text"><input name='16-w-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:676px;left:681px;white-space:nowrap" class="q-text"><input name='16-x-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:676px;left:724px;white-space:nowrap" class="q-text"><input name='16-x-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:676px;left:794px;white-space:nowrap" class="q-text"><input name='16-x-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:710px;left:600px;white-space:nowrap" class="q-text"><input name='16-y-1' style="width: 70px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:705px;left:681px;white-space:nowrap" class="q-text"><input name='16-y-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:705px;left:724px;white-space:nowrap" class="q-text"><input name='16-y-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:705px;left:794px;white-space:nowrap" class="q-text"><input name='16-y-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:747px;left:455px;white-space:nowrap" class="q-text"><input name="16-z-pickup" type="checkbox" value="no"></p>
<p style="position:absolute;top:730px;left:551px;white-space:nowrap" class="q-text"><input name="16-z-delivery" type="checkbox" value="no"></p>
<p style="position:absolute;top:747px;left:551px;white-space:nowrap" class="q-text"><input name="16-z-services" type="checkbox" value="no"></p>
<p style="position:absolute;top:740px;left:681px;white-space:nowrap" class="q-text"><input name='16-z-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:740px;left:681px;white-space:nowrap" class="q-text"><input name='16-z-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:740px;left:724px;white-space:nowrap" class="q-text"><input name='16-z-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:740px;left:794px;white-space:nowrap" class="q-text"><input name='16-z-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:765px;left:681px;white-space:nowrap" class="q-text"><input name='16-aa-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:765px;left:724px;white-space:nowrap" class="q-text"><input name='16-aa-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:765px;left:794px;white-space:nowrap" class="q-text"><input name='16-aa-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:783px;left:681px;white-space:nowrap" class="q-text"><input name='16-bb-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:783px;left:724px;white-space:nowrap" class="q-text"><input name='16-bb-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:783px;left:794px;white-space:nowrap" class="q-text"><input name='16-bb-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>

<p style="position:absolute;top:808px;left:681px;white-space:nowrap" class="q-text"><input name='16-cc-2' style="width: 38px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:808px;left:724px;white-space:nowrap" class="q-text"><input name='16-cc-3' style="width: 65px; height: 10px; font-size: 8pt;"></p>
<p style="position:absolute;top:808px;left:794px;white-space:nowrap" class="q-text"><input name='16-cc-4' style="width: 65px; height: 10px; font-size: 8pt;"></p>
 -->
<c:set var="otherList" value="${fn:split(dd619.other, ',') }" />
<p style="position:absolute;top:1102px;left:910px;white-space:nowrap;"><input style="height:10px; width:50px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center" value="${otherList[0] }"/></p>
<p style="position:absolute;top:1102px;left:980px;white-space:nowrap;"><input style="height:10px; width:70px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center" value="${otherList[1] }"/></p>
<p style="position:absolute;top:1102px;left:1075px;white-space:nowrap;"><input style="height:10px; width:70px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center;" value="${otherList[2] }"/></p>

<c:set var="totalList" value="${fn:split(dd619.total, ',') }" />
<p style="position:absolute;top:1130px;left:910px;;white-space:nowrap" class="q-text"><input style="height:20px; width:50px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center;"value="${totalList[0] }"/></p>
<p style="position:absolute;top:1130px;left:980px;white-space:nowrap" class="q-text"><input style="height:20px; width:70px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center;"value="${totalList[1] }"/></p>
<p style="position:absolute;top:1130px;left:1075px;white-space:nowrap" class="q-text"><input style="height:20px; width:70px;font-size:8pt; font-weight:bold; font-family:arial; text-align: center;"value="${totalList[2] }"/></p>
<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

</div>
</body>
</html>
