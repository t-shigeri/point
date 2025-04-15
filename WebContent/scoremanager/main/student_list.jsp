<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<%@ include file="../../header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理一覧</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #aaa; padding: 8px; text-align: left; }
        .form-section { margin-bottom: 20px; }
    </style>
</head>
<body>
    <h1>学生管理一覧</h1>

    <div class="form-section">
        <form action="StudentListServlet" method="get">
            <label for="year">入学年度:</label>
            <select name="year" id="year">
                <option value="">--選択--</option>
                <!-- ここでデータベース -->
                <option value="2023">2023</option>
                <option value="2024">2024</option>
            </select>

            <label for="class">クラス:</label>
            <select name="class" id="class">
                <option value="">--選択--</option>
                <!--  ここでデータベース-->
                <option value="A">A</option>
                <option value="B">B</option>
            </select>

            <label>
                <input type="checkbox" name="enrolled" value="true" /> 在学中
            </label>

            <button type="submit">絞り込み</button>
        </form>

        <p><a href="studentRegister.jsp">新規登録</a></p>
    </div>

    <div>
        <p>検索結果: ${studentList.size()} 件</p>
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>クラス</th>
                </tr>
            </thead>
            <tbody>
               <!-- ここでデータベース -->
                    <tr>
                        <td>${student.enrollmentYear}</td>
                        <td>${student.studentId}</td>
                        <td>${student.name}</td>
                        <td>${student.className}</td>
                    </tr>
                <!-- ここでデータベース -->
            </tbody>
        </table>
    </div>
</body>
</html>
