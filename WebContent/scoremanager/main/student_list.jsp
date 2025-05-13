
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<meta charset="UTF-8">
<title>学生一覧</title>

<style>
    .main {
        padding: 20px;
    }
    .title-box {
        background-color: #e6f2ff;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 1.5em;
        margin-bottom: 20px;
    }
    .form-section {
        background-color: #f9f9f9;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
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
<div class="main">
    <div class="title-box">学生一覧</div>

    <div class="form-section">
        <form action="list" method="get">
            <label for="f1">入学年度:</label>
            <select name="f1" id="f1">
                <option value="">--選択--</option>
                <c:forEach var="year" items="${enrollmentYears}">
                    <option value="${year}" <c:if test="${param.f1 == year}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>

            <label for="f2">クラス:</label>
            <select name="f2" id="f2">
                <option value="">--選択--</option>
                <c:forEach var="cls" items="${classList}">
                    <option value="${cls}" <c:if test="${param.f2 == cls}">selected</c:if>>${cls}</option>
                </c:forEach>
            </select>

            <label>
                <input type="checkbox" name="f3" value="t" <c:if test="${param.f3 == 't'}">checked</c:if> />
                在学中
            </label>

            <button type="submit">絞り込み</button>
            <span class="right-align">
                <a href="${pageContext.request.contextPath}/student_create">新規登録</a>
            </span>
        </form>
    </div>

    <p>検索結果: <c:out value="${fn:length(studentList)}" /> 件</p>

    <c:choose>
        <c:when test="${not empty studentList}">
            <table>
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>在学中</th>
                        <th>変更</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${studentList}">
                        <tr>
                            <td>${student.enrollmentYear}</td>
                            <td>${student.studentId}</td>
                            <td>${student.name}</td>
                            <td>${student.className}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${student.enrolled}">〇</c:when>
                                    <c:otherwise>×</c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="update?id=${student.studentId}">変更</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>該当する学生情報はありません。</p>
        </c:otherwise>
    </c:choose>
</div>
</body>

<%@ include file="../../footer.jsp" %>
</html>
