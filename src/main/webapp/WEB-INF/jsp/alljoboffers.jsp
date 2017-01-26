<%--
  Created by IntelliJ IDEA.
  User: zoer
  Date: 22.01.17
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Job Offers</title>
</head>
<%@include file="includes/head.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<section>
    <c:set scope="request" value="${requestScope.alljoboffers}" var="alljoboffers"/>
    <div class="container">
        <c:forEach items="${alljoboffers}" var="joboffer">
            <div class="row">
            <div class="col-lg-12 col-md-12 bg-info center-block">
                <c:out value="${joboffer.getKey().getDescription()} in ${joboffer.getValue()}"/>
                <a class="btn btn-primary btn-lg pull-right"
                   href="/Controller?command=JOBOFFERINFO&item=${joboffer.getKey().getId()}"><fmt:message key="moreinfo" bundle="${bundle}"/></a>
            </div>
            </div>
            <hr/>
        </c:forEach>
    </div>
</section>

<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
