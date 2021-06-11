<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>파일 업로드 확인 및 다운로드</title>
</head>
<body>
	<p>
		<a href="fileUp.do">파일 올리기</a>
	</p>

	<p>글 번호 : ${tblAttachList.bno }</p>

	<c:forEach var="file" items="${tblAttachList.files}">

		<p>
			파일명 : <a href="fileDown.do?file_name=${file.fullName}">${file.fullName}</a>
		</p>

		<c:set var="fname" value="${file.fullName}" />
		<c:choose>
			<c:when test="${fn:endsWith(fname, '.jpg')==true}">
				<img src="/appB/upload/${file.fullName}" width="500" />
			</c:when>
			<c:when test="${fn:endsWith(fname, '.png')==true}">
				<img src="/appB/upload/${file.fullName}" width="500" />
			</c:when>
			<c:when test="${fn:endsWith(fname, '.gif')==true}">
				<img src="/appB/upload/${file.fullName}" width="500" />
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>

	</c:forEach>

</body>
</html>
