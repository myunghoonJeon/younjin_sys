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
						<span id="inbound_memorandum_back" class="yj_button memorandum_back">
							back
						</span>
					</li>
					<c:if test="${checkMemorandum.seq ne null and checkMemorandum.seq ne '' }">
						<li>
							<span id="inbound_memorandum_print" class="yj_button memorandum_print" data-seq="${checkMemorandum.gblSeq }" data-article="${articleComa}" data-type="${type }" data-memorandumSeq="${memorandumSeq }">print</span>
						</li>
					</c:if>
					<li>
						<span id="${(checkMemorandum.seq ne null and checkMemorandum.seq ne '' ? 'inbound_memorandum_update' : 'inbound_memorandum_add')}" class="yj_button ${(checkMemorandum.seq ne null and checkMemorandum.seq ne '' ? 'memorandum_update' : 'memorandum_add')}">
							${(checkMemorandum.seq ne null and checkMemorandum.seq ne '' ? 'update' : 'add')}
						</span>
					</li>
				</ul>
			</div>
			<div id="memorandum_title">YOUNGJIN TRADE & TRANSPORTATION CO. LTD.</div>
			<ul>	
				<li id="memorandum_date"></li>
				<li id="memorandum_form_subject">
					SUBJECT :
					<c:choose> 
						<c:when test="${memorandum.subCode eq '01' }">
							Request For Authorization of additional Service for rowering of article
						</c:when>
						<c:when test="${memorandum.subCode eq '03' }">
							Request For Authorization of special crate
						</c:when>
						<c:otherwise>
							<input type="text" id="memorandum_subject" value="${checkMemorandum.subject }" />
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					INSTALLATION TRANSPORTATION OFFICER<br/>
					${responsibility }
				</li>
				<li>
					<p>NAME: ${gbl.customerName }</p>
					<p>RANK: ${gbl.rank }</p>
					<p>SSN: XXXX-XX-${fn:substring(gbl.ssn, 6,10)}</p><br/>
					<p>P/U DATE: ${gbl.pud }</p>
					<p>GBL NO: ${gbl.no }</p>
					<p>CARRIER: ${gbl.scac }</p>
					<p>CODE: ${gbl.code }</p>
				</li>
				<li>
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
						<c:when test="${ memorandum.subCode eq '03'}">
							NAME OF ARTICLE : (1) ${memorandum.codeName }							
						</c:when>
					</c:choose>
				</li>
				<li id="memorandum_form_article_comment">
					<c:choose>
						<c:when test="${memorandum.subCode eq '02' or memorandum.subCode eq '03' }">
							<input type="text" id="memorandum_article_comment1" value="${checkMemorandum.articleComment1 }" /><br/>
							<input type="text" id="memorandum_article_comment2" value="${checkMemorandum.articleComment2 }" /><br/>
							<input type="text" id="memorandum_article_comment3" value="${checkMemorandum.articleComment3 }" /><br/>
							<input type="text" id="memorandum_article_comment4" value="${checkMemorandum.articleComment4 }" /><br/>
							<input type="text" id="memorandum_article_comment5" value="${checkMemorandum.articleComment5 }" />
						</c:when>
						<c:when test="${memorandum.subCode eq '01' }">
							<input type="text" id="memorandum_article_comment1" value="${checkMemorandum.articleComment1 }" /><br/>
							<input type="text" id="memorandum_article_comment2" value="${checkMemorandum.articleComment2 }" /><br/>
							<input type="text" id="memorandum_article_comment3" value="${checkMemorandum.articleComment3 }" /><br/><br/>
							<input type="text" id="memorandum_article_comment4" value="${checkMemorandum.articleComment4 }" /><br/>
							<input type="text" id="memorandum_article_comment5" value="${checkMemorandum.articleComment5 }" />							
						</c:when>
					</c:choose>
				</li>
				<li id="memorandum_form_chief_of_office">
					${branch.branchManager }<br />
					${branch.position }<br />
					YOUNGJIN TRAED & TRANS CO.,LTD
				</li>
				<li id="memorandum_form_office_info">
					${branch.itoAddress } <br/>
					FOR YOUNGJIN T&T CO.,LTD
				</li>				
				<li id="memorandum_form_area_director">
					<span>TA FOR TO</span><br/>
					${branch.itoChief }
				</li>
			</ul>
		</div>
	</div>
</body>
</html>