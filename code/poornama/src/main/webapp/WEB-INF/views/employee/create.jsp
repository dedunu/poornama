<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("admin");
    roles.add("manager");
    //roles.add("accountant");
    //roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>

<style scoped>

</style>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/employee/create">
    <div class="form-group">
        <label for="firstName" class="col-sm-3 control-label">First Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="lastName" class="col-sm-3 control-label">Last Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="employeeType" class="col-sm-3 control-label">Designation</label>

        <div class="col-sm-3">
            <select class="form-control" id="employeeType" name="employeeType">
                ${employeeTypeList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="nic" class="col-sm-3 control-label">NIC Number</label>

        <div class="col-sm-3">
            <div class="input-group">
                <input type="text" class="form-control" id="nic" name="nic" placeholder="">
                <span class="input-group-addon">V</span>
            </div>

        </div>
    </div>
    <div class="form-group">
        <label for="address" class="col-sm-3 control-label">Address</label>

        <div class="col-sm-9">
            <textarea class="form-control" id="address" name="address" rows="3" placeholder=""></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth" class="col-sm-3 control-label">Date of Birth</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfJoining" class="col-sm-3 control-label">Date of Joining</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="dateOfJoining" name="dateOfJoining" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-3 control-label">Description</label>

        <div class="col-sm-9">
            <textarea class="form-control" id="description" name="description" rows="3" placeholder=""></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="telephone" class="col-sm-3 control-label">Mobile Number</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="telephone" name="telephone" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="emergencyContact" class="col-sm-3 control-label">Emergency Contact</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="emergencyContact" name="emergencyContact" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success">Create</button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>
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