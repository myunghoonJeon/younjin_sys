<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>

<script>
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
	var addError = false;
	if (typeof youngjin == 'undefined') {
		youngjin = {};
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
        
        <style type="text/css">
            body {
                padding: 0;
                margin: 0;
                text-align: center;
                background-color: white;
            }
            
            input, select {
                border: 0px solid skyblue;
                font-family: Gulim, sans-serif;
            }
            
            input::-ms-clear {
                display: none;
            }
            
            #paper {
                width: 257.5mm;
                font-family: Gulim, sans-serif;
                font-size: 4mm;
                margin: 0 auto;
                text-align: left;
                ㅠㅁ
            }
            
            #paper h1 {
                text-align: center;
                text-decoration: underline;
                font-size: 4.5mm;
                font-weight: bold;
                margin: 0;
                margin-bottom: 2mm;
            }
            
            #paper #header {
                margin-left: 1.2mm;
            }
            
            #paper #header input {
                width: 80%;
                font-size: 4mm;
            }
            
            #paper #header #dest_agent {
                margin-top: 2mm;
            }
            
            #paper #header #date {
                text-align: right;
                margin-right: 17mm;
                margin-top: -2mm;
                margin-bottom: 0;
                vertical-align: top;
            }
            
            #paper #header #date input {
                font-size: 3.5mm;
                text-align: center;
                padding: 0;
                margin-top: 1px;
                width: 25mm;
            }
            
            #paper table{
                width: 100%;
                border-collapse: collapse;
                text-align: center;
                padding: 0;
            }
            
            #paper table th {
                font-size: 3.5mm;
                font-weight: normal;
                border: 1px solid black;
                line-height: 4.2mm;
                padding: 0;
            }
            
            #paper table td {
                font-size: 3.3mm;
                border: 1px solid black;
                height: 4.2mm;
            }
            
            #paper table input, #paper table select {
                font-size: 3.3mm;
                border: 0;
                width: 100%;
                padding-top: 1px;
                padding-bottom: 0;
                padding-left: 0px;
                padding-right: 0;
                margin: 0;
                text-align: center;
            }
            
            #paper table #first_header {
                height: 5mm;
            }
            
            #paper table #first_header #code {
                padding-top: 5mm;
                width: 6mm;
            }
            
            #paper table #second_header {
                height: 10mm;
            }
            
            #paper table #second_header #name {
                width: 39mm;
            }
            
            #paper table #second_header #rank {
                width: 10.5mm;
            }
            
            #paper table #second_header #ssn {
                width: 23.5mm;
            }
            
            #paper table #second_header #gbl {
                width: 23mm;
            }
            
            #paper table #second_header #pcs {
                width: 7mm;
            }
            
            #paper table #second_header #gross_wt {
                width: 14.5mm;
            }
            
            #paper table #second_header #net_wt {
                width: 12.5mm;
            }
            
            #paper table #second_header #cuft {
                width: 10mm;
            }
            
            #paper table #second_header #pud {
                width: 17.5mm;
            }
            
            #paper table #second_header #rdd {
                width: 16.5mm;
            }
            
            #paper table #second_header #arrival {
                width: 16.5mm;
            }
            
            #paper table #second_header .measurements {
                font-size: 2.7mm;
            }
            
            #paper table #second_header #first_available {
                font-size: 2.2mm;
                font-family: Arial;
                letter-spacing: -0.15mm;
                word-spacing: -0.2mm;
                padding-top: 0;
                line-height: 3.2mm;
                width: 16.5mm;
            }
            
            #paper table #second_header #by {
                width: 20mm;
            }
            
            #paper table #second_header #replied_by {
                width: 21mm;
            }
            
            #paper #footer #singnature {
                float: right;
                margin-top: 15mm;
                padding-top: 0.6mm;
                padding-left: 0;
                text-indent: 68mm;
                border: 0;
                border-top: 2px solid black;
                width: 110mm;
                font-size: 3.5mm;
            }
            
        </style>
    </head>
    <body onload="window.print();">
        <div id="paper">
            <h1>DPS-REQUEST FOR AUTHORIZATION OF SIT NUMBERS</h1>
            <div id="header">
                <div id="to">TO : CBO ${onHandListContentList[0].onHandListContent.gbl.areaLocal }</div>
                <div id="dest_agent">DEST AGENT : YOUNGJIN TRADE &amp; TRANSPORTATION CO., LTD</div>
	            <fmt:parseDate value="${onHandListContentList[0].onHandDate }" var="formDate" pattern="yyyy-MM-dd" />
                <div id="date">DATE : <fmt:formatDate value="${formDate }" pattern="MM-dd-yy"/></div>
            </div>
            <table>
                <thead>
                    <tr id="first_header">
                        <th colspan="4">PRIMARY INFORMATION</th>
                        <th rowspan="2" id="code">CO<br>DE</th>
                        <th colspan="4">MEASUREMENTS</th>
                        <th colspan="6">DATES / ARRIVAL NOTICE TO MEMBER</th>
                    </tr>
                    <tr id="second_header">
                        <th id="name">NAME</th>
                        <th id="rank">RANK</th>
                        <th id="ssn">SSN</th>
                        <th id="gbl">GBL&#35;</th>
                        
                        <th id="pcs">PCS</th>
                        <th id="gross_wt" class="measurements">GROSS WT</th>
                        <th id="net_wt" class="measurements">NET WT</th>
                        <th id="cuft">CUFT</th>
                        
                        <th id="pud">PUD</th>
                        <th id="rdd">RDD</th>
                        <th id="arrival">ARRIVAL</th>
                        <th id="first_available">FIRST AVAILABLE<br>DELIVERY DATE</th>
                        <th id="by">BY</th>
                        <th id="replied_by">REPLIED<br>BY MEMBER</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="onHandListContent" items="${onHandListContentList }" >
	                    <tr class="on_hand_list_form_tr" data-onHandListContentSeq="${onHandListContent.onHandListContent.seq }">
	                        <td>${onHandListContent.onHandListContent.gbl.shipperName }</td>
	                        <td>${onHandListContent.onHandListContent.gbl.rank }</td>
							<td>XXX-XX-${fn:substring(onHandListContent.onHandListContent.gbl.ssn, 5, 9) }
	                        <td>${onHandListContent.onHandListContent.gbl.gblNo }</td>
	                        <td>${onHandListContent.onHandListContent.gbl.code }</td>
	                        <td>${onHandListContent.onHandListContent.weight.piece }</td>
	                        <td>${onHandListContent.onHandListContent.weight.gross }</td>
	                        <td>${onHandListContent.onHandListContent.weight.net }</td>
	                        <td>${onHandListContent.onHandListContent.weight.cuft }</td>
	                        <fmt:parseDate value="${onHandListContent.onHandListContent.gbl.pud }" var="pud" pattern="yyyyMMdd" />
	                        <td><fmt:formatDate value="${pud }" pattern="MM-dd-yy"/></td>
	                        <fmt:parseDate value="${onHandListContent.onHandListContent.gbl.rdd }" var="rdd" pattern="yyyyMMdd" />
	                        <td><fmt:formatDate value="${rdd }" pattern="MM-dd-yy"/></td>
	                        <fmt:parseDate value="${onHandListContent.onHandDate }" var="onHandDate" pattern="yyyy-MM-dd" />
	                        <td><fmt:formatDate value="${onHandDate }" pattern="MM-dd-yy"/></td>
	                        <fmt:parseDate value="${onHandListContent.firstArrivalableDeliverDate }" var="firstDate" pattern="yyyy-MM-dd" />
	                        <td><fmt:formatDate value="${firstDate }" pattern="MM-dd-yy"/></td>
	                        <td>
	                        	<c:choose>
	                        		<c:when test="${onHandListContent.onHandListContent.by eq 'nextweek' }">
	                        			다음주 예정
	                        		</c:when>
	                        		<c:otherwise>
	                        		    ${fn:toUpperCase(onHandListContent.onHandListContent.by) }
	                        		</c:otherwise>
	                        	</c:choose>
	                        </td>
	                        <td></td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="footer">
                <div id="singnature">
                    SINGNATURE
                </div>
            </div>
        </div>
    </body>
</html>