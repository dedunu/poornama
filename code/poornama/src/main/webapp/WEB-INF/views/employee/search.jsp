<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>
<div class="row">
    <div>
        <form class="navbar-form" role="search">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" id="searchText" name="searchText">

                <div class="input-group-btn">
                    <button class="btn btn-default" type="button" id="searchButton" name="searchButton">
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

    $("#searchText").keyup(function() {
        searchEmployee();
    });

    $("#searchButton").click(function () {
        searchEmployee();
    });

    function searchEmployee() {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/employee/search/' + document.getElementById('searchText').value,
            success: function (result) {
                document.getElementById("tableContainer").innerHTML = result;
            }
        });
    }

</script>

<%@ include file="../template/footer.jsp" %>
