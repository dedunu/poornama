package com.poornama.api.reporting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dedunu on 8/2/15.
 */
public class DateHelper {

    public Date getDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date result = new Date();
        try {
            result = simpleDateFormat.parse(dateString);
        } catch (ParseException e){
            return null;
        }
        return result;
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

