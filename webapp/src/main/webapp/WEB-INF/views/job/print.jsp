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
        <p>Address :<spring:message code="web.company.address" /><br/>
        Telephone : +94-112-488201<br/>
        Mobile : +94-777-313289<br/>
        Email: poornamatransportservice@gmail.com
        </p>
    </div>
    <div class="col-md-6">
        <p>Date : ${date}<br/>
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