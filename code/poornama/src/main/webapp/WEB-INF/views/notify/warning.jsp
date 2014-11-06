<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>
<div class="alert alert-warning" role="alert">${message}</div>
<a href="${pageContext.request.contextPath}">
    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> Home</button>
</a>
<%@ include file="../template/footer.jsp" %>