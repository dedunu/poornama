<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("admin");
    //roles.add("manager");
    //roles.add("accountant");
    //roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>

<style scoped>

</style>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/user/edit/${userId}">
    <div class="form-group">
        <label for="userName" class="col-sm-3 control-label">Username</label>

        <div class="col-sm-3">
            <input type="text" class="form-control" id="userName" name="userName" placeholder="" value="${userName}">
        </div>
    </div>
    <div class="form-group">
        <label for="displayName" class="col-sm-3 control-label">Display Name</label>

        <div class="col-sm-6">
            <input type="text" class="form-control" id="displayName" name="displayName" placeholder="" value="${displayName}">
        </div>
    </div>
    <div class="form-group">
        <label for="userRole" class="col-sm-3 control-label">Role</label>

        <div class="col-sm-3">
            <select class="form-control" id="userRole" name="userRole">
                ${userRoleList}
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-3 control-label">Password</label>

        <div class="col-sm-3">
            <input type="password" class="form-control" id="password" name="password" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="confirmPassword" class="col-sm-3 control-label">Confirm Password</label>

        <div class="col-sm-3">
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success">Create</button>
            <a href="${pageContext.request.contextPath}/user/search">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>

<script>
    $("#userRole").val("${userRole}");
</script>
<%@ include file="../template/footer.jsp" %>