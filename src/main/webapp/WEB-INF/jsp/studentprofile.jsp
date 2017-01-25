<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<%@include file="includes/head.jsp" %>

<body>
<c:set var="profile" scope="session" value="${sessionScope.user.getProfile().getStudentProfile().get()}"/>
<c:set var="specifications" scope="session" value="${sessionScope.studentSpecifications}"/>
<c:set var="allspecifications" scope="session" value="${sessionScope.allspecifications}"/>
<section>
    <div class="container">
        <div class="row text-center">
            <div class="col-md-12">

                <span class="head-main">Hello, ${profile.getName()}</span>

            </div>
        </div>
    </div>
</section>

<%@include file="includes/navbar.jsp" %>


<section id="about">
    <div class="container">
        <div class="row g-pad-bottom ">
            <div class="col-md-6 ">
            </div>

        </div>
        <div class="col-md-6 ">
            <h1>${profile.getName()}</h1>
            <a href="#" class="btn btn-primary btn-lg">DOWNLOAD MY RESUME</a>
        </div>


        <div class="row text-center g-pad-bottom">
            <form action="/Controller">
                <div class="col-sm-6">
                    <INPUT type="hidden" name="command" value="ADDSPECIFICATION"/>
                    <select name="item">
                        <c:forEach items="${allspecifications}" var="speci">
                            <option name="specification" value="${speci.getId()}--${speci.getValue()}">
                                    ${speci.getValue()}
                                    <option hidden name="id" value="${speci.getId()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit">add Specification</button>
            </form>
            <div class="col-md-12">
                <h2>MY SKILL SET</h2>
                <br/>
                <div class="alert-info">
                    <c:forEach items="${specifications}" var="spec">
                    <div class="progress progress-adjust">
                        <div class="progress-bar progress-bar-success" style="width: 30%">
                            <div class="skill-div">${spec.getValue()}</div>
                            <span class="sr-only">30% Complete (success)</span>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>

    </div>
    </div>
    </div>
</section>

<!-- END FOOTER SECTION -->
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>


</body>
</html>
