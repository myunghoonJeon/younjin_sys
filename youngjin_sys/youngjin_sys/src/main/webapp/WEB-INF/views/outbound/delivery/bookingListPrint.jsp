<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>

<style>
	#booking_table_id tr td{ border-width: 1px; }

	#topletter{
		text-align : center;
		font-size : 13px;
		font-family:arial;
		font-weight:bold;
	}
	#pudtd{
		width : 50px;
		font-size: 13px;
		font-family:arial;
		font-weight:bold;
		border: solid;
	}
	#carriertd{
		width : 40px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#codetd{
		width : 35px;
		font-size: 13px;
		border-width: 1px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#shippertd{
		width : 180px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#gblnotd{
		width : 80px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#milsvctd{
		width : 30px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#origngbloctd{
		width : 50px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#pcstd{
		width : 30px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
		}
	#cufttd{
		width : 45px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#gwttd{
		width : 45px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#nwttd{
		width : 45px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#containertd{
		width : 210px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	.subtd{
		width : 25px;
		font-size: 13px;
		border-bottom: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#destporttd{
		width : 30px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#deststatetd{
		width : 30px;
		font-size: 13px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#bigotd{
		width : 150px;
		font-size: 12px;
		border-bottom:solid;
		border-top: solid;
		border-right: solid;
	}
	#remarktop{
		font-size: 13px;
		font-weight: bold;
		border-left : solid;		
		border-right: solid;
		text-align: left;
		font-family:arial;
		font-weight:bold;
		
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
		font-family:arial;
		font-weight:bold;
	}
	#input{
		font-size:13px;
		border-bottom:solid;
		border-right: solid;
		font-family:arial;
		font-weight:bold;
	}
	#sign{
		text-align: right;
		font-weight: bold;
		font-size: 13px;
		font-family:arial;
		font-weight:bold;
	}
</style>


<script type="text/javascript" language="Javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" language="Javascript" src="${cp }/resources/jquery/jquery.json.js"></script>
<script type="text/javascript" language="Javascript" >
	var contextPath = '<c:out value="${cp}"/>';
	var realPath = '<c:out value="${rp}"/>';
    $(document).ready(function () {
        $("#yjnInput").focusout(function (event) {
            	var bookSeq = $("#bookSeq").attr('data-seq');
            	var column = $("#yjnInput").attr('name');
            	var value = $("#yjnInput").val();
            	var json = {
                		'bookSeq' : bookSeq,
                		'column' : column,
                		'value' : value
                	};
                	var url = contextPath + '/outbound/bookinglistUpdate.json';
              	
                	$.postJSON(url, json, function(){
                		return jQuery.ajax({
                			success : function(){
                			},
                			error : function(){
                				alert('에러 발생');
                			}
                		});
                	});
            }
        );
        
        $("#remark").focusout(function (event) {
        	var bookSeq = $("#bookSeq").attr('data-seq');
        	var column = 'remark';
        	var value = $("#remark").val();

        	var json = {
            		'bookSeq' : bookSeq,
            		'column' : column,
            		'value' : value
            	};
            	var url = contextPath + '/outbound/bookinglistUpdate.json';
            	$.postJSON(url, json, function(){
            		return jQuery.ajax({
            			success : function(){
            			},
            			error : function(){
            				alert('에러 발생');
            			}
            		});
            	});
        }
    );
    });
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Booking List1</title>
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
		<tr id="bookSeq" class="bookinglistInfo_wrap" data-seq="${bookingList.seq }">
			<td colspan="10"><input type="text" class="po" name="yjn" id="yjnInput" style="top:40px;border:none; left:56px; width:450px; height:20px; font-size: 13pt;font-weight:bold; font-family: arial;" value="${bookingList.yjnInput }"/></td>
		</tr>
		<tr style=" align="center">
			<td id='topletter' colspan="3">OF</td>
			<td id='topletter'colspan="1">CFS</td>
			<td colspan="1"> </td>
			<td colspan="1"> </td>
			<td id='topletter' colspan="4">YJ　-　#4,#7　- ${bookingList.yjCount } </td>
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
				<td id="input">${gbl.type11 }</td>
				<td id="input">${gbl.type125 }</td>
				<td id="input">${gbl.type91 }</td>
				<td id="input">${gbl.type49 }</td>
				<td id="input">${gbl.typeOf1 }</td>
				<td id="input">${gbl.typeOf2 }</td>
				<td id="input">${gbl.typeSp }</td>
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
				<td id="input">${gbl.pod }</td>
				<td id="input">${gbl.usNo }</td>
				<td id="input"><input style="border:none;  font-family: arial; font-weight: bold;" value="${gbl.destState }"></td>
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
		 	<td colspan="21" id="remarktop">REMARK　　　　<input id="remark" name="remark" value="${bookingList.remark }" style="font-weight:bold; font-family:arial; font-size:10pt;border: none;width:1000px;"> </input></td>
		 </tr>
		 <tr>
		 	<td colspan="21" id="remarkbottom"></td>
		 </tr>
		 <tr>
		 <td colspan="21" id ="sign">BOOKING AGENT : YOUNGJIN</td>
		 </tr>
		</table>
	</div>	
</body>
</html>