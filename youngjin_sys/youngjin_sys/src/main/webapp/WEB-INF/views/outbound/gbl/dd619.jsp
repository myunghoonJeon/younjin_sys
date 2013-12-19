<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
	font: 8px Arial, Verdana, Tahoma, sans-serif;
	border: 1px solid #000000;
	text-align: left;
	vertical-align: top;
 	line-height: 8px;
}

#wrapTable {
	margin: 0 auto;
	font: 8px Arial, Verdana, Tahoma, sans-serif;
	text-align: center;
}

#mytable {
 	width: 173mm;
 	height: 232mm;
	margin-top: 50px;
	border: 3px solid #000000;
	border-spacing: 0px;
	border-collapse: collapse;
}

#table_tr1 {
	height: 4.92%;
}

#table_tr2,#table_tr51 {
	height: 4.1%;
}

#table_tr3,#table_tr4 {
	height: 2.05%;
}

#table_tr5,#table_tr6,#table_tr7,#table_tr8,#table_tr9,#table_tr10,#table_tr11,#table_tr12,#table_tr13,#table_tr14,#table_tr15,#table_tr16,#table_tr17,#table_tr18,#table_tr19,#table_tr20,#table_tr21,#table_tr22,#table_tr23,#table_tr24,#table_tr25,#table_tr26,#table_tr28,#table_tr29,#table_tr30,#table_tr31,#table_tr32,#table_tr33,#table_tr34,#table_tr35,#table_tr36,#table_tr37,#table_tr38,#table_tr39,#table_tr40,#table_tr41,#table_tr43,#table_tr45,#table_tr46,#table_tr47,#table_tr48,#table_tr49,#table_tr50
	{
	height: 1.64%;
}

#table_tr27 {
	height: 3.28%;
}

#table_tr42 {
	height: 8.2%;
}

#table_tr44 {
	height: 0.82%;
}

#tablecol1,#tablecol4,#tablecol8,#tablecol11,#tablecol13,#tablecol15,#tablecol17,#tablecol19,#tablecol21,#tablecol22
	{
	width: 3.01%;
}

#tablecol2 {
	width: 4.22%;
}

#tablecol3,#tablecol18 {
	width: 7.23%;
}

#tablecol5,#tablecol9,#tablecol12 {
	width: 1.81%;
}

#tablecol6,#tablecol10 {
	width: 2.41%;
}

#tablecol7,#tablecol16 {
	width: 4.82%;
}

#tablecol14 {
	width: 3.61%;
}

#tablecol20 {
	width: 14.46%;
}

#tablecol23,#tablecol24 {
	width: 6.63%;
}

#table_td_24_1,#table_td_24_3,#table_td_25_1,#table_td_46_2,#table_td_49_18,#table_td_50_18,#table_td_36_1,#table_td_37_1
	{
	border-right: 0px solid #000000;
}

#table_td_24_3,#table_td_24_6,#table_td_25_3,#table_td_46_4,#table_td_49_22,#table_td_36_2
	{
	border-left: 0px solid #000000;
}

#table_td_24_1,#table_td_24_3,#table_td_24_6,#table_td_24_8,#table_td_33_17,#table_td_33_20,#table_td_44_1,#table_td_45_2,#table_td_45_5,#table_td_48_1,#table_td_49_2,#table_td_49_11,#table_td_48_11,#table_td_49_18,#table_td_48_18,#table_td_48_22,#table_td_36_1
	{
	border-bottom: 0px solid #000000;
}

