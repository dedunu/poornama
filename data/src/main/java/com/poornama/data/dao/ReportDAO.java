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

    private HashMap<Integer, HashMap<String, Integer>> integerTable = new HashMap<Integer, HashMap<String, Integer>>();

    private HashMap<Integer, HashMap<String, Double>> doubleTable = new HashMap<Integer, HashMap<String, Double>>();

    public HashMap<Integer, HashMap<String, Integer>> getIntegerTable() {
        return integerTable;
    }

    public void setIntegerTable(HashMap<Integer, HashMap<String, Integer>> integerTable) {
        this.integerTable = integerTable;
    }

    public HashMap<Integer, HashMap<String, Double>> getDoubleTable() {
        return doubleTable;
    }

    public void setDoubleTable(HashMap<Integer, HashMap<String, Double>> doubleTable) {
        this.doubleTable = doubleTable;
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
                        queryString = queryString + "WHERE date >= \'" + simpleDateFormat.format(startDate) + "\' AND date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";

                        log.debug("[" + className + "] getMonthlyEmployeeAttendanceReport() :" + queryString);
                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Integer>> dataTable = new HashMap<Integer, HashMap<String, Integer>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Integer>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getInt(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setIntegerTable(dataTable);
                    }
                }
        );
        return getIntegerTable();
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
                        queryString = queryString + "WHERE date >= \'" + simpleDateFormat.format(startDate) + "\' AND date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date) ";

                        log.debug("[" + className + "] getAnnualEmployeeAttendanceReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Integer>> dataTable = new HashMap<Integer, HashMap<String, Integer>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Integer>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getInt(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setIntegerTable(dataTable);
                    }
                }
        );
        return getIntegerTable();
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
                        queryString = queryString + "WHERE date >= \'" + simpleDateFormat.format(startDate) + "\' AND date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";

                        log.debug("[" + className + "] getMonthlyEmployeeSalaryReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
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
                        queryString = queryString + "WHERE date >= \'" + simpleDateFormat.format(startDate) + "\' AND date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY employeeId, YEAR(date) ";

                        log.debug("[" + className + "] getAnnualEmployeeSalaryReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getMonthlyEmployeeRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "(SELECT e.id as id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges) ";
                        queryString = queryString + "FROM Job AS j  ";
                        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.driverId ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate), MONTH(j.startDate)) ";
                        queryString = queryString + "UNION ";
                        queryString = queryString + "(SELECT e.id as id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.hireCharges) ";
                        queryString = queryString + "FROM Job AS j  ";
                        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.cleanerId ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate), MONTH(j.startDate)) ";

                        log.debug("[" + className + "] getMonthlyEmployeeRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        log.debug("[" + className + "] getMonthlyEmployeeRevenueReport() : finished");
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getAnnualEmployeeRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "(SELECT e.id as id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges) ";
                        queryString = queryString + "FROM Job AS j  ";
                        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.driverId ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate)) ";
                        queryString = queryString + "UNION ";
                        queryString = queryString + "(SELECT e.id as id, YEAR(j.startDate), SUM(j.hireCharges) ";
                        queryString = queryString + "FROM Job AS j  ";
                        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.cleanerId ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate)) ";

                        log.debug("[" + className + "] getAnnualEmployeeRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        log.debug("[" + className + "] getAnnualEmployeeRevenueReport() : finished");
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getMonthlyClientRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT c.id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
                        queryString = queryString + "INNER JOIN Client AS c ON jt.clientId = c.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY c.id, YEAR(j.startDate), MONTH(j.startDate) ";
                        log.debug("[" + className + "] getMonthlyClientRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getAnnualClientRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT c.id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
                        queryString = queryString + "INNER JOIN Client AS c ON jt.clientId = c.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY c.id, YEAR(j.startDate) ";

                        log.debug("[" + className + "] getAnnualClientRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getMonthlyVehicleRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT v.id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate), MONTH(j.startDate) ";

                        log.debug("[" + className + "] getMonthlyVehicleRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }


    public HashMap<Integer, HashMap<String, Double>> getAnnualVehicleRevenueReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT v.id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate) ";


                        log.debug("[" + className + "] getAnnualVehicleRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getMonthlyVehicleMilageReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT v.id, YEAR(j.startDate), MONTH(j.startDate), SUM(jt.distance) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
                        queryString = queryString + "INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate), MONTH(j.startDate) ";
                        log.debug("[" + className + "] getMonthlyVehicleMilageReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getAnnualVehicleMileageReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT v.id, YEAR(j.startDate), SUM(jt.distance) ";
                        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
                        queryString = queryString + "INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
                        queryString = queryString + "WHERE j.startDate >= \'" + simpleDateFormat.format(startDate) + "\' AND j.startDate <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate) ";

                        log.debug("[" + className + "] getAnnualVehicleRevenueReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getMonthlyExpenseReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT t.id, YEAR(e.date), MONTH(e.date), SUM(e.amount) ";
                        queryString = queryString + "FROM Expense AS e INNER JOIN Expense_Tag AS et ON e.id = et.expenses_id ";
                        queryString = queryString + "INNER JOIN Tag AS t ON et.tags_id = t.id ";
                        queryString = queryString + "WHERE e.date >= \'" + simpleDateFormat.format(startDate) + "\' AND e.date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY t.id, YEAR(e.date), MONTH(e.date) ";
                        log.debug("[" + className + "] getMonthlyExpenseReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-" + String.format("%02d", resultSet.getInt(3)) + "-01", resultSet.getDouble(4));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }

    public HashMap<Integer, HashMap<String, Double>> getAnnualExpenseReport(final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        Statement statement = connection.createStatement();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        String queryString = "SELECT t.id, YEAR(e.date), SUM(e.amount) ";
                        queryString = queryString + "FROM Expense AS e INNER JOIN Expense_Tag AS et ON e.id = et.expenses_id ";
                        queryString = queryString + "INNER JOIN Tag AS t ON et.tags_id = t.id ";
                        queryString = queryString + "WHERE e.date >= \'" + simpleDateFormat.format(startDate) + "\' AND e.date <= \'" + simpleDateFormat.format(endDate) + "\' ";
                        queryString = queryString + "GROUP BY t.id, YEAR(e.date) ";

                        log.debug("[" + className + "] getAnnualExpenseReport() :" + queryString);

                        ResultSet resultSet = statement.executeQuery(queryString);

                        HashMap<Integer, HashMap<String, Double>> dataTable = new HashMap<Integer, HashMap<String, Double>>();

                        while (resultSet.next()) {
                            HashMap tempHashMap;
                            if (dataTable.get(resultSet.getInt(1)) == null) {
                                tempHashMap = new HashMap<String, Double>();
                            } else {
                                tempHashMap = dataTable.get(resultSet.getInt(1));
                            }
                            tempHashMap.put(resultSet.getString(2) + "-01-01", resultSet.getDouble(3));
                            dataTable.put(resultSet.getInt(1), tempHashMap);
                        }

                        setDoubleTable(dataTable);
                    }
                }
        );
        return getDoubleTable();
    }
}
