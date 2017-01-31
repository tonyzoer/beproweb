<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <%@include file="includes/navbar.jsp" %>
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
                    <c:set scope="request" value="${requestScope.studentsCourses}" var="studcourses"></c:set>
                    <c:forEach items="${courses}" var="course">
                        <li><a href="${course.getUrl()}">${course.getSpecName()}</a></li>
                        <c:if test="${requestScope.student==true}">
                            <c:choose>
                            <c:when test="${studcourses.get(course)!=null}">
                                <a href="${studcourses.get(course)}" class="btn btn-info pull-right"><fmt:message
                                        bundle="${bundle}" key="sertificate"/></a>
                                <cf:commandbtn command="REMOVECOURSEFROMSTUDENT" item="${course.getId()}" method="post">
                                    <fmt:message bundle="${bundle}" key="delete"/></cf:commandbtn>
                            </c:when>
                                <c:otherwise>
                                    <cf:commandform command="ADDCOURSETOSTODENT" item="${spec.getId()}" method="post">
                                        <INPUT hidden name="course" value="${course.getId()}"/>
                                        <input class="form-group" type="text" name="url" value=""/>
                                        <button class="btn-info" type="submit"><fmt:message bundle="${bundle}"
                                                                                            key="add"/></button>
                                    </cf:commandform>
                                </c:otherwise>
                            </c:choose>
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
