<%@ include file="../../template/headerWithNav.jsp" %>
<style scoped>

</style>
<div id="alertArea"></div>
<div class="week-picker"></div>
<br/>

<div class="row">
    <div id="tableContainer">
    </div>
</div>

<div class="div-hidden" id="buttonDiv">
    <button class="btn btn-primary" type="button" id="saveButton" name="saveButton">Save</button>
    <a href="${pageContext.request.contextPath}">
        <button class="btn btn-primary" type="button" id="cancelButton" name="saveButton">Cancel</button>
    </a>
</div>
<script>
    $(function () {
        var startDate;
        var endDate;

        var selectCurrentWeek = function () {
            window.setTimeout(function () {
                $('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
            }, 1);
        }

        $('.week-picker').datepicker({
            showOtherMonths: true,
            selectOtherMonths: true,
            onSelect: function (dateText, inst) {
                var date = $(this).datepicker('getDate');
                startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
                endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 6);
                var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
                var ajaxDate = (date.getMonth() + 1) + "_" + (date.getDate() - date.getDay()) + "_" + date.getFullYear();
                getTable(ajaxDate);
                document.getElementById("alertArea").innerHTML = "";
                $("#buttonDiv").removeClass("div-hidden");
                selectCurrentWeek();
            },
            beforeShowDay: function (date) {
                var cssClass = '';
                if (date >= startDate && date <= endDate)
                    cssClass = 'ui-datepicker-current-day';
                return [true, cssClass];
            },
            onChangeMonthYear: function (year, month, inst) {
                selectCurrentWeek();
            }
        });

        $('.week-picker .ui-datepicker-calendar tr').live('mousemove', function () {
            $(this).find('td a').addClass('ui-state-hover');
        });

        $('.week-picker .ui-datepicker-calendar tr').live('mouseleave', function () {
            $(this).find('td a').removeClass('ui-state-hover');
        });
    });

    function getTable(startDate) {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/attendance/search/' + startDate,
            success: function (result) {
                document.getElementById("tableContainer").innerHTML = result;
            }
        });
    }

    $("#saveButton").click(function () {
        var checkbox_value = "";
        $(":checkbox").each(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                checkbox_value += $(this).val() + "|";
            }
        });

        var date = $(".week-picker").datepicker('getDate');
        var ajaxDate = (date.getMonth() + 1) + "/" + (date.getDate() - date.getDay()) + "/" + date.getFullYear();

        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/attendance/save',
            data: {data: checkbox_value, date: ajaxDate},
            success: function (result) {
                if (result == "success") {
                    document.getElementById("alertArea").innerHTML = "<div class=\"alert alert-success\" role=\"alert\" id=\"successAlert\">Saved attendance successfully.</div>";
                } else {
                    document.getElementById("alertArea").innerHTML = "<div class=\"alert alert-danger\" role=\"alert\" id=\"errorAlert\">Something went wrong please try again.</div>";
                }
            }
        }).fail(function () {
            document.getElementById("alertArea").innerHTML = "<div class=\"alert alert-danger\" role=\"alert\" id=\"errorAlert\">Something went wrong please try again.</div>";
        });
    });


</script>
<%@ include file="../../template/footer.jsp" %>