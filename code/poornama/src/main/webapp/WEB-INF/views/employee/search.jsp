<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>
<div class="row">
    <div class="col-sm-6 col-md-6">
        <form class="navbar-form" role="search">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" id="search" name="search">

                <div class="input-group-btn">
                    <button class="btn btn-default" type="button" id="btnSearch" name="btnSearch">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row">
    <div id="tableContainer">
        ${table}
    </div>
</div>
<script>

    $("#btnSearch").click(function () {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/employee/search/' + document.getElementById('search').value,
            success: function (result) {
                document.getElementById("tableContainer").innerHTML = result;
            }
        });
    });

</script>
<%@ include file="../template/footer.jsp" %>