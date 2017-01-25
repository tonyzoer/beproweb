<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="includes/head.jsp"%>
<body>
<%@include file="includes/navbar.jsp"%>
<section id="registration">
    <div class="container">
<form method="post" action="/Controller">
    <INPUT type="hidden" name="command" value="REGISTER"/>
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
                <td><input type="text" name="uname" value="" /></td>
            </tr><tr>
                <td><fmt:message key="email" bundle="${bundle}"/></td>
                <td><input type="text" name="email" value="" /></td>
            </tr>
            <tr>
                <td><fmt:message key="password" bundle="${bundle}"/></td>
                <td><input type="password" name="pass" value="" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit" /></td>
                <td><input type="reset" value="Reset" /></td>
            </tr>
            <tr>
                <td colspan="2"><fmt:message key="alreadyreg" bundle="${bundle}"/> <a href="#log" class="btn-login"><fmt:message key="loginhere" bundle="${bundle}"/></a></td>
            </tr>
            </tbody>
        </table>
        </div>
    </center>
</form>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp"%>
</body>
</html>