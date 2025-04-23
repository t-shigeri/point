<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
    <meta charset="UTF-8">
    <title>登録完了</title>
    <style>
        .main {
            padding: 20px;
        }
        .title-box {
            background-color: #e6f2ff;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1.5em;
            margin-bottom: 20px;
        }
        .message-box {
            background-color: #f9f9f9;
           px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }
        .message-box p {
            font-size: 1.2em;
        }
        .button-box {
            text-align: center;
        }
        .button-box a {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .button-box a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title-box">登録完了</div>

    <div class="message-box">
        <p>学生情報の登録が完了しました。</p>
    </div>

    <div class="button-box">
        <a href="student_list.jsp">学生一覧に戻る</a>
    </div>
</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>
