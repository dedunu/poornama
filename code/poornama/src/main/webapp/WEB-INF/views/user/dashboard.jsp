<%@ include file="../template/headerWithNav.jsp" %>
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
                <span class="glyphicon glyphicon-search"></span>
            </button>
            <button type="button" class="btn btn-primary" id="edit-employee">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
            <button type="button" class="btn btn-danger" id="delete-employee">
                <span class="glyphicon glyphicon-minus"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/create";
    })
    $('#search-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/search";
    })
    $('#edit-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/search";
    })
    $('#delete-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/search";
    })
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
    })
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-truck"></i> Vehicle
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-vehicle">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-vehicle">
                <span class="glyphicon glyphicon-search"></span>
            </button>
            <button type="button" class="btn btn-primary" id="edit-vehicle">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
            <button type="button" class="btn btn-danger" id="delete-vehicle">
                <span class="glyphicon glyphicon-minus"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/create";
    })
    $('#search-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/search";
    })
    $('#edit-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/search";
    })
    $('#delete-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/search";
    })
</script>

<div class="panel panel-primary">
    <div class="panel-body">
        <i class="fa fa-user"></i> User
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-success" id="create-user">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <button type="button" class="btn btn-primary" id="search-user">
                <span class="glyphicon glyphicon-search"></span>
            </button>
            <button type="button" class="btn btn-primary" id="edit-user">
                <span class="glyphicon glyphicon-pencil"></span>
            </button>
            <button type="button" class="btn btn-danger" id="delete-user">
                <span class="glyphicon glyphicon-minus"></span>
            </button>
        </div>
    </div>
</div>
<script>
    $('#create-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/create";
    })
    $('#search-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/search";
    })
    $('#edit-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/search";
    })
    $('#delete-user').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/user/search";
    })
</script>
<br/>
<%@ include file="../template/footer.jsp" %>