<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
<head>	
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
	
	<%@ include file="../../../layout/include_script.jspf" %>
	
	<title>YOUNGJIN TRADE &amp; TRANSPORTATION CO., LTD</title>
	
	<style type="text/css">
	body {
	    text-transform: uppercase;
	    text-align: center;
	    font-family: Arial, sans-serif;
	    font-size: 0.95em;
	}
	
	div.left {
	    float: left;
	}
	
	div.right {
	    float: right;
	}
	
	h1 {
	    text-align: center;
	    font-size: 18pt;
	    word-spacing: 3px;
	}
	
	h2 {
	    font-weight: normal;
	    font-size: 14pt;
	    text-align: center;
	    margin-top: 0;
	    margin-bottom: 10px;
	    word-spacing: 3px;
	    text-decoration: underline;
	}
	
	table {
	    border-collapse: collapse;
	    border-top: 2px solid black;
	    border-bottom: 2px solid black;
	    font-size: 0.95em;
	}
	
	table th {
	    text-align: left;
	    border-bottom: 2px solid black;
	    padding-left: 2mm;
	}
	
	table td {
	    text-align: left;
	    border: 0px;
	    padding-left: 2mm;
	    padding-top: 5px;
	    padding-bottom: 5px;
	    
	}
	
	#list_date {
	    padding-left: 3mm;
	}
	
	#total_shipment {
	    margin-top:2.5mm;
	    margin-right: 1.5cm;
	}
	
	#list_total {
	    display:inline-block;
	    width: 1cm;
	}
	
	#received_by {
	    margin-top:0.7cm;
	    margin-right: 1cm;
	    line-height: 1.8em;
	}
	
	.clear {
	    clear:both;
	}
	</style>
</head>
<body onload="window.print();">
    <div style="width: 173mm; margin: 0 auto; text-align: left;">
        <h1>YOUNGJIN TRADE &amp; TRANSPORTATION CO., LTD</h1>
        <h2>EXPORT A DECLARATION LIST</h2>
        
        <div class="left" id="list_date"><c:out value="${item_date}"/></div>
        <div class="right" style="margin-bottom: 2mm; margin-right: 2cm;">PAGE : <span id="list_page"><c:out value="1" /></span></div>
        <table class="clear" width="100%">
            
            <tr class="list_title">
                <th style="width:8.670%;">NO</th>
                <th style="width:16.185%;">GBL - NO</th>
                <th style="width:10.983%;">RANK</th>
                <th>SHIPPER NAME</th>
                <th style="width:22.543%;">SSN - NO</th>
            </tr>
            
            <c:forEach items="${item_list}" var="current_item" varStatus="i">
            <tr>
                <td><c:out value="${i.count}" /></td>
                <td><c:out value="${current_item.no}" /></td>
                <td><c:out value="${current_item.rank}" /></td>
                <td><c:out value="${current_item.shipper}" /></td>
                <td><c:out value="000-00-${fn:substring(current_item.ssn, 5, 9) }" /></td>
            </tr>
            </c:forEach>
            
        </table>
        <div class="right clear" id="total_shipment">TOTAL: <span id="list_total"><c:out value="${fn:length(item_list)}" /></span> SHIPMENTS</div>
        
        <div class="right clear" id="received_by">RECEIVED BY :<br>FROM MAIN OFFICE</div>
    </div>
</body>
</html>