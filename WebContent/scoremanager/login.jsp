<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<h2>ログイン</h2>
<form action="" method="post">
	<!-- ログインID -->
	<label for="ID">ID</label> <input type="text" id="id" name="id"
		inputmode="latin" placeholder="半角でご入力ください" value="${id}"
		maxlength="10" required>
	<!-- パスワード -->
	<br> <label for="password">パスワード</label> <input type="password"
		id="password" name="password" inputmode="latin"
		placeholder="30文字以内の半角英数字でご入力ください" maxlength="30" required>

	<!-- パスワード表示/非表示 -->
	<br> <input type="checkbox" id="chk_d_ps" name="chk_d_ps">
	<label for="chk_d_ps">パスワードを表示</label>

	<!-- ログインボタン -->
	<br>
	<button type="submit" id="login" name="login">ログイン</button>
</form>
<%@ include file="../footer.jsp"%>


