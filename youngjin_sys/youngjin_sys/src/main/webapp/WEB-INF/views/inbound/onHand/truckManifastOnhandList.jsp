<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<c:set var="pagination" value="${inboundFilter.pagination }"/>
<html>
<head>
<title>Truck GBL List</title>

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
	
	var numPagesPerScreen = <c:out value='${pagination.numPagesPerScreen}'/>;
	var page = <c:out value='${pagination.currentPage}'/>;
	var numPages = <c:out value='${pagination.numPages}'/>;
	
	function goToNextPages() {
		goToPage(Math.min(numPages, page + numPagesPerScreen));
	}
	
	function goToPage(page) {	
		//location.href =  contextPath + '/member/leading/archives/page/'+page;
		$("input#page").val(page);
		$("form#inboundFilter").submit();
	}
	
	function goToPreviousPages() {
		goToPage(Math.max(1, page - numPagesPerScreen));
	}
</script>

<%@ include file="../../../layout/include_script.jspf" %>
</head>
<body>
	<div class="truck_gbl_list_wrap">
		<div class="pop_title_line">
			<span>ON HAND LIST</span>
		</div>	
		
		<div class="gbl_filter">
			<ul>
				<li>Truck Manifast Date : <input type="text" id="truck_manifast_date" /></li>	
				<li>AREA : <input type="text" id="truck_manifast_area"/></li>
				<li>
					<span class="truck_gbl_addButton truck_gbl_onHand_addbutton yj_button" >add</span>
				</li>
			</ul>
		</div>
		<div>
			<table class="yj_table">
				<thead>
					<tr>
						<th>NO</th>
						<th>ON HAND DATE</th>
						<th>FIRST ARRIVALABLE DELIVER DATE</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${onHandList eq '[]' or onHandList eq null or onHandList eq '' }">
						<tr>
							<td colspan="14">onHandList 가 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="onHand" items="${onHandList }" varStatus="i">
						<tr class="inbound_on_hand_list_select_tr" data-seq="${onHand.seq }">
							<td>${i.count }</td>
							<td>${onHand.onHandDate }</td>
							<td>${onHand.firstArrivalableDeliverDate }</td>
							<td><input type="checkbox" value="${onHand.seq }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>