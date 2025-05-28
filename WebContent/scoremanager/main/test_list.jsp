<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp"   %>

<h2>成績参照</h2>

<!-- ■ 科目情報による検索 -->
<form action="<c:url value='/testListSubjectExecute.action'/>" method="get">
  <fieldset>
    <legend>科目で検索</legend>
    <label>年度:
      <select name="enrollmentYear">
        <option value="">--選択--</option>
        <c:forEach var="y" items="${enrollmentYears}">
          <option value="${y}">${y}</option>
        </c:forEach>
      </select>
    </label>
    <label>クラス番号:
      <select name="classNum">
        <option value="">--選択--</option>
        <c:forEach var="c" items="${classList}">
          <option value="${c}">${c}</option>
        </c:forEach>
      </select>
    </label>
    <label>科目:
      <select name="subjectCd">
        <option value="">--選択--</option>
        <c:forEach var="s" items="${subjectList}">
          <option value="${s.cd}">${s.name}</option>
        </c:forEach>
      </select>
    </label>
    <button type="submit">検索</button>
  </fieldset>
</form>

<!-- ■ 学生番号による検索 -->
<form action="<c:url value='/testListStudentExecute.action'/>" method="get">
  <fieldset>
    <legend>学生番号で検索</legend>
    <label>学生番号:
      <input type="text" name="studentNo" required placeholder="例：2231111"/>
    </label>
    <button type="submit">検索</button>
  </fieldset>
</form>

<!-- メニューへ戻る -->
<p>
  <a href="<c:url value='/scoremanager/main/menu.jsp'/>">
    メニューへもどる
  </a>
</p>

<%@ include file="../../footer.jsp" %>
