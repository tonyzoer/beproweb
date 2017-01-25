<div id="footer">
    2016 www.nodomain.com | All Right Reserved
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <input type="hidden" name="command" value="LOCALE"/>
            <select name="language">
                <option selected value="ENG">ENG</option>
                <option selected value="UKR">UKR</option>
            </select>
            <input type="submit"
                   value="<fmt:message key="chooseLanguage" bundle="${bundle}"/>">
        </form>
</div>