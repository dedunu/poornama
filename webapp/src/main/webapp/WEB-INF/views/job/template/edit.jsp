<%@ include file="../../template/headerWithNav.jsp" %>
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

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/jobTemplate/edit/${jobTemplateId}">
    <div class="form-group">
        <label for="client" class="col-sm-3 control-label">Client</label>

        <div class="col-sm-3">
            <select class="form-control" id="client" name="client">
                ${clientList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="jobType" class="col-sm-3 control-label">Job Type</label>

        <div class="col-sm-3">
            <select class="form-control" id="jobType" name="jobType">
                ${jobTypeList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="displayName" class="col-sm-3 control-label">Display Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="displayName" name="displayName" placeholder=""
                   value="${displayName}">
        </div>
    </div>
    <div class="form-group">
        <label for="fromLocation" class="col-sm-3 control-label">From</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="fromLocation" name="fromLocation" placeholder=""
                   value="${fromLocation}">
        </div>
    </div>
    <div class="form-group">
        <label for="toLocation" class="col-sm-3 control-label">To</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="toLocation" name="toLocation" placeholder=""
                   value="${toLocation}">
        </div>
    </div>
    <div class="form-group">
        <label for="containerSize" class="col-sm-3 control-label">Container Size</label>

        <div class="col-sm-3">
            <div class="input-group">
                <select class="form-control" id="containerSize" name="containerSize">
                    <option value="20">20"</option>
                    <option value="40">40"</option>
                    <option value="45">45"</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="hireCharges" class="col-sm-3 control-label"><spring:message code="web.job.hire_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol"/></span>
                <input type="number" class="form-control" id="hireCharges" name="hireCharges" value="${hireCharges}">
                <span class="input-group-addon"><spring:message code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="labourCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.labour_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol"/></span>
                <input type="number" class="form-control" id="labourCharges" name="labourCharges"
                       value="${labourCharges}">
                <span class="input-group-addon"><spring:message code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="hourlyDetentionCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.hourly_detention_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol"/></span>
                <input type="number" class="form-control" id="hourlyDetentionCharges" name="hourlyDetentionCharges"
                       value="${hourlyDetentionCharges}" onchange="onHourlyDetentionChargesChange();">
                <span class="input-group-addon"><spring:message code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="dailyContainerCharges" class="col-sm-3 control-label"><spring:message
                code="web.job.daily_container_charges"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <span class="input-group-addon"><spring:message code="web.currency.symbol"/></span>
                <input type="number" class="form-control" id="dailyContainerCharges" name="dailyContainerCharges"
                       value="${dailyContainerCharges}" onchange="onDailyContainerChargesChange();">
                <span class="input-group-addon"><spring:message code="web.currency.suffix"/></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="freeHours" class="col-sm-3 control-label"><spring:message code="web.job.free_hours"/></label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="number" class="form-control" id="freeHours" name="freeHours" value="${freeHours}"
                       onchange="onFreeHoursChange();">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="distance" class="col-sm-3 control-label">Distance (km)</label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="number" class="form-control" id="distance" name="distance" value="${distance}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-primary"><spring:message code="web.button.save"/></button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default"><spring:message code="web.button.cancel"/></button>
            </a>
        </div>
    </div>
</form>

<script type="text/javascript">
    $("#client").val("${client}");
    $("#jobType").val("${jobType}");
    $("#containerSize").val("${containerSize}");
</script>

<%@ include file="../../template/footer.jsp" %>