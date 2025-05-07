<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<body>
    <div class="login">
        <div class="title">
            <h2>ログイン</h2>
        </div>
        <form action="LoginExecuteAction" method="post">
            <!-- ログインID -->
            <label for="ID">ID</label>
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
