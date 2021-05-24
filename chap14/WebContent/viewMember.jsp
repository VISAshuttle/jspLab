<%@ page contentType = "text/html; charset=utf-8" %>

<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

<%
	String memberID = request.getParameter("memberID");
%>
<html>
<head><title>회원 정보</title></head>
<body>

<%
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = null;
	Statement state = null;
	ResultSet result = null;
	
	try {
		String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?" +
							"useUnicode=true&characterEncoding=utf8";
		String dbUser = "jspexam";
		String dbPass = "jsppw";
		String query = "select * from MEMBER where MEMBERID = '" + memberID + "'";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		state = conn.createStatement();
		
		result = state.executeQuery(query);
		if( result.next() ) {
%>
<table border="1">
<tr>
	<td>아이디</td><td><%= memberID %></td>
</tr>
<tr>
	<td>암호</td><td><%= result.getString("PASSWORD") %></td>
</tr>
<tr>
	<td>이름</td><td><%= result.getString("NAME") %></td>
</tr>
<tr>
	<td>이메일</td><td><%= result.getString("EMAIL") %></td>
</tr>
</table>
<%
		} else {
%>
<%= memberID %>에 해당하는 정보가 존재하지 않습니다.
<%
		}
	} catch(SQLException ex) {
%>
에러 발생: <%= ex.getMessage() %>
<%
	} finally {
		if (result != null) try { result.close(); } catch(SQLException ex) {}
		if (state != null) try { state.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>

</body>
</html>
