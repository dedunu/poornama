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
      action="${pageContext.request.contextPath}/vehicle/delete/${vehicleId}">
    <div class="form-group">
        <label class="col-sm-3 control-label">Vehicle Number</label>

        <div class="col-sm-6 data-div">
            ${vehicleNumber}
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">Vehicle Type</label>

        <div class="col-sm-3 data-div">
            ${vehicleType}
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-danger">Delete</button>
            <a href="${pageContext.request.contextPath}/vehicle/search">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>

</script>
<%@ include file="../template/footer.jsp" %>