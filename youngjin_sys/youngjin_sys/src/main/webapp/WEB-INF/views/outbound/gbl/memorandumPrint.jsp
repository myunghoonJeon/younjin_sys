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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 20cm;
	}
	#left{
		width:2.5cm;
	}
	#right{
		width:2.5cm;
	}
	#toptitletd{
		font-weight: bold;
		font-size: 17pt;
		border-bottom: solid;
		border-bottom-style: double;
		text-align: center;
		vertical-align:bottom;
		height: 1cm;
	}
	#date_td{
		padding-top: 0.6cm;
		text-align: right;
		padding-bottom: 0.5cm;
		font-size: 10pt;
	}
	#subject_context_td{
		padding-left : 0.2cm;
		font-size: 10pt;
	}
	#subject_title_td{
		font-size: 10pt;
		width: 1cm;
		vertical-align: top;
	}
	#context_td1{
		padding-top: 0.5cm;
		width: 8cm;
		font-size: 10pt;
	}
	#context_td2{
		padding-top: 0.5cm;
		font-size: 10pt;
	}
	#inaccordance_td{
		padding-top: 1cm;
	}
	#name_td, #rank_td, #ssn_td, #pud_td, #gblno_td, #carrier_td, #code_td, #address_td, #nameofarticle_td, #estimatedwt_td{
		text-decoration : underline;
		font-size: 10pt;
		padding-top:0.5cm;
	}
	#thirdparty, #locality, #approved{
		font-size:10pt;
		padding-top:0.8cm;
		width:8cm;
	}
	#localmanager{
		font-size:10pt;
		padding-top:0.8cm;
		width:8cm;
		height:2cm;
	}
	#term{
		width:1cm;
	}
	#taforto{
		font-size:10pt;
		width:8cm;
		height:2cm;
	}
</style>
<%
	String date="";
	String subject="REQUEST FOR AUTHORIZATION OF ADDITIONAL SERVICE<br>FOR LOWERING OF ARTICLES";
	String installation="INSTALLATION TRANSPORTATION OFFICER<BR>403rd Army Field Support Brigade(AFSB)<BR>Logistics Readiness Center Yongsan<BR>UNIT #15802, APO AP 96205-5802";
	String inaccordance="IN accordance with DPS International Tender 223b(4) request compensation to the carrier<BR>for use of lowering equipment for safe transportation of the following listed articles for";
	String name="DEUS, WILLIAM, J.";
	String rank="1LT";
	String ssn="XXX-XX-5019";
	String pud="19 JUL 13";
	String gbl="QXAK0060503";
	String carrier="AALF";
	String code="4";
	String adress="BUNDANG-GU BAKHYUNDONG 542 PRUGIO GURANGBULL 111-701";
	String nameofarticle="USING FOR THE LOWERING EQUIPMENT";
	String estimatedwt="4000LBS";
	String localmanager="SHIN, DOO SIK<BR>YONG SAN BRANCH MANAGER<BR>YOUNGJIN TRAED & CO.LTD";
	String locality="ASKO-LRC-YON-LGT<BR>Unit #15802, APO AP 96205-5802<BR>FOR YOUNGJIN T&T CO.,LTD.";
	String taforto="JOHN F. CUTTER. GS-13,<BR>";
%>
</head>
<body onload="window.print();">
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>		
			<td id='toptitletd' colspan="3">YOUNGJIN TRADE & TRANSPORTATION CO. LTD.</td>
		</tr>
		<tr>			
			<fmt:parseDate var="parseWriteDate" value="${fn:substring(checkMemorandum.writeDate, 0, 10)}" pattern="yyyy-MM-dd"/>
			<c:set var="writeDate" value="${parseWriteDate }" />
			<td id="date_td" colspan="3">DATE : ${fn:substring(writeDate, 8, 10) }-${ fn:substring(writeDate, 4, 7)}-${ fn:substring(writeDate, 26, 28) }</td>
			<td></td>
		</tr>
		<tr>
			
			<td colspan="2" id='subject_title_td'>SUBJECT : 
				<c:choose> 
					<c:when test="${memorandum.subCode eq '01' }">
						Request For Authorization of additional Service for rowering of article
					</c:when>
					<c:when test="${memorandum.subCode eq '03' }">
						Request For Authorization of special crate
					</c:when>
					<c:otherwise>
						${checkMemorandum.subject }
					</c:otherwise>
				</c:choose>
			</td>
			<td id='right'></td>
		</tr>
	</table>
	
	<table  style="font-weight: bold;" align="center" cellspacing="0">
		<tr >			
			<td id="context_td1"><%out.print(installation);%></td>
			<td></td>
		</tr>
		<tr id="context2">
			
			<td id='context_td2'  colspan="2">${checkMemorandum.comment }</td>
		</tr>
	</table>
	
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>
			<td colspan="5" id='inaccordance_td'></td>
		</tr>
		<tr>			
			<td id='name_td'>NAME : ${gbl.customerName }</td>
			<td id='rank_td'>RANK : ${gbl.rank }</td>
			<td id='ssn_td' colspan="2">SSN : XXXX-XX-${fn:substring(gbl.ssn, 5,10)}</td>
		</tr>
		<tr>			
			<fmt:parseDate var="parsePud" value="${gbl.pud}" pattern="yyyyMMdd"/>
			<c:set var="pud" value="${parsePud }" />
			<td id='pud_td'>P/U DATE : ${fn:substring(pud, 8, 10) }-${ fn:substring(pud, 4, 7)}-${ fn:substring(pud, 26, 28) }</td>
			<td id='gblno_td'>GBL NO : ${gbl.no }</td>
			<td id='carrier_td'>CARRIER : ${gbl.scac }</td>
			<td id='code_td'>CODE : ${gbl.code }</td>
		</tr>
		<tr>			
			<td id='address_td' colspan="4">ADDRESS : ${gbl.originAddress }</td>
		</tr>
		<tr>			
			<td id='nameofarticle_td' colspan="4">
				<c:choose>
					<c:when test="${articles ne '' and articles ne '[]' and articles ne null }">
					NAME OF ARTICLE : 
						<c:forEach var="article" items="${articles}" varStatus="i">
							<c:if test="${i.count > 1 }">
								<c:forEach begin="0" end="13" step="1">
									&nbsp;
								</c:forEach>
							</c:if>
						(${i.count }) ${memorandum.codeName } ${article }<br/>
						</c:forEach>
						<input type="hidden" id="memorandum_articles" value="${articleComa }">
					</c:when>
					<c:when test="${ memorandum.subCode eq '03'}">
						NAME OF ARTICLE : (1) ${memorandum.codeName }							
					</c:when>
				</c:choose>
			</td>
		</tr>
		<c:if test="${type eq '01' }">
			<tr>			
				<td id='estimatedwt_td' colspan="4">ESTIMATED WT : <%out.print(estimatedwt);%></td>
			</tr>
		</c:if>
	</table>
	<table style="font-weight: bold;" align="center" cellspacing="0">
		<tr>			
			<td id='thirdparty' >
				<c:choose>
					<c:when test="${memorandum.subCode eq '02' or memorandum.subCode eq '03' }">
						${checkMemorandum.articleComment1 }
						<c:if test="${checkMemorandum.articleComment1 ne null and  checkMemorandum.articleComment1 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment2 }
						<c:if test="${checkMemorandum.articleComment2 ne null and  checkMemorandum.articleComment2 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment3 }
						<c:if test="${checkMemorandum.articleComment3 ne null and  checkMemorandum.articleComment3 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment4 }
						<c:if test="${checkMemorandum.articleComment4 ne null and  checkMemorandum.articleComment4 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment5 }
					</c:when>
					<c:when test="${memorandum.subCode eq '01' }">
						${checkMemorandum.articleComment1 }
						<c:if test="${checkMemorandum.articleComment1 ne null and  checkMemorandum.articleComment1 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment2 }
						<c:if test="${checkMemorandum.articleComment2 ne null and  checkMemorandum.articleComment2 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment3 }
						<c:if test="${checkMemorandum.articleComment3 ne null and  checkMemorandum.articleComment3 ne '' }">
							<br /><br />
						</c:if>
						${checkMemorandum.articleComment4 }
						<c:if test="${checkMemorandum.articleComment4 ne null and  checkMemorandum.articleComment4 ne '' }">
							<br />
						</c:if>
						${checkMemorandum.articleComment5 }
					</c:when>
				</c:choose>
			</td>
			<td id='term'></td>
			<td></td>
		</tr>
		<tr>			
			<td></td>
			<td></td>
			<td id='localmanager'>
				${branch.branchManager }<br />
				${branch.position }<br />
				YOUNGJIN TRAED & TRANS CO.,LTD
			</td>
		</tr>
		<tr>			
			<td id='locality'>
				${branch.itoAddress } <br/>
				FOR YOUNGJIN T&T CO.,LTD
			</td>
			<td id='term'></td>
			<td></td>
		</tr>
		<tr>
			<td id='approved' colspan="4">APPROVED</td>
		</tr>
		<tr>			
			<td></td>
			<td></td>
			<td id='taforto'>				
				${branch.itoChief }
			</td>
		</tr>
	</table>
</body>
</html>