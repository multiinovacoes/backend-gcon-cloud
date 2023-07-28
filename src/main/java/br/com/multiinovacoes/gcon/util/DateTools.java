package br.com.multiinovacoes.gcon.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package multiwork.util:
//            NumberTools

public class DateTools
{

    public DateTools()
    {
    }

    public static String dateTimeToString(Date date)
    {
        if(date == null)
        {
            return "";
        }
        if(date.getTime() == 0L)
        {
            return "";
        } else
        {
            String parts[] = dateTimeToStringParts(date);
            return parts[0] + "/" + parts[1] + "/" + parts[2] + " " + parts[3] + ":" + parts[4];
        }
    }

    public static String[] dateTimeToStringParts(Date date)
    {
        if(date == null)
        {
            return new String[5];
        }
        if(date.getTime() == 0L)
        {
            return new String[5];
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(5);
            int month = calendar.get(2) + 1;
            int year = calendar.get(1);
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            String result[] = new String[5];
            result[0] = NumberTools.completeZero(day, 2);
            result[1] = NumberTools.completeZero(month, 2);
            result[2] = NumberTools.completeZero(year, 4);
            result[3] = NumberTools.completeZero(hour, 2);
            result[4] = NumberTools.completeZero(minute, 2);
            return result;
        }
    }

    public static String dateToString(Date date)
    {
        if(date == null)
        {
            return "";
        }
        if(date.getTime() == 0L)
        {
            return "";
        } else
        {
            String parts[] = dateToStringParts(date);
            return parts[0] + "/" + parts[1] + "/" + parts[2];
        }
    }

    public static String dateToStringMonthYear(Date date)
    {
        if(date == null)
        {
            return "";
        }
        if(date.getTime() == 0L)
        {
            return "";
        } else
        {
            String parts[] = dateToStringParts(date);
            return parts[1] + "/" + parts[2];
        }
    }

    public static String[] dateToStringParts(Date date)
    {
        if(date == null)
        {
            return new String[3];
        }
        if(date.getTime() == 0L)
        {
            return new String[3];
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(5);
            int month = calendar.get(2) + 1;
            int year = calendar.get(1);
            String result[] = new String[3];
            result[0] = NumberTools.completeZero(day, 2);
            result[1] = NumberTools.completeZero(month, 2);
            result[2] = NumberTools.completeZero(year, 4);
            return result;
        }
    }

    public static int getActual(int field)
    {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(field);
    }

    public static int getActualDayOfMonth()
    {
        return getActual(5);
    }

    public static int getActualDayOfWeek()
    {
        return getActual(7);
    }

    public static int getActualDayOfYear()
    {
        return getActual(6);
    }

    public static int getActualMonth()
    {
        return getActual(2) + 1;
    }

    public static int getActualYear()
    {
        return getActual(1);
    }

    public static Timestamp stringDateTimeToTimestamp(String value)
    {
        if(value == null || value.trim().equals(""))
        {
            return new Timestamp(0L);
        }
        int firstBar = value.indexOf('/');
        int lastBar = value.lastIndexOf('/');
        int space = value.indexOf(' ');
        int twoPoints = value.indexOf(':');
        String day = value.substring(0, firstBar);
        String month = value.substring(firstBar + 1, lastBar);
        String year = value.substring(lastBar + 1, space);
        String hour = value.substring(space + 1, twoPoints);
        String minute = value.substring(twoPoints + 1, value.length());
        if(hour.equals("24"))
        {
            hour = "23";
            minute = "59";
        }
        return Timestamp.valueOf(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00.000000000");
    }

    public static Timestamp stringDateToTimestamp(String value)
    {
        if(value == null || value.trim().equals(""))
        {
            return new Timestamp(0L);
        } else
        {
            int firstBar = value.indexOf('/');
            int lastBar = value.lastIndexOf('/');
            String day = value.substring(0, firstBar);
            String month = value.substring(firstBar + 1, lastBar);
            String year = value.substring(lastBar + 1, value.length());
            return Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00.000000000");
        }
    }

    public static Timestamp stringMonthYearToTimestamp(String value)
    {
        if(value == null || value.trim().equals(""))
        {
            return new Timestamp(0L);
        } else
        {
            int firstBar = value.indexOf('/');
            String month = value.substring(0, firstBar);
            String year = value.substring(firstBar + 1, value.length());
            return Timestamp.valueOf(year + "-" + month + "-01 00:00:00.000000000");
        }
    }

    public static String timeToString(Date date)
    {
        if(date == null)
        {
            return "";
        }
        if(date.getTime() == 0L)
        {
            return "";
        } else
        {
            String parts[] = dateTimeToStringParts(date);
            return parts[3] + ":" + parts[4];
        }
    }
}
