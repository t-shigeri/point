<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>科目削除確認</title>
</head>
<body>
    <h1>科目削除確認</h1>
    <p>科目「${subject.name}」を削除しますか？</p> <%-- Assuming you pass the subject object --%>
    <form action="/point/scoremanager/main/subject_delete_execute.action" method="post">

        <input type="hidden" name="cd" value="${subject.cd}">
        <input type="hidden" name="schoolCd" value="${subject.school.cd}">
        <input type="submit" value="削除">
        <a href="/point/scoremanager/main/subject_list.action">キャンセル</a>
    </form>
</body>
</html>
<%@ include file="../../footer.jsp" %>
