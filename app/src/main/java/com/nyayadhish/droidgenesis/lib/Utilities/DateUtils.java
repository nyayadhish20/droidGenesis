package com.nyayadhish.droidgenesis.lib.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getDate(String actualDate) {
        try {
            return new SimpleDateFormat("E dd-MM-yyyy").parse(actualDate).getDate();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getInFormetNeeded(String actualDate) {
        try {
            Date completeDate = new SimpleDateFormat("E").parse(actualDate);
            return (completeDate.getYear() + 1900) + "-" + (completeDate.getMonth() + 1) + "-" + completeDate.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String getDateForBookingApppointment(String actualDate) {
        try {
            Date completeDate = new SimpleDateFormat("E dd-MM-yyyy").parse(actualDate);
            return (completeDate.getYear() + 1900) + "-" + (completeDate.getMonth() + 1) + "-" + completeDate.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static int getDay(String actualDate){
        try {
            return new SimpleDateFormat("E dd-MM-yyyy").parse(actualDate).getDate();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static String getFormnattedDateTime(String dateTime){
        DateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("ddMMMyy hh:mm a");
        Date stringDate = null;
        try {

            stringDate = simpledateformat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(stringDate);
    }


    public static String getFormattedDate(String date) {
        if (date != null && !date.equals("")) {
            DateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df = new SimpleDateFormat("dd MMM yy");
            Date stringDate = null;
            try {

                stringDate = simpledateformat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return df.format(stringDate);
        }
        return "";
    }

    public static String getFormattedTime(String time) {
        if (time != null && !time.equals("")) {
            DateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss");
            DateFormat df = new SimpleDateFormat("hh:mm a");
            Date stringTime = null;
            try {

                stringTime = simpledateformat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return df.format(stringTime);
        }
        return "";
    }

    public static Calendar getTimeInCalendarFormat(String time, boolean is24Hours) {
        /**
         It require time in 12:00 PM format
         */
        if (is24Hours) {
            Date datee = null;
            DateFormat simpledateformat = new SimpleDateFormat("hh:mm a");
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            try {
                datee = simpledateformat.parse(time);
                df.format(datee);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(datee);
            return cal;
        } else {
            Date datee = null;
            DateFormat simpledateformat = new SimpleDateFormat("hh:mm a");
            try {
                datee = simpledateformat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(datee);
            return cal;
        }
    }

    public static String getTimeFromCalendar(Calendar calendar, boolean is24Hour) {
        String date ="";
        if (is24Hour){
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss" );
            return dateFormat.format(calendar.getTime());
        }else{
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a" );
            return dateFormat.format(calendar.getTime());

        }
        /*String time ="";
        if(calendar != null){
            time =  calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " "
                    + (calendar.get(Calendar.AM_PM)==0?"AM":"PM");
        }
        return time;*/
    }

    public static String getDateFromCalendar(Calendar calendar) {
        String date ="";
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm a");
//        return dateFormat.format(calendar);
        if(calendar != null){
            date =  calendar.get(Calendar.YEAR) + "-" + getEntityAppendWithZero((calendar.get(Calendar.MONTH) + 1)) + "-"
                    + getEntityAppendWithZero(calendar.get(Calendar.DAY_OF_MONTH));
        }
        return date;
    }

    private static String getEntityAppendWithZero(int i) {
        if (i < 10)
            return "0"+i;
        else
            return i+"";
    }
}
