package com.poornama.test;

import com.poornama.api.objects.Expense;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TempMain {

	public static void main(String[] args) throws Exception{
		// For Temporary Test.
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
		Date startDate = format.parse("01-01-2014");
		Date endDate = format.parse("01-31-2016");

		Date tempDate = startDate;
		Calendar tempCalendar = Calendar.getInstance();

		while (tempDate.before(endDate)) {

			System.out.println("tempDate = " + tempDate);

			tempCalendar.setTime(tempDate);
			tempCalendar.add(Calendar.YEAR, 1);
			tempDate = tempCalendar.getTime();
		}
	}

}
