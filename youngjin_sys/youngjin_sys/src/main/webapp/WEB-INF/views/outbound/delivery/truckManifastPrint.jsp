<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import = "java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#topcodetd{
		padding-top : 30px;
		padding-left : 30px;
		padding-bottom : 20px;
		text-align: left;
		font-size: 20px;
		font-weight: bold;
	}
	#topdatetd{
		padding-right : 10px;
		padding-bottom : 10px;
		text-align: right;
		font-size: 13px;
		font-weight: bold;
	}
	#gblnotd{
		padding-top : 5px;
		padding-bottom : 5px;
		width : 100px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
	}
	#shippernametd{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
		width : 200px;
	}
	#origintd{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
		width : 150px;
	}
	#podtd{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
		width : 100px;
	}
	#piecetd{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
		width : 200px;
	}
	#cntrnotd{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-size: 13px;
		font-weight: bold;
		border-top : solid;
		border-bottom: solid;
		width : 100px;
	}
	#inputstart{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-weight : bold;
		font-size:13px;
	}
	#input{
		padding-top : 5px;
		padding-bottom : 5px;
		text-align: center;
		font-weight : bold;
		font-size:13px;
	}
</style>
<%-- <%
	////////////////////////
	//부킹리스트 따라하기
	ArrayList<ArrayList<String>> arr=new ArrayList<ArrayList<String>>();//순서대로 데이터 집어넣기
	ArrayList<String> test=new ArrayList<String>();
	ArrayList<String> test2=new ArrayList<String>();
	ArrayList<String> test3=new ArrayList<String>();
	for(int i=0;i<6;i++){
		test.add(""+i);
		test2.add(""+i+1);
		test3.add(""+i+"asdf");
	}
	arr.add(test);
	arr.add(test2);
	arr.add(test3);
	String date="30-AUG-13";
%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		<table align="center" width="90%" cellspacing="0">
		<tr>
		<td id='topcodetd' colspan="6">CODE #${truckManisfast.code }</td>
		</tr>
		<tr>
			<fmt:parseDate var="parsePud" value="${fn:substring(truckManisfast.date, 0, 10)}" pattern="yyyy-MM-dd"/>
			<c:set var="pud" value="${parsePud }" />
			<td colspan="6" id="topdatetd">${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }</td><%-- <% out.println("<td colspan=\"6\" id='topdatetd'>"+date+"</td>"); %> --%>
		</tr>
		<tr>
		<td id='gblnotd'>GBL-NO</td>
		<td id='shippernametd'>SHIPPER NAME</td>
		<td id='origintd'>ORIGIN</td>
		<td id='podtd'>POD</td>
		<td id='piecetd'>PIECE</td>
		<td id='cntrnotd'>CNTR NO</td>
		</tr>
		<c:forEach var="gbl" items="${gblList }">
			<tr style="text-align: center;">
				<td id="inputstart">${gbl.no }</td>
				<td id="inputstart">${gbl.shipper }</td>
				<td id="inputstart">${gbl.area }</td>
				<td id="inputstart">${gbl.pod } / ${podMap[gbl.pod].podAgentName})</td>
				<c:forEach var="weightCertificate" items="${gbl.containerList }" varStatus="i">
					<c:choose>
						<c:when test="${i.index eq 0 }">
							<td id="inputstart">
								${weightCertificate.gross }-${weightCertificate.net }-${weightCertificate.tare } ${weightCertificate.piece }
							</td>
							<td id="inputstart">
								${weightCertificate.remark }
							</td>
						</c:when>
						<c:otherwise>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td id="inputstart">
									${weightCertificate.gross }-${weightCertificate.net }-${weightCertificate.tare } ${weightCertificate.piece }
								</td>
								<td id="inputstart">
									${weightCertificate.remark }
								</td>								
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
		<%-- <%for(int i=0;i<arr.size();i++){//부킹리스트 따라하기
			out.print("<tr style=\"text-align : center;\">");
				for(int j=0;j<6;j++){ // 6개의 데이터가 있을거니깐
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
		</table>
	</div>
</body>
</html>