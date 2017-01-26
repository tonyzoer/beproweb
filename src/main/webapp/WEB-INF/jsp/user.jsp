<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="includes/head.jsp"%>
<%@include file="includes/navbar.jsp"%>
<head>
    <title>Title</title>
</head>
<body>
<c:set scope="request" value="${requestScope.pageuser}" var="pageuser"/>
<c:set scope="request" value="${requestScope.profiletype}" var="profiletype"/>
<c:choose>
    <c:when test="${profiletype==}"></c:when>
</c:choose>


<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
