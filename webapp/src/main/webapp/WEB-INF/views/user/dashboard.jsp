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

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-users"></i> Employee
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-employee">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-employee">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/create";
    });
    $('#search-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/search";
    });
</script>


<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-check-square-o"></i> Attendance
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-primary" id="attendance">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#attendance').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/attendance";
    });
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-truck"></i> Vehicle
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-vehicle">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-vehicle">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/create";
    });
    $('#search-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/search";
    });
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-user"></i> User
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-user">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-user">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/create";
    });
    $('#search-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/search";
    });
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-tasks"></i> Job
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-job">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-job">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-job').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/job/create";
    });
    $('#search-job').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/job/search";
    });
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <span class="glyphicon glyphicon-usd"></span> Salary
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-primary" id="salary-calculate">
                <i class="fa fa-calculator"></i>
            </button>
        </div>
    </div>
</div>
<script>
    $('#salary-calculate').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/salary/calculate";
    });
</script>

<br/>
<%@ include file="../template/footer.jsp" %>