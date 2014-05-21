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
<head>
<title>CONTENT</title>
<script>
var contextPath = '<c:out value="${cp}"/>';
var realPath = '<c:out value="${rp}"/>';
function test(){
	var value = $("#input").val();
	
	var json = {
    		'value' : value
    	};
    	var url = contextPath + '/outbound/test.json';
    	alert(url + " "+value);
    	$.postJSON(url, json, function(){
    		return jQuery.ajax({
    			success : function(){
    				alert("sdfwef");
    			},
    			error : function(){
    				alert("??");
    			}
    		});
    	});
}
</script>

</head>
<body>
	<div>
		결과 여기나온다 : ${test }????
	</div>
	<div>
		<input id="input" type="text" name="input" col="50" value="test1"><input type="button" id="btn" name="btn" value="click23" onclick="test()"/>
	</div>
</body>
</html>