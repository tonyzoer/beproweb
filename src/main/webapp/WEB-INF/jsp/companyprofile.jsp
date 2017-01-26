<%--
  Created by IntelliJ IDEA.
  User: zoer
  Date: 18.01.17
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="includes/head.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<section>
    <div class="container">
        <div class="col-lg-8">
            <img src="${sessionScope.user.getProfile().getCompanyProfile().get().getImgurl()}" alt="Cinque Terre" class="img-responsive">
            <c:set scope="request" value="${requestScope.joboffers}" var="joboffers"/>
            <c:forEach items="${joboffers}" var="joboffer">
                <div class="row">
                    <div class="col-lg-6 col-md-12 bg-info center-block">
                        <c:out value="${joboffer.getDescription()}"/>
                        <a class="btn btn-primary btn-lg pull-right"
                           href="/Controller?command=DELETEJOBOFFER&item=${joboffer.getId()}"><fmt:message key="delete" bundle="${bundle}"/></a>
                        <a class="btn btn-primary btn-lg pull-right"
                           href="/Controller?command=JOBOFFERINFO&item=${joboffer.getId()}"><fmt:message key="moreinfo" bundle="${bundle}"/></a>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
        <div class="col-md-4">
    <a href="/Controller?command=CREATEJOBOFFERVIEW" class="btn btn-lg btn-primary pull-right"><fmt:message key="create" bundle="${bundle}"/> <fmt:message key="joboffer" bundle="${bundle}"/></a>
    </div>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
