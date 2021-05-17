<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set value="2014-02-26 18:27:24" var="dateString" />
	<fmt:parseDate value="${dateString}" var="dateObject"
		pattern="yyyy-MM-dd HH:mm:ss" />

	<p>Date is :
	<b><fmt:formatDate value="${dateObject }" pattern="yyyy-MM-dd" /></b></p>
	
	<p>Time is :
	<b><fmt:formatDate value="${dateObject }" pattern="a hh:mm" /></b></p>

</body>
</html>