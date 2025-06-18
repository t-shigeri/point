<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>


<%@ include file="../header.jsp"%>
<script>
    function togglePasswordVisibility() {
        const passwordInput = document.getElementById("password");
        const checkbox = document.getElementById("chk_d_ps");
        if (checkbox.checked) {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>

<body>
    <div class="login">
        <div class="title">
            <h2>ログイン</h2>
        </div>

            <!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>


        <form action="${pageContext.request.contextPath}/LoginExecute.action" method="post">
            <!-- ログインID -->
            <label for="id">ID</label>
            <input type="text" id="id" name="id" style="ime-mode: disabled;" placeholder="半角でご入力ください" value="${id}" maxlength="10" required>

            <!-- パスワード -->
            <br>
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" style="ime-mode: disabled;" placeholder="30文字以内の半角英数字でご入力ください" maxlength="30" required>

            <!-- パスワード表示/非表示 -->
            <br>
            <input type="checkbox" id="chk_d_ps" name="chk_d_ps" onclick="togglePasswordVisibility()">
            <label for="chk_d_ps">パスワードを表示</label>

            <!-- ログインボタン -->
            <br>
            <button type="submit" id="login" name="login">ログイン</button>
        </form>
    </div>
</body>

<%@ include file="../footer.jsp"%>
