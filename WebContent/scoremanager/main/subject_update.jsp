<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>科目情報変更</h2>

<c:if test="${not empty error}">
  <p style="color: red">${error}</p>
</c:if>

<c:if test="${not empty subject}">
<form action="${pageContext.request.contextPath}/scoremanager/main/SubjectUpdateExecute.action" method="post">
  <table border="1">
    <tr>
      <th>科目コード</th>
      <td>
        <input type="text" name="cd" value="${subject.cd}" readonly>
      </td>
    </tr>
    <tr>
      <th>科目名</th>
      <td>
        <input type="text" name="name" value="${subject.name}">
      </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="変更">
</form>

<p><a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.action">戻る</a></p>
</c:if>

<c:if test="${empty subject}">
  <p>指定された科目が見つかりませんでした。</p>
</c:if>

<%@ include file="../../footer.jsp" %>
