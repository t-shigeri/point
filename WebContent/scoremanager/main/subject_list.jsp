<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>

<h2>科目情報登録</h2>

<table border="1">
  <tr>
    <th>教科コード</th>
    <th>教科名</th>
    <th>学校コード</th>
  </tr>

  <c:forEach var="subject" items="${subjectList}">
    <tr>
      <td>${subject.cd}</td>
      <td>${subject.name}</td>
      <td>${subject.school.cd}</td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../../footer.jsp" %>
