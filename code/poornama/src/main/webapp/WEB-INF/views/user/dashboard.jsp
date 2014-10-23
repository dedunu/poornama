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
        window.location.href = "${pageContext.request.contextPath}/employee/edit";
    })
    $('#delete-employee').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/employee/delete";
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
        window.location.href = "${pageContext.request.contextPath}/vehicle/edit";
    })
    $('#delete-vehicle').on('click', function (e) {
        window.location.href = "${pageContext.request.contextPath}/vehicle/delete";
    })
</script>
<br/>
<%@ include file="../template/footer.jsp" %>