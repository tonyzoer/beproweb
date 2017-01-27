<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New JobOffer</title>
</head>
<%@include file="includes/head.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<section>
    <h1>CREATE NEW JOB OFFER</h1>
    <form action="/Controller" method="post">
        <table class="table-bordered table table-responsive">
            <INPUT type="hidden" name="command" value="CREATEJOBOFFER"/>
            <tr>
                <td>Name</td>
                <td>
                    <input type="text" name="name" placeholder="Description">
                </td>
            </tr>
            <tr>
                <td><fmt:message key="spec" bundle="${bundle}"/></td>
                <td>
                    <select id="spec">
                        <c:set value="${requestScope.specificationList}" scope="request" var="specificationList"/>
                        <c:forEach items="${specificationList}" var="item">
                            <option value="${item.getId()}--${item.getValue()}">${item.getValue()}</option>
                        </c:forEach>>
                    </select>
                    <button onclick="document.getElementById('outSpec').value+=document.getElementById('spec').value+',';
                    var li=document.createElement('li');
                    li.appendChild(document.createTextNode(document.getElementById('spec').value))
                    document.getElementById('specList').appendChild(li);
                    $('#spec option:selected').remove();
                                "
                            type="button" class="btn"><fmt:message bundle="${bundle}" key="addtolst"/>
                    </button>
                    <br>

                    <input type="text" value="" id="newSpecInput">
                    <button onclick="document.getElementById('outNewSpec').value+=document.getElementById('newSpecInput').value+',';
                    var li=document.createElement('li');
                    li.appendChild(document.createTextNode(document.getElementById('newSpecInput').value))
                    document.getElementById('specList').appendChild(li);"
                            type="button" class="btn"><fmt:message bundle="${bundle}" key="addtolst"/>
                    </button>

                    <input type="text" hidden value="" id="outSpec" name="spec">
                    <input type="text" hidden value="" id="outNewSpec" name="newspec">
                </td>
                <td>
                    <ol id="specList"></ol>
                </td>
            </tr>
            <%--<tr>--%>
            <%--<td>Courses</td>--%>
            <%--<td><input type="text" id="courseUrl" value="">--%>
            <%--<button onclick="document.getElementById('courses').value+=document.getElementById('courseUrl').value+','; document.getElementById('coursesViewList').--%>
            <%--appendChild(document.createElement('li').appendChild(document.createTextNode(document.getElementById('courseUrl').value)));" type="button" class="btn"></button>--%>
            <%--<input type="text" hidden value="" name="courses" id="courses">--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<ol type="1" id="coursesViewList"></ol>--%>
            <%--</td>--%>
            <%--</tr>--%>
        </table>
        <textarea name="textinfo" value="" placeholder=
        <fmt:message key="jobofferfulldesc" bundle="${bundle}"/> class="col-lg-12"></textarea>
        <br>
        <button type="submit" class="btn btn-lg btn-primary pull-right">Create</button>
    </form>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
</body>
</html>
