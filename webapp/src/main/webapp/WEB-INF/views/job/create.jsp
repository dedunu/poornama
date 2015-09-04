<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("manager");
    roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>

<style scoped>
</style>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/job/create">
    <div class="form-group">
        <label for="jobTemplate" class="col-sm-3 control-label"><spring:message
                code="web.job.job_template"/></label>

        <div class="col-sm-3">
            <select class="form-control" id="jobTemplate" name="jobTemplate"
                    onchange="getJobTemplateDetails()"> ${jobTemplateList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="vehicle" class="col-sm-3 control-label"><spring:message
                code="web.general.vehicle"/></label>

        <div class="col-sm-3">
            <select class="form-control" id="vehicle" name="vehicle">
                ${vehicleList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="driver" class="col-sm-3 control-label"><spring:message
                code="web.general.driver"/></label>

        <div class="col-sm-3">
            <select class="form-control" id="driver" name="driver">
                ${driverList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="cleaner" class="col-sm-3 control-label"><spring:message
                code="web.general.cleaner"/></label>

        <div class="col-sm-3">
            <select class="form-control" id="cleaner" name="cleaner">
                ${cleanerList}
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="startDate" class="col-sm-3 control-label"><spring:message
                code="web.job.start"/></label>

        <div class="col-sm-3">
            <div class='input-group date' id="startDateInputGroup">
                <input type='text' class="form-control" id="startDate"
                       name="startDate"/> <span class="input-group-addon"> <span
                    class="glyphicon glyphicon-calendar"></span>
				</span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="endDate" class="col-sm-3 control-label"><spring:message
                code="web.job.end"/></label>

        <div class="col-sm-3">
            <div class='input-group date' id="endDateInputGroup">
                <input type='text' class="form-control" id="endDate" name="endDate"/>
				<span class="input-group-addon"> <span
                        class="glyphicon glyphicon-calendar"></span>
				</span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="hireCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.hire_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="hireCharges"
                                                                   name="hireCharges"> <span
                    class="input-group-addon"><spring:message
                    code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="labourCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.labour_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="labourCharges"
                                                                   name="labourCharges">
				<span class="input-group-addon"><spring:message
                        code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="containerCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.container_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="containerCharges"
                                                                   name="containerCharges">
				<span class="input-group-addon"><spring:message
                        code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="detentionCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.detention_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="detentionCharges"
                                                                   name="detentionCharges">
				<span class="input-group-addon"><spring:message
                        code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="hourlyDetentionCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.hourly_detention_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="hourlyDetentionCharges"
                                                                   name="hourlyDetentionCharges"
                                                                   onchange="onHourlyDetentionChargesChange();"> <span
                    class="input-group-addon"><spring:message
                    code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="dailyContainerCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.daily_container_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
				<span class="input-group-addon"><spring:message
                        code="web.currency.symbol"/></span> <input type="number"
                                                                   class="form-control" id="dailyContainerCharges"
                                                                   name="dailyContainerCharges"
                                                                   onchange="onDailyContainerChargesChange();"> <span
                    class="input-group-addon"><spring:message
                    code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="freeHours" class="col-sm-3 control-label"><spring:message
                code="web.job.free_hours"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="number" class="form-control" id="freeHours"
                       name="freeHours" onchange="onFreeHoursChange();">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success">
                <spring:message code="web.button.create"/>
            </button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">
                    <spring:message code="web.button.cancel"/>
                </button>
            </a>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $('#startDateInputGroup').datetimepicker();
        $('#endDateInputGroup').datetimepicker();
        $("#startDateInputGroup").on("dp.change", function (e) {
            $('#endDateInputGroup').data("DateTimePicker").minDate(e.date);
            onTimeChange();
        });
        $("#endDateInputGroup").on("dp.change", function (e) {
            $('#startDateInputGroup').data("DateTimePicker").maxDate(e.date);
            onTimeChange();
        });
    });

    function getJobTemplateDetails() {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/job/details/' + document.getElementById('jobTemplate').value,
            success: function (result) {
                document.getElementById("labourCharges").value = result.labourCharges.string;
                document.getElementById("hireCharges").value = result.hireCharges.string;
                document.getElementById("hourlyDetentionCharges").value = result.hourlyDetentionCharges.string;
                document.getElementById("dailyContainerCharges").value = result.dailyContainerCharges.string;
                document.getElementById("freeHours").value = result.freeHours.string;
            }
        });
    }

    function calculateHours() {
        if (!Date.isValid(document.getElementById("startDate").value, '<spring:message code="web.date.format" />')) {
            console.log("startDate Error!");
            return null;
        }

        if (!Date.isValid(document.getElementById("endDate").value, '<spring:message code="web.date.format" />')) {
            console.log("endDate Error!");
            return null;
        }

        var startDate = Date.parseString(document.getElementById("startDate").value, '<spring:message code="web.date.format" />');
        var endDate = Date.parseString(document.getElementById("endDate").value, '<spring:message code="web.date.format" />');

        var hours = Math.round(Math.abs(endDate - startDate) / 36e5);

        console.log("Hours : " + hours);
        return hours;
    }

    function onTimeChange() {
        onFreeHoursChange();
        onDailyContainerChargesChange();
    }

    function onFreeHoursChange() {
        onHourlyDetentionChargesChange();
    }

    function onDailyContainerChargesChange() {
        var days = Math.ceil(calculateHours() / 24);
        var dailyContainerCharges = parseInt(document.getElementById("dailyContainerCharges").value);
        document.getElementById("containerCharges").value = days * dailyContainerCharges;
    }


    function onHourlyDetentionChargesChange() {
        var totalHours = calculateHours();
        var freeHours = parseInt(document.getElementById("freeHours").value);
        var hourlyDetentionCharges = parseInt(document.getElementById("hourlyDetentionCharges").value);
        if (totalHours >= freeHours) {
            document.getElementById("detentionCharges").value = (totalHours - freeHours) * hourlyDetentionCharges;
        } else {
            document.getElementById("detentionCharges").value = 0;
        }
    }
</script>

<%@ include file="../template/footer.jsp" %>