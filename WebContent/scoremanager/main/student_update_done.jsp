<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報更新完了</title>
</head>
<body>
<div class="container">
    <div class="title-box">更新完了</div>
    <div class="form-section">
        <p>以下の学生情報を更新しました。</p>
        <table>
            <tr>
                <th>学籍番号</th>
                <td>${student.studentId}</td>
            </tr>
            <tr>
                <th>氏名</th>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>入学年度</th>
                <td>${student.enrollmentYear}</td>
            </tr>
            <tr>
                <th>クラス</th>
                <td>${student.classId}</td>
            </tr>
            <tr>
                <th>在籍</th>
                <td>
                    <c:choose>
                        <c:when test="${student.enrolled}">在籍</c:when>
                        <c:otherwise>退学</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/student/list">一覧に戻る</a>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>