#table_td_25_1,#table_td_25_3,#table_td_25_6,#table_td_25_9,#table_td_25_12,#table_td_25_16,#table_td_34_18,#table_td_34_20,#table_td_45_2,#table_td_46_2,#table_td_45_5,#table_td_46_4,#table_td_49_2,#table_td_50_2,#table_td_49_11,#table_td_50_11,#table_td_49_18,#table_td_50_18,#table_td_49_22,#table_td_37_1
	{
	border-top: 0px solid #000000;
}
.divtext{
	margin-top: 2px;
	margin-left: 5px;
	margin-right: 5px;
	margin-bottom: 2px;
	font-size: 7px;
}
.divnomargintext{
	margin: 0px auto;
	font-size: 7px;
	vertical-align: middle;
}
.divsmallmargintext{
	margin-top: 3px;
	margin-bottom: 1px;
	margin-left: 2px;
	font-size: 6.5px;
	vertical-align: middle;
}
.ptext1{
	font-size: 13px;
}
.ptext2{
	font-size: 7px;
	line-height: 10px;
}
.ptext3{
	font-size: 9px;
}
.ptext4{
	font-size: 6px;
	font-weight: bold;
}
.ptext5{
	font-size: 7px;
}
.ptext6{
	font-size: 5.5px;
}
.textJustify{
	text-align: justify;
	text-align-last:justify;
	text-justify:distribute;
}
input{
	border: 0px solid #000000;
	width: 20px;
	height: 9px;
	font-size: 8px;
	display: inline;
}
.input_style1{
	
}
</style>
</head>
<body>
	<%
		String textbox = "<input type='text' value='_' />";
		String textarea = "<input type='textarea' value='_' />";
		String textfield[][] = new String[52][25];
		textfield[1][1] = "<p class='ptext1'><center><b>STATEMENT OF ACCESSORIAL SERVICES PERFORMED</b></center><br/></p>"
				+ "<p> This form is required only when accessorial services are chargeable to the Government. Carrier will enter complete information or<br/>"
				+ "　None　 in columns. 　Unit Price　 and 　Charge　 columns may be omitted when charges are itemized on the Standard Form 1113.</p>";
		textfield[1][23] = "<p class='ptext2'><i>OMB No. 0702-0022<br/>OMB approval expires<br/>May 31, 2011</i></p>";
		textfield[2][1] = "<p class='textJustify'>The public reporting burden for this collection of information is estimated to average 5 minutes per response, including the time for reviewing instructions, searching existing data sources, gathering<br/>"
				+ "and maintaining the data needed, and completing and reviewing the collection of information. Send comments regarding this burden estimate or any other aspect of this collection of information,<br/>"
				+ "including suggestions for reducing the burden, to the Department of Defense, Washington Headquarters Services, Executive Services Directorate, Information Management Division, 1155 Defense<br/>"
				+ "Pentagon, Washington, DC 20301-1155 (0702-0022). Respondents should be aware that notwithstanding any other provision of law, no person shall be subject to any penalty for failing to comply with</p>"
				+ "<p>a collection of information if it does not display a currently valid OMB control number.</p>"
				+ "<p class='ptext3'><b>PLEASE DO NOT RETURN YOUR COMPLETED FORM TO THE ABOVE ORGANIZATION.</b></p>";
		textfield[3][1] = "<p class='ptext4'>1. GOVERNMENT BILL OF LADING NUMBER</p>"+textbox;
		textfield[3][9] = "<p class='ptext4'>2. DATE OF PICKUP AT ORIGIN</p><p><i>(YYYYMMDD)</i> "+textbox+"</p>";
		textfield[5][1] = "<p class='ptext4'>3.a. NAME OF OWNER</p><p><i>(Last, First, Middle Initial)</i></p>"+textbox;
		textfield[7][1] = "<p class='ptext4'>b. SSN</p>"+textbox;
		textfield[7][8] = "<p class='ptext4'>c. RANK OR GRADE</p>"+textbox;
		textfield[9][1] = "<p class='ptext4'>4. ORIGIN OF SHIPMENT</p>"+textbox;
		textfield[9][8] = "<p class='ptext4'>5. DESTINATION OF SHIPMENT</p>"+textbox;
		textfield[11][1] = "<p class='ptext4'>6.a. ORDERING ACTIVITY/INSTALLATION<br/>NAME</p>"+textbox;
		textfield[11][8] = "<p class='ptext4'>b. LOCATION</p>"+textbox;
		textfield[14][1] = "<p class='ptext4'>7.a. NAME OF CARRIER</p>"+textbox;
		textfield[14][8] = "<p class='ptext4'>b. NAME OF AGENT (Last, First, Middle Initial)</p>"+textbox;
		textfield[16][1] = "<p class='ptext4'>8. SIGNATURE OF CARRIER'S REPRESENTATIVE</p>"+textbox;
		textfield[16][14] = "<p class='ptext4'>9. DATE</p><p><i>(YYYYMMDD)</p>"+textbox;
		textfield[19][1] = "<p class='ptext4'>10. CARRIER'S SHIPMENT REFERENCE NO.</p>"+textbox;
		textfield[19][11] = "<p class='ptext4'>11. AGENT OR DRIVER CODE</p>"+textbox;
		textfield[21][1] = "<p class='ptext4'>12. PROFESSIONAL BOOKS, PAPERS AND EQUIPMENT (PBP&E)<br/>INCLUDED IN SHIPMENT (If not included, write None.)</p>";
		textfield[21][14] = "<p class='ptext4'>LBS</p>"+textbox;
		textfield[23][1] = "<p class='ptext4'>13. STORAGE-IN-TRANSIT (SIT)</p>";
		textfield[24][1] = "<p class='ptext4' style='font-size: 5px;'>a. STORED AT</p>";
		textfield[24][3] = "<p class='ptext4'>(1) CITY</p>";
		textfield[24][6] = "<p class='ptext4'>(2) STATE</p>";
		textfield[24][8] = "<p><b>b. SIT SERVICES PROVIDED AT</b> (X one)</p>";
		textfield[25][9] = "<p class='ptext5'><b>ORIGIN</b></p>";
		textfield[25][12] = "<p class='ptext5'><b>DESTINATION</b></p>";
		textfield[25][16] = "<p class='ptext5'>OTHER</p>";
		textfield[26][1] = "<p><b>DATES</b> <i>(YYYYMMDD)</i>:";
		textfield[27][1] = "<p class='ptext4'>c. IN</p>"+textbox;
		textfield[27][3] = "<p class='ptext4'>d. ORDERED OUT</p>"+textbox;
		textfield[27][7] = "<p class='ptext4'>e. DELIVERED OUT</p>"+textbox;
		textfield[26][11] = "<p class='ptext4'>f. NUMBER<br/>OF DAYS</p>"+textbox;
		textfield[26][14] = "<p class='ptext4'>g. NET WEIGHT<br/></p>"+textbox;
		textfield[28][1] = "<p class='ptext4'>h. REQUESTED DELIVERY<br/>DATE <i>(YYYYMMDD)</i></p>"+textbox;
		textfield[28][5] = "<p class='ptext4'>i. SHIPMENT ORDERED INTO AND OUT OF SIT ON DATES<br/>INDICATED AND AUTHORIZED BY SIT CONTROL NO.</p>"+textbox;
		textfield[30][1] = "<p><b>j. WAS STORAGE POINT FOR CARRIER'S CONVENIENCE</b> (X one)</p>";
		textfield[30][14] = "<p class='ptext4'>YES</p>";
		textfield[30][16] = "<p class='ptext4'>NO</p>";
		textfield[31][1] = "<p class='ptext4'>14. REWEIGH CERTIFICATION (If applicable)</p>";
		textfield[31][8] = "<p class='ptext4'>a. NUMBER "+textbox+"</p>";
		textfield[32][1] = "<p class='ptext4'>b. ORIGINAL GROSS "+textbox+"</p>";
		textfield[32][8] = "<p class='ptext4'>c. REWEIGH GROSS "+textbox+"</p>";
		textfield[33][1] = "<p class='ptext4'>d. ORIGINAL TARE "+textbox+"</p>";
		textfield[33][8] = "<p class='ptext4'>e. REWEIGH TARE "+textbox+"</p>";
		textfield[34][1] = "<p class='ptext4'>f. ORIGINAL NET "+textbox+"</p>";
		textfield[34][8] = "<p class='ptext4'>g. REWEIGH NET "+textbox+"</p>";
		textfield[35][1] = "<p><b>15. APPLIANCES SERVICED</b> <i>(Owner/Agent must initial each entry separately.)</i></p>";
		textfield[36][2] = "<center><p class='ptext4'>TYPE<br/>a.</p></center>";
		textfield[36][5] = "<center><p class='ptext4'>MAKE/MODEL NO./MANUFACTURER<br/>b.</p></center>";
		textfield[36][14] = "<center><p class='ptext4'>OWNER/AGENT<br/>INITIALS<br/>c.</p></center>";
		textfield[3][17] = "<p class='ptext4'>16. ACCESSORIAL SERVICES</p>";
		textfield[4][17] = "<center><p class='ptext4'>PACKING, PACK MATERIALS AND UNPACKING<br/>(1)</p></center>";
		textfield[4][21] = "<center><p class='ptext6'>NUMBER<br/>(2)<p></center>";
		textfield[4][23] = "<center><p class='ptext6'>UNIT PRICE<br/>(3)<p></center>";
		textfield[4][24] = "<center><p class='ptext6'>CHARGE<br/>(4)<p></center>";
		textfield[6][17] = "<p><b>a. DISH PACK</b></p>";
		textfield[7][17] = "<p><b>b. CARTONS</b> (Less than 3 cubic feet)</p>";
		textfield[8][17] = "<p><b>c. CARTONS</b> (3 cubic feet)</p>";
		textfield[9][17] = "<p><b>d. CARTONS</b> (4-1/2cubic feet)</p>";
		textfield[10][17] = "<p><b>e. CARTONS</b> (8 cubic feet)</p>";
		textfield[11][17] = "<p><b>f. CARTONS</b> (8-1/2 cubic feet)</p>";
		textfield[12][17] = "<p><b>g. WARDROBE</b> (Not less than 10 cubic feet)</p>";
		textfield[13][17] = "<p><b>h. MATTRESS, CRIB</b></p>";
		textfield[14][17] = "<p><b>i. MATTRESS</b> (Not exceeding 39　 x 75　)</p>";
		textfield[15][17] = "<p><b>j. MATTRESS</b> (Not exceeding 54　 x 75　)</p>";
		textfield[16][17] = "<p><b>k. MATTRESS</b> (39　 x 80　)</p>";
		textfield[17][17] = "<p><b>l. MATTRESS</b> (Exceeding 54　 x 75　)</p>";
		textfield[18][17] = "<p><b>m. TOTAL</b></p>";
		textfield[19][17] = "<p><b>n. TOTAL SUBJECT MAX-PAK $</b> "+textbox+"/cwt)</p>";
		textfield[20][17] = "<p><b>o. GRANDFATHER CLOCK CARTONS</b></p>";
		textfield[21][17] = "<p><b>p. CORRUGATED CONTAINERS</b> (Special constr.)</p>";
		textfield[22][17] = "<p><b>q. BOXES - WOODEN/CRATES</b> (Not over 5 cu.ft.)</p>";
		textfield[23][17] = "<p><b>r. BOXES</b> (Over 5 cu.ft./not over 8 cu.ft.)</p>";
		textfield[24][17] = "<p><b>s. BOXES</b> (Over 8 cu.ft.) (Gross cu.ft.: "+textbox+")</p>";
		textfield[25][17] = "<p><b>t. CRATES</b> (Cubic feet: "+textbox+")<br/>(mINIMUM CHARGE: "+textbox+")</p>";
		textfield[27][17] = "<p><b>u. CARTONS, DOUBLE WALL (PPP-B-1364) & <br/>TRIPLE WALL (PPP-B-640)</b> (Not over 4 cu.ft.)</p>";
		textfield[28][17] = "<p><b>v. CARTONS</b> (Over 4 cu.ft./less than 7 cu.ft.)</p>";
		textfield[29][17] = "<p><b>w. CARTONS</b> (7 cu.ft./less than 15 cu.ft.)</p>";
		textfield[30][17] = "<p><b>x. TOTAL PACKING CHARGE</b></p>";
		textfield[31][17] = "<p><b>y. LABOR</b> <i>(Describe service in 　Remarks　)<i><br/><i>(Enter number of man-hours)</i> "+textbox+"</p>";
		textfield[33][17] = "<p><b>z.</b> <i class='ptext6'>(X as applicable)</i></p>";
		textfield[33][20] = "<p class='ptext4'>EXTRA DELIVERY</p>";
		textfield[34][18] = "<p class='ptext4'>EXTRA PICKUP</p>";
		textfield[34][20] = "<p class='ptext4'>AUXILIARY SERVICES</p>";
		textfield[35][17] = "<p><b>aa. PIANO/ORGAN CARRY SERVICE</b></p>";
		textfield[36][17] = "<p><b>bb. ELEVATOR/STAIR/EXCESS DISTANCE</b></p>";
		textfield[37][17] = "<p><b>cc. SERVICING APPLIANCES/OTHER ARTICLES</b> (As itemized and initialed in Item 15)</p>";
		textfield[39][17] = "<p><b>dd. OTHER</b> <i>(Describe in 　Remarks　)</i></p>";
		textfield[40][17] = "<p><b>ee. TOTAL ACCESSORIAL SERVICE CHARGES</b></p>";
		textfield[42][1] = "<p class='ptext4'>17. remarks</p>"+textbox;
		textfield[43][1] = "<p class='ptext4'>18. STATEMENT OF OWNER, MILITARY INSPECTOR/TRANSPORTATION OFFICER</p>";
		textfield[44][1] = "<p class='ptext4'>a. MATERIALS WERE FURNISHED/ACCESSORIAL SERVICES WERE PERFORMED</p>";
		textfield[45][2] = "<p class='ptext4'>AT ORIGIN</p>";
		textfield[45][5] = "<p><b>OTHER</b> (Explain)</p>";
		textfield[46][2] = "<p class='ptext4'>AT DESTINATION</p>";
		textfield[44][16] = "<p><b>b. SIGNATURE</b> <i>(Do not sign until Carrier has completed column 16(2).)</i>"+textbox;
		textfield[44][23] = "<p><b>c. DATE SIGNED</b><br/><i>(YYYYMMDD)</i></p>"+textbox;
		textfield[47][1] = "<p class='ptext4'>19. TRANSPORTATION OFFICER CERTIFICATION. I CERTIFY THAT SHIPMENT SERVICES WERE ACCOMPLISHED AS SHOWN BELOW.</p>";
		textfield[48][1] = "<p><b>a. SERVICES ACCOMPLISHED</b> <i>(X as applicable)</i>";
		textfield[49][2] = "<p><b>(1) ACCESSORIAL SERVICES</b> <i>(Listed in Item 16)</i>";
		textfield[50][2] = "<p class='ptext4'>(2) STORAGE-IN-TRANSIT</p>";
		textfield[48][11] = "<p class='ptext4'>(3) REWEIGH CERTIFICATION</p>";
		textfield[49][11] = "<p class='ptext4'>(4) THIRD PARTY SERVICES</p>";
		textfield[50][11] = "<p class='ptext4'>(5) BULKY ARTICLE CHARGE</p>";
		textfield[48][18] = "<p class='ptext4'>(6) WAITING TIME</p>";
		textfield[49][18] = "<p><b>(7) UNPACKING SERVICE</b> <i>(Baggage only)</i>";
		textfield[50][18] = "<p class='ptext4'>(8) OVERTIME LOADING/UNLOADING CHARGE</p>";
		textfield[48][22] = "<p><b>(9) OTHER</b> <i>(Specify)</i>";
		textfield[49][22] = textbox;
		textfield[51][1] = "<p class='ptext4'>b. SIGNATURE OF TRANSPORTATION OFFICER</p>"+textbox;
		textfield[51][16] = "<p><b>c. TITLE</b> <i>(Print or type)</i></p>"+textbox;
		textfield[51][23] = "<p class='ptext4'>d. DATE SIGNED</p><i>(YYYYMMDD)</i>"+textbox;

		// 	textfield[][] = "";
		// 	textfield[][] = "";
		// <input type='text' value='test' />
	%>
	<c:set var="textfield" value="<%=textfield%>" />
	<div id="wrapTable">
		<table id="mytable">
			<colgroup>
				<c:forEach begin="1" end="24" var="i">
					<col id="tablecol${i}">
				</c:forEach>
			</colgroup>
			<tbody>
				<c:forEach begin="1" end="51" var="i">
					<tr id="table_tr${i}">
						<c:forEach begin="1" end="24" var="j">
							<td id="table_td_${i}_${j}"><input type="hidden"
								id="hidden_${i}_${j}" value="${textfield[i][j]}" /></td>
							<%-- 					<td id="table_td_${i}_${j}">${textfield[i][j]}</td> --%>
							<%-- 					<td id="table_td_${i}_${j}"><center>${i},${j}</center></td> --%>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script>
		spanning(1, 1, 1, 22);
		spanning(1, 23, 1, 2);
		spanning(2, 1, 1, 24);
		spanning(3, 1, 2, 8); // 1
		spanning(3, 9, 2, 8); // 2
		spanning(3, 17, 1, 8); // 16
		spanning(5, 1, 2, 16); // 3.a
		spanning(7, 1, 2, 7); // 3.b
		spanning(7, 8, 2, 9); // 3.c
		spanning(9, 1, 2, 7); // 4
		spanning(9, 8, 2, 9); // 5
		spanning(11, 1, 3, 7); // 6.a
		spanning(11, 8, 3, 9); // 6.b
		spanning(14, 1, 2, 7); // 7.a
		spanning(14, 8, 2, 9); // 7.b
		spanning(16, 1, 3, 13); // 8
		spanning(16, 14, 3, 3); // 9
		spanning(4, 17, 2, 4); // 16(1)
		spanning(4, 21, 2, 2); // 16(2)
		spanning(4, 23, 2, 1); // 16(3)
		spanning(4, 24, 2, 1); // 16(4)
		spanning(19, 1, 2, 10); // 10
		spanning(19, 11, 2, 6); // 11
		spanning(21, 1, 2, 13); // 12
		spanning(21, 14, 2, 3); // LBS
		spanning(23, 1, 1, 16); // 13
		spanning(24, 1, 1, 2); // 13.a_top
		spanning(25, 1, 1, 2); // 13.a_bottom
		spanning(24, 3, 1, 3); // 13.(1)_top
		spanning(25, 3, 1, 3); // 13.(1)_bottom	
		spanning(24, 6, 1, 2); // 13.(2)_top
		spanning(25, 6, 1, 2); // 13.(2)_bottom
		spanning(24, 8, 1, 9); // 13.b
		spanning(25, 8, 1, 1); // 13.b_
		spanning(25, 9, 1, 2); // 13.b_origin
		spanning(25, 11, 1, 1); // 13.b_
		spanning(25, 12, 1, 3); // 13.b_destination
		spanning(25, 15, 1, 1); // 13.b_
		spanning(25, 16, 1, 1); // 13.b_other
		spanning(26, 1, 1, 10); // 13.dates
		spanning(27, 1, 1, 2); // 13.c
		spanning(27, 3, 1, 4); // 13.d
		spanning(27, 7, 1, 5); // 13.e
		spanning(26, 11, 2, 3); // 13.f
		spanning(26, 14, 2, 3); // 13.g
		spanning(28, 1, 2, 4); // 13.h
		spanning(28, 5, 2, 12); // 13.i
		spanning(30, 1, 1, 12); // 13.j
		spanning(30, 13, 1, 1); // 13.j_
		spanning(30, 14, 1, 1); // 13.j_yes
		spanning(30, 15, 1, 1); // 13.j_
		spanning(30, 16, 1, 1); // 13.j_no
		spanning(31, 1, 1, 7); // 14.
		spanning(32, 1, 1, 7); // 14.b
		spanning(33, 1, 1, 7); // 14.d
		spanning(34, 1, 1, 7); // 14.f
		spanning(31, 8, 1, 9); // 14.a
		spanning(32, 8, 1, 9); // 14.c
		spanning(33, 8, 1, 9); // 14.e
		spanning(34, 8, 1, 9); // 14.g
		spanning(35, 1, 1, 16); // 15
		spanning(36, 2, 2, 3); // type a. // bug...! hidden 38 line
		spanning(38, 1, 1, 4); // type a._1
		spanning(39, 1, 1, 4); // type a._2
		spanning(40, 1, 1, 4); // type a._3
		spanning(41, 1, 1, 4); // type a._4
		spanning(36, 5, 2, 9); // type b.
		spanning(38, 5, 1, 9); // type b._1
		spanning(39, 5, 1, 9); // type b._2
		spanning(40, 5, 1, 9); // type b._3
		spanning(41, 5, 1, 9); // type b._4
		spanning(36, 14, 2, 3); // type c.
		spanning(38, 14, 1, 3); // type c._1
		spanning(39, 14, 1, 3); // type c._2
		spanning(40, 14, 1, 3); // type c._3
		spanning(41, 14, 1, 3); // type c._4
		for (var i = 6; i <= 41; ++i) {
			switch (i) {
			case 25:
			case 31:
			case 37:
			case 40:
				spanning(i, 17, 2, 4); // 16.t,y
				spanning(i, 21, 2, 2); // 16.t,y
				spanning(i, 23, 2, 1); // 16.t,y
				spanning(i, 24, 2, 1); // 16.t,y
				break;
			case 33:
				spanning(i, 17, 1, 2); // 16.z
				spanning(i+1, 17, 1, 1); // 16.z
				spanning(i+1, 18, 1, 1); // 16.z
				spanning(i, 19, 1, 1); // 16.z
				spanning(i, 20, 1, 1); // 16.z
				spanning(i+1, 20, 1, 1); // 16.z
				spanning(i, 21, 2, 2); // 16.z
				spanning(i, 23, 2, 1); // 16.z
				spanning(i, 24, 2, 1); // 16.z
				break;
			case 26:
			case 32:
			case 34:
			case 38:
			case 41:
				break;
			default:
				spanning(i, 17, 1, 4); // 16~
				spanning(i, 21, 1, 2); // 16~
				spanning(i, 23, 1, 1); // 16~
				spanning(i, 24, 1, 1); // 16~
				break;
			}
		}
		spanning(42, 1, 1, 24); // 17.
		spanning(43, 1, 1, 24); // 18.
		spanning(44, 1, 1, 15); // 18.a
		spanning(45, 2, 1, 2); // 18.a
		spanning(45, 4, 1, 1); // 18.a
		spanning(45, 5, 1, 11); //18.a
		spanning(46, 2, 1, 2); // 18.a
		spanning(46, 4, 1, 12); // 18.a
		spanning(44, 16, 3, 7); // 18.b
		spanning(44, 23, 3, 2); // 18.c
		spanning(47, 1, 1, 24); // 19.
		spanning(48, 1, 1, 9); // 19.a
		spanning(48, 11, 1, 6); // 19.a
		spanning(48, 18, 1, 3); // 19.a
		spanning(48, 22, 1, 3); // 19.a
		spanning(49, 2, 1, 8); // 19.a
		spanning(49, 11, 1, 6); // 19.a
		spanning(49, 18, 1, 4); // 19.a
		spanning(50, 2, 1, 8); // 19.a
		spanning(50, 11, 1, 6); // 19.a
		spanning(50, 18, 1, 4); // 19.a
		spanning(49, 22, 2, 3); // 19.a
		spanning(51, 1, 1, 15); // 19.b
		spanning(51, 16, 1, 7); // 19.c
		spanning(51, 23, 1, 2); // 19.d

		function spanning(tr, td, rowspan, colspan) {
			var thisRow = $("#table_td_" + tr + "_" + td);
			thisRow.attr('rowspan', rowspan);
			thisRow.attr('colspan', colspan);
			if((tr==25)&&(td==9||td==12||td==16)){
				document.getElementById("table_td_" + tr + "_" + td).innerHTML =
					"<div class='divnomargintext'>"+document.getElementById("hidden_" + tr + "_" + td).value+"</div>";
			}else if((tr==33&&td==20)||(tr==34&&(td==18||td==20))){
				document.getElementById("table_td_" + tr + "_" + td).innerHTML =
					"<div class='divsmallmargintext'>"+document.getElementById("hidden_" + tr + "_" + td).value+"</div>";
			}else{
				document.getElementById("table_td_" + tr + "_" + td).innerHTML =
					"<div class='divtext'>"+document.getElementById("hidden_" + tr + "_" + td).value+"</div>";
			}			
			
			document.getElementById("table_td_" + tr + "_" + td).innerHTML = document
					.getElementById("table_td_" + tr + "_" + td).innerHTML
					.replace(/　/gi, '"');
			
			for (var i = 0; i < rowspan; ++i) {
				for (var j = 0; j < colspan; ++j) {
					if (!(i == 0 && j == 0)) {
						removeCell(tr + i, td + j);
					}
				}
			}
		}

		function rowSpan(tr, td, rowspan) {
			var thisRow = $("#table_td_" + tr + "_" + td);
			thisRow.attr('rowspan', rowspan);
			for (var i = 1; i < rowspan; ++i) {
				removeCell(tr + i, td);
			}
		}
		function colSpan(tr, td, colspan) {
			var thisRow = $("#table_td_" + tr + "_" + td);
			thisRow.attr('colspan', colspan);
			for (var i = 1; i < colspan; ++i) {
				removeCell(tr, td + i);
			}
		}
		function removeCell(tr, td) {
			var thisCell = $("#table_td_" + tr + "_" + td);
			thisCell.remove();
		}
	</script>
</body>
