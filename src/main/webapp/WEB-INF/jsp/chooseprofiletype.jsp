<%--
  Created by IntelliJ IDEA.
  User: zoer
  Date: 18.01.17
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="includes/head.jsp"%>
<%@include file="includes/navbar.jsp"%>
<body>
<section id="choosetype">
<div class="container">
        <center>
            <div class="table-responsive">
                <button  value="company" name="type" style="font-size: 250%"
                         class="btn btn-primary btn-lg"
                         onclick="document.getElementById('student').style.visibility='hidden';
                         document.getElementById('company').style.visibility='visible';"><fmt:message key="company" bundle="${bundle}"/></button>
                <button  value="student" name="type" style="font-size: 250%"
                         class="btn btn-primary btn-lg"
                         onclick="document.getElementById('company').style.visibility='hidden';
                         document.getElementById('student').style.visibility='visible';"><fmt:message key="student" bundle="${bundle}"/></button>
            </div>
            <div class="row">
                <div class="col-sm-8" style="visibility: hidden" id="company">
                    <form action="/Controller" method="post" >
                        <INPUT type="hidden" name="command" value="SETPROFILE"/>
                        <input type="text" name="text" value="" placeholder=<fmt:message key="info" bundle="${bundle}"/>/>
                        <input type="text" name="picture" value="" placeholder=<fmt:message key="pictureurl" bundle="${bundle}"/>>
                        <button type="submit" value="company" name="type"><fmt:message key="submit" bundle="${bundle}"/></button>
                    </form>
                </div>
                <div class="col-sm-8" style="visibility: hidden" id="student">
                    <form action="/Controller" method="post" >
                        <INPUT type="hidden" name="command" value="SETPROFILE"/>
                        <input type="text" name="name" value="" placeholder=<fmt:message key="name" bundle="${bundle}"/>/>
                        <input type="text" name="country" value="" placeholder=<fmt:message key="country" bundle="${bundle}"/>/>
                        <input type="text" name="tel" value="" placeholder="<fmt:message key="tel" bundle="${bundle}"/>"/>
                        <input type="text" name="cv" value="" placeholder=<fmt:message key="cvurl" bundle="${bundle}"/>/>
                        <button type="submit" value="student" name="type"><fmt:message key="submit" bundle="${bundle}"/></button>
                    </form>
                </div>
            </div>
        </center>

</div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp"%>
</body>

</html>
