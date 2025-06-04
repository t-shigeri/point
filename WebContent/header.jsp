<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    /* ヘッダー全体 */
    .header {
      background-color: #e6f2ff;
      padding: 10px 20px;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    /* システム名のスタイル */
    .site-title {
      margin: 0;
      font-family: "Helvetica Neue", Arial, sans-serif;
      font-size: 1.6rem;
      color: #003366;
          }
    /* 右上に表示するユーザー情報 */
    .user-info {
      font-family: "Helvetica Neue", Arial, sans-serif;
      font-size: 0.9rem;
      color: #333;
    }
    .user-info a {
      color: #003366;
      text-decoration: none;
      margin-left: 12px;
    }
    .user-info a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <header class="header">
    <!-- システム名 -->
    <h1 class="site-title">得点管理システム</h1>

    <!-- ログイン中の先生名とログアウト -->
    <div class="user-info">
      <c:if test="${not empty sessionScope.teacher}">
        ${sessionScope.teacher.name} 様
        <a href="<c:url value='/scoremanager/main/Logout.action'/>">ログアウト</a>
      </c:if>
    </div>
  </header>
</body>
</html>
