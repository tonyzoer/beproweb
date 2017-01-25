<%--
  Created by IntelliJ IDEA.
  User: zoerTitle
  Date: 22.01.17
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <title></title>
</head>
<%@include file="includes/head.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<c:set value="${requestScope.jobOffer}" scope="request" var="jobOffer"/>
<c:set value="${requestScope.jobOfferSpecification}" scope="request" var="jobOfferSpecification"/>
<c:set value="${requestScope.jobOfferText}" scope="request" var="jobOfferText"/>
<c:set value="${sessionScope.student}" scope="session" var="student"/>
<section>
    <div class="container">
        <div class="col-md-12">
            <div class="col-md-6"><h1>${jobOffer.getDescription()}</h1>
            <p>${jobOfferText.getText()}</p>
            </div>
            <div class="col-md-6"><h2><fmt:message key="spectoknow" bundle="${bundle}"/></h2>
        <ol>
    <c:forEach items="${jobOfferSpecification}" var="speci">
        <li>${speci.getValue()}</li>
    </c:forEach>
    </ol>
    </div>
            <c:if test="${student}">
    <div class="col-md-6"><h2><fmt:message key="apply" bundle="${bundle}"/></h2>
        <a href="/Controller?command=ADDJOBOFFERTOSTUDENT&jobofferid=${jobOffer.getId()}" class="btn btn-primary btn-lg"><fmt:message key="sendaply" bundle="${bundle}"/></a>
    </div>
            </c:if>
        </div>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
