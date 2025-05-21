<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Servlet/JSP Samples</title>
    <style>
    h1{
    	background-color: #e6f2ff;
    	padding: 10px;
    }
    </style>
</head>
<body class="bg-light">
	<div id="title">
    <h1>得点管理システム</h1>
    </div>
    <%
        String username = (String) session.getAttribute("name");
    %>
    ${teacher.name}

<a href="Logout.action">ログアウト</a>

</body>
</html>