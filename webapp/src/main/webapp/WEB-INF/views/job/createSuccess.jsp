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
<div class="alert alert-success" role="alert">${message}</div>
<a href="${pageContext.request.contextPath}/job/print/${jobId}">
    <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> <spring:message code="web.button.print" /></button>
</a>
<a href="${pageContext.request.contextPath}">
    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> <spring:message code="web.button.home" /></button>
</a>
<%@ include file="../template/footer.jsp" %>