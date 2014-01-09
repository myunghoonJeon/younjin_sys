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
  	border-left: 0.1mm solid #000000;
  	border-top: 0.1mm solid #000000;
 	font: normal 10pt 굴림;
 	text-align: left;
}
#table_title1, #table_list{
	border-top: 0.5mm solid #000000;
	border-left: 0.5mm solid #000000;
	border-right: 0.5mm solid #000000;
	border-bottom: 0.5mm solid #000000;
}
#table_title2{
	border-left: 0.5mm solid #000000;
	border-right: 0.5mm solid #000000;
	border-bottom: 0.5mm solid #000000;
}
p {
	margin-left: 3pt;
	margin-right: 3pt;
	word-break: break-all;	
}
.tablecol0{
	width: 20.16mm;
}
.tablecol1{
	width: 42.53mm;
}
.tablecol2{
	text-align: center;
	width: 98.04mm;
}
.tablecol3{
	text-align: center;
	width: 26.03mm;
}
.tablecol4{
	width: 27.30mm;
}
.tablecol5{
	width: 52.54mm;
}

.tdRepeat{
	height: 6.51mm;
}

#head_tr{
	text-align: center;
	font: bold 15pt 굴림;
	height: 8.28mm;
}
#head_0, #head_1, #head_2, #head_3, #head_4, #head_5 {
	text-align: center;
	font: bold 11pt 굴림;
	height: 11.08mm;
}
.tdRepeat_tail{
	height: 35.43mm; 
}
#tail_0{
	width: 160.73mm;
}
#tail_1{
	width: 106.14mm;
}
#p_tail{
	line-height: 10mm;
}
.title1{
	font: bold 22pt 굴림;
	width: 247.95mm;
	height: 15.02mm;
	text-align: center;
}
.title2, .title3{
	font: normal 10pt 굴림;
	width: 18.92mm;
	height: 7.51mm;
	text-align: center;
}
.title_td0{
	text-align: center;
	font: bold 15pt 굴림;
	width: 183.22mm;
	height: 6.28mm;
}
.title_td1_p{
	font: bold 11pt 굴림;
	text-align: left;
	line-height: 18pt;
}
.title_td2, .title_td3, .title_td4,
.title_td5, .title_td6, .title_td7{
	font: bold 11pt 굴림;
	height: 11.08mm;
	text-align: center;
}
.title_td8{
	font: bold 10pt 굴림;
	height: 11.08mm;
	text-align: center;
}
.title_td2v, .title_td3v, .title_td4v,
.title_td5v, .title_td6v, .title_td7v, .title_td8v{
	font: normal 11pt 굴림;
	height: 9.52mm;
	text-align: center;
}
.title_td1{
/*  	width: 83.65mm; */
  	width: 82mm;
	height: 26.88mm;
}
.title_td2, .title_td2v{
	width: 33.47mm;
}
.title_td3, .title_td3v{
	width: 15.74mm;
}
.title_td4, .title_td4v{
	width: 35.79mm;
}
.title_td5, .title_td5v{
	width: 30.13mm;
}
.title_td6, .title_td6v{
	width: 19.54mm;
}
.title_td7, .title_td7v{
 	width: 18.54mm;
}
.title_td8, .title_td8v{
   	width: 30.01mm;
}
.title_blank{
	width: 266.88mm;
	height: 2.69mm;
	border: 0mm solid #000000;
}
</style>
</head>
<body>
	<%
		String titlefield[] = new String[8];
		titlefield[0] = "상호 영진상운(주)<br>사업자등록번호 109-81-08161<br>주소 서울 특별시 동작구 상도1동 475번지<br>성명 신현경";
		titlefield[1] = "　";
		titlefield[2] = "　";
		titlefield[3] = "APLU080257382";
		titlefield[4] = "미군이사화물";
		titlefield[5] = "13";
		titlefield[6] = "5085";
		titlefield[7] = "　";
		
		
		String textfield[][] = new String[12][6];
		for(int i=0;i<12;++i){
			for(int j=0;j<6;++j){
				textfield[i][j] = "　";
			}
		}
		textfield[0][0] = "001";		
		textfield[1][0] = "002";
		textfield[2][0] = "003";
		textfield[3][0] = "004";
		textfield[4][0] = "005";
		textfield[5][0] = "006";
		textfield[6][0] = "007";
		for(int i=0;i<7;++i){
			textfield[i][1] = "영진상운(주)";
			textfield[i][2] = "서울 특별시 동작구 상도1동 475번지";
		}
		textfield[0][3] = "1";		
		textfield[1][3] = "2";
		textfield[2][3] = "3";
		textfield[3][3] = "2";
		textfield[4][3] = "1";
		textfield[5][3] = "3";
		textfield[6][3] = "1";
		textfield[0][4] = "523";		
		textfield[1][4] = "762";
		textfield[2][4] = "1150";
		textfield[3][4] = "600";
		textfield[4][4] = "348";
		textfield[5][4] = "1515";
		textfield[6][4] = "187";
		
		int processingDate = 1;		
	%>
	<c:set var="titlefield" value="<%=titlefield%>" />
	<c:set var="textfield" value="<%=textfield%>" />
	<c:set var="processingDate" value="<%=processingDate%>" />
	<table id="table_title1" cellspacing="0">
		<tr>
		<td class="title1" rowspan="2"><p>B/L 분할 신 청 (승인) 서</p></td>
		<td class="title2">처리기한</td>
		</tr>
		<tr><td class="title3">${processingDate}일</td></tr>
	</table>
	<table id="table_title2" cellspacing="0">
		<tr>
		<td class="title_td1" rowspan="3" valign="top">
		<p><span class="title_td1_p">1. 신고인</span><br>
		${titlefield[0]}</p>
		</td>
		<td class="title_td0" colspan="7">B/L 분 할 전 화 물 내 역</td>
		</tr>
		<tr>
			<td class="title_td2"><p>2. 화물관리번호</p></td>
			<td class="title_td3"><p>3.<br>MSN</p></td>
			<td class="title_td4"><p>4. B/L번호</p></td>
			<td class="title_td5"><p>5. 품 명</p></td>
			<td class="title_td6"><p>6.<br>포장개수</p></td>
			<td class="title_td7"><p>7. 중 량</p></td>
			<td class="title_td8"><p>8. 장치장소<br>(보세구역부호)</p></td>
		</tr>
		<tr>
			<c:forEach begin="1" end="7" var="i">
				<td class="title_td${i+1}v"><p>${titlefield[i]}</p></td>
			</c:forEach>
		</tr>

	</table>
	<table id="table_title3" cellspacing="0">
		<tr><td class="title_blank"></td></tr>
	</table>
	<table id="table_list" cellspacing="0">
		<colgroup>
			<c:forEach begin="0" end="5" var="i">
				<col class="tablecol${i}">
			</c:forEach>
		</colgroup>
		<tbody>
			<tr>
				<td colspan="6" id="head_tr"><center>B/L 분 할 후 화 물 내역</center></td>
			</tr>
			<tr>
				<td class="tdRepeat_head" id="head_0"><p>9. HSN</p></td>
				<td class="tdRepeat_head" id="head_1"><p>10. 수하인 상호</p></td>
				<td class="tdRepeat_head" id="head_2"><p>11. 수하인 주소</p></td>
				<td class="tdRepeat_head" id="head_3"><p>12.<br/>포장개수</p></td>
				<td class="tdRepeat_head" id="head_4"><p>13. 중 량</p></td>
				<td class="tdRepeat_head" id="head_5"><p>15. 세관기재란</p></td>
			</tr>
			<c:forEach begin="0" end="11" var="i">
				<tr class="trRepeat_${i}" id="table_tr${i}">
					<c:forEach begin="0" end="5" var="j">
						<td class="tdRepeat" id="td${i}${j}Repeat">
						 	<c:if test="${j==2||j==3||j==4||j==5}"><center></c:if>
							<p>${textfield[i][j]}</p>
							<c:if test="${j==2||j==3||j==4||j==5}"></center></c:if>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			<tr>
				<td class="tdRepeat_tail" colspan="3" id="tail_0" valign="middle" align="center">
					<p>
					보세화물 관리에 관한 고시 제10조6의 규정에 의거 B/L 분할 신청합니다<br><br>
					<c:forEach begin="0" end="45" var="i">
						&nbsp;
					</c:forEach>
					2013년 월 일<br>
					<c:forEach begin="0" end="45" var="i">
						&nbsp;
					</c:forEach>
					세&nbsp;&nbsp;&nbsp;관&nbsp;&nbsp;&nbsp;장&nbsp;&nbsp;귀하
					</p>
				</td>
				<td class="tdRepeat_tail" colspan="3" id="tail_1">
					<p class="p_tail">
					위 승인합니다.<br><br>
					<c:forEach begin="0" end="25" var="i">
						&nbsp;
					</c:forEach>
					2013년&nbsp;&nbsp;&nbsp;월&nbsp;&nbsp;&nbsp;&nbsp;일<br>
					<c:forEach begin="0" end="25" var="i">
						&nbsp;
					</c:forEach>
					세&nbsp;&nbsp;&nbsp;관&nbsp;&nbsp;&nbsp;장&nbsp;&nbsp;&nbsp;(인)
					</p>
				</td>
			</tr>
		</tbody>
	</table>
	<p align="left"><br>
			※ 첨부서류: 1. B/L 사본<br>
			　　　　　　   2. Invoice, Packing list 사본 각 1부</p>
</body>
