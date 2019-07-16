package com.app.remote.domain.utils;

import com.app.remote.domain.constants.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class DateTimeUtils {
  public static String getDate() {
    return new DateTime().toString("dd-MMM-yyyy");
  }

  public static String getPastDate(int count) {
    DateTime today = new DateTime();
    return today.minusDays(count).toString("YYYY-MM-dd HH");
  }

  public static String formatDate(String date) {

    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
    try {
      Date inputDate = inputFormat.parse(date);
      String formattedDate = outputFormat.format(inputDate);
      return formattedDate;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return Constants.EMPTY_STRING;
  }

  public static long getTimeStamp(String date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date1;
    try {
      date1 = simpleDateFormat.parse(date);
      return date1.getTime() - 15;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return Long.parseLong(null);
  }

  public static String getElapsedTime(long timestamp) {
    DateTime now = new DateTime();
    DateTime posted = new DateTime(timestamp * 1000);

    Interval interval = new Interval(posted, now);
    Period period = interval.toPeriod();

    int years, months, weeks, days, hours, minutes, seconds;

    years = period.getYears();
    months = period.getMonths();
    weeks = period.getWeeks();
    days = period.getDays();
    hours = period.getHours();
    minutes = period.getMinutes();
    seconds = period.getSeconds();

    if (years > 0) {
      return String.format("%s%s", years, Constants.YEARS);
    } else if (months > 0) {
      return String.format("%s%s", months, Constants.MONTHS);
    } else if (weeks > 0) {
      return String.format("%s%s", weeks, Constants.WEEKS);
    } else if (days > 0) {
      return String.format("%s%s", days, Constants.DAYS);
    } else if (hours > 0) {
      return String.format("%s%s", hours, Constants.HOURS);
    } else if (minutes > 0) {
      return String.format("%s%s", minutes, Constants.MINUTES);
    } else {
      return String.format("%s%s", seconds, Constants.SECONDS);
    }
  }
}
