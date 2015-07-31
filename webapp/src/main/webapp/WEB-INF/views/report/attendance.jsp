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
                    <option value="3">Client Revenue Report</option>
                    <option value="4">Job Type wise Revenue Report</option>
                    <option value="5">Vehicle Mileage Report</option>
                    <option value="6">Expense Report</option>
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
<br/>
<h1>${displayName}</h1>
<br/>
<br/>
<div class="row" id="chart">

</div>

<br/>
<br/>

<div class="row">
    <div id="tableContainer">
        ${table}
    </div>
</div>

<script>

    $("#report").val("${report}");

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
                ['x', '2013-01-01', '2013-02-01', '2013-03-01', '2013-04-01', '2013-05-01', '2013-06-01'],
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 130, 340, 200, 500, 250, 350],
                ['data3', 400, 500, 450, 700, 600, 500]
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


</script>
<%@ include file="../template/footer.jsp" %>