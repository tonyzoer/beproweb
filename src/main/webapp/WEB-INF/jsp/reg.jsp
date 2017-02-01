<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <%@include file="includes/navbar.jsp" %>
</head>
<body>


<section id="registration">
    <div class="container"><div class="form-group">
        <cf:commandform  command="REGISTER" method="post">
            <center>
                <div class="table-responsive">
                    <table border="1" width="30%" cellpadding="5" class="table">
                        <thead>
                        <tr>
                            <th colspan="2"><fmt:message key="enterinfo" bundle="${bundle}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><fmt:message key="username" bundle="${bundle}"/></td>
                            <td><input class="form-control" type="text" name="uname" value=""/>
                                <c:if test="${not empty requestScope.error&& requestScope.error=='WRONG_LOGIN_LENGTH'}">
                                    <div class="alert alert-danger" id="error">
                                        <p>${requestScope.error_message}</p>
                                    </div>
                                </c:if></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="email" bundle="${bundle}"/></td>
                            <td><input class="form-control" type="text" name="email" value=""
                                       onfocus="document.getElementById('error').outerHTML=''"/>
                                <c:if test="${not empty requestScope.error&& requestScope.error=='WRONG_EMAIL'}">
                                    <div class="alert alert-danger" id="error">
                                        <p>${requestScope.error_message}</p>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message key="password" bundle="${bundle}"/></td>
                            <td><input class="form-control" type="password" name="pass" value=""/>
                                <c:if test="${not empty requestScope.error&& requestScope.error=='WRONG_PASSWORD'}">
                                    <div class="alert alert-danger" id="error">
                                        <p>${requestScope.error_message}</p>
                                    </div>
                                </c:if></td>
                        </tr>
                        <tr>
                            <td><input class="btn-info" type="submit" value="Submit"/></td>
                            <td><input class="btn-info" type="reset" value="Reset"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><fmt:message key="alreadyreg" bundle="${bundle}"/> <a href="#log"
                                                                                                  class="btn-login"><fmt:message
                                    key="loginhere" bundle="${bundle}"/></a>
                                <c:if test="${not empty requestScope.error&& requestScope.error=='LOGIN_ALREADY_EXIST'}">
                                    <div class="alert alert-danger" id="error">
                                        <p>${requestScope.error_message}</p>
                                    </div>
                                </c:if><
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </center>
        </cf:commandform></div>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>