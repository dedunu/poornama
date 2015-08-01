package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Client;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Tag;
import com.poornama.api.objects.Vehicle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dedunu on 7/29/15.
 */
@Service
public class ReportLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ReportLogic.class.getName();

    private String getChartColumns(HashMap<Integer, HashMap<String, Integer>> dataTable, Date startDate, Date endDate, List<Object> objectList, int calendarField) {
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Object object : objectList) {
            result = result + "[ ";

            HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();

            if (object instanceof Employee) {
                int id = ((Employee) object).getId();
                tempHashMap = dataTable.get(id);
                result = result + "\'" + ((Employee) object).getFirstName() + " " + ((Employee) object).getLastName() + "\',";
            }

            if (object instanceof Client) {
                int id = ((Client) object).getId();
                tempHashMap = dataTable.get(id);
                result = result + "\'" +  ((Client) object).getOrganizationName() + "\',";
            }

            if (object instanceof Vehicle) {
                int id = ((Vehicle) object).getId();
                tempHashMap = dataTable.get(id);
                result = result + "\'" +  ((Vehicle) object).getVehicleNumber() + "\',";
            }

            if (object instanceof Tag) {
                int id = ((Tag) object).getId();
                tempHashMap = dataTable.get(id);
                result = result + "\'" +  ((Tag) object).getDisplayName() + "\',";
            }

            Date tempDate = startDate;
            Calendar tempCalendar = Calendar.getInstance();

            while (tempDate.before(endDate)) {

                if (tempHashMap.get(simpleDateFormat.format(startDate)) != null) {
                    result = result + tempHashMap.get(simpleDateFormat.format(startDate)) + ",";
                } else {
                    result = result + "0,";
                }

                tempCalendar.setTime(tempDate);
                tempCalendar.add(calendarField, 1);
                tempDate = tempCalendar.getTime();
            }

            if (result.length() > 0 && result.charAt(result.length() - 1) == ',') {
                result = result.substring(0, result.length() - 1);
            }

            result = result + " ],";

        }

        if (result.length() > 0 && result.charAt(result.length() - 1) == ',') {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    private String getAxis(Date startDate, Date endDate, int calendarField) {
        String result = "[ \'x\',";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = startDate;
        Calendar tempCalendar = Calendar.getInstance();

        while (tempDate.before(endDate)) {
            result = result + " \'" + simpleDateFormat.format(startDate) + "\' ,";
            tempCalendar.setTime(tempDate);
            tempCalendar.add(calendarField, 1);
            tempDate = tempCalendar.getTime();
        }

        if (result.length() > 0 && result.charAt(result.length() - 1) == ',') {
            result = result.substring(0, result.length() - 1);
        }

        result = result + " ],";
        return result;
    }

    public Date getDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        return simpleDateFormat.parse(dateString);
    }

    public Date getStartDateMonthy(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    public Date getEndDateMonthy(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public Date getStartDateAnually(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
        return calendar.getTime();
    }

    public Date getEndDateAnually(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
        return calendar.getTime();
    }


}
