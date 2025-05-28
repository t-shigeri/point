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

<body>
	<div class="container">
		<div class="title-box">成績管理</div>

		<!-- 検索フォーム -->
		<div class="form-section">
			<form action="${pageContext.request.contextPath}/test/regist"
				method="get">
				<label for="f1">入学年度:</label> <select name="f1" id="f1">
					<option value="">--選択--</option>
					<c:forEach var="year" items="${enrollmentYears}">
						<option value="${year}"
							<c:if test="${param.f1 == year.toString()}">selected</c:if>>${year}</option>
					</c:forEach>
				</select> <label for="f2">クラス:</label> <select name="f2" id="f2">
					<option value="">--選択--</option>
					<c:forEach var="cls" items="${classList}">
						<option value="${cls}"
							<c:if test="${param.f2 == cls}">selected</c:if>>${cls}</option>
					</c:forEach>
				</select> <label for="f3">科目:</label> <select name="f3" id="f3">
					<option value="">--選択--</option>
					<c:forEach var="subject" items="${subjectList}">
						<option value="${subject.cd}"
							<c:if test="${param.f3 == subject.cd}">selected</c:if>>${subject.name}</option>
					</c:forEach>
				</select> <label for="f4">回数:</label> <select name="f4" id="f4">
					<option value="">--選択--</option>
					<c:forEach var="num" items="${countList}">
						<option value="${num}"
							<c:if test="${param.f4 == num.toString()}">selected</c:if>>${num}</option>
					</c:forEach>
				</select> <span class="right-align">
					<button type="submit">検索</button>
				</span>
			</form>
		</div>
</div>

</body>
<%@ include file="../../footer.jsp"%>
</html>
