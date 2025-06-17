<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<%@include file="../../base.jsp" %>

<h2>科目情報の更新</h2>

<c:if test="${not empty subject}">
<form action="${pageContext.request.contextPath}/scoremanager/main/SubjectUpdateExecute.action" method="post">

    <table border="1">
      <tr>
        <th>教科コード</th>
        <td>
          <input type="text" name="cd" value="${subject.cd}" readonly>
        </td>
      </tr>
      <tr>
        <th>教科名</th>
        <td>
          <input type="text" name="name" value="${subject.name}">
        </td>
      </tr>
    </table>
    <input type="submit" value="更新する">
  </form>
</c:if>

<c:if test="${empty subject}">
  <p>指定された科目が見つかりませんでした。</p>
</c:if>

<%@include file="../../footer.jsp" %>
