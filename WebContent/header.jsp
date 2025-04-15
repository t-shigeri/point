<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Servlet/JSP Samples</title>
    <style>
    h1{
    	background-color: #bce2e8;
    	padding: 10px;
    }
    </style>
</head>
<body class="bg-light">
    <h1>得点管理システム</h1>
<%
    String loggedInUser = (String) session.getAttribute("userName");
%>
<span>
<%= loggedInUser != null ? loggedInUser +"様" : "" %>
</span>
<%if (loggedInUser != null) { %>
<a href="git/scoremanager/main/logout.jsp">ログアウト</a>
<% } %>
</body>
</html>