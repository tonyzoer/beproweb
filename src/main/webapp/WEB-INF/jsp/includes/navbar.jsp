
<!-- NAV SECTION -->
<section>
<div class="navbar navbar-inverse navbar-fixed-top">

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">BE PRO</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><cf:commandbtn command="" method="get" action="/"><fmt:message key="home" bundle="${bundle}"/></cf:commandbtn></li>
                <li><cf:commandbtn command="JOBOFFERS" method="get"><fmt:message key="joboffer" bundle="${bundle}"/></cf:commandbtn></li>
                <c:choose>
                    <c:when test="${user!=null}">
                        <li><cf:commandbtn command="DETECTPROFILE" method="get"><fmt:message key="account" bundle="${bundle}"/></cf:commandbtn></li>
                        <li><cf:commandbtn command="LOGOUT" method="get"><fmt:message key="logout" bundle="${bundle}"/></cf:commandbtn></li>
                    </c:when>
                    <c:otherwise>

                        <li><button class="btn-info btn-login btn-lg"><fmt:message key="login" bundle="${bundle}"/></button></li>
                        <li><cf:commandbtn command="REGISTERVIEW" method="get"><fmt:message key="reg" bundle="${bundle}"/></cf:commandbtn></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

    </div>
</div>
<!--END NAV SECTION -->
<%@include file="logpop.jsp" %>
</section>