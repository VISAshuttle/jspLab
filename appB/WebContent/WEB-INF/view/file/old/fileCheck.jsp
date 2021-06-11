<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>파일 업로드 확인 및 다운로드</title>
</head>
<body>
올린 사람 : ${name }<br>
제목 : ${subject }<br>
<!-- 파일 명 링크 거는 부분 수정함. -->
파일명1 : <a href="fileDown.do?file_name=${tblAttachList[0].fullName}">${tblAttachList[0].fullName}</a><br>
파일명2 : <a href="fileDown.do?file_name=${tblAttachList[1].fullName}">${tblAttachList[1].fullName}</a><br>

<img src="/appB/upload/${tblAttachList[0].fullName}" /><br>
<img src="/appB/upload/${tblAttachList[1].fullName}" /><br>


</body>
</html>
