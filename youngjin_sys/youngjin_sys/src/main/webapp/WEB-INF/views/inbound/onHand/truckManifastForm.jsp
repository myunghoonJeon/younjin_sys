<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>TRUCK MANIFEST</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        
        <style type="text/css">
        	
            body {
                margin: 0;
                padding: 0;
                text-align: center;
                font-family: Gulim, sans-serif;
            }
            
            #paper {
                margin: 0 auto;
                text-align: left;
                width: 170mm;
            }
            
            #paper h1 {
                text-align: center;
                /*letter-spacing: -1px;*/
                font-size: 5mm;
            }
            
            #paper input {
                font-family: Gulim, sans-serif;
                border: 0px solid blue;
                /*padding: 2px;*/
            }
            
            #paper th {
                font-weight: normal;
            }
            
            #head_info {
                height: 6mm;
            }
            
            #head_info input {
                padding: 2px;
            }
            
            #head_info * {
                vertical-align: middle;
            }
            
            #head_info #company {
                float: left;
                font-size: 4.3mm;
                margin-left:1.5mm;
            }
            
            #head_info #date {
                float: left;
                margin-left: 12mm;
                margin-top: 2px;
                font-size: 3.5mm;
            }
            
            #head_info #date input {
                width: 28mm;
                font-size: 3.5mm;
                margin-top: -2px;
                vertical-align: top;
            }
            
            #head_info #date input::-ms-clear {
                display: none;
            }
            
            #head_info #route {
                float: right;
                font-size: 3.5mm;
                margin-top: -1px;
            }
            
            #head_info #route input {
                width: 20mm;
                font-size: 4mm;
                margin-top: 1px;
                vertical-align: middle;
            }
            
            .center_box {
                text-align: center;
            }
            
            .right_box {
                text-align: right;
            }
            
            .left_box {
                text-align: left;
            }
                   
            #paper table .no {
                text-align: center;
                width: 5.29%;
            }
            
            #paper table .rank {
                text-align: left;
                width: 7.06%;
            }
            
            #paper table .name {
                text-align: left;
                width: 26.47%;
            }
            
            #paper table .gbl_no {
                text-align: left;
                width: 12.26%;
            }
            
            #paper table .pcs {
                text-align: right;
                width: 4.71%;
            }
            
            #paper table .weight {
                text-align: right;
                width: 8.24%;
            }
            
            #paper table .cuft {
                text-align: right;
                width: 6.47%;
            }
            
            #paper table .yj_no {
                text-align: left;
                width: 13.03%;
                padding-left: 2mm;
            }
            
            #paper table .code {
                text-align: center;
                width: 7.09%;
            }
            
            #paper table .rdd {
                text-align: left;
                width: 9.41%;
            }
            
            #paper table .total_line {
                border-top: 1px solid black;
            }
 
            #paper table {
                width: 100%;
                font-size: 3.3mm;
                border-collapse: collapse;
            }
            
            #paper table th {
                border-bottom: 1px solid black;
                border-top: 1px solid black;
            }
            
            #paper table td, #paper table th {
                padding-top: 1.5mm;
                padding-bottom: 1.5mm;
                padding-right: 2.0mm;
                padding-left: 2.0mm;
            }
            
            #paper table td input {
                width: 100%;
                font-size: 3.3mm;
            }
            
        </style>
    </head>
    <body onload="window.print();">
        <div id="paper">
            <h1>TRUCK MANIFEST</h1>
            <div id="head_info">
                <div id="company">YOUNGJIN T&amp;T CO., LTD.</div>
                <div id="date">${truckInfo.truckManifastDate }</div>
                <div id="route">${truckInfo.area }</div>
            </div>
            <table>
                <tr>
                    <th class="center_box">NO</th>
                    <th class="left_box">RANK</th>
                    <th class="left_box">NAME</th>
                    <th class="left_box">GBL-NO</th>
                    <th class="right_box">PCS</th>
                    <th class="right_box">WEIGHT</th>
                    <th class="right_box">CUFT</th>
                    <th class="left_box" style="padding-left: 2mm;">YJ - NO</th>
                    <th class="center_box">CODE</th>
                    <th class="left_box">RDD</th>
                </tr>
                <c:set var="totalGross" value="0"/>
                <c:set var="totalCuft" value="0"/>
                <c:set var="totalPcs" value="0" />
                <c:forEach var="truckContent" items="${truckList }" varStatus="i">
                	<c:set var="totalPcs" value="${ totalPcs + truckContent.pcs }" />
                	<c:choose>
                		<c:when test="${truckContent.code == '4'||truckContent.code == '5'||truckContent.code == '6'||truckContent.code == 'T' }">
                			<c:set var="totalGross" value="${ totalGross + truckContent.netWeight }" />
                		</c:when>
                		<c:otherwise>
                			<c:set var="totalGross" value="${ totalGross + truckContent.lbs }" />
                		</c:otherwise>
                	</c:choose>
                	
                	<c:set var="totalCuft" value="${ totalCuft + truckContent.cuft }" />
	                <tr>
	                    <td class="no">${ i.count }</td>
	                    <td class="rank">${truckContent.rank }</td>
	                    <td class="name">${truckContent.shipperName }</td>
	                    <td class="gbl_no">${truckContent.gblNo }</td>
	                    <td class="pcs">${truckContent.pcs }</td>
	                    <td class="weight">
	                    <c:choose>
	                		<c:when test="${truckContent.code == '4'||truckContent.code == '5'||truckContent.code == '6'||truckContent.code == 'T' }">
	                			${ truckContent.netWeight }
	                		</c:when>
	                		<c:otherwise>
	                			${ truckContent.lbs }
	                		</c:otherwise>
                		</c:choose></td>
	                    <td class="cuft">${truckContent.cuft }</td>
	                    <td class="yj_no">${truckContent.yjNo }</td>
	                    <td class="code">${truckContent.code }</td>
	                    <td class="rdd">${truckContent.rdd }</td>
	                </tr>                	
                </c:forEach>
                <tr>
                    <td class="total_line"></td>
                    <td class="total_line"></td>
                    <td class="total_line">гу&nbsp;&nbsp;╟Х</td>
                    <td class="total_line"></td>
                    <td class="pcs total_line">${totalPcs }</td>
                    <td class="weight total_line">${totalGross }</td>
                    <td class="cuft total_line">${totalCuft }</td>
                    <td class="total_line"></td>
                    <td class="total_line"></td>
                    <td class="total_line"></td>
                </tr>
            </table>
        </div>
    </body>
</html>