<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("admin");
    //roles.add("manager");
    //roles.add("accountant");
    //roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>

<style scoped>

</style>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/expense/edit">
    <div class="form-group">
        <label for="expenseDate" class="col-sm-3 control-label">Date</label>

        <div class="col-sm-3">
            <div class='input-group date' id='expenseDateGroup'>
                <input type='text' class="form-control" id="expenseDate" name="expenseDate" value="${expenseDate}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar">
                    </span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="tags" class="col-sm-3 control-label">Tags</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="tags" value="${tagValue}"/>
        </div>
    </div>
    <div class="form-group">
        <label for="amount" class="col-sm-3 control-label">Amount</label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol"/></span>
                <input type="number" class="form-control" id="amount" name="amount" value="${amount}"/>
                <span class="input-group-addon"><spring:message code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-primary"><spring:message code="web.button.save"/></button>
            <a href="${pageContext.request.contextPath}/expense/search">
                <button type="button" class="btn btn-default"><spring:message code="web.button.cancel"/></button>
            </a>
        </div>
    </div>
</form>

<script>

    $(function () {
        $("#expenseDateGroup").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });

    $('#tags').tokenfield({
        autocomplete: {
            source: [${tagValueJS}],
            delay: 100
        },
        showAutocompleteOnFocus: true
    })

</script>
<%@ include file="../template/footer.jsp" %>