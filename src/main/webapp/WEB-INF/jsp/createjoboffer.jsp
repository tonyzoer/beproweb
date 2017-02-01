<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>Create New JobOffer</title>
    <%@include file="includes/navbar.jsp" %>
</head>
<body>

<section>
    <h1>CREATE NEW JOB OFFER</h1>
    <div class="form-group">
        <cf:commandform command="CREATEJOBOFFER" method="post">
            <table class="table-bordered table table-responsive">
                <tr>
                    <td>Name</td>
                    <td>
                        <input type="text" name="name" class="form-control" placeholder="Description">
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="spec" bundle="${bundle}"/></td>
                    <td>
                        <select id="spec" class="form-control">
                            <c:set value="${requestScope.specificationList}" scope="request" var="specificationList"/>
                            <c:forEach items="${specificationList}" var="item">
                                <option value="${item.getId()}--${item.getValue()}">${item.getValue()}</option>
                            </c:forEach>>
                        </select>
                        <button onclick="document.getElementById('outSpec').value+=document.getElementById('spec').value+',';
                    var li=document.createElement('li');
                    li.className='list-group-item';
                    li.appendChild(document.createTextNode(document.getElementById('spec').value.split('--')[1]));
                    document.getElementById('specList').appendChild(li);
                    addremovebtn(li);
                    $('#spec option:selected').remove();
                                "
                                type="button" class="btn btn-info form-control"><fmt:message bundle="${bundle}"
                                                                                             key="addtolst"/>
                        </button>
                        <br>

                        <input type="text" class="form-control" value="" id="newSpecInput" placeholder=<fmt:message
                                bundle="${bundle}" key="createown"/>>
                        <button onclick="document.getElementById('outNewSpec').value+=document.getElementById('newSpecInput').value+',';
                    var li=document.createElement('li');
                    li.className='list-group-item';
                    li.appendChild(document.createTextNode(document.getElementById('newSpecInput').value));
                    addremovebtn(li);
                    document.getElementById('specList').appendChild(li);"
                                type="button" class="btn btn-info form-control"><fmt:message bundle="${bundle}"
                                                                                             key="addtolst"/>
                        </button>

                        <input type="text" hidden value="" id="outSpec" name="spec">
                        <input type="text" hidden value="" id="outNewSpec" name="newspec">
                    </td>
                    <td>
                        <ol id="specList" class="list-group"></ol>
                    </td>
                </tr>
            </table>
            <textarea name="textinfo" class="form-control" value="" rows="10" placeholder=
                <fmt:message key="jobofferfulldesc" bundle="${bundle}"/> class="col-lg-12"></textarea>
            <br>
            <button type="submit" class="btn btn-lg btn-info btn-primary pull-right col-xs-offset-2">Create</button>
        </cf:commandform>
    </div>
</section>
<%@include file="includes/footer.jsp" %>
<%@include file="includes/jscripts.jsp" %>
<script> function addremovebtnOld (li) {
    li.innerHTML+='<a href="#" onclick="remove(this)">remove</a>'
}
function remove(el) {
    el.parentNode.parentNode.removeChild(link.parentNode);
}
function removeFromOutSpec(name) {
    var str= document.getElementById('outSpec').value;
    str.split
}
</script>
</body>
</html>
