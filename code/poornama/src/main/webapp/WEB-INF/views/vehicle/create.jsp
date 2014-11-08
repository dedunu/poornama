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

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/vehicle/create">
    <div class="form-group">
        <label for="vehicleNumber" class="col-sm-3 control-label">Vehicle Number</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="vehicleNumber" name="vehicleNumber" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="vehicleType" class="col-sm-3 control-label">Vehicle Type</label>

        <div class="col-sm-3">
            <select class="form-control" id="vehicleType" name="vehicleType">
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