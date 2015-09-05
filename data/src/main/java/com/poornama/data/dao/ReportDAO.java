package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author dedunu
 */
public class ReportDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ReportDAO.class.getName();

    public ReportDAO() {
        log.debug("[" + className + "] ReportDAO: constructor()");
    }

    private HashMap<Integer, HashMap<String, Integer>> integerTable = new HashMap<Integer, HashMap<String, Integer>>();

    private HashMap<Integer, HashMap<String, Double>> doubleTable = new HashMap<Integer, HashMap<String, Double>>();

    /**
     * Returns the integerTable object
     *
     * @return IntegerTable for reports
     */
    public HashMap<Integer, HashMap<String, Integer>> getIntegerTable() {
        return integerTable;
    }

    /**
     * Sets the integerTable object
     *
     * @param integerTable HashMap&lt;Integer, HashMap&lt;String, Integer&gt;&gt;
     */
    public void setIntegerTable(HashMap<Integer, HashMap<String, Integer>> integerTable) {
        this.integerTable = integerTable;
    }

    /**
     * Returns the doubleTable object
     *
     * @return DoubleTable for reports
     */
    public HashMap<Integer, HashMap<String, Double>> getDoubleTable() {
        return doubleTable;
    }

    /**
     * Sets the doubleTable object
     *
     * @param doubleTable HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public void setDoubleTable(HashMap<Integer, HashMap<String, Double>> doubleTable) {
        this.doubleTable = doubleTable;
    }

    /**
     * Generic method to generate monthly integer reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Integer&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Integer>> getMonthlyIntegerReport(final String queryString, final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        preparedStatement.setDate(1, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                        preparedStatement.setDate(2, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));

                        log.debug("[" + className + "] getMonthlyIntegerReport() :" + queryString);
                        ResultSet resultSet = preparedStatement.executeQuery(queryString);

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

    /**
     * Generic method to generate monthly double reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @param repeatDate  boolean
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyDoubleReport(final String queryString, final Date startDate, final Date endDate, final boolean repeatDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        preparedStatement.setDate(1, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                        preparedStatement.setDate(2, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));

                        if (repeatDate) {
                            preparedStatement.setDate(3, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                            preparedStatement.setDate(4, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));
                        }

                        log.debug("[" + className + "] getMonthlyDoubleReport() :" + queryString);

                        ResultSet resultSet = preparedStatement.executeQuery(queryString);

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

    /**
     * Generic method to generate monthly double reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyDoubleReport(final String queryString, final Date startDate, final Date endDate) {
        return getMonthlyDoubleReport(queryString, startDate, endDate, false);
    }

    /**
     * Generic method to generate annual integer reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Integer&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Integer>> getAnnualIntegerReport(final String queryString, final Date startDate, final Date endDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        preparedStatement.setDate(1, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                        preparedStatement.setDate(2, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));

                        log.debug("[" + className + "] getAnnualIntegerReport() :" + queryString);
                        ResultSet resultSet = preparedStatement.executeQuery(queryString);

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

    /**
     * Generic method to generate annual double reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @param repeatDate  boolean
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualDoubleReport(final String queryString, final Date startDate, final Date endDate, final boolean repeatDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        Session session = databaseSession.getSession();
        session.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        preparedStatement.setDate(1, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                        preparedStatement.setDate(2, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));

                        if (repeatDate) {
                            preparedStatement.setDate(3, java.sql.Date.valueOf(simpleDateFormat.format(startDate)));
                            preparedStatement.setDate(4, java.sql.Date.valueOf(simpleDateFormat.format(endDate)));
                        }

                        log.debug("[" + className + "] getMonthlyDoubleReport() :" + queryString);

                        ResultSet resultSet = preparedStatement.executeQuery(queryString);

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

    /**
     * Generic method to generate annual double reports
     *
     * @param queryString String
     * @param startDate   Date
     * @param endDate     Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualDoubleReport(final String queryString, final Date startDate, final Date endDate) {
        return getAnnualDoubleReport(queryString, startDate, endDate, false);
    }

    /**
     * Returns data table for the monthly employee attendance report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Integer&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Integer>> getMonthlyEmployeeAttendanceReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT employeeId, YEAR(date), MONTH(date), SUM(attendance) ";
        queryString = queryString + "FROM EmployeeAttendance ";
        queryString = queryString + "WHERE date >= ? AND date <= ? ";
        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";
        return getMonthlyIntegerReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual employee attendance report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Integer&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Integer>> getAnnualEmployeeAttendanceReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT employeeId, YEAR(date), SUM(attendance) ";
        queryString = queryString + "FROM EmployeeAttendance ";
        queryString = queryString + "WHERE date >= ? AND date <= ? ";
        queryString = queryString + "GROUP BY employeeId, YEAR(date) ";
        return getAnnualIntegerReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the monthly employee salary report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyEmployeeSalaryReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT employeeId, YEAR(date), MONTH(date), SUM(netSalary) ";
        queryString = queryString + "FROM Salary ";
        queryString = queryString + "WHERE date >= ? AND date <= ? ";
        queryString = queryString + "GROUP BY employeeId, YEAR(date), MONTH(date) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual employee salary report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualEmployeeSalaryReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT employeeId, YEAR(date), SUM(netSalary) ";
        queryString = queryString + "FROM Salary ";
        queryString = queryString + "WHERE date >= ? AND date <= ? ";
        queryString = queryString + "GROUP BY employeeId, YEAR(date) ";
        return getAnnualDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the monthly employee revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyEmployeeRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "(SELECT e.id as id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges) ";
        queryString = queryString + "FROM Job AS j  ";
        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.driverId ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate), MONTH(j.startDate)) ";
        queryString = queryString + "UNION ";
        queryString = queryString + "(SELECT e.id as id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.hireCharges) ";
        queryString = queryString + "FROM Job AS j  ";
        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.cleanerId ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ?";
        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate), MONTH(j.startDate)) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate, true);
    }

    /**
     * Returns data table for the annual employee revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualEmployeeRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "(SELECT e.id as id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges) ";
        queryString = queryString + "FROM Job AS j  ";
        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.driverId ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate)) ";
        queryString = queryString + "UNION ";
        queryString = queryString + "(SELECT e.id as id, YEAR(j.startDate), SUM(j.hireCharges) ";
        queryString = queryString + "FROM Job AS j  ";
        queryString = queryString + "INNER JOIN Employee AS e ON e.id = j.cleanerId ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY e.id, YEAR(j.startDate)) ";
        return getAnnualDoubleReport(queryString, startDate, endDate, true);
    }

    /**
     * Returns data table for the monthly client revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyClientRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT c.id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
        queryString = queryString + "INNER JOIN Client AS c ON jt.clientId = c.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY c.id, YEAR(j.startDate), MONTH(j.startDate) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual client revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualClientRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT c.id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
        queryString = queryString + "INNER JOIN Client AS c ON jt.clientId = c.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY c.id, YEAR(j.startDate) ";
        return getAnnualDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the monthly vehicle revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyVehicleRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT v.id, YEAR(j.startDate), MONTH(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
        queryString = queryString + "FROM Job AS j INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate), MONTH(j.startDate) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual vehicle revenue report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualVehicleRevenueReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT v.id, YEAR(j.startDate), SUM(j.containerCharges + j.detentionCharges + j.hireCharges + j.labourCharges) ";
        queryString = queryString + "FROM Job AS j INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate) ";
        return getAnnualDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the monthly vehicle mileage report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyVehicleMileageReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT v.id, YEAR(j.startDate), MONTH(j.startDate), SUM(jt.distance) ";
        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
        queryString = queryString + "INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate), MONTH(j.startDate) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual vehicle mileage report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualVehicleMileageReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT v.id, YEAR(j.startDate), SUM(jt.distance) ";
        queryString = queryString + "FROM Job AS j INNER JOIN JobTemplate AS jt ON j.jobTemplateId = jt.id ";
        queryString = queryString + "INNER JOIN Vehicle AS v ON j.vehicleId = v.id ";
        queryString = queryString + "WHERE j.startDate >= ? AND j.startDate <= ? ";
        queryString = queryString + "GROUP BY v.id, YEAR(j.startDate) ";
        return getAnnualDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the monthly expense report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getMonthlyExpenseReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT t.id, YEAR(e.date), MONTH(e.date), SUM(e.amount) ";
        queryString = queryString + "FROM Expense AS e INNER JOIN Expense_Tag AS et ON e.id = et.expenses_id ";
        queryString = queryString + "INNER JOIN Tag AS t ON et.tags_id = t.id ";
        queryString = queryString + "WHERE e.date >= ? AND e.date <= ? ";
        queryString = queryString + "GROUP BY t.id, YEAR(e.date), MONTH(e.date) ";
        return getMonthlyDoubleReport(queryString, startDate, endDate);
    }

    /**
     * Returns data table for the annual expense report
     *
     * @param startDate Date
     * @param endDate   Date
     * @return HashMap&lt;Integer, HashMap&lt;String, Double&gt;&gt;
     */
    public HashMap<Integer, HashMap<String, Double>> getAnnualExpenseReport(final Date startDate, final Date endDate) {
        String queryString = "SELECT t.id, YEAR(e.date), SUM(e.amount) ";
        queryString = queryString + "FROM Expense AS e INNER JOIN Expense_Tag AS et ON e.id = et.expenses_id ";
        queryString = queryString + "INNER JOIN Tag AS t ON et.tags_id = t.id ";
        queryString = queryString + "WHERE e.date >= ? AND e.date <= ? ";
        queryString = queryString + "GROUP BY t.id, YEAR(e.date) ";
        return getAnnualDoubleReport(queryString, startDate, endDate);
    }
}
