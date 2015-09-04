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
<div class="alert alert-info" role="alert">${message}</div>
<a href="${pageContext.request.contextPath}">
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-home"></span> Home
    </button>
</a>
<%@ include file="../template/footer.jsp" %>