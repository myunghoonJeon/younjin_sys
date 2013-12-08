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
	<div class="dd619_wrap">
		<table>
			<thead>
				<tr>
					<th>CONTAINS INFORMATION SUBJECT TO THE PRIVACY ACT OF 1974, AS AMENDED.</th>
				</tr>
			</thead>
			<tbody>
				<tr id="dd619_table_1_tr">
					<td id="dd619_table_1_1_td">
						<p id="dd619_table_1_1_p">STATEMENT OF ACCESSORIAL SERVICES PERFORMED</p>
						<p>This form is required only when accessorial services are chargeable to the Government. Carrier will enter complete information or "None" in columns. "Unit Price" and "Charge" columns may be omitted when charges are itemized on the Standard Form 1113.</p>
					</td>
					<td id="dd619_table_1_2_td">
						OBM NO. 0702-0022<br/>
						OMB approval expires<br/>
						May 31, 2011
					</td>
				</tr>
				<tr>
					<td id="dd619_table_2_1_td" colspan="2">
						The public reporting burden for this collection of information is estimated to average 5 minutes per response, including the time for reviewing instructions, searching existing data sources, gathering and maintaining the data needed, and completing and reviewing the collection of information. Send comments regarding this burden estimate or any other aspect of this collection of information, including suggestions for reducing the burden, to the Department of Defense, Washington Headquarters Services, Executive Services Directorate, Information Management Division, 1155 Defense Pentagon, Washington, DC 20301-1155 (0702-0022). Respondents should be aware that notwithstanding any other provision of law, no person shall be subject to any penalty for failing to comply with a collection of information if it does not display a currently valid OMB control number.
					</td>
				</tr>
			</tbody>
			<tfoot>
			
			</tfoot>
		</table>
	</div>
</body>
</html>