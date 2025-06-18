<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../header.jsp"%>
<%@ include file="../../base.jsp"%>
<meta charset="UTF-8">
<title>成績登録</title>

<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
}

.container {
	display: flex;
	flex-direction: column;
	height: 100vh;
	padding: 20px;
}

.main {
	padding: 20px;
	margin-bottom: 20px;
}

.title-box {
	background-color: #e6f2ff;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 1.5em;
	margin-bottom: 10px;
}

.form-section {
	background-color: #f9f9f9;
	padding: 10px 15px;
	border-radius: 5px;
	margin-bottom: 10px;
}

table {
	border-collapse: collapse;
	width: 100%;
	background-color: #fff;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px 10px;
	text-align: center;
}

th {
	background-color: #f0f0f0;
}

.right-align {
	float: right;
}
</style>
</head>

	<div class="container">
		<div class="title-box">成績管理</div>
		<div class="regist_done">登録が完了しました</div>
		<div>
			<a href="${pageContext.request.contextPath}/test/regist" class="button-link">戻る</a>
			<a href="/point/testList.action" class="menu-link">成績参照</a>
		</div>
	</div>
<%@ include file="../../footer.jsp"%>
</html>
