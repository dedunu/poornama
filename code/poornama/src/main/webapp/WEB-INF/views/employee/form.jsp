<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>

<form class="form-horizontal" role="form">
    <div class="form-group">
        <label for="firstName" class="col-sm-2 control-label">First Name</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="firstName" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">Last Name</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="lastName" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="nic" class="col-sm-2 control-label">NIC Number</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="nic" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth" class="col-sm-2 control-label">Date of Birth</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="dateOfBirth" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Sign in</button>
        </div>
    </div>
</form>

<%@ include file="../template/footer.jsp" %>