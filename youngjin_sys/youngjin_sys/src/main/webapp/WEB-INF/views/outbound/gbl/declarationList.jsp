<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="utf-8"%>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

body {
	margin: 0 auto;
	padding: 0;
	background: #ffffff;
	color: #000000;
	text-align: center;
	text-decoration: none;
	word-break: break-all;
}
div{
	word-break: break-all;
}

td {
/* 	padding: 0.49mm; */
  	border: 0mm solid #000000;
 	font: normal 10pt 굴림;
 	text-align: left;
 	padding: 10px;
}
p {
	margin-left: 3pt;
	margin-right: 3pt;
	word-break: break-all;	
}
.title{
	font: bold 13pt 굴림;
	text-align: center;
}
.tdHead{
	border-top: 1px solid #000000;
	border-bottom: 1px solid #000000;
}
.tdTail{
	border-bottom: 1px solid #000000;
}
</style>
</head>
<body>
	<%
		String values[][] = new String[3][6];
		values[0][0] = "E-5";
		values[0][1] = "THAYN, JACOB D";
		values[0][2] = "AGFM00857979";
		values[0][3] = "000-00-5204";
		values[0][4] = "860111400";
		values[0][5] = "VESSEL";
		values[1][0] = "E-5";
		values[1][1] = "THAYN, JACOB D";
		values[1][2] = "AGFM00857979";
		values[1][3] = "000-00-5204";
		values[1][4] = "860111400";
		values[1][5] = "VESSEL";
		values[2][0] = "E-5";
		values[2][1] = "THAYN, JACOB D";
		values[2][2] = "AGFM00857979";
		values[2][3] = "000-00-5204";
		values[2][4] = "860111400";
		values[2][5] = "VESSEL";
		
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy년 MM월 dd일");
		String date = formater.format(currentDate);
	%>
	<c:set var="values" value="<%=values%>" />
	<c:set var="date" value="<%=date%>" />
	<table cellspacing="0">
		<tr>
			<td colspan="7"><p class="title">DECLEARATION LIST FOR IMPORT</p></td>
		</tr>
		<tr>
			<td colspan="4">YOUNGJIN T & T CO., LTE.</td>
			<td colspan="3">${date}</td>
		</tr>
		<tr>
		<td class="tdHead">NO</td>
		<td class="tdHead">RANK</td>
		<td class="tdHead">NAME</td>
		<td class="tdHead">GBL NO</td>
		<td class="tdHead">SSN</td>
		<td class="tdHead">OBL-NO</td>
		<td class="tdHead">VESSEL</td>
		</tr>
		<c:forEach var="value" items="${values}" varStatus="i">
			<tr>
			<td <c:if test="${i.last}">class="tdTail"</c:if>>${i.index+1}</td>
			<c:forEach var="val" items="${value}" varStatus="j">
				<td <c:if test="${i.last}">class="tdTail"</c:if>>${val}</td>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
</body>
