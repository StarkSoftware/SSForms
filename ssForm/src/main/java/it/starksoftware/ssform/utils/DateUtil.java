package it.starksoftware.ssform.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {

    public static DateFormat getMonthNameDateFormat(){
        return new SimpleDateFormat("MMMM, yy", Locale.getDefault());
    }

    public static DateFormat getMonthDayDateFormat(){
        return new SimpleDateFormat("MMM dd", Locale.getDefault());
    }

}