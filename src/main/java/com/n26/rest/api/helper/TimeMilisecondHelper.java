package com.n26.rest.api.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeMilisecondHelper {
	public static String fromMilisecondsToDate() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		// Get date and time information in milliseconds
		long now = System.currentTimeMillis();
		// Create a calendar object that will convert the date and time value
		// in milliseconds to date. We use the setTimeInMillis() method of the
		// Calendar object.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		return formatter.format(calendar.getTime());
	}
}