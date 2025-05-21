<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報更新</title>
    <style>
        .form-section { padding: 20px; background-color: #f9f9f9; border-radius: 5px; max-width: 600px; margin: auto; }
        .form-section label { display: block; margin-top: 10px; }
        .form-section input, .form-section select { width: 100%; padding: 8px; margin-top: 5px; }
        .form-actions { margin-top: 20px; text-align: center; }
    </style>
</head>
<body>
<div class="main">
    <div class="title-box">学生情報更新</div>
    <div class="form-section">
        <form action="${pageContext.request.contextPath}/student/updateExecute" method="post">
            <input type="hidden" name="no" value="${student.studentId}" />

            <label for="name">氏名</label>
            <input type="text" id="name" name="name" value="${student.name}" required />

            <label for="enrollmentYear">入学年度</label>
            <select id="enrollmentYear" name="enrollmentYear">
                <c:forEach var="year" items="${enrollmentYears}">
                    <option value="${year}" ${year == student.enrollmentYear ? 'selected' : ''}>${year}</option>
                </c:forEach>
            </select>

            <label for="classId">クラス</label>
            <select id="classId" name="classId">
                <c:forEach var="cls" items="${classList}">
                    <option value="${cls}" ${cls == student.classId ? 'selected' : ''}>${cls}</option>
                </c:forEach>
            </select>

            <label>
                <input type="checkbox" name="enrolled" value="true" ${student.enrolled ? 'checked' : ''} /> 在籍
            </label>

            <div class="form-actions">
                <button type="submit">更新</button>
                <a href="${pageContext.request.contextPath}/student/list">戻る</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
