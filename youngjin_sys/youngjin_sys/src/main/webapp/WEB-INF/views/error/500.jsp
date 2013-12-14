<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Youngjin</title>
<%-- Icon --%>
<link rel="shortcut icon" href="${cp }/resources/images/favicon.ico" type="image/x-icon"/>
<style>
	body{background: #fff;}
	.error{width: 990px; margin: 30px auto; display: block;}
</style>
</head>
<body>
<a href="javascript:history.back();" class="error"><img alt="500 Internal server error" src="${cp }/resources/images/error/500.jpg" ></a>
</body>
</html>