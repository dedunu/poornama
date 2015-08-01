package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by dedunu on 8/1/15.
 */
public class ReportDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ReportDAO.class.getName();

    public ReportDAO() {
        log.debug("[" + className + "] ReportDAO: constructor()");
    }

    private HashMap<Integer, HashMap<String, Integer>> employeeAttendanceTable = new HashMap<Integer, HashMap<String, Integer>>();

    private HashMap<Integer, HashMap<String, Double>> employeeSalaryTable = new HashMap<Integer, HashMap<String, Double>>();

    protected HashMap<Integer, HashMap<String, Integer>> getEmployeeAttendanceTable() {
        return employeeAttendanceTable;
    }

    protected void setEmployeeAttendanceTable(HashMap<Integer, HashMap<String, Integer>> employeeAttendanceTable) {
        this.employeeAttendanceTable = employeeAttendanceTable;
    }

    protected void setEmployeeSalaryTable(HashMap<Integer, HashMap<String, Double>> employeeSalaryTable) {
        this.employeeSalaryTable = employeeSalaryTable;
    }

    protected HashMap<Integer, HashMap<String, Double>> getEmployeeSalaryTable() {
        return employeeSalaryTable;
    }

    public HashMap<Integer, HashMap<String, Integer>> getMonthlyEmployeeAttendanceReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT employeeId, YEAR(date), MONTH(date), SUM(attendance) ";
                        queryString = queryString + "FROM EmployeeAttendance ";
                        queryString = queryString + "WHERE date > \'" + simpleDateFormat.format(startDate) + "\' AND date < \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";
                        queryString = queryString + "ORDER BY 4 DESC";

                        log.debug("[" + className + "] getMonthlyEmployeeAttendanceReport() :" + queryString);
                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Integer>> dataTable = new HashMap<Integer, HashMap<String, Integer>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap = new HashMap<String, Integer>();
                            tempHashMap.put(resultSet.getString(1) + "-" + resultSet.getString(2) + "-01", resultSet.getInt(3));
                            dataTable.put(resultSet.getInt(0), tempHashMap);
                        }

                        setEmployeeAttendanceTable(dataTable);
                    }
                }
        );
        return getEmployeeAttendanceTable();
    }

    public HashMap<Integer, HashMap<String, Integer>> getAnnualEmployeeAttendanceReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT employeeId, YEAR(date), SUM(attendance) ";
                        queryString = queryString + "FROM EmployeeAttendance ";
                        queryString = queryString + "WHERE date > \'" + simpleDateFormat.format(startDate) + "\' AND date < \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date) ";
                        queryString = queryString + "ORDER BY 4 DESC";

                        log.debug("[" + className + "] getAnnualEmployeeAttendanceReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Integer>> dataTable = new HashMap<Integer, HashMap<String, Integer>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap = new HashMap<String, Integer>();
                            tempHashMap.put(resultSet.getString(1) + "-01-01", resultSet.getInt(2));
                            dataTable.put(resultSet.getInt(0), tempHashMap);
                        }

                        setEmployeeAttendanceTable(dataTable);
                    }
                }
        );
        return getEmployeeAttendanceTable();
    }


    public HashMap<Integer, HashMap<String, Double>> getMonthlyEmployeeSalaryReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT employeeId, YEAR(date), MONTH(date), SUM(netSalary) ";
                        queryString = queryString + "FROM Salary ";
                        queryString = queryString + "WHERE date > \'" + simpleDateFormat.format(startDate) + "\' AND date < \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";
                        queryString = queryString + "ORDER BY 4 DESC";

                        log.debug("[" + className + "] getMonthlyEmployeeSalaryReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap = new HashMap<String, Double>();
                            tempHashMap.put(resultSet.getString(1) + "-" + resultSet.getString(2) + "-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(0), tempHashMap);
                        }

                        setEmployeeSalaryTable(dataTable);
                    }
                }
        );
        return getEmployeeSalaryTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getAnnualEmployeeSalaryReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT employeeId, YEAR(date), SUM(netSalary) ";
                        queryString = queryString + "FROM Salary ";
                        queryString = queryString + "WHERE date > \'" + simpleDateFormat.format(startDate) + "\' AND date < \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date), ";
                        queryString = queryString + "ORDER BY 4 DESC";

                        log.debug("[" + className + "] getMonthlyEmployeeSalaryReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap = new HashMap<String, Double>();
                            tempHashMap.put(resultSet.getString(1) + "-01-01", resultSet.getDouble(2));
                            dataTable.put(resultSet.getInt(0), tempHashMap);
                        }

                        setEmployeeSalaryTable(dataTable);
                    }
                }
        );
        return getEmployeeSalaryTable();
    }

}
