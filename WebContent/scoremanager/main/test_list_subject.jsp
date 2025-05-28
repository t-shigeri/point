<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp"   %>

<h2>科目 ${param.subjectCd} の成績一覧</h2>
<table border="1">
  <tr><th>学生番号</th><th>回</th><th>点数</th><th>クラス番号</th></tr>
  <c:forEach var="t" items="${testList}">
    <tr>
      <td>${t.studentNo}</td>
      <td>${t.no}</td>
      <td>${t.point}</td>
      <td>${t.classNum}</td>
    </tr>
  </c:forEach>
  <c:if test="${empty testList}">
    <tr><td colspan="4">該当データがありません。</td></tr>
  </c:if>
</table>

<p>
  <a href="<c:url value='/testList.action'/>">検索画面に戻る</a>
</p>


<%@ include file="../../footer.jsp" %>
