package com.app.remote.domain.utils;

import com.app.remote.domain.constants.Constants;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class DateTimeUtils {

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
