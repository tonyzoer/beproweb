<div class="footer">
    <footer>

        <div class="form-group" style="background-color: aliceblue;">
          <div class="col-xs-3">  <p class="text-muted"> 2016 www.nodomain.com | All Right Reserved</p></div>
            <form action="${pageContext.request.contextPath}/Controller" method="post">
                <input type="hidden" name="command" value="LOCALE"/>
                <div class="col-xs-3"><select class="form-control" name="language">
                    <option selected value="ENG">ENG</option>
                    <option selected value="UKR">UKR</option>
                </select></div>
                <div class="col-xs-2"><input type="submit" class="form-select-button"
                                             value="<fmt:message key="chooseLanguage" bundle="${bundle}"/>"></div>
            </form>
        </div>
    </footer>
</div>