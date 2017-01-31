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
<c:set value="${requestScope.company}" scope="request" var="company"/>
<c:set value="${requestScope.student}" scope="request" var="student"/>
<c:set value="${requestScope.alreadyaplied}" scope="request" var="alreadyaplied"/>

<section>
    <div class="container">
        <div class="col-md-12">
            <img src="${company.getImgurl()}" alt="Cinque Terre" class="img-responsive">
            <div class="col-md-6"><h1>${jobOffer.getDescription()} <fmt:message key="in"
                                                                                bundle="${bundle}"/> ${company.getInfotxt()}</h1>
                <p>${jobOfferText.getText()}</p>
            </div>
            <div class="col-md-6"><h2><fmt:message key="spectoknow" bundle="${bundle}"/></h2>
                <ol>
                    <c:forEach items="${jobOfferSpecification}" var="speci">
                        <li><a href="/Controller?command=SPECINFO&item=${speci.getId()}" class="btn-link">${speci.getValue()}</a></li>
                    </c:forEach>
                </ol>
            </div>
            <c:if test="${student==true}">
                <div class="col-md-6"><h2><fmt:message key="apply" bundle="${bundle}"/></h2>
                <c:choose>
                    <c:when test="${alreadyaplied==false}">


                        <a href="/Controller?command=ADDJOBOFFERTOSTUDENT&jobofferid=${jobOffer.getId()}"
                           class="btn btn-primary btn-lg"><fmt:message key="sendaply" bundle="${bundle}"/></a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h2>
                            <fmt:message key="alreadyapplied" bundle="${bundle}"/>
                        </h2>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
    <c:if test="${not empty requestScope.students}">
        <c:set scope="request" value="${requestScope.students}" var="students"/>
    <div class="col-md-12">
    <c:forEach items="${students}" var="studi">
        <div class="col-lg-6 col-md-12 bg-info center-block">
            <h3>${studi.getProfile().getStudentProfile().get().getName()}</h3>
            <form action="/Controller" method="get">
                <INPUT hidden name="command" value="USERINFO"/>
                <button name="nickname" value="${studi.getNickname()}"><fmt:message bundle="${bundle}" key="moreinfo"/></button>
            </form>
        </div>
    </c:forEach>
    </div>
    </c:if>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
