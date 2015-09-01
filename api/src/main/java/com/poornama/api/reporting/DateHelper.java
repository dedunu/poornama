package com.poornama.api.reporting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public Date getDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date result;
        try {
            result = simpleDateFormat.parse(dateString);
        } catch (ParseException e){
            return null;
        }
        return result;
    }

    public Date getStartDateMonthly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    public Date getEndDateMonthly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public Date getStartDateAnnually(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
        return calendar.getTime();
    }

    public Date getEndDateAnnually(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        return calendar.getTime();
    }
}

