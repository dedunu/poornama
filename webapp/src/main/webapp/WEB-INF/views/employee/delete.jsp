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
<div class="alert alert-danger" role="alert">Once you delete data
    you cannot restore it. Please be careful when you are deleting records.
</div>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/employee/delete/${employeeId}">
    <div class="form-group">
        <label class="col-sm-3 control-label">Name</label>

        <div class="col-sm-6 data-div">${firstName} ${lastName}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Designation</label>

        <div class="col-sm-3 data-div">${employeeType}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">NIC Number</label>

        <div class="col-sm-3 data-div">${nic}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Address</label>

        <div class="col-sm-9 data-div">${address}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Date of Birth</label>

        <div class="col-sm-3 data-div">${dateOfBirth}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Date of Joining</label>

        <div class="col-sm-3 data-div">${dateOfJoining}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Description</label>

        <div class="col-sm-9 data-div">${description}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Mobile Number</label>

        <div class="col-sm-3 data-div">${telephone}</div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Emergency Contact</label>

        <div class="col-sm-3 data-div">${emergencyContact}</div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-danger">Delete</button>
            <a href="${pageContext.request.contextPath}/employee/search">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>

</script>
<%@ include file="../template/footer.jsp" %>