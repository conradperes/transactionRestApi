package com.n26.rest.api.helper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDifferentHelper {

	public static void main(String[] args) {
		String dateStart = "2018-12-29T15:30:59.312Z";
		//String dateStop = "2018-12-30T14:48:00.312Z";
		System.out.println(differenceBeteweenDates(dateStart));

	}

	public static boolean differenceBeteweenDates(String dateStart) {

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(TimeMilisecondHelper.fromMilisecondsToDate());

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
			if(		diffDays == 0 		&&
					diffMinutes == 0 	&&
					diffHours == 0  	&&
					diffSeconds <= 60		)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}