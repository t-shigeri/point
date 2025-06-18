<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>科目一覧</title>
</head>
<body>
    <h1>科目一覧</h1>

    <a href="/point/scoremanager/main/subject_create.action">科目新規作成</a> <br><br>
<%
    Object t = session.getAttribute("teacher");
    if (t != null) {
%>
        <p>user <%= t %></p>
<%
    } else {
%>
        <p>してない</p>
<%
    }
%>
    <table border="1">
        <tr>
            <th>学校コード</th>
            <th>科目コード</th>
            <th>科目名</th>
            <th>操作</th>
        </tr>
        <c:forEach var="subject" items="${subjectList}">
            <tr>
                <td>${subject.school.cd}</td>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td>
                  <a href="/point/scoremanager/main/subject_update.action?cd=${subject.cd}&schoolCd=${subject.school.cd}">編集</a>
                    <a href="/point/scoremanager/main/subject_delete.action?cd=${subject.cd}&schoolCd=${subject.school.cd}">削除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
<%@ include file="../../footer.jsp" %>
