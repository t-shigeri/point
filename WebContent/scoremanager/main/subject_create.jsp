<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../header.jsp" %>
<%@ include file="../../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>科目情報登録</h2>

<form action="${pageContext.request.contextPath}/scoremanager/main/subject_create_done.action" method="post">
    <label>科目コード</label><br>
    <input type="text" name="cd" value="<c:out value='${subject.cd}'/>"><br>
    <c:if test="${not empty cdError}">
        <div style="color:red">${cdError}</div>
    </c:if>

    <br>
    <label>科目名</label><br>
    <input type="text" name="name" value="<c:out value='${subject.name}'/>"><br>
    <c:if test="${not empty nameError}">
        <div style="color:red">${nameError}</div>
    </c:if>

    <br>
    <input type="submit" value="登録">
</form>

<p><a href="${pageContext.request.contextPath}/scoremanager/main/subject_list.action">戻る</a></p>

<%@ include file="../../footer.jsp" %>
