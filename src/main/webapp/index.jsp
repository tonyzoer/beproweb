<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<%@include file="WEB-INF/jsp/includes/head.jsp"%>
<!--NAVBAR SECTION-->
<%@include file="WEB-INF/jsp/includes/navbar.jsp"%>
<!--END NAVBAR SECTION-->
<head></head>
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
