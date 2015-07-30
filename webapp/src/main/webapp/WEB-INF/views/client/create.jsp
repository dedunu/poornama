<%@ include file="../template/headerWithNav.jsp" %>
<%
  RoleValidator roleValidator = new RoleValidator();
  List<String> roles = new ArrayList<String>();
  roles.add("admin");
  roles.add("manager");
  //roles.add("accountant");
  //roles.add("clerk");
  roleValidator.validate(session, request, response, roles);
%>

<style scoped>

</style>

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/client/create">
  <div class="form-group">
    <label for="organizationName" class="col-sm-3 control-label">Organization Name</label>

    <div class="col-sm-6">
      <input type="text" class="form-control" id="organizationName" name="organizationName" placeholder="">
    </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-3 control-label">Address</label>

    <div class="col-sm-9">
      <textarea class="form-control" id="address" name="address" rows="3" placeholder=""></textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="telephone" class="col-sm-3 control-label">Telephone Number</label>

    <div class="col-sm-3">
      <input type="text" class="form-control" id="telephone" name="telephone" placeholder="">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-10">
      <button type="submit" class="btn btn-success">Create</button>
      <a href="${pageContext.request.contextPath}">
        <button type="button" class="btn btn-default">Cancel</button>
      </a>
    </div>
  </div>
</form>

<script>

</script>
<%@ include file="../template/footer.jsp" %>