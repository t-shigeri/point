<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<style>
   .sidebar {
   			float: left;
            width: 200px;
            background-color: #f0f0f0;
            padding: 20px;
            height: 100vh;
        }
        .sidebar a {
            display: block;
            margin-bottom: 10px;
        }
</style>
<div class=sidebar>
<div class="navbar">
	<ul class=nav>
    	<li><a href=point/scoremanager/main/menu.jsp>商品</a></li>
    	<li><a href="point/scoremanager/main/student_list.jsp">学生管理</a></li>
    	<li>成績管理
    	<ul>
    	<li><a href="point/scoremanager/main/test_regist.jsp">成績登録</a></li>
    	<li><a href="/point/scoremanager/main/test_list.jsp">成績参照</a></li>
    	</ul>
    	</li>
    	<li><a href="/point/scoremanager/main/subject_list.jsp">科目管理</a></li>
    </ul>
</div>
</div>

<hr>