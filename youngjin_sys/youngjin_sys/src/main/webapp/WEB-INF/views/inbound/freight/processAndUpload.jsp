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
<title>Select</title>

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
</head>

<c:if test="${addUpdateCheck eq 'true'}">
	<script>
		parent.$.smartPop.close();
	</script>
</c:if>
<body>
	<div class="gbl_process_and_upload_div">
		<div class="pop_title_line">
			<span style="font-size: 12pt; text-align: center; vertical-align: middle;">
			GBL NO : <font color="yellow">${gblInform.gblNo }</font>　　Customer : <font color="yellow">${gblInform.shipperName }</font></span>
		</div>
		<div class="inbound_gbl_process" data-seq="${seq}">
			<ul>
				<li class="gbl_process_input inbound_gbl_process_input" ><img src="${cp }/resources/images/freight/gbl_input_complete.png" /></li>
				<li class="gbl_process_input inbound_gbl_process_weight">
					<c:choose>
						<c:when test="${process.weight eq 0 }">
							<img src="${cp }/resources/images/freight/gbl_weight_delay.png" />
						</c:when>
						<c:when test="${process.weight eq 1 }">
							<img src="${cp }/resources/images/freight/gbl_weight_complete.png" />
						</c:when>						
					</c:choose>
				</li>
				<li class="gbl_process_input inbound_gbl_process_custom">
					<c:choose>
						<c:when test="${process.custom eq 0 and process.weight eq 0 }">
							<img src="${cp }/resources/images/freight/custom_basic.png" />
						</c:when>
						<c:when test="${process.custom eq 0 and process.weight eq 1 }">
							<img src="${cp }/resources/images/freight/custom_delay.png" />
						</c:when>						
						<c:when test="${process.custom eq 1 }"></c:when>						
					</c:choose>
					<c:if test="${process.custom eq 1 }">						
						<img src="${cp }/resources/images/freight/custom_complete.png" />
					</c:if>
				</li>
				<li class="gbl_process_delivery inbound_gbl_process_onHandList">
					<c:choose>									
						<c:when test="${process.custom eq 0 and process.onHandList eq 0}">
							<img src="${cp }/resources/images/freight/onhandlist_basic.png" />
						</c:when>									
						<c:when test="${process.custom eq 1 and process.onHandList eq 0}">
							<img src="${cp }/resources/images/freight/onhandlist_delay.png" />
						</c:when>									
						<c:when test="${process.onHandList eq 1}"></c:when>
					</c:choose>
					<c:if test="${process.onHandList eq 1}">
						<img src="${cp }/resources/images/freight/onhandlist_complete.png" />
					</c:if>
				</li>
				<li class="gbl_process_preparation inbound_gbl_process_delivery">
					<c:choose>
						<c:when test="${process.onHandList eq 0 and process.delivery eq 0 }">
							<img src="${cp }/resources/images/freight/delivery_basic.png" />
						</c:when>
						<c:when test="${process.onHandList eq 1 and process.delivery eq 0 }">
							<img src="${cp }/resources/images/freight/delivery_delay.png" />
						</c:when>
						<c:when test="${process.delivery eq 1 }">
						</c:when>
					</c:choose>
					<c:if test="${process.delivery eq 1 }">
						<img src="${cp }/resources/images/freight/delivery_complete.png" />
					</c:if>
				</li>
				<li class="gbl_process_preparation inbound_gbl_process_invoice">	
					<c:choose>				
						<c:when test="${process.delivery eq 0 and process.invoice eq 0 }">
							<img src="${cp }/resources/images/freight/invoice_basic.png" />
						</c:when>
						<c:when test="${process.delivery eq 1 and process.invoice eq 0 }">
							<img src="${cp }/resources/images/freight/invoice_delay.png" />
						</c:when>
						<c:when test="${process.invoice eq 1 }">
						</c:when>
					</c:choose>
					<c:if test="${process.invoice eq 1 }">
						<img src="${cp }/resources/images/freight/invoice_complete.png" />
					</c:if>
				</li>
			</ul>
		</div>
		<div class="gbl_upload">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="60%" />
					<col width="20%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>TITLE</th>
						<th>DATE</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fileList eq '[]' }">
						<tr>
							<td colspan="4">등록된 파일이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="file" items="${fileList }" varStatus="i">
						<c:choose>
							<c:when test="${file.gblFileNo eq 1 }">
								<tr class="selectFileTr" data-no="1">
									<td>${i.count }</td>						
									<td>GBL 관련 전체문서</td>
									<td>${file.gblUpdateDate }</td>
									<td><input type="radio" value="${file.seq }" name="selectFileList"></td>
								</tr>
							</c:when>
							<c:when test="${file.gblFileNo eq 2 }">
								<tr class="selectFileTr" data-no="2">
									<td>${i.count }</td>
									<td>GBL 관련 추가문서</td>
									<td>${file.gblUpdateDate }</td>
									<td><input type="radio" value="${file.seq }" name="selectFileList"></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td id="upload_tfoot" class="inbound_upload_tfoot" colspan="4" data-seq="${seq }">
						<!-- 	<span class="document_bl_button yj_button">BL 분할 신청서</span>
							<span class="document_power_of_attorney_button yj_button">Power Of Attorney</span> -->
							<span class="inbound_gbl_delete yj_button"><font color="yellow">GBL [${gblInform.seq }] DELETE</font></span>
							<span class="document_upload_button yj_button">Document Upload</span>
							<span class="document_view yj_button">Document Download</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>