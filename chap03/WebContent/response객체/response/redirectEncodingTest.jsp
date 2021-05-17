<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "java.net.URLEncoder" %>
<%
    String value = "ÀÚ¹Ù";
    String encodedValue = URLEncoder.encode(value);
    response.sendRedirect("first.jsp?name=" + encodedValue);
%>