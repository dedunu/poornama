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

<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/report/index">
    <div class="form-group">
        <label for="report" class="col-sm-3 control-label">Report</label>

        <div class="col-sm-6">
            <div class="input-group">
                <select class="form-control" id="report" name="report">
                    <option value="1">Employee Attendance Report</option>
                    <option value="2">Employee Salary Report</option>
                    <option value="3">Employee wise Revenue Report</option>
                    <option value="4">Client wise Revenue Report</option>
                    <option value="5">Vehicle wise Revenue Report</option>
                    <option value="6">Vehicle Mileage Report</option>
                    <option value="7">Expense Report</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="frequency" class="col-sm-3 control-label">Frequency</label>

        <div class="col-sm-6">
            <div class="input-group">
                <select class="form-control" id="frequency" name="frequency">
                    <option value="1">Annually</option>
                    <option value="2">Monthly</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="startDate" class="col-sm-3 control-label">From</label>

        <div class="col-sm-3">
            <div class='input-group date' id="startDateInputGroup">
                <input type='text' class="form-control" id="startDate" name="startDate" value="${startDate}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="endDate" class="col-sm-3 control-label">To</label>

        <div class="col-sm-3">
            <div class='input-group date' id="endDateInputGroup">
                <input type='text' class="form-control" id="endDate" name="endDate" value="${endDate}"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-10">.
            <button type="submit" class="btn btn-success">Generate</button>
            <a href="${pageContext.request.contextPath}">
                <button type="button" class="btn btn-default">Home</button>
            </a>
        </div>
    </div>
</form>

<br/>

<h1>${displayName}</h1>
<br/>

<div class="row" id="pieChart">

</div>

<br/>
<div class="row" id="chart">

</div>

<br/>

<div class="row">
    <div id="tableContainer">
        ${tableText}
    </div>
</div>

<script>

    $("#report").val("${report}");

    $("#frequency").val("${frequency}");

    $(function () {
        $('#startDateInputGroup').datetimepicker({
            viewMode: 'months',
            format: 'MM/YYYY'
        });

        $('#endDateInputGroup').datetimepicker({
            viewMode: 'months',
            format: 'MM/YYYY'
        });

        $("#startDateInputGroup").on("dp.change", function (e) {
            $('#endDateInputGroup').data("DateTimePicker").minDate(e.date);
        });

        $("#endDateInputGroup").on("dp.change", function (e) {
            $('#startDateInputGroup').data("DateTimePicker").maxDate(e.date);
        });
    });

    var chart = c3.generate({
        bindto: '#chart',
        data: {
            x: 'x',
            columns: [
                ${chartText}
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%Y-%m-%d'
                }
            }
        }
    });

    var chart = c3.generate({
        bindto: '#pieChart',
        data: {
            columns: [
                ${pieChartText}
            ],
            type : 'pie',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        }
    });


</script>
<%@ include file="../template/footer.jsp" %>