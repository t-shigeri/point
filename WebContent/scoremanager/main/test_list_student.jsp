<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp"   %>

<h2>学生 ${param.studentNo} の成績</h2>
<table border="1">
  <tr><th>科目</th><th>回</th><th>点数</th><th>クラス番号</th></tr>
  <c:forEach var="t" items="${testList}">
    <tr>
      <td>${t.subjectCd}</td>
      <td>${t.no}</td>
      <td>${t.point}</td>
      <td>${t.classNum}</td>
    </tr>
  </c:forEach>
  <c:if test="${empty testList}">
    <tr><td colspan="4">該当データがありません。</td></tr>
  </c:if>
</table>

<p><a href="<c:url value='/test_list'/>">検索画面に戻る</a></p>

<%@ include file="../../footer.jsp" %>
