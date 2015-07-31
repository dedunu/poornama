package com.poornama.logic.reporting;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dedunu on 7/31/15.
 */
@Service
public class EmployeeAttendanceReportingLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceReportingLogic.class.getName();

    public void aggregate(String startDateString, String endDateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date startDate = new Date();
        Date endDate = new Date();

        try {
            startDate = simpleDateFormat.parse(startDateString);
        } catch (ParseException e) {
            log.error("[" + className + "] aggregate: Error in parsing startDate");
        }

        try {
            endDate = simpleDateFormat.parse(endDateString);
        } catch (ParseException e) {
            log.error("[" + className + "] aggregate: Error in parsing endDate");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        Date firstDay = calendar.getTime();

        calendar.setTime(endDate);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date lastDay = calendar.getTime();
    }
}
