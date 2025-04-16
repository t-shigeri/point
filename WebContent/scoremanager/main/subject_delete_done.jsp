<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../../header.jsp" %>
<%@include file="../../base.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>
<p>こんにちは、<%=request.getParameter("subject_name") %>さん！</p>
<
<%@include file="../../footer.jsp" %>
