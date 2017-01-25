<c:set var="lang" value="${empty lang ? 'en_US' : sessionScope.lang}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localization/messages" var="bundle"/>
<!-- NAV SECTION -->
<div class="navbar navbar-inverse navbar-fixed-top">

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BE PRO</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/"><fmt:message key="home" bundle="${bundle}"/></a></li>
                <li><a href="/Controller?command=JOBOFFERS"><fmt:message key="joboffer" bundle="${bundle}"/></a></li>
                <c:choose>
                    <c:when test="${user!=null}">

                        <li><a href="Controller?command=DETECTPROFILE"><fmt:message key="account" bundle="${bundle}"/></a></li>
                        <li><a href="Controller?command=LOGOUT"><fmt:message key="logout" bundle="${bundle}"/></a></li>
                    </c:when>
                    <c:otherwise>

                        <li><a href="#log" class="btn-login"><fmt:message key="login" bundle="${bundle}"/></a></li>
                        <li><a href="Controller?command=REGISTERVIEW"><fmt:message key="reg" bundle="${bundle}"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

    </div>
</div>
<!--END NAV SECTION -->

<%@include file="logpop.jsp" %>
