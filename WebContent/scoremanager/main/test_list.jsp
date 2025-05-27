<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <%@ include file="../../header.jsp"%>
<%@ include file="../../base.jsp"%>
  <title>成績参照</title>
</head>
<body>
  <h2>成績参照</h2>

  <!-- ■ 科目情報による検索 -->
  <form action="test_list_subject_execute" method="get">
    <fieldset>
      <legend>科目情報で検索</legend>
      <label>入学年度:
        <select name="f1">
          <option value="">--選択なし--</option>
          <c:forEach var="year" items="${enrollmentYears}">
            <option value="${year}">${year}</option>
          </c:forEach>
        </select>
      </label>
      <label>クラス:
        <select name="f2">
          <option value="">--選択なし--</option>
          <c:forEach var="cls" items="${classList}">
            <option value="${cls}">${cls}</option>
          </c:forEach>
        </select>
      </label>
      <label>科目:
        <select name="f3">
          <option value="">--選択なし--</option>
          <c:forEach var="subj" items="${subjectList}">
            <option value="${subj.cd}">${subj.name}</option>
          </c:forEach>
        </select>
      </label>
      <button type="submit">検索</button>
    </fieldset>
  </form>

  <!-- ■ 学生情報による検索 -->
  <form action="test_list_student_execute" method="get">
    <fieldset>
      <legend>学生番号で検索</legend>
      <label>学生番号:
        <input type="text" name="f4" required placeholder="例：00001" />
      </label>
      <button type="submit">検索</button>
    </fieldset>
  </form>

  <p><a href="menu">メニューへ戻る</a></p>
  <%@ include file="../../footer.jsp"%>
</body>
</html>
