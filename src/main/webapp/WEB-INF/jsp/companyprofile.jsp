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
<head>
    <%@include file="includes/head.jsp" %>
    <%@include file="includes/navbar.jsp" %>
</head>
<body>
<section>
    <div class="container">
        <div class="col-lg-8">
            <img src="${sessionScope.user.getProfile().getCompanyProfile().get().getImgurl()}" alt="Cinque Terre"
                 class="img-responsive">
            <c:set scope="request" value="${requestScope.joboffers}" var="joboffers"/>
            <c:forEach items="${joboffers}" var="joboffer">
                <div class="row">
                    <div class="col-lg-6 bg-info center-block">
                        <c:out value="${joboffer.getDescription()}"/>
                        <div class="pull-right"><cf:commandbtn command="DELETEJOBOFFER" item="joboffer.getId()"
                                                               method="post">
                            <fmt:message key="delete" bundle="${bundle}"/>
                        </cf:commandbtn>
                        </div>
                        <div class="pull-right">
                            <cf:commandbtn command="JOBOFFERINFO" item="${joboffer.getId()}">
                                <fmt:message key="moreinfo" bundle="${bundle}"/>
                            </cf:commandbtn>
                        </div>

                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
        <div class="col-md-4">
            <cf:commandbtn command="CREATEJOBOFFERVIEW"><fmt:message
                    key="create" bundle="${bundle}"/> <fmt:message key="joboffer" bundle="${bundle}"/></cf:commandbtn>
        </div>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
