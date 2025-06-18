<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>

<h2>科目情報を更新しました</h2>

<table border="1">
  <tr>
    <th>科目コード</th>
    <td>${subject.cd}</td>
  </tr>
  <tr>
    <th>科目名</th>
    <td>${subject.name}</td>
  </tr>
</table>

<p><a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.action">一覧に戻る</a></p>

<%@ include file="../../footer.jsp" %>
