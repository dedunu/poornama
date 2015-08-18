package com.poornama.test;

import com.poornama.api.objects.Expense;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class TempMain {

	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static void main(String[] args) throws Exception{
		// For Temporary Test.
		System.out.println(String.format("%02d", 11));

		HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();


		tempHashMap.put("2015-01-01", 10);

		String str = "2015-01-01";
		System.out.println(tempHashMap.get(str));
	}

}
