<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="includes/head.jsp"%>
<%@include file="includes/navbar.jsp"%>
<head>
    <title>Title</title>
</head>
<body>
<c:set scope="request" value="${requestScope.courses}" var="courses"/>
<c:set scope="request" value="${requestScope.spec}" var="spec"/>
<div class="container">
    <div class="col-lg-12 center-block">
        <h2>${spec.getValue()}</h2>
        <div class="col-lg-8">
            <ol>
                <c:forEach items="${courses}" var="course" >
                    <li><a href="${course.getUrl()}">${course.getSpecName()}</a></li>
                    <hr/>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>



<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
