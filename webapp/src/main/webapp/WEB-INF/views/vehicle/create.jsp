<%@ include file="../template/headerWithNav.jsp" %>
<%
    // Creates RoleValidator object
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    // Adds the manager to allowed role list
    roles.add("clerk");
    roles.add("manager");
    // Calls validation method
    roleValidator.validate(session, request, response, roles);
%>
<style scoped>
</style>

<form class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/vehicle/create">
    <div class="form-group">
        <label for="vehicleNumber" class="col-sm-3 control-label">Vehicle
            Number</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="vehicleNumber"
                   name="vehicleNumber" placeholder="" required>
        </div>
    </div>
    <div class="form-group">
        <label for="vehicleType" class="col-sm-3 control-label">Vehicle
            Type</label>

        <div class="col-sm-3">
            <select class="form-control" id="vehicleType" name="vehicleType" required>
                ${vehicleTypeList}
            </select>
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

</script>
<%@ include file="../template/footer.jsp" %>