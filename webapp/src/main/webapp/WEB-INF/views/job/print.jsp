<%@ include file="../template/header.jsp" %>
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

<h1><spring:message code="web.company.name" /></h1>
<div class="row">
    <div class="col-md-6">
        <p><spring:message code="web.general.address" /> : <spring:message code="web.company.address" /><br/>
            <spring:message code="web.general.telephone" /> : <spring:message code="web.company.telephone" /><br/>
            <spring:message code="web.general.mobile" /> : <spring:message code="web.company.mobile" /><br/>
            <spring:message code="web.general.email" />: <spring:message code="web.company.email" />
        </p>
    </div>
    <div class="col-md-6">
        <p><spring:message code="web.general.date" /> : ${date}<br/>
            <b>Invoice Number : ${jobId}</b><br/>
            <b>Invoice To:</b><br/>
        ${client}</p>
    </div>
</div>

${table}

<script>
    $(document).ready(function() {
        window.print();
    });
</script>
<p><b><spring:message code="web.job.invoice.message" /></b></p>

<%@ include file="../template/footer.jsp" %>