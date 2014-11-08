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

<div class="row">
    <div id="tableContainer">
        ${table}
    </div>
</div>

<script>

</script>
<%@ include file="../template/footer.jsp" %>