<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	#booking_table_id tr td{ border-width: 1px; }

	#topletter{
		text-align : center;
		font-size : 13px;
		font-weight: bold;
	}
	#pudtd{
		width : 50px;
		font-size: 11px;
		border: solid;
	}
	#carriertd{
		width : 40px;
		font-size: 10px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#codetd{
		width : 35px;
		font-size: 10px;
		border-width: 1px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#shippertd{
		width : 180px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#gblnotd{
		width : 80px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#milsvctd{
		width : 30px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#origngbloctd{
		width : 50px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#pcstd{
		width : 30px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		}
	#cufttd{
		width : 45px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#gwttd{
		width : 45px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#nwttd{
		width : 45px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#containertd{
		width : 210px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	.subtd{
		width : 25px;
		font-size: 12px;
		border-bottom: solid;
		border-right: solid;
	}
	#destporttd{
		width : 30px;
		font-size: 10px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#deststatetd{
		width : 30px;
		font-size: 10px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#bigotd{
		width : 150px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#remarktop{
		font-size: 15px;
		font-weight: bold;
		border-left : solid;		
		border-right: solid;
		text-align: left;
		
	}
	#remarkbottom{
		border-left:solid;
		border-right:solid;
		border-bottom:solid;
	}
	#inputstart{
		font-size:13px;
		border-bottom:solid;
		border-right: solid;
		border-left : solid;
	}
	#input{
		font-size:13px;
		border-bottom:solid;
		border-right: solid;
	}
	#sign{
		text-align: right;
		font-weight: bold;
		font-size: 15px;
	}
	<%-- <%
		//setting parameter <TD> 21th
		//declare parameter
		//all of String type;
		//case 1 이건 변수 마다 각각 데이터를 넣어서 세팅하는경우
		String pud="";
		String carrier="";
		String code="";
		String shipper="";
		String gblno="";
		String milsvc="";
		String origngbloc="";
		String pcs="";
		String cuft="";
		String gwt="";
		String nwt="";
		String c1,c2,c3,c4,c5,c6,c7="";
		String destport,deststate,bigo="";
		String remark_context="remark context test";
		////////////////////////
		//만약 어레이 리스트로 데이터를 넘길경우 21가지 순서대로
		ArrayList<ArrayList<String>> arr=new ArrayList<ArrayList<String>>();//순서대로 데이터 집어넣기
		ArrayList<String> test=new ArrayList<String>();
		ArrayList<String> test2=new ArrayList<String>();
		for(int i=0;i<21;i++){
			test.add(""+i);
			test2.add(""+i+1);
		}
		arr.add(test);
		arr.add(test2);
	%> --%>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Booking List</title>
<LINK REL=StyleSheet HREF="" TYPE="text/css">
</head>
<body>
	<div style="height: 100px; text-align: center;">
		<br><br>
		<font style="text-decoration:underline; font-weight:bold; font-style: inherit; color: black; font-size: 20pt;">B O O K I N G　L I S T</font>
		<br>
	</div>
	<div>
		<table id="booking_table_id" border = "0" cellspacing="0" width="90%" align="center">
		<tr style=" align="center">
			<td id='topletter' colspan="3">OF</td>
			<td id='topletter'colspan="1">CFS</td>
			<td colspan="1"> </td>
			<td colspan="1"> </td>
			<td id='topletter' colspan="4">YJ　-　#4,#7　-　15</td>
			<td colspan="1"> </td>
			<td colspan="7"> </td>
			<td > </td>
			<td > </td>
			<fmt:parseDate var="parsePud" value="${fn:substring(bookingList.writeDate, 0, 10)}" pattern="yyyy-MM-dd"/>
			<c:set var="pud" value="${parsePud }" />
			<td id='topletter'>${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }</td> <!-- 아마 오늘 날짜 JSP로 들어가야할듯 -->
		</tr>
		<tr style="font-weight: bold; text-align: center; ">
			<td id='pudtd' rowspan="2">P/U<BR>DATE</td>
			<td id='carriertd' rowspan="2">CARRIER<BR>(SCAC)</td>
			<td id='codetd' rowspan="2">CODE</td>
			<td id='shippertd'rowspan="2">SHIPPER</td>
			<td id='gblnotd' rowspan="2">GBL　NO</td>
			<td id='milsvctd' rowspan="2">MIL<BR>SVC</td>
			<td id='origngbloctd' rowspan="2">ORIGN<BR>GBLOC</td>
			<td id='pcstd' rowspan="2">PCS</td>
			<td id='cufttd' rowspan="2">CUFT</td>
			<td id='gwttd' rowspan="2">G/WT</td>
			<td id='nwttd' rowspan="2">N/WT</td>
			<td id='containertd' colspan="7">CONTAINER</td>
			<td id='destporttd' rowspan="2">DEST<BR>PORT<BR>(POD)</td>
			<td id='deststatetd' rowspan="2">DEST<BR>STATE<BR>(SPLC)</td>
			<td id='bigotd' rowspan="2" >비 고</td>
		</tr>
		<tr style="text-align: center;font-weight: bold;">
		<td class='subtd'>11</td>
		<td class='subtd'>125</td>
		<td class='subtd'>91</td>
		<td class='subtd'>49</td>
		<td class='subtd'>O/F</td>
		<td class='subtd'>O/F</td>
		<td class='subtd'>S/P</td>
		</tr>
		<c:forEach var="gbl" items="${gblList }">
			<tr style="text-align : center;">
				<td id="inputstart">${fn:substring(gbl.pud, 4, 6) }-${ fn:substring(gbl.pud, 6, 8)}-${ fn:substring(gbl.pud, 2, 4) }</td>
				<td id="input">${gbl.scac }</td>
				<td id="input">${gbl.code }</td>
				<td id="input">${gbl.shipper }</td>
				<td id="input">${gbl.no }</td>
				<td id="input">${gbl.milSvc }</td>
				<td id="input">${gbl.originGblock }</td>
				<td id="input">${gbl.pcs }</td>
				<td id="input">${gbl.cuft }</td>
				<td id="input">${gbl.gross }</td>
				<td id="input">${gbl.net }</td>
				<c:forEach begin="1" end="7">
					<td id="input"></td>
				</c:forEach>
			<%-- 	<c:forEach var="container" items="${gbl.containerList}" >
					<c:choose>
						<c:when test="${container.type eq '11'}">
							<td>${container.type }</td>
						</c:when>
						<c:when test="${container.type eq '125'}">
							<td>${container.type }</td>
						</c:when>
						<c:when test="${container.type eq '91'}">
							<td>${container.type }</td>
						</c:when>
						<c:when test="${container.type eq '49'}">
							<td>${container.type }</td>
						</c:when>
						<c:when test="${container.type eq 'O/F'}">
							<td>${container.type }</td>
						</c:when>
						<c:when test="${container.type eq 'S/F'}">
							<td>${container.type }</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</c:forEach> --%>
				<td id="input">${gbl.destPort }</td>
				<td id="input">${gbl.destState }</td>
				<td id="input"></td>
			</tr>
		</c:forEach>
		<%-- <%for(int i=0;i<arr.size();i++){//여기서 부킹리스트 들어간만큼 포문돌면서 채워너야하는데 총 21개의 td가 존재 각각의 갑응ㄹ 끌어와야하는데 끌어오는 형태를 몰라서 더이상 진행 X
			out.print("<tr style=\"text-align : center;\">");
				for(int j=0;j<21;j++){ // 21개의 데이터가 있을거니깐
					if(j==0){
						out.print("<td id='inputstart'>"+arr.get(i).get(j)+"</td>");
					}
					else{
						out.print("<td id='input'>"+arr.get(i).get(j)+"</td>");
					}
					System.out.println("input check : "+i+"number list "+arr.get(i).toString()+" = inner : "+j+"th : "+arr.get(i).get(j));
				}
			out.println("</tr>");
		}
		 %> --%>
		 <tr>
		 	<td colspan="21" id="remarktop">REMARK</td>
		 </tr>
		 <tr>
		 	<td colspan="21" id="remarkbottom">${bookingList.remark }</td>
		 </tr>
		 <tr>
		 <td colspan="21" id ="sign">BOOKING AGENT : YOUNGJIN</td>
		 </tr>
		</table>
	</div>	
</body>
</html>