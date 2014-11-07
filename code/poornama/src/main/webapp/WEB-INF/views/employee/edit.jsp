<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/employee/edit/${employeeId}">
    <div class="form-group">
        <label for="firstName" class="col-sm-3 control-label">First Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="${firstName}">
        </div>
    </div>
    <div class="form-group">
        <label for="lastName" class="col-sm-3 control-label">Last Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="${lastName}">
        </div>
    </div>
    <div class="form-group">
        <label for="employeeType" class="col-sm-3 control-label">Designation</label>

        <div class="col-sm-3">
            <select class="form-control" id="employeeType" name="employeeType">
                ${employeeList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="nic" class="col-sm-3 control-label">NIC Number</label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="text" class="form-control" id="nic" name="nic" placeholder="" value="${nic}">
                <span class="input-group-addon">V</span>
            </div>

        </div>
    </div>
    <div class="form-group">
        <label for="address" class="col-sm-3 control-label">Address</label>

        <div class="col-sm-9">
            <textarea class="form-control" id="address" name="address" rows="3" placeholder="">${address}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth" class="col-sm-3 control-label">Date of Birth</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder=""
                   value="${dateOfBirth}">
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfJoining" class="col-sm-3 control-label">Date of Joining</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="dateOfJoining" name="dateOfJoining" placeholder=""
                   value="${dateOfJoining}">
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-3 control-label">Description</label>

        <div class="col-sm-9">
            <textarea class="form-control" id="description" name="description" rows="3"
                      placeholder="">${description}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="telephone" class="col-sm-3 control-label">Mobile Number</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="telephone" name="telephone" placeholder="" value="${telephone}">
        </div>
    </div>
    <div class="form-group">
        <label for="emergencyContact" class="col-sm-3 control-label">Emergency Contact</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="emergencyContact" name="emergencyContact" placeholder=""
                   value="${emergencyContact}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>
    $("#employeeType").val("${employeeType}");

    $(function () {
        $("#dateOfBirth").datepicker({
            changeMonth: true,
            changeYear: true
        });

        $("#dateOfJoining").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<%@ include file="../template/footer.jsp" %>