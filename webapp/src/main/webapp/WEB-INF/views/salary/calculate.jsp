<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("manager");
    roleValidator.validate(session, request, response, roles);
%>
<style scoped>
</style>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/salary/calculate">
    <div class="form-group">
        <label for="salaryMonth" class="col-sm-3 control-label">Month
        </label>

        <div class="col-sm-6">
            <div class='input-group date' id='salaryMonthGroup'>
                <input type='text' class="form-control" id="salaryMonth"
                       name="salaryMonth"/> <span class="input-group-addon"> <span
                    class="glyphicon glyphicon-calendar"> </span>
				</span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success">Calculate</button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>
    $(function () {
        $('#salaryMonthGroup').datetimepicker({
            viewMode: 'months',
            format: 'MM/YYYY'
        });
    });
</script>
<%@ include file="../template/footer.jsp" %>