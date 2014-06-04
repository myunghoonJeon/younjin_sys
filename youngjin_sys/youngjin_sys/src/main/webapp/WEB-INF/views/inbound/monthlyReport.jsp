<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<style>
	th{
		border:solid;
		border-style: thin;
		border-width: 1px;
	}
	td{
		border:solid;
		border-style: thin;
		border-width: 1px;
		font-family: airal;
		font-weight: bold;
		text-align: center;
	}
	.title_td{
		border:none;
		font-size: 12pt;
		font-family: aial;
		font-weight: bold;
	}
	.blank_td{
		border:none;
	}
	.total_td{
		border:solid;
		border-style: thin;
		border-width: 1px;
		font-family: airal;
		font-weight: bold;
		background: gray;
		font-size: 12pt;
	}
	.grand_td{
		border:solid;
		border-style: thin;
		border-width: 1px;
		font-family: airal;
		font-weight: bold;
		font-size: 12pt;
	}
</style>
<div>
	<center>
	<table style="height: 50px;width: 900px;">
		<tr >
			<td class="title_td" style="text-decoration: underline;text-align: center;font-family: arial;font-weight: bold;font-size: 15pt;">MONTHLY REPORT FOR INBOUND UB/HHG FOR DPS</td>
		</tr>
		<tr>
			<td style="border:none; text-align: left;">
				<input style="font-family: arial;font-size: 13pt; font-weight: bold; border: none;"type="text" value="年 月 日"/>
			</td>
			<td style="border:none; text-align: right;">
				<input style="font-family: arial;font-size: 13pt; font-weight: bold; border: none;text-align: right;"type="text" value="YOUNGJIN"/>
			</td>
		</tr>
	</table>
		<table  style="height: 40px;width: 900px;" cellspacing="0">
				<tr >
					<th>STATUS</th>
					<th colspan="2">SHIPMENT RECEIVED</th>
					<th colspan="2">MISSED RDD</th>
					<th >SHPMTS INTOSIT</th>
					<th colspan="4">SHIPMENTS DELIVERED</th>
				</tr>
				<tr>
					<td>MODE</td>
					<td>SHPMT NO</td><td>WEIGHT</td>
					<td>NBR</td><td>%</td>
					<td>SIT NO</td>
					<td>YONGSAN</td><td>WEIGHT</td><td>OTHER</td><td>WEIGHT</td>
				</tr>
				<tr>
					<td>CODE-3</td>
					<td>${m3['size'] }</td><td>${m3['weight'] }</td>
					<td></td><td></td>
					<td>${m3count }</td>
					<td>${my3['size'] }</td><td>${my3['weight'] }</td><td>${mo3['size'] }</td><td>${mo3['weight'] }</td>
				</tr>
								<tr>
					<td>CODE-4</td>
					<td>${m4['size'] }</td><td>${m4['weight'] }</td>
					<td></td><td></td>
					<td>${m4count }</td>
					<td>${my4['size'] }</td><td>${my4['weight'] }</td><td>${mo4['size'] }</td><td>${mo4['weight'] }</td>
				</tr>
				<tr>
					<td>CODE-5</td>
					<td>${m5['size'] }</td><td>${m5['weight'] }</td>
					<td></td><td></td>
					<td>${m5count }</td>
					<td>${my5['size'] }</td><td>${my5['weight'] }</td><td>${mo5['size'] }</td><td>${mo5['weight'] }</td>
				</tr>
				<tr>
					<td>CODE-6</td>
					<td>${m6['size'] }</td><td>${m6['weight'] }</td>
					<td></td><td></td>
					<td>${m6count }</td>
					<td>${my6['size'] }</td><td>${my6['weight'] }</td><td>${mo6['size'] }</td><td>${mo6['weight'] }</td>
				</tr>
								<tr>
					<td>CODE-T</td>
					<td>${mT['size'] }</td><td>${mT['weight'] }</td>
					<td></td><td></td>
					<td>${mTcount }</td>
					<td>${myT['size'] }</td><td>${myT['weight'] }</td><td>${moT['size'] }</td><td>${moT['weight'] }</td>
				</tr>
								<tr>
					<td>DPM HHG MAC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>DPM HHG MSC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<c:set var="totalCount" value="${m4['size']+m3['size']+m5['size']+m6['size']+mT['size'] }"/>
					<c:set var="sitCount" value="${m4count+m3count+m5count+m6count+mTcount }"/>
					<c:set var="percentage" value="${(sitCount/totalCount)}"/>
					<td class="total_td">HHG TOTAL</td>
					<td class="total_td">${totalCount}</td>
					<td class="total_td">${m4['weight']+m3['weight']+m5['weight']+m6['weight']+mT['weight'] }</td>
					
					<td class="total_td"></td><td class="total_td"></td>
					<td class="total_td">${sitCount } ( <fmt:formatNumber type="percent" value="${percentage}"/> )</td>
					<td class="total_td">${my4['size']+my3['size']+my5['size']+my6['size']+myT['size'] }</td >
					<td class="total_td">${my4['weight']+my3['weight']+my5['weight']+my6['weight']+myT['weight'] }</td>
					<td class="total_td">${mo4['size']+mo3['size']+mo5['size']+mo6['size']+moT['size'] }</td>
					<td class="total_td">${mo4['weight']+mo3['weight']+mo5['weight']+mo6['weight']+moT['weight'] }</td>
				</tr>
				<tr style="height:5px;">
					<td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td>
					<td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td><td class="blank_td">　</td><td class="blank_td">　</td>
				</tr>
				<tr>
					<td>CODE-J</td>
					<td>${mJ['size'] }</td><td>${mJ['weight'] }</td>
					<td></td><td></td>
					<td>${mJcount }</td>
					<td>${myJ['size'] }</td><td>${myJ['weight'] }</td><td>${moJ['size'] }</td><td>${moJ['weight'] }</td>
				</tr>
												<tr>
					<td>CODE-7</td>
					<td>${m7['size'] }</td><td>${m7['weight'] }</td>
					<td></td><td></td>
					<td>${m7count }</td>
					<td>${my7['size'] }</td><td>${my7['weight'] }</td><td>${mo7['size'] }</td><td>${mo7['weight'] }</td>
				</tr>
												<tr>
					<td>CODE-8</td>
					<td>${m8['size'] }</td><td>${m8['weight'] }</td>
					<td></td><td></td>
					<td>${m8count }</td>
					<td>${my8['size'] }</td><td>${my8['weight'] }</td><td>${mo8['size'] }</td><td>${mo8['weight'] }</td>
				</tr>
												<tr>
					<td>DPM UB MAC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
					<tr>
					<td>DPM UB MSC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>DPM UB COM</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<c:set var="ubTotalCount" value="${mJ['size']+m7['size']+m8['size']}"/>
					<c:set var="ubTotalSitCount" value="${mJcount+m7count+m8count}"/>
					<c:set var="ubTotalPercentage" value="${ubTotalSitCount/ubTotalCount }"/>
					<td class="total_td">UB TOTAL</td>
					<td class="total_td">${mJ['size']+m7['size']+m8['size']}</td><td class="total_td">${mJ['weight']+m7['weight']+m8['weight']}</td>
					<td class="total_td"></td><td class="total_td"></td>
					<td class="total_td">${mJcount+m7count+m8count} ( <fmt:formatNumber type="percent" value="${ubTotalPercentage}"/> )</td>
					<td class="total_td">${myJ['size']+my7['size']+my8['size']}</td>
					<td class="total_td">${myJ['weight']+my7['weight']+my8['weight']}</td>
					<td class="total_td">${moJ['size']+mo7['size']+mo8['size']}</td>
					<td class="total_td">${moJ['weight']+mo7['weight']+mo8['weight']}</td>
				</tr>
				<tr>
					<td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td>
					<td class="blank_td">　</td>
					<td class="blank_td">　</td><td class="blank_td">　</td><td class="blank_td">　</td><td class="blank_td">　</td>
				</tr>
				<tr>
					<c:set var="grandTotalCount" value="${m4['size']+m3['size']+m5['size']+m6['size']+mT['size']+ mJ['size']+m7['size']+m8['size']}"/>
					<c:set var="grandTotalSitCount" value="${m4count+m3count+m5count+m6count+mTcount+mJcount+m7count+m8count}"/>
					<c:set var="grandTotalPercentage" value="${ubTotalSitCount/ubTotalCount }"/>
					<td class="grand_td">GRAND TOTAL</td>
					<td class="grand_td">${m4['size']+m3['size']+m5['size']+m6['size']+mT['size']+ mJ['size']+m7['size']+m8['size']}</td>
					<td class="grand_td">${m4['weight']+m3['weight']+m5['weight']+m6['weight']+mT['weight']+mJ['weight']+m7['weight']+m8['weight'] }</td>
					
					<td class="grand_td"></td>
					<td class="grand_td"></td>
					
					<td class="grand_td">${m4count+m3count+m5count+m6count+mTcount+mJcount+m7count+m8count} ( <fmt:formatNumber type="percent" value="${grandTotalPercentage}"/> )</td>
					<td class="grand_td">${my4['size']+my3['size']+my5['size']+my6['size']+myT['size'] +myJ['size']+my7['size']+my8['size']}</td >
					<td class="grand_td">${my4['weight']+my3['weight']+my5['weight']+my6['weight']+myT['weight'] +myJ['weight']+my7['weight']+my8['weight']}</td>
					<td class="grand_td">${mo4['size']+mo3['size']+mo5['size']+mo6['size']+moT['size'] +moJ['size']+mo7['size']+mo8['size']}</td>
					<td class="grand_td">${mo4['weight']+mo3['weight']+mo5['weight']+mo6['weight']+moT['weight'] +moJ['weight']+mo7['weight']+mo8['weight']}</td>
				</tr>
		</table>
		</center>
	</div>
</body>
</html>