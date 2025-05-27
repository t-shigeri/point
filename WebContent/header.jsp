<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet/JSP Samples</title>
<style>
h1 {
	background-color: #e6f2ff;
	padding: 10px;
}
</style>
</head>
<body class="bg-light">
	<div id="title">
		<h1>得点管理システム</h1>
	</div>
	<!-- セッションから取得したユーザー名をEL式で表示 -->

	<c:if test="${not empty sessionScope.teacher}">
		<p>${sessionScope.teacher.name}様</p>
		<a href="point/scoremanager/main/Logout.action">ログアウト</a>
	</c:if>




</body>
</html>
