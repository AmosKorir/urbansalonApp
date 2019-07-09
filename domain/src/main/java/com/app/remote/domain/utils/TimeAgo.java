package com.app.remote.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Korir on 6/30/19.
 * amoskrr@gmail.com
 */
public class TimeAgo {

  public static String covertTimeToText(String dataDate) {

    String convTime = null;

    String prefix = "";
    String suffix = "Ago";

    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date pasTime = dateFormat.parse(dataDate);

      Date nowTime = new Date();

      long dateDiff = nowTime.getTime() - pasTime.getTime();

      long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
      long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
      long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
      long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

      if (second < 60) {
        convTime = second+" Seconds "+suffix;
      } else if (minute < 60) {
        convTime = minute+" Minutes "+suffix;
      } else if (hour < 24) {
        convTime = hour+" Hours "+suffix;
      } else if (day >= 7) {
        if (day > 30) {
          convTime = (day / 30)+" Months "+suffix;
        } else if (day > 360) {
          convTime = (day / 360)+" Years "+suffix;
        } else {
          convTime = (day / 7) + " Week "+suffix;
        }
      } else if (day < 7) {
        convTime = day+" Days "+suffix;
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }

    return convTime;

  }

}
