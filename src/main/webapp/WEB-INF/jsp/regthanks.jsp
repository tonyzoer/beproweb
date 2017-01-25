<%@ page import="java.util.Timer" %>
<%@ page import="java.util.TimerTask" %>
<%@ page import="javafx.application.Platform" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: Zoer
  Date: 16.01.2017
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="includes/head.jsp"%>
<body>
<%@include file="includes/navbar.jsp"%>
<section>
<div style="text-align: center; font-size: 40px;" class="col-md-6"><fmt:message key="regthank" bundle="${bundle}"/>
</b>
<a href="/"><fmt:message key="home" bundle="${bundle}"/></a>
</div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp"%>
</body>
</html>
