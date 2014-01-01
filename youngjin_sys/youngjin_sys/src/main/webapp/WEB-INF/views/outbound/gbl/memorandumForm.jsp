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
<title>Memorandum</title>

<link rel="stylesheet" href="${cp }/resources/css/default.css">
<link rel="stylesheet" href="${cp }/resources/css/font.css">
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
<body>
	<div class="memorandum_form_wrap">
		<div class="pop_title_line">
			<span>MEMORANDUM</span>
		</div>	
		<div class="memorandum_form_content_wrap" data-seq="${seq }" data-type="${type }" data-memorandumSeq="${memorandumSeq }" >
			<div id="memorandum_button">
				<ul id="memorandum_button_list">
					<li>
						<span class="yj_button memorandum_back">
							back
						</span>
					</li>
					<c:if test="${checkMemorandum.seq ne null and checkMemorandum.seq ne '' }">
						<li>
							<span class="yj_button memorandum_print" data-seq="${checkMemorandum.gblSeq }" data-article="${articleComa}" data-type="${type }" data-memorandumSeq="${memorandumSeq }">print</span>
						</li>
					</c:if>
					<li>
						<span class="yj_button ${(checkMemorandum.seq ne null and checkMemorandum.seq ne '' ? 'memorandum_update' : 'memorandum_add')}">
							${(checkMemorandum.seq ne null and checkMemorandum.seq ne '' ? 'update' : 'add')}
						</span>
					</li>
				</ul>
			</div>
			<div id="memorandum_title">YOUNGJIN TRADE & TRANSPORTATION CO. LTD.</div>
			<ul>	
				<li id="memorandum_date"></li>
				<li id="memorandum_form_subject">
					SUBJECT : <input type="text" id="memorandum_subject" value="${checkMemorandum.subject }" />
				</li>
				<li>
					INSTALLATION TRANSPORTATION OFFICER<br/>
					403rd Army Field Supprot Brigade(AFSB)<br/>
					Logistics Readiness Center Yongsan<br/>
					UNIT #15802, APO AP 96205-5802
				</li>
				<li id="memorandum_form_comment">
					<textarea id="memorandum_comment">${checkMemorandum.comment }</textarea>
				</li>
				<li>
					<p>NAME: ${gbl.customerName }</p>
					<p>RANK: ${gbl.rank }</p>
					<p>SSN: ${gbl.ssn }</p><br/>
					<p>P/U DATE: ${gbl.pud }</p>
					<p>GBL NO: ${gbl.no }</p>
					<p>CARRIER: ${gbl.scac }</p>
					<p>CODE: ${gbl.code }</p>
				</li>
				<li>
					<c:choose>
						<c:when test="${articles ne '' and articles ne '[]' and articles ne null }">
							NAME OF ARTICLE : 
							<c:forEach var="article" items="${articles}" varStatus="i">
								<c:if test="${i.count > 1 }">
									<c:forEach begin="0" end="8" step="1">
										&nbsp;
									</c:forEach>
								</c:if>
								(${i.count }) ${memorandum.codeName } ${article }
							</c:forEach>
							<input type="hidden" id="memorandum_articles" value="${articleComa }">
						</c:when>
						<c:otherwise>
							NAME OF ARTICLE : (1) ${memorandum.codeName }							
						</c:otherwise>
					</c:choose>
				</li>
				<li id="memorandum_form_article_comment">
					<textarea id="memorandum_article_comment">${checkMemorandum.articleComment }</textarea>
				</li>
				<li id="memorandum_form_chief_of_office">
					<textarea id="memorandum_chief_of_office">${checkMemorandum.chiefOfOffice }</textarea>
				</li>
				<li id="memorandum_form_office_info">
					<textarea id="memorandum_office_info">${checkMemorandum.officeInfo}</textarea>
				</li>				
				<li id="memorandum_form_area_director">
					<textarea id="memorandum_area_director">${checkMemorandum.areaDirector }</textarea>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>