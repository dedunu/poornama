<%@ include file="../template/header.jsp" %>
<style scoped>

</style>
<form role="form" action="${pageContext.request.contextPath}/session/login" method="post">
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModal"
         aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body">
                    <h3 class="modal-title" id="myModalLabel" style="text-align: center;">Login</h3>
                    <br/>

                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input type="text" class="form-control" name="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary pull-right" style="right: 0px;">Login</button>
                    <br/>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    $(document).ready(function () {
        $('#loginModal').modal('show');
    });

</script>
<%@ include file="../template/footer.jsp" %>