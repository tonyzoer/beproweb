<div class="navbar navbar-inverse navbar-fixed-bottom">
    <footer>

        <div class="form-group" style="background-color: aliceblue;">
          <div class="col-xs-6">  <p class="text-muted"> 2016 www.nodomain.com | All Right Reserved</p></div>
            <cf:commandform command="LOCALE" action="${pageContext.request.contextPath}/Controller" method="post">
                <div class="col-xs-3"><select class="form-control" name="language">
                    <option selected value="ENG">ENG</option>
                    <option selected value="UKR">UKR</option>
                </select></div>
                <div class="col-xs-2"><input type="submit" class="form-select-button btn-info"
                                             value="<fmt:message key="chooseLanguage" bundle="${bundle}"/>"></div>
            </cf:commandform>
        </div>
    </footer>
</div>