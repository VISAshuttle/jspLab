<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="jdbc.connection.*"%>
<%
	Connection conn = ConnectionProvider.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM TBL_BOARD");
%>
<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>** TBL_BOARD LIST **</title>
</head>

<body bgcolor="white" text="black" link="blue" vlink="purple"
	alink="red">
	<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7"
		bordercolordark="white" bordercolorlight="#B9E0FA">
		<tr>
			<td width="50" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>EMPNO</b></span>
				</p>
			</td>
			<td width="150" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>ENAME</b></span>
				</p>
			</td>
			<td width="150" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>JOB</b></span>
				</p>
			</td>
		</tr>
		<%
			if (rs != null) {
			while (rs.next()) {
				int EMPNO = rs.getInt("BNO");
				String ENAME = rs.getString("TITLE");
				String JOB = rs.getString("CONTENT");
		%>
		<tr>
			<td width="50" height="20">
				<p>
					<span style="font-size: 9pt;"> <font face="돋움체"
						color="black"> <%=EMPNO%></font></a></span>
				</p>
			</td>
			<td width="150" height="20">
				<p align="center">
					<span style="font-size: 9pt;"> <font face="돋움체"><%=ENAME%></font></span>
				</p>
			</td>
			<td width="150" height="20">
				<p align="center">
					<span style="font-size: 9pt;"> <font face="돋움체"><%=JOB%></font></span>
				</p>
			</td>
		</tr>
		<%
			}
		}
		rs.close();
		stmt.close();
		conn.close();
		%>
	</table>

</body>
</html>