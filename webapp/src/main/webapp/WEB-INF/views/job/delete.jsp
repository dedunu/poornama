<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("manager");
    roleValidator.validate(session, request, response, roles);
%>
<style scoped>
    .data-div {
        padding-top: 7px;
    }
</style>
<div class="alert alert-danger" role="alert">Once you delete data you cannot restore it. Please be careful when you are
    deleting records.
</div>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/job/delete/${jobId}">
    <div class="form-group">
        <label class="col-sm-3 control-label">Job Number</label>

        <div class="col-sm-3 data-div">
            ${jobId}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">From</label>

        <div class="col-sm-3 data-div">
            ${fromLocation}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">To</label>

        <div class="col-sm-3 data-div">
            ${toLocation}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Vehicle</label>

        <div class="col-sm-3 data-div">
            ${vehicle}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Driver</label>

        <div class="col-sm-3 data-div">
            ${driver}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Cleaner</label>

        <div class="col-sm-3 data-div">
            ${cleaner}
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">Start</label>

        <div class="col-sm-3 data-div">
            ${startDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">End</label>

        <div class="col-sm-3 data-div">
            ${endDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Hire Charges</label>

        <div class="col-sm-3 data-div">
            ${hireCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Labour Charges</label>

        <div class="col-sm-3 data-div">
            ${labourCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Container Charges</label>

        <div class="col-sm-3 data-div">
            ${containerCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Detention Charges</label>

        <div class="col-sm-3 data-div">
            ${detentionCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Hourly Detention Charges</label>

        <div class="col-sm-3 data-div">
            ${hourlyDetentionCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Daily Container Charges</label>

        <div class="col-sm-3 data-div">
            ${dailyContainerCharges}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Free Hours</label>

        <div class="col-sm-3 data-div">
            ${freeHours}
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-danger">Delete</button>
            <a href="${pageContext.request.contextPath}/job/search">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>

</script>
<%@ include file="../template/footer.jsp" %>