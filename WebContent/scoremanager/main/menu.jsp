<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>

<style>
.menu-container {
    max-width: 500px;
    margin: 40px auto;
    padding: 32px 24px;
    background: #f9f9fb;
    border-radius: 18px;
    box-shadow: 0 4px 24px rgba(0,0,0,0.10);
    display: flex;
    flex-direction: column;
    gap: 32px;
}

.menu-title {
    font-size: 1.7em;
    font-weight: 700;
    letter-spacing: 2px;
    margin-bottom: 20px;
    color: #49596b;
    text-align: center;
}

.menu-list {
    display: flex;
    flex-direction: column;
    gap: 16px;
}

.menu-link {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 15px 20px;
    font-size: 1.2em;
    text-decoration: none;
    color: #263043;
    background: #fff;
    border-radius: 10px;
    transition: background 0.18s, transform 0.16s;
    box-shadow: 0 2px 8px rgba(0,0,0,0.05);
}
.menu-link:hover {
    background: #e1e9f3;
    transform: translateY(-2px) scale(1.03);
}

.menu-category {
    font-size: 1.1em;
    font-weight: 600;
    color: #607497;
    margin-bottom: 8px;
    margin-top: 16px;
    padding-left: 2px;
}
</style>

<div class="menu-container">
    <div class="menu-title">メニュー</div>
    <div class="menu-list">
        <a href="/point/student/list" class="menu-link">📒 学生管理</a>
        <div class="menu-category">成績管理</div>
        <a href="/point/test/regist" class="menu-link">📝 成績登録</a>
        <a href="/point/testList.action" class="menu-link">📊 成績参照</a>
        <a href="subject_list.action" class="menu-link">📚 科目管理</a>
    </div>
</div>

<%@ include file="../../footer.jsp" %>
