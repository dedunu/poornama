<%@ include file="../template/headerWithNav.jsp" %>
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

<div id="validateNotMatching" class="hidden">
    <div class="alert alert-danger" role="alert">Passwords are not matching.</div>
</div>
<div id="validateEmpty" class="hidden">
    <div class="alert alert-danger" role="alert">You can't leave any of these text boxes empty. Please type your password.</div>
</div>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/user/changePassword" onsubmit="return validatePassword()">
    <div class="form-group">
        <label for="oldPassword" class="col-sm-3 control-label">Old Password</label>

        <div class="col-sm-3">
            <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="newPassword" class="col-sm-3 control-label">New Password</label>

        <div class="col-sm-3">
            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="confirmPassword" class="col-sm-3 control-label">Confirm New Password</label>

        <div class="col-sm-3">
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">
            <button type="submit" class="btn btn-success">Change Password</button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">Cancel</button>
            </a>
        </div>
    </div>
</form>


<script>
    function validatePassword() {

        hideAll();

        if (document.getElementById("oldPassword").value == "") {
            unhideEmpty();
            return false;
        }

        if (document.getElementById("newPassword").value == "") {
            unhideEmpty();
            return false;
        }

        if (document.getElementById("confirmPassword").value == "") {
            unhideEmpty();
            return false;
        }

        if (document.getElementById("newPassword").value != document.getElementById("confirmPassword").value) {
            unhideNotMatching();
            return false;
        } else {
            return true;
        }
    }

    function hideAll() {
        $("#validateEmpty").addClass("hidden");
        $("#validateNotMatching").addClass("hidden");
    }

    function unhideEmpty() {
        $("#validateEmpty").removeClass("hidden");
    }

    function unhideNotMatching() {
        $("#validateNotMatching").removeClass("hidden");
    }
</script>
<%@ include file="../template/footer.jsp" %>