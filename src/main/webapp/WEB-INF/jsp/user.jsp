<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="includes/head.jsp" %>
<%@include file="includes/navbar.jsp" %>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <div class="col-lg-12">
        <c:set scope="request" value="${requestScope.pageuser}" var="pageuser"/>
        <c:set scope="request" value="${requestScope.type}" var="type"/>
        <c:choose>
            <c:when test="${type==1}">
                <c:set scope="request" value="${requestScope.name}" var="name"/>
                <c:set scope="request" value="${requestScope.spec}" var="spec"/>
                <c:set scope="request" value="${requestScope.cv}" var="cv"/>
                <h1>${name}</h1>
                <div class="col-lg-6">
                    <h2>SKILL SET</h2>
                    <ol>
                        <c:forEach items="${jobOfferSpecification}" var="speci">
                            <li><a href="/Controller?command=SPECINFO&item=${speci.getId()}"
                                   class="btn-link">${speci.getValue()}</a></li>
                        </c:forEach>
                    </ol>
                </div>
                <div class="col-lg-6">
                    <a href="${cv}" class="btn btn-primary btn-lg"><fmt:message bundle="${bundle}" key="downloadcv"/></a>
                </div>
            </c:when>
            <c:when test="${type==2}">
                <c:set scope="request" value="${requestScope.imgurl}" var="imgurl"/>
                <c:set scope="request" value="${requestScope.name}" var="name"/>
                <c:set scope="request" value="${requestScope.joboffers}" var="joboffers"/>
                <div class="col-lg-8">
                <img src="${imgurl}" alt="Cinque Terre" class="img-responsive">
                    <h1>${name}</h1>
                    <c:forEach items="${joboffers}" var="joboffer">
                        <div class="row">
                            <div class="col-lg-6 col-md-12 bg-info center-block">
                                <c:out value="${joboffer.getDescription()}"/>
                                <a class="btn btn-primary btn-lg pull-right"
                                   href="/Controller?command=JOBOFFERINFO&item=${joboffer.getId()}"><fmt:message key="moreinfo" bundle="${bundle}"/></a>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
                    </c:when>
            <c:when test="${type==3}"></c:when>
        </c:choose>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
