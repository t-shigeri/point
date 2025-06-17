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
				<label for="f1">入学年度:</label>
				<select name="f1" id="f1" required>
					<option value="">--選択--</option>
					<c:forEach var="year" items="${enrollmentYears}">
						<option value="${year}"
							<c:if test="${param.f1 == year.toString()}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>

				<label for="f2">クラス:</label>
				<select name="f2" id="f2" required>
					<option value="">--選択--</option>
					<c:forEach var="cls" items="${classList}">
						<option value="${cls}"
							<c:if test="${param.f2 == cls}">selected</c:if>>${cls}</option>
					</c:forEach>
				</select>

				<label for="f3">科目:</label>
				<select name="f3" id="f3" required>
					<option value="">--選択--</option>
					<c:forEach var="subject" items="${subjectList}">
						<option value="${subject.cd}"
							<c:if test="${param.f3 == subject.cd}">selected</c:if>>${subject.name}</option>
					</c:forEach>
				</select>

				<label for="f4">回数:</label>
				<select name="f4" id="f4" required>
					<option value="">--選択--</option>
					<option value="1" <c:if test="${param.f4 == '1'}">selected</c:if>>1</option>
					<option value="2" <c:if test="${param.f4 == '2'}">selected</c:if>>2</option>
					<option value="3" <c:if test="${param.f4 == '3'}">selected</c:if>>3</option>
					<option value="4" <c:if test="${param.f4 == '4'}">selected</c:if>>4</option>
					<option value="5" <c:if test="${param.f4 == '5'}">selected</c:if>>5</option>
				</select>

				<span class="right-align">
					<button type="submit">検索</button>
				</span>
			</form>
		</div>

		<!-- 検索結果がある場合のみ成績登録フォームを表示 -->
		<c:if test="${not empty studentList}">
			<div class="form-section">
				<form action="${pageContext.request.contextPath}/test/registExecute" method="post">
					<table>
						<thead>
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="student" items="${studentList}" varStatus="status">
								<tr>
									<td>${student.enrollmentYear}</td>
									<td>${student.classId}</td>
									<td>
										${student.studentId}
										<input type="hidden" name="studentList[${status.index}].studentNo" value="${student.studentId}" />
									</td>
									<td>${student.name}</td>
									<td>
										<input type="number" name="studentList[${status.index}].point"
											min="0" max="100" required
											value="${student.point != null ? student.point : ''}" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- 検索条件をhiddenで送信 -->
					<input type="hidden" name="f1" value="${param.f1}" />
					<input type="hidden" name="f2" value="${param.f2}" />
					<input type="hidden" name="f3" value="${param.f3}" />
					<input type="hidden" name="f4" value="${param.f4}" />

					<div class="right-align" style="margin-top:10px;">
						<button type="submit">登録</button>
					</div>
				</form>
			</div>
		</c:if>

	</div>
</body>
<%@ include file="../../footer.jsp"%>
</html>
