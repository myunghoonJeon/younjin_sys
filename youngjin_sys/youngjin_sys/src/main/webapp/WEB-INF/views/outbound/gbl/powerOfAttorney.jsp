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
<title>POWER OF ATTORNEY</title>

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
p {
	margin-left: 3pt;
	margin-right: 3pt;
	word-break: break-all;	
}
input {
	border: 0 solid #000000;
	border-bottom: 1px solid #000000;
}
.input1{
	width:150px;
}
.input2{
	width:370px;
}
.input3{
	width:220px;
}
.content{
	width: 600px;
	height: 1000px;
}
.subjectTable{
	margin-left: 35px;
}
</style>

</head>
<body>
	<%
		String to_line[] = new String[3];
		to_line[0] = "Chief of Custorms";
		to_line[1] = "Inchon / Pusan Customs Office";
		to_line[2] = "Inchon / Pusan, Korea";
		String subject_line[] = new String[5];
		subject_line[0] = "Customs Clearance of Used Household Goods and Personal Effects";
		subject_line[1] = "GS12  ASHMORE, JACQUELINE R";
		subject_line[2] = "6949";
		subject_line[3] = "5450";
		subject_line[4] = "　";
		String content_line[] = new String[6];
		content_line[0] = "Gentleman";
		content_line[1] = "";
		content_line[2] = "";
		content_line[3] = "WK-079939";
		content_line[4] = "";
		content_line[5] = "";
	%>
	<c:set var="to_line" value="<%=to_line%>" />
	<c:set var="subject_line" value="<%=subject_line%>" />
	<c:set var="content_line" value="<%=content_line%>" />
	
	<div class="content">
	<p class="title" align="center" style="margin:20px">
		POWER OF ATTORNEY
	</p>
	<p class="to" align="left">
		To : ${to_line[0]}<br>
		　　${to_line[1]}<br>
		　　${to_line[2]}
	</p>
	<br>
	<p class="subject" align="left">
		Subject : ${subject_line[0]}<br><br>
	</p>
		<table class="subjectTable">
		<tr>
			<td align="left">Name of Owner :</td>
			<td colspan="2">${subject_line[1]}</td>
		</tr>
		<tr>
			<td align="left">Gross Weight :</td>
			<td>${subject_line[2]}</td>
			<td>Lbs</td>
		</tr>
		<tr>
			<td align="left">Net Weight :</td>
			<td>${subject_line[3]}</td>
			<td>Lbs</td>
		</tr>
		<tr>
			<td align="left">Booked Vessel :</td>
			<td colspan="2">${subject_line[4]}</td>
		</tr>
		</table>
	<p align="left"><br><br>
	${content_line[0]} : <br><br>
	 I, the undersigned, authorize Youngjin Trade & Transportation Co., Ltd. 4th FL Taeyoung<br>
	Bldg Konghang-dong, Gangsuh-ku, Seoul, Korea who represents <input type="text" maxlength="15" class="input1" value="${content_line[1]}" /><br>
	<input type="text" maxlength="30" class="input2" value="${content_line[2]}" /> U. S. GBL carrier approved by<br>
	Department of Defense of United States of America to process customs clearance for my<br>
	household goods moved under GBL number / Carrier's B/L number <u>${content_line[3]}</u> at<br>
	Inchon / Pusan Customs Office, on my behalf.<br><br><br>
	<p align="left" style="margin-top: 30px; margin-left: 380px;">
	Very turly yours,<br><br><br>
	<input type="text" maxlength="30" class="input3" value="${content_line[4]}" /><br>
	　Customs Clearance Officer<br>
	　United States Forces, Korea/<br>
	　Property Owner<br>
	<input type="text" maxlength="30" class="input3" value="${content_line[5]}"/><br>
	<br>
	　Date signed<br><br>
	</p>
	<p align="left">Youngjin Form</p>
	</div>
</body>
</html>