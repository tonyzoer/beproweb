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
<section>
<div class="container">
    <div class="col-lg-12 center-block">
        <h2>${spec.getValue()}</h2>
        <div class="col-lg-8">
            <ol>

                <c:forEach items="${courses}" var="course" >
                    <li><a href="${course.getUrl()}">${course.getSpecName()}</a></li>
                    <c:if test="${requestScope.student==true}">
                        <c:set scope="request" value="${requestScope.studentsCourses}" var="studcourses"></c:set>
                    <c:if test="${studcourses.get(course)!=null}">
                        <a href="${studcourses.get(course)}" class="btn btn-primary pull-right"><fmt:message bundle="${bundle}" key="sertificate" /></a>
                    </c:if>
                    <c:if test="${requestScope.student==true}">
                        <form action="/Controller" method="post">
                            <INPUT hidden name="command" value="ADDCOURSETOSTODENT"/>
                            <INPUT hidden name="course" value="${course.getId()}"/>
                            <INPUT hidden name="item" value="${spec.getId()}"/>
                            <input type="text" name="url" value=""/>
                            <button type="submit"><fmt:message bundle="${bundle}" key="add"/></button>
                        </form>
                    </c:if>
                    </c:if>
                    <hr/>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>
</section>


<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
