package com.poornama.test;

import java.util.Calendar;
import java.util.Date;

public class TempMain {

	public static void main(String[] args) {
		// For Temporary Test.

		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date lastDay = calendar.getTime();

		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date firstDay = calendar.getTime();

		System.out.println("date = " + date);
		System.out.println("firstDay = " + firstDay);
		System.out.println("lastDay = " + lastDay);

		for (int i = 0; i < 10; i++) {

			Date tempDate = firstDay;
			Calendar tempCalendar = Calendar.getInstance();

			while (tempDate.compareTo(lastDay) != 0) {
				System.out.println("tempDate = " + tempDate);
				tempCalendar.setTime(tempDate);
				tempCalendar.add(Calendar.DATE, 1);
				tempDate = tempCalendar.getTime();
			}
		}
	}

}
