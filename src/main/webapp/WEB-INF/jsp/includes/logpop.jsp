<form method="post" action="/Controller">
    <!-- login popup Start -->
    <div class="login_popup">
        <div class="overlay"></div>
        <div class="mask">
            <i class="glyphicon glyphicon-remove"><a href=""></a></i>
            <div class="login_table">
                <INPUT type="hidden" name="command" value="LOGIN"/>
                <h3><fmt:message key="login" bundle="${bundle}"/></h3>
                <table>
                    <tr>
                        <td><input type="text" class="form-control" name="uname" placeholder=<fmt:message key="user" bundle="${bundle}"/>></td>
                    </tr>
                    <tr>
                        <td><input type="password" class="form-control" name="pass" placeholder=<fmt:message key="password" bundle="${bundle}"/>></td>
                    </tr>

                    <tr>

                        <td>
                            <input type="submit" class="btn btn-ok" value="LOGIN"></input>
                        </td>
                    </tr>
                </table>
            </div>
        </div>


    </div>
    <!-- login popup End -->
</form>