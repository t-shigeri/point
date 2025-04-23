<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
    <meta charset="UTF-8">
    <title>学生登録</title>
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
        .form-section {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"], select {
            width: 100%;
            padding: 10px;
            border: 1px: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title-box">学生登録</div>

    <div class="form-section">
        <form action="student_create_done.jsp" method="post">
            <label for="enrollmentYear">入学年度:</label>
            <input type="text" id="enrollmentYear" name="enrollmentYear" required>

            <label for="studentId">学生番号:</label>
            <input type="text" id="studentId" name="studentId" required>

            <label for="name">氏名:</label>
            <input type="text" id="name" name="name" required>

            <label for="className">クラス:</label>
            <input type="text" id="className" name="className" required>

            <label for="enrolled">在学中:</label>
            <select id="enrolled" name="enrolled">
                <option value="true">はい</option>
                <option value="false">いいえ</option>
            </select>

            <button type="submit">登録</button>
        </form>
    </div>
</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>
