<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>科目別成績参照</title></head>
<body>
  <h2>科目 ${param.subjectCd} の成績</h2>
  <table border="1">
    <tr><th>学生番号</th><th>テスト番号</th><th>点数</th><th>クラス番号</th></tr>
    <c:forEach var="t" items="${testList}">
      <tr>
        <td>${t.studentNo}</td>
        <td>${t.no}</td>
        <td>${t.point}</td>
        <td>${t.classNum}</td>
      </tr>
    </c:forEach>
  </table>
  <p><a href="test_list">検索条件に戻る</a></p>
</body></html>
