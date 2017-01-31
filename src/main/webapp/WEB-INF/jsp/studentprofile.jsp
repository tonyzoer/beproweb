<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<head>
    <%@include file="includes/head.jsp" %>
    <title>Profile</title>
    <%@include file="includes/navbar.jsp" %></head>
<body>
<c:set var="profile" scope="session" value="${sessionScope.user.getProfile().getStudentProfile().get()}"/>
<c:set var="specifications" scope="session" value="${sessionScope.studentSpecifications}"/>
<c:set var="allspecifications" scope="session" value="${sessionScope.allspecifications}"/>
<section>
    <div class="container">
        <div class="row text-center">
            <div class="col-md-12">

                <span class="head-main"><fmt:message bundle="${bundle}" key="hello"/> ${profile.getName()}</span>

            </div>
        </div>
    </div>
</section>




<section id="about">
    <div class="container">
        <div class="row g-pad-bottom ">
            <div class="col-md-6 ">
            </div>

        </div>
        <div class="col-md-6 ">
            <h1>${profile.getName()}</h1>
            <a href="${profile.getCvurl()}" class="btn btn-primary btn-lg"><fmt:message bundle="${bundle}" key="downloadcv"/></a>
        </div>


        <div class="row text-center g-pad-bottom">
            <cf:commandform command="ADDSPECIFICATION">
                <div class="col-sm-6">
                    <select name="item">
                        <c:forEach items="${allspecifications}" var="speci">
                            <option name="specification" value="${speci.getId()}--${speci.getValue()}">
                                    ${speci.getValue()}
                            <option hidden name="id" value="${speci.getId()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn-info"><fmt:message bundle="${bundle}" key="addtolst"/></button>
            </cf:commandform>
            <div class="col-md-12">
                <h2><fmt:message bundle="${bundle}" key="mysklset"/></h2>
                <br/>

                    <c:forEach items="${specifications}" var="spec">
                        <cf:commandbtn command="SPECINFO" item="${spec.getId()}">${spec.getValue()}</cf:commandbtn>
                    </c:forEach>

            </div>
        </div>
    </div>

    </div>
    </div>
</section>

<section>
    <div class="container">
        <h2><fmt:message bundle="${bundle}" key="joboffer"/></h2>
        <div class="col-lg-12 center-block">
            <c:set scope="request" value="${requestScope.studentsOffers}" var="joboffers"/>
            <c:forEach items="${joboffers}" var="joboffer">
                <div class="row">
                    <div class="col-lg-12 col-md-12 bg-info center-block">
                        <c:out value="${joboffer.getDescription()}"/>
                    <cf:commandbtn command="JOBOFFERINFO" item="${joboffer.getId()}"><fmt:message key="moreinfo" bundle="${bundle}"/></cf:commandbtn>
                    </div>
                </div>
                <hr/>
            </c:forEach>
        </div>
    </div>
</section>
<!-- END FOOTER SECTION -->
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>


</body>
</html>
