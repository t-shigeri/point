<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<%@include file="../../base.jsp" %>
<style>
</style>
<h2>科目情報登録</h2>
<form action="/point/scoremanager/main/subject_create_done.action" method="post">
    <label>科目コード</label><br>
    <input type="text" name="cd"><br>
    <label>科目名</label><br>
    <input type="text" name="name"><br>
    <input type="submit" value="登録">
</form>
<%@include file="../../footer.jsp" %>