<%@ include file="../template/header.jsp" %>
<style scoped>

</style>

<pre>
</pre>

<script>
    $(document).ready(function () {
        getLogs();
    });

    window.setInterval(function () {
        getLogs();
    }, 1000);

    function getLogs() {
        $.ajax({
            url: '${pageContext.request.contextPath}/system/logsAJAX',
            type: 'GET',
            cache: false,
            success: function (result) {
                $('pre').html(result);
            }
        });
        $("html, body").animate({scrollTop: $(document).height() - $(window).height()});
    }
</script>
<%@ include file="../template/footer.jsp" %>