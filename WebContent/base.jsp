<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<style>
  /* ----------- レイアウト共通 ----------- */
  body {
    margin: 0;
    padding: 0;
    font-family: "Helvetica Neue", Arial, sans-serif;
  }
  /* ヘッダー部分（すでにあるもの） */
  .header {
    background-color: #e6f2ff;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;

  }

  .user-info {
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

  /* ----------- サイドバー部分 ----------- */
  .sidebar {
    float: left;
    width: 200px;
    height: calc(100vh - 52px);
       background: linear-gradient(
      to bottom,
      #e6f2ff 0%,
      #cce0ff 30%,
      #99c2ff 60%,
      #003366 100%
    );
    box-shadow: 1px 0 4px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    padding-top: 12px;
  }
  .navbar {
    flex: 1;
    overflow-y: auto;
    padding-left: 0;
    margin: 0;
  }

  .nav {
    list-style: none;
    margin: 0;
    padding: 0;
  }


  .nav > li > span {
    display: block;
    color: #003366;
    font-weight: 600;
    font-size: 0.9rem;
    margin: 16px 0 8px 12px;
  }

  /* メインメニュー */
  .nav > li > a {
    display: block;
    color: #003366;
    text-decoration: none;
    font-size: 0.95rem;
    padding: 10px 16px;
    border-radius: 4px;
    transition: background 0.25s, padding-left 0.25s;
    margin: 4px 8px;
  }
  .nav > li > a:hover {
    background: rgba(230, 242, 255, 0.6);
    padding-left: 24px;
  }

  .nav > li > ul {
    list-style: none;
    margin: 0;
    padding: 0 0 0 16px;
  }
  .nav > li > ul > li > a {
    display: block;
    color: #004080;
    text-decoration: none;
    font-size: 0.85rem;
    padding: 8px 16px;
    border-radius: 4px;
    transition: background 0.25s, padding-left 0.25s;
    margin: 2px 8px;
  }
  .nav > li > ul > li > a:hover {
    background: rgba(204, 224, 255, 0.7);
    padding-left: 28px;
  }
  .navbar::-webkit-scrollbar {
    width: 6px;
  }
  .navbar::-webkit-scrollbar-thumb {
    background-color: rgba(0, 51, 102, 0.3);
    border-radius: 3px;
  }
  .navbar::-webkit-scrollbar-track {
    background: rgba(230, 242, 255, 0.2);
  }

  /* ----------- メインコンテンツ（参考） ----------- */
  .main-content {
    margin-left: 200px;
    padding: 20px;
    background-color: #f9fcff;
    min-height: calc(100vh - 52px);
  }
</style>

<body>
  <!-- ヘッダー -->
  <header class="header">
    <div class="user-info">
      <c:if test="${not empty sessionScope.teacher}">
        ${sessionScope.teacher.name} 様
        <a href="<c:url value='/scoremanager/main/Logout.action'/>">ログアウト</a>
      </c:if>
    </div>
  </header>

  <!-- サイドバー -->
  <div class="sidebar">
    <div class="navbar">
      <ul class="nav">
        <li>
          <a href="/point/scoremanager/main/menu.jsp">🏠 メニュー</a>
        </li>
        <li>
          <a href="/point/student/list">👥 学生管理</a>
        </li>
        <li>
          <span>📊 成績管理</span>
          <ul>
            <li>
              <a href="/point/test/regist">📝 成績登録</a>
            </li>
            <li>
              <a href="/point/testList.action">📈 成績参照</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="/point/scoremanager/main/subject_list.action">📚 科目管理</a>
        </li>
      </ul>
    </div>
  </div>
</body>
