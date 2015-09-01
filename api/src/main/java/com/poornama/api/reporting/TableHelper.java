package com.poornama.api.reporting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dedunu on 9/1/15.
 */
public class TableHelper {

    protected String getAxis(Date startDate, Date endDate, int calendarField) {
        String result = "[ \'x\',";

        List<String> stringList = getAxisList(startDate, endDate, calendarField);
        for (String dateString : stringList) {
            result = result + " \'" + dateString + "\' ,";
        }

        if (result.length() > 0 && result.charAt(result.length() - 1) == ',') {
            result = result.substring(0, result.length() - 1);
        }

        result = result + " ],";
        return result;
    }

    protected List<String> getAxisList(Date startDate, Date endDate, int calendarField) {
        List<String> stringList = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = startDate;
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(tempDate);

        while (tempDate.before(endDate)) {
            stringList.add(simpleDateFormat.format(tempDate));
            tempCalendar.add(calendarField, 1);
            tempDate = tempCalendar.getTime();
        }

        return stringList;
    }

}
