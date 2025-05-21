<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Servlet/JSP Samples</title>
    <style>
    h1 {
        background-color: #e6f2ff;
        padding: 10px;
    }
    </style>
</head>
<body class="bg-light">
    <div id="title">
        <h1>得点管理システム</h1>
    </div>

    <!-- セッションから取得したユーザー名をEL式で表示 -->
    <p>ログインユーザー: ${teacher.name}</p>

    <a href="point/scoremanager/main/Logout.action">ログアウト</a>

</body>
</html>
