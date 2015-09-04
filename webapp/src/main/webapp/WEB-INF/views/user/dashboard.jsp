<%@ include file="../template/headerWithNav.jsp" %>
<%
    RoleValidator roleValidator = new RoleValidator();
    List<String> roles = new ArrayList<String>();
    roles.add("admin");
    roles.add("owner");
    roles.add("manager");
    roles.add("clerk");
    roleValidator.validate(session, request, response, roles);
%>
<style scoped>
</style>

<%
    List<String> employeeSectionRoleList = new ArrayList<String>();
    employeeSectionRoleList.add("owner");
    employeeSectionRoleList.add("manager");
    employeeSectionRoleList.add("clerk");

    List<String> employeeCreateRoleList = new ArrayList<String>();
    employeeCreateRoleList.add("manager");

    if (roleValidator.isVisible(session, request, response, employeeSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-users"></i> Employee
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, employeeCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-employee">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-employee">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, employeeCreateRoleList)) { %>
    $('#create-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/create";
    });
    <% } %>

    $('#search-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/search";
    });
</script>
<% } %>

<%
    List<String> employeeAttendanceSectionRoleList = new ArrayList<String>();
    employeeAttendanceSectionRoleList.add("owner");
    employeeAttendanceSectionRoleList.add("manager");

    if (roleValidator.isVisible(session, request, response, employeeAttendanceSectionRoleList)) {
%>
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
<% } %>

<%
    List<String> vehicleSectionRoleList = new ArrayList<String>();
    vehicleSectionRoleList.add("owner");
    vehicleSectionRoleList.add("manager");
    vehicleSectionRoleList.add("clerk");

    List<String> vehicleCreateRoleList = new ArrayList<String>();
    vehicleCreateRoleList.add("manager");

    if (roleValidator.isVisible(session, request, response, vehicleSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-truck"></i> Vehicle
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, vehicleCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-vehicle">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-vehicle">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, vehicleCreateRoleList)) { %>
    $('#create-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/create";
    });
    <% } %>

    $('#search-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/search";
    });
</script>
<% } %>

<%
    List<String> userSectionRoleList = new ArrayList<String>();
    userSectionRoleList.add("owner");
    userSectionRoleList.add("manager");
    userSectionRoleList.add("admin");

    List<String> userCreateRoleList = new ArrayList<String>();
    userCreateRoleList.add("admin");

    if (roleValidator.isVisible(session, request, response, userSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-user"></i> User
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, userCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-user">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-user">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, userCreateRoleList)) { %>
    $('#create-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/create";
    });
    <% } %>
    $('#search-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/search";
    });
</script>
<% } %>

<%
    List<String> jobSectionRoleList = new ArrayList<String>();
    jobSectionRoleList.add("owner");
    jobSectionRoleList.add("manager");
    jobSectionRoleList.add("clerk");

    List<String> jobCreateRoleList = new ArrayList<String>();
    jobCreateRoleList.add("manager");
    jobCreateRoleList.add("clerk");

    if (roleValidator.isVisible(session, request, response, jobSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-tasks"></i> Job
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, jobCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-job">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-job">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, jobCreateRoleList)) { %>
    $('#create-job').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/job/create";
    });
    <% } %>
    $('#search-job').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/job/search";
    });
</script>
<% } %>

<%
    List<String> salarySectionRoleList = new ArrayList<String>();
    salarySectionRoleList.add("manager");

    if (roleValidator.isVisible(session, request, response, salarySectionRoleList)) {
%>
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
<% } %>

<%
    List<String> expenseSectionRoleList = new ArrayList<String>();
    expenseSectionRoleList.add("owner");
    expenseSectionRoleList.add("manager");
    expenseSectionRoleList.add("clerk");

    List<String> expenseCreateRoleList = new ArrayList<String>();
    expenseCreateRoleList.add("manager");
    expenseCreateRoleList.add("clerk");

    if (roleValidator.isVisible(session, request, response, expenseSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-money"></i> Expenses
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, expenseCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-expense">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-expense">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, expenseCreateRoleList)) { %>
    $('#create-expense').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/expense/create";
    });
    <% } %>
    $('#search-expense').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/expense/search";
    });
</script>
<% } %>

<%
    List<String> jobTemplateSectionRoleList = new ArrayList<String>();
    jobTemplateSectionRoleList.add("owner");
    jobTemplateSectionRoleList.add("manager");
    jobTemplateSectionRoleList.add("clerk");

    List<String> jobTemplateCreateRoleList = new ArrayList<String>();
    jobTemplateCreateRoleList.add("manager");
    jobTemplateCreateRoleList.add("owner");

    if (roleValidator.isVisible(session, request, response, jobTemplateSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <span class="glyphicon glyphicon-inbox"></span> Job Template
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, jobTemplateCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-jobTemplate">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-jobTemplate">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, jobTemplateCreateRoleList)) { %>
    $('#create-jobTemplate').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/jobTemplate/create";
    });
    <% } %>
    $('#search-jobTemplate').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/jobTemplate/search";
    });
</script>
<% } %>

<%
    List<String> reportSectionRoleList = new ArrayList<String>();
    reportSectionRoleList.add("owner");
    reportSectionRoleList.add("manager");

    if (roleValidator.isVisible(session, request, response, reportSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-file-text"></i> Reports
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-primary" id="report">
                <span class="glyphicon glyphicon-list-alt"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#report').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/report/index";
    });
</script>
<% } %>


<%
    List<String> clientSectionRoleList = new ArrayList<String>();
    clientSectionRoleList.add("owner");
    clientSectionRoleList.add("manager");
    clientSectionRoleList.add("clerk");

    List<String> clientCreateRoleList = new ArrayList<String>();
    clientCreateRoleList.add("manager");
    clientCreateRoleList.add("owner");

    if (roleValidator.isVisible(session, request, response, clientSectionRoleList)) {
%>
<div class="panel panel-primary">
    <div class="panel-body">
        <span class="glyphicon glyphicon-briefcase"></span> Client
        <div class="btn-group pull-right">
            <% if (roleValidator.isVisible(session, request, response, clientCreateRoleList)) { %>
            <button type="button" class="btn btn-success" id="create-client">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <% } %>
            <button type="button" class="btn btn-primary" id="search-client">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
        </div>
    </div>
</div>
<script>
    <% if (roleValidator.isVisible(session, request, response, clientCreateRoleList)) { %>
    $('#create-client').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/client/create";
    });
    <% } %>
    $('#search-client').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/client/search";
    });
</script>
<% } %>

<br/>
<%@ include file="../template/footer.jsp" %>