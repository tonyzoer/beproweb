﻿<!DOCTYPE html>
<html lang="en" class="full">
<!--<![endif]-->
<!--END NAVBAR SECTION-->
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:set var="lang" value="${empty lang ? 'en_US' : sessionScope.lang}"
           scope="session"/>
    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="localization/messages" var="bundle"/>
    <%@taglib prefix="cf" uri="WEB-INF/commandForm.tld" %>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <!--REQUIRED STYLE SHEETS-->
    <!-- BOOTSTRAP CORE STYLE CSS -->
    <link href="../../../assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLE CSS -->
    <link href="../../../assets/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- CUSTOM STYLE CSS -->
    <link href="../../../assets/css/style.css" rel="stylesheet"/>
    <link href="../../../assets/css/logpop.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <%@include file="WEB-INF/jsp/includes/navbar.jsp"%>
</head>
<body>
<!--HOME SECTION-->
<div id="home-sec">
    <div class="container"  >
        <div class="row text-center">
            <div  class="col-md-12" >
                <span class="head-main" ><fmt:message key="becomeapro" bundle="${bundle}" /></span>
                <h3 class="head-last col-md-8 col-md-offset-2  col-sm-8 col-sm-offset-2"><fmt:message key="rightnow" bundle="${bundle}" /></h3>


            </div>
        </div>
    </div>

</div>
<!--END HOME SECTION-->



<!--FOOTER SECTION -->

<!-- END FOOTER SECTION -->

<%@include file="WEB-INF/jsp/includes/footer.jsp"%>
<%@include file="WEB-INF/jsp/includes/jscripts.jsp"%>


</body>
</html>
