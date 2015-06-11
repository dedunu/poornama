<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("admin");
    roles.add("manager");
    roles.add("accountant");
    roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>

<style scoped>

</style>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/job/create">
    <div class="form-group">
        <label for="jobTemplate" class="col-sm-3 control-label"><spring:message code="web.job.job_template" /></label>

        <div class="col-sm-3">
            <select class="form-control" id="jobTemplate" name="jobTemplate">
                ${jobTemplateList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="driver" class="col-sm-3 control-label"><spring:message code="web.general.driver" /></label>

        <div class="col-sm-3">
            <select class="form-control" id="driver" name="driver">
                ${driverList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="cleaner" class="col-sm-3 control-label"><spring:message code="web.general.cleaner" /></label>

        <div class="col-sm-3">
            <select class="form-control" id="cleaner" name="cleaner">
                ${cleanerList}
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="startDate" class="col-sm-3 control-label"><spring:message code="web.job.start" /></label>

        <div class="col-sm-3">
            <div class='input-group date'>
                <input type='text' class="form-control" id="startDate" name="startDate" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="endDate" class="col-sm-3 control-label"><spring:message code="web.job.end" /></label>

        <div class="col-sm-3">
            <div class='input-group date'>
                <input type='text' class="form-control" id="endDate" name="endDate" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="hireCharges" class="col-sm-3 control-label"><spring:message code="web.job.hire_charges" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol" /></span>
                <input type="text" class="form-control" id="hireCharges" name="hireCharges">
                <span class="input-group-addon"><spring:message code="web.currency.suffix" /></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="labourCharges" class="col-sm-3 control-label"><spring:message code="web.job.labour_charges" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol" /></span>
                <input type="text" class="form-control" id="labourCharges" name="labourCharges">
                <span class="input-group-addon"><spring:message code="web.currency.suffix" /></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="containerCharges" class="col-sm-3 control-label"><spring:message code="web.job.container_charges" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol" /></span>
                <input type="text" class="form-control" id="containerCharges" name="containerCharges">
                <span class="input-group-addon"><spring:message code="web.currency.suffix" /></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="detentionCharges" class="col-sm-3 control-label"><spring:message code="web.job.detention_charges" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol" /></span>
                <input type="text" class="form-control" id="detentionCharges" name="detentionCharges">
                <span class="input-group-addon"><spring:message code="web.currency.suffix" /></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="dailyContainerCharges" class="col-sm-3 control-label"><spring:message code="web.job.daily_container_charges" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol" /></span>
                <input type="text" class="form-control" id="dailyContainerCharges" name="dailyContainerCharges">
                <span class="input-group-addon"><spring:message code="web.currency.suffix" /></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="freeHours" class="col-sm-3 control-label"><spring:message code="web.job.free_hours" /></label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="text" class="form-control" id="freeHours" name="freeHours">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success"><spring:message code="web.button.create" /></button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default"><spring:message code="web.button.cancel" /></button>
            </a>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $('#startDate').datetimepicker();
        $('#endDate').datetimepicker();
        $("#startDate").on("dp.change", function (e) {
            $('#endDate').data("DateTimePicker").minDate(e.date);
        });
        $("#endDate").on("dp.change", function (e) {
            $('#startDate').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>

<%@ include file="../template/footer.jsp" %>