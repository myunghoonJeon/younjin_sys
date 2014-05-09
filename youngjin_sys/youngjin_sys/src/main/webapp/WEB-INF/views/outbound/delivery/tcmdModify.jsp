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
<title>TCMD MODIFY</title>

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

<style type="text/css">
	p {
        margin: 0;
        padding: 0;
        font-size: 7pt;
    }
    
    input {
        position: absolute;
        border: 0px solid gray;
        font-size: 7pt;
        font-family: Arial, 돋움, san-serif;
        color: black;
        height: 14px;
    }
    
    .q-row-1 { top: 91px; font-size: 9pt; font-family: arial; font-weight: bold; text-align: center;}
    .q-row-2 { top: 124px;font-size: 9pt; font-family: arial; font-weight: bold; text-align: center;}
    .q-row-3 { top: 156px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-4 { top: 187px;font-size: 9pt; font-family: arial; font-weight: bold; text-align: center;}
    .q-row-5 { top: 220px;font-size: 9pt; font-family: arial; font-weight: bold; text-align: center;}
    .q-row-6 { top: 253px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-7 { top: 286px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    
    .q-row-A1 { top: 379px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A2 { top: 415px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A3 { top: 450px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A4 { top: 486px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A5 { top: 521px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A6 { top: 553px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    .q-row-A7 { top: 585px; font-size: 9pt; font-family: arial; font-weight: bold;text-align: center;}
    
</style>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body  bgcolor="#A0A0A0" vlink="blue" link="blue"> 
<div style="background-color: white;">
	<span class="yj_button" onclick="location.href='${cp}/outbound/delivery/mil/${tcmd.seq }/tcmdPrint'">print</span> 
</div>
<a name="1"></a>
<c:forEach var="page" begin="1" end="${pageNum }">
		<c:if test="${ page gt 0}">
			<div style='page-break-after: always'></div>
		</c:if>
		<c:set var="start" value="${(page-1)*3}"></c:set>
		<c:set var="end" value="${page*3 }"></c:set>
		<div><br/>start = ${start } end = ${end }</div>
		<!-- TO DO UNDER HERE -->
		<DIV id="page1-div" style="position:relative; width:1010px; height:714px;" data-tcmdSeq="${tcmd.seq }">
		<div class="tcmdInfo_wrap">
		<input name="army" style="top:35px; left:230px; width:100px; height:12px;" value="ARMY"/>
	    <input name="dodsponsred" style="top:35px; left:385px; width:100px; height:12px;" value="DOD-SPONSORED"/>
	    <input name="turnInDate" style="top:35px; left:545px; width:100px; height:12px;" value="TURN IN DATE : "/>
	    <input name="date" style="top:35px; left:630px; width:100px; height:12px;" value="26-AUG-13"/>
		
	    <IMG width="1010" height="714" src="<c:url value='/resources/images/DPM_TCMD-6.jpg'/>" alt="background image"/>
	    
	    <input type="text" name="yjn" style="top:56px; left:56px; width:150px; height:20px; font-size: 10pt; font-family: arial;" value="${tcmd.yjn }"/>
	    <input type="text" name="pageNo" style="top:63px; left:869px; width:92px; height:12px;" value="${tcmd.pageNo }" />
	    
	    
	    <input name="docId"     type="text" class="q-row-1" style="left:56px;  width:68px;" value="${tcmd.docId }" />
	    <input name="trirCont"     type="text" class="q-row-1" style="left:131px; width:52px;"  />
	    <input name="consignor"     type="text" class="q-row-1" style="left:190px; width:206px;" value="${tcmd.consignor }"/>
	    <input name="commSpecHdig"     type="text" class="q-row-1" style="left:403px; width:191px;" value="${tcmd.commSpecHdig}" />
	    <input name="airDim"     type="text" class="q-row-1" style="left:601px; width:46px;" value="${tcmd.airDim}" />
	    <input name="poe"     type="text" class="q-row-1" style="left:655px; width:167px;" value="${tcmd.poe}"/>
	    <input name="pod"     type="text" class="q-row-1" style="left:829px; width:132px;" value="${tcmd.pod}"/> 
	        
	    <input name="mode"     type="text" class="q-row-2" style="left:56px;  width:68px;" value="${tcmd.mode}" />
	    <input name="pack"     type="text" class="q-row-2" style="left:131px; width:52px;" value="${tcmd.pack}" />
	    <input name="transControlNo1"    type="text" class="q-row-2" style="left:190px; width:40px;" value="${tcmd.transControlNo1}"/>
	    <input name="transControlNo2"    type="text" class="q-row-2" style="left:230px; width:40px;" value="${tcmd.transControlNo2}"/>
	    <input name="transControlNo3"    type="text" class="q-row-2" style="left:270px; width:40px;" value="${tcmd.transControlNo3}"/>
	    <input name="transControlNo4"    type="text" class="q-row-2" style="left:310px; width:40px;" value="${tcmd.transControlNo4}"/>
	    <input name="transControlNo5"    type="text" class="q-row-2" style="left:350px; width:40px;" value="${tcmd.transControlNo5}"/>
	    <input name="consignee"    type="text" class="q-row-2" style="left:403px; width:191px;" value="${tcmd.pod eq 'SUU' ? 'TRAVIS AFB, CA( ' : ' ' }${tcmd.consignee} ${tcmd.pod eq 'SUU' ? ')' : ' '}"/>
	    <input name="pri"    type="text" class="q-row-2" style="left:601px; width:46px;"  value="${tcmd.pri}"/>
	    <input name="rdd"    type="text" class="q-row-2" style="left:655px; width:47px;"  value="${tcmd.rdd}"/>
	    <input name="proj"    type="text" class="q-row-2" style="left:709px; width:51px;" value="${tcmd.proj}" />
	    <input name="dataShpd"    type="text" class="q-row-2" style="left:767px; width:55px;" value="${tcmd.dataShpd}" />
	    <input name="eta"    type="text" class="q-row-2" style="left:829px; width:44px;"  value="${tcmd.eta}"/>
	    <input name="trAcct"    type="text" class="q-row-2" style="left:880px; width:81px;" value="${tcmd.trAcct}" />
	    
	    <input name="carrier"    type="text" class="q-row-3" style="left:56px;  width:127px;" value="${tcmd.carrier}"/>
	    <input name="flightTruckVoyDoc"    type="text" class="q-row-3" style="left:190px; width:135px;" value="${tcmd.flightTruckVoyDoc}"/>
	    <input name="ref"    type="text" class="q-row-3" style="left:332px; width:64px;" value="${tcmd.ref}" />
	    <input name="remark"    type="text" class="q-row-3" style="left:402px; width:358px;"value="${tcmd.remark}" />
	    <input name="pieces"    type="text" class="q-row-3" style="left:767px; width:55px;"  value="${tcmd.pieces}"/>
	    <input name="weight"    type="text" class="q-row-3" style="left:829px; width:44px;"  value="${tcmd.weight}"/>
	    <input name="cube"    type="text" class="q-row-3" style="left:880px; width:81px;"  value="${tcmd.cube}"/>
	    
	    <input name="q-27a-1" type="text" class="q-row-4" style="left:56px;  width:139px;" />
	    <input name="q-27b-1" type="text" class="q-row-4" style="left:202px; width:50px;"  />
	    <input name="q-27c-1" type="text" class="q-row-4" style="left:259px; width:66px;"  />
	    <input name="q-27d-1" type="text" class="q-row-4" style="left:332px; width:93px;"  />
	    <input name="q-27e-1" type="text" class="q-row-4" style="left:432px; width:73px;"  />
	    <input name="q-27f-1" type="text" class="q-row-4" style="left:512px; width:159px;" />
	    <input name="q-27g-1" type="text" class="q-row-4" style="left:678px; width:24px;"  />
	    <input name="q-27h-1" type="text" class="q-row-4" style="left:709px; width:51px;"  />
	    <input name="q-27i-1" type="text" class="q-row-4" style="left:768px; width:53px;"  />
	    <input name="q-27j-1" type="text" class="q-row-4" style="left:829px; width:32px;"  />
	    <input name="q-27k-1" type="text" class="q-row-4" style="left:870px; width:90px;"  />
	    
	    <input name="q-27a-2" type="text" class="q-row-5" style="left:56px;  width:139px;" />
	    <input name="q-27b-2" type="text" class="q-row-5" style="left:202px; width:50px;"  />
	    <input name="q-27c-2" type="text" class="q-row-5" style="left:259px; width:66px;"  />
	    <input name="q-27d-2" type="text" class="q-row-5" style="left:332px; width:93px;"  />
	    <input name="q-27e-2" type="text" class="q-row-5" style="left:432px; width:73px;"  />
	    <input name="q-27f-2" type="text" class="q-row-5" style="left:512px; width:159px;" />
	    <input name="q-27g-2" type="text" class="q-row-5" style="left:678px; width:24px;"  />
	    <input name="q-27h-2" type="text" class="q-row-5" style="left:709px; width:51px;"  />
	    <input name="q-27i-2" type="text" class="q-row-5" style="left:768px; width:53px;"  />
	    <input name="q-27j-2" type="text" class="q-row-5" style="left:829px; width:32px;"  />
	    <input name="q-27k-2" type="text" class="q-row-5" style="left:870px; width:90px;"  />
	    
	    <input name="q-27a-3" type="text" class="q-row-6" style="left:56px;  width:139px;" />
	    <input name="q-27b-3" type="text" class="q-row-6" style="left:202px; width:50px;"  />
	    <input name="q-27c-3" type="text" class="q-row-6" style="left:259px; width:66px;"  />
	    <input name="q-27d-3" type="text" class="q-row-6" style="left:332px; width:93px;"  />
	    <input name="q-27e-3" type="text" class="q-row-6" style="left:432px; width:73px;"  />
	    <input name="q-27f-3" type="text" class="q-row-6" style="left:512px; width:159px;" />
	    <input name="q-27g-3" type="text" class="q-row-6" style="left:678px; width:24px;"  />
	    <input name="q-27h-3" type="text" class="q-row-6" style="left:709px; width:51px;"  />
	    <input name="q-27i-3" type="text" class="q-row-6" style="left:768px; width:53px;"  />
	    <input name="q-27j-3" type="text" class="q-row-6" style="left:829px; width:32px;"  />
	    <input name="q-27k-3" type="text" class="q-row-6" style="left:870px; width:90px;"  />
    </div>
    
    <input name="consignee2"    type="text" class="q-row-7" style="left:56px;  width:139px;" value="${tcmd.consignee2} "/>
    <input name="dataReceivedOffered"    type="text" class="q-row-7" style="left:202px; width:223px;" value="${tcmd.dataReceivedOffered}"/>
    <input name="condition"    type="text" class="q-row-7" style="left:432px; width:73px;" value="${tcmd.condition}" />
    <input name="remarks"    type="text" class="q-row-7" style="left:512px; width:448px;" value="${tcmd.remarks}"/>

	<div class="tcmdGbl_wrap">
		<c:forEach var="gbl" items="${gblList }" varStatus="i">
		<if test="${i.count gt start and i.count le end} ">
			<if test="${i.count*2-1 gt 6 }">
				<c:set var="roofFlag" value="${(i.count*2)-1-((page-1)*6) }"/>
			</if>
			<div data-seq="${gbl.seq }">
		    	<input name="q-32-1"  type="text" class="q-row-A${roofFlag }" style="left:56px;  width:21px;" value="${gbl.code eq 'J' ? 'TFD' : 'TH1' }" readonly="readonly" />
		   		<input name="q-33-1"  type="text" class="q-row-A${roofFlag  }" style="left:85px;  width:39px;" readonly="readonly" value="${i.count  }" />
		   		<c:choose>
		   			<c:when test="${gbl.areaLocal eq 'OS' }">
			    		<input name="q-34-1"  type="text" class="q-row-A${roofFlag  }" style="left:131px; width:52px;" value="FB5294" readonly="readonly"/>
			    	</c:when>
		   			<c:when test="${gbl.areaLocal eq 'KS' }">
			    		<input name="q-34-1"  type="text" class="q-row-A${roofFlag  }" style="left:131px; width:52px;" value="FB5284" readonly="readonly"/>
			    	</c:when>
			    	<c:otherwise>
			    		<input name="q-34-1"  type="text" class="q-row-A${roofFlag  }" style="left:131px; width:52px;" value="W81LYE" readonly="readonly"/>
			    	</c:otherwise>
			    </c:choose>
			    
			    <input name="q-35-1"  type="text" class="q-row-A${roofFlag}" style="left:190px; width:61px;" value="${tcmd.commSpecHdig }" readonly="readonly" />
			    <input name="q-36a-1" type="text" class="q-row-A${roofFlag  }" style="left:259px; width:33px;" value="${tcmd.airDim }" readonly="readonly" />
			    <input name="q-36b-1" type="text" class="q-row-A${roofFlag  }" style="left:300px; width:25px;" value="${gbl.poe }" readonly="readonly" />
			    <input name="q-37-1"  type="text" class="q-row-A${roofFlag  }" style="left:332px; width:24px;" value="${gbl.pod }" readonly="readonly" />
			    <input name="q-38-1"  type="text" class="q-row-A${roofFlag  }" style="left:363px; width:32px;" value="${tcmd.mode }" readonly="readonly" />
			    <input name="q-39-1"  type="text" class="q-row-A${roofFlag  }" style="left:403px; width:21px;" value="${tcmd.pack }" readonly="readonly" />
			    
			    <input name="q-40-1"  type="text" class="q-row-A${roofFlag  }" style="left:433px; width:160px;" value="${gbl.milSVC }${julianDate }${gbl.ssn }${gbl.jk}" readonly="readonly" />
			    <input name="consignee3"  type="text" class="q-row-A${roofFlag  }" style="left:601px; width:46px;" value="${gbl.tcmdConsignee3 }" readonly="readonly" />
			    <input name="pri2"  type="text" class="q-row-A${roofFlag  }" style="left:655px; width:15px;" value="2" />
			    <input name="remark_rdd" type="text" class="q-row-A${roofFlag  }" style="left:678px; width:30px;" value="${gbl.tcmdRddJulianDate }"/>
			    <input name="remark_proj" type="text" class="q-row-A${roofFlag  }" style="left:709px; width:51px;"  />
			    <input name="remark_shpd" type="text" class="q-row-A${roofFlag  }" style="left:767px; width:25px;"  />
			    <input name="remark_eta" type="text" class="q-row-A${roofFlag  }" style="left:800px; width:21px;"  />
			    <input name="remark_tac" type="text" class="q-row-A${roofFlag  }" style="left:813px; width:30px;" value="${gbl.remarkTac }"  />
			    <input name="q-44a-1" type="text" class="q-row-A${roofFlag  }" style="left:860px; width:15px;" value="${gbl.pcs }" readonly="readonly" />
			    <input name="q-44b-1" type="text" class="q-row-A${roofFlag  }" style="left:885px; width:37px;" value="${gbl.lbs }" readonly="readonly"  />
			    <input name="q-44c-1" type="text" class="q-row-A${roofFlag  }" style="left:933px; width:29px;" value="${gbl.cuft }" readonly="readonly"/>	
			     
			    <input name="q-32b-1" type="text" class="q-row-A${roofFlag*2}" style="left:56px;  width:21px;" value="${roofFlag*2 }"/>
			    <input name="q-40-1"  type="text" class="q-row-A${roofFlag*2}" style="left:433px; width:160px; background-color: transparent;" value="${gbl.no }  ${gbl.scac}" readonly="readonly" />
			    <input name="q-41-1"  type="text" class="q-row-A${roofFlag*2}" style="left:601px; width:46px; background-color: transparent;" value="${gbl.areaLocal }" readonly="readonly" />
			    <input name="q-43a-1" type="text" class="q-row-A${roofFlag*2}" style="left:678px; width:132px; background-color: transparent;text-align:left;" value="${gbl.customerName }   ${gbl.rank}" readonly="readonly"/>
			    <input name="q-44a-1" type="text" class="q-row-A${roofFlag*2}" style="left:854px; width:97px; background-color: transparent;text-align:left;" value="${fn:substring(gbl.ssn, 0, 3)}-${fn:substring(gbl.ssn, 3, 5)}-${fn:substring(gbl.ssn, 5, 9)}" readonly="readonly" />
			</div>
			</if>
			
		</c:forEach>
	</div>

</DIV>
</c:forEach>

</body>

</html>

<!-- 
    <input name="q-32-2"  type="text" class="q-row-B" style="left:56px;  width:21px;"  />
    <input name="q-33-2"  type="text" class="q-row-B" style="left:85px;  width:39px;"  />
    <input name="q-34-2"  type="text" class="q-row-B" style="left:131px; width:52px;"  />
    <input name="q-35-2"  type="text" class="q-row-B" style="left:190px; width:61px;"  />
    <input name="q-36a-2" type="text" class="q-row-B" style="left:259px; width:33px;"  />
    <input name="q-36b-2" type="text" class="q-row-B" style="left:300px; width:25px;"  />
    <input name="q-37-2"  type="text" class="q-row-B" style="left:332px; width:24px;"  />
    <input name="q-38-2"  type="text" class="q-row-B" style="left:363px; width:32px;"  />
    <input name="q-39-2"  type="text" class="q-row-B" style="left:403px; width:21px;"  />
    <input name="q-40-2"  type="text" class="q-row-B" style="left:433px; width:160px;" />
    <input name="q-41-2"  type="text" class="q-row-B" style="left:601px; width:46px;"  />
    <input name="q-42-2"  type="text" class="q-row-B" style="left:655px; width:15px;"  />
    <input name="q-43a-2" type="text" class="q-row-B" style="left:678px; width:23px;"  />
    <input name="q-43b-2" type="text" class="q-row-B" style="left:709px; width:51px;"  />
    <input name="q-43c-2" type="text" class="q-row-B" style="left:767px; width:25px;"  />
    <input name="q-43d-2" type="text" class="q-row-B" style="left:800px; width:21px;"  />
    <input name="q-43e-2" type="text" class="q-row-B" style="left:829px; width:12px;"  />
    <input name="q-44a-2" type="text" class="q-row-B" style="left:848px; width:25px;"  />
    <input name="q-44b-2" type="text" class="q-row-B" style="left:880px; width:40px;"  />
    <input name="q-44c-2" type="text" class="q-row-B" style="left:928px; width:32px;"  />

    <input name="q-32-3"  type="text" class="q-row-C" style="left:56px;  width:21px;"  />
    <input name="q-33-3"  type="text" class="q-row-C" style="left:85px;  width:39px;"  />
    <input name="q-34-3"  type="text" class="q-row-C" style="left:131px; width:52px;"  />
    <input name="q-35-3"  type="text" class="q-row-C" style="left:190px; width:61px;"  />
    <input name="q-36a-3" type="text" class="q-row-C" style="left:259px; width:33px;"  />
    <input name="q-36b-3" type="text" class="q-row-C" style="left:300px; width:25px;"  />
    <input name="q-37-3"  type="text" class="q-row-C" style="left:332px; width:24px;"  />
    <input name="q-38-3"  type="text" class="q-row-C" style="left:363px; width:32px;"  />
    <input name="q-39-3"  type="text" class="q-row-C" style="left:403px; width:21px;"  />
    <input name="q-40-3"  type="text" class="q-row-C" style="left:433px; width:160px;" />
    <input name="q-41-3"  type="text" class="q-row-C" style="left:601px; width:46px;"  />
    <input name="q-42-3"  type="text" class="q-row-C" style="left:655px; width:15px;"  />
    <input name="q-43a-3" type="text" class="q-row-C" style="left:678px; width:23px;"  />
    <input name="q-43b-3" type="text" class="q-row-C" style="left:709px; width:51px;"  />
    <input name="q-43c-3" type="text" class="q-row-C" style="left:767px; width:25px;"  />
    <input name="q-43d-3" type="text" class="q-row-C" style="left:800px; width:21px;"  />
    <input name="q-43e-3" type="text" class="q-row-C" style="left:829px; width:12px;"  />
    <input name="q-44a-3" type="text" class="q-row-C" style="left:848px; width:25px;"  />
    <input name="q-44b-3" type="text" class="q-row-C" style="left:880px; width:40px;"  />
    <input name="q-44c-3" type="text" class="q-row-C" style="left:928px; width:32px;"  />

    <input name="q-32-4"  type="text" class="q-row-D" style="left:56px;  width:21px;"  />
    <input name="q-33-4"  type="text" class="q-row-D" style="left:85px;  width:39px;"  />
    <input name="q-34-4"  type="text" class="q-row-D" style="left:131px; width:52px;"  />
    <input name="q-35-4"  type="text" class="q-row-D" style="left:190px; width:61px;"  />
    <input name="q-36a-4" type="text" class="q-row-D" style="left:259px; width:33px;"  />
    <input name="q-36b-4" type="text" class="q-row-D" style="left:300px; width:25px;"  />
    <input name="q-37-4"  type="text" class="q-row-D" style="left:332px; width:24px;"  />
    <input name="q-38-4"  type="text" class="q-row-D" style="left:363px; width:32px;"  />
    <input name="q-39-4"  type="text" class="q-row-D" style="left:403px; width:21px;"  />
    <input name="q-40-4"  type="text" class="q-row-D" style="left:433px; width:160px;" />
    <input name="q-41-4"  type="text" class="q-row-D" style="left:601px; width:46px;"  />
    <input name="q-42-4"  type="text" class="q-row-D" style="left:655px; width:15px;"  />
    <input name="q-43a-4" type="text" class="q-row-D" style="left:678px; width:23px;"  />
    <input name="q-43b-4" type="text" class="q-row-D" style="left:709px; width:51px;"  />
    <input name="q-43c-4" type="text" class="q-row-D" style="left:767px; width:25px;"  />
    <input name="q-43d-4" type="text" class="q-row-D" style="left:800px; width:21px;"  />
    <input name="q-43e-4" type="text" class="q-row-D" style="left:829px; width:12px;"  />
    <input name="q-44a-4" type="text" class="q-row-D" style="left:848px; width:25px;"  />
    <input name="q-44b-4" type="text" class="q-row-D" style="left:880px; width:40px;"  />
    <input name="q-44c-4" type="text" class="q-row-D" style="left:928px; width:32px;"  />

    <input name="q-32-5"  type="text" class="q-row-E" style="left:56px;  width:21px;"  />
    <input name="q-33-5"  type="text" class="q-row-E" style="left:85px;  width:39px;"  />
    <input name="q-34-5"  type="text" class="q-row-E" style="left:131px; width:52px;"  />
    <input name="q-35-5"  type="text" class="q-row-E" style="left:190px; width:61px;"  />
    <input name="q-36a-5" type="text" class="q-row-E" style="left:259px; width:33px;"  />
    <input name="q-36b-5" type="text" class="q-row-E" style="left:300px; width:25px;"  />
    <input name="q-37-5"  type="text" class="q-row-E" style="left:332px; width:24px;"  />
    <input name="q-38-5"  type="text" class="q-row-E" style="left:363px; width:32px;"  />
    <input name="q-39-5"  type="text" class="q-row-E" style="left:403px; width:21px;"  />
    <input name="q-40-5"  type="text" class="q-row-E" style="left:433px; width:160px;" />
    <input name="q-41-5"  type="text" class="q-row-E" style="left:601px; width:46px;"  />
    <input name="q-42-5"  type="text" class="q-row-E" style="left:655px; width:15px;"  />
    <input name="q-43a-5" type="text" class="q-row-E" style="left:678px; width:23px;"  />
    <input name="q-43b-5" type="text" class="q-row-E" style="left:709px; width:51px;"  />
    <input name="q-43c-5" type="text" class="q-row-E" style="left:767px; width:25px;"  />
    <input name="q-43d-5" type="text" class="q-row-E" style="left:800px; width:21px;"  />
    <input name="q-43e-5" type="text" class="q-row-E" style="left:829px; width:12px;"  />
    <input name="q-44a-5" type="text" class="q-row-E" style="left:848px; width:25px;"  />
    <input name="q-44b-5" type="text" class="q-row-E" style="left:880px; width:40px;"  />
    <input name="q-44c-5" type="text" class="q-row-E" style="left:928px; width:32px;"  />
    
    <input name="q-32-6"  type="text" class="q-row-F" style="left:56px;  width:21px;"  />
    <input name="q-33-6"  type="text" class="q-row-F" style="left:85px;  width:39px;"  />
    <input name="q-34-6"  type="text" class="q-row-F" style="left:131px; width:52px;"  />
    <input name="q-35-6"  type="text" class="q-row-F" style="left:190px; width:61px;"  />
    <input name="q-36a-6" type="text" class="q-row-F" style="left:259px; width:33px;"  />
    <input name="q-36b-6" type="text" class="q-row-F" style="left:300px; width:25px;"  />
    <input name="q-37-6"  type="text" class="q-row-F" style="left:332px; width:24px;"  />
    <input name="q-38-6"  type="text" class="q-row-F" style="left:363px; width:32px;"  />
    <input name="q-39-6"  type="text" class="q-row-F" style="left:403px; width:21px;"  />
    <input name="q-40-6"  type="text" class="q-row-F" style="left:433px; width:160px;" />
    <input name="q-41-6"  type="text" class="q-row-F" style="left:601px; width:46px;"  />
    <input name="q-42-6"  type="text" class="q-row-F" style="left:655px; width:15px;"  />
    <input name="q-43a-6" type="text" class="q-row-F" style="left:678px; width:23px;"  />
    <input name="q-43b-6" type="text" class="q-row-F" style="left:709px; width:51px;"  />
    <input name="q-43c-6" type="text" class="q-row-F" style="left:767px; width:25px;"  />
    <input name="q-43d-6" type="text" class="q-row-F" style="left:800px; width:21px;"  />
    <input name="q-43e-6" type="text" class="q-row-F" style="left:829px; width:12px;"  />
    <input name="q-44a-6" type="text" class="q-row-F" style="left:848px; width:25px;"  />
    <input name="q-44b-6" type="text" class="q-row-F" style="left:880px; width:40px;"  />
    <input name="q-44c-6" type="text" class="q-row-F" style="left:928px; width:32px;"  />
    
    <input name="q-32-7"  type="text" class="q-row-G" style="left:56px;  width:21px;"  />
    <input name="q-33-7"  type="text" class="q-row-G" style="left:85px;  width:39px;"  />
    <input name="q-34-7"  type="text" class="q-row-G" style="left:131px; width:52px;"  />
    <input name="q-35-7"  type="text" class="q-row-G" style="left:190px; width:61px;"  />
    <input name="q-36a-7" type="text" class="q-row-G" style="left:259px; width:33px;"  />
    <input name="q-36b-7" type="text" class="q-row-G" style="left:300px; width:25px;"  />
    <input name="q-37-7"  type="text" class="q-row-G" style="left:332px; width:24px;"  />
    <input name="q-38-7"  type="text" class="q-row-G" style="left:363px; width:32px;"  />
    <input name="q-39-7"  type="text" class="q-row-G" style="left:403px; width:21px;"  />
    <input name="q-40-7"  type="text" class="q-row-G" style="left:433px; width:160px;" />
    <input name="q-41-7"  type="text" class="q-row-G" style="left:601px; width:46px;"  />
    <input name="q-42-7"  type="text" class="q-row-G" style="left:655px; width:15px;"  />
    <input name="q-43a-7" type="text" class="q-row-G" style="left:678px; width:23px;"  />
    <input name="q-43b-7" type="text" class="q-row-G" style="left:709px; width:51px;"  />
    <input name="q-43c-7" type="text" class="q-row-G" style="left:767px; width:25px;"  />
    <input name="q-43d-7" type="text" class="q-row-G" style="left:800px; width:21px;"  />
    <input name="q-43e-7" type="text" class="q-row-G" style="left:829px; width:12px;"  />
    <input name="q-44a-7" type="text" class="q-row-G" style="left:848px; width:25px;"  />
    <input name="q-44b-7" type="text" class="q-row-G" style="left:880px; width:40px;"  />
    <input name="q-44c-7" type="text" class="q-row-G" style="left:928px; width:32px;"  /> -->