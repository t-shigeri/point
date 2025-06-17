<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<%@include file="../../base.jsp" %>

<h2>科目情報更新完了</h2>

<c:choose>
  <c:when test="${success}">
    <p>科目情報の更新が完了しました。</p>
  </c:when>
  <c:otherwise>
    <p>更新に失敗しました。</p>
  </c:otherwise>
</c:choose>

<p><a href="subject_list.action">科目一覧へ戻る</a></p>

<%@include file="../../footer.jsp" %>
