package agent.app.converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TypeConverter {

    public static Long parseLong(String longStr) {
        return Long.valueOf(longStr);
    }

    public static String printLong(Long longVal) {
        return longVal.toString();
    }

    public static Integer parseInteger(String intStr) {
        return Integer.valueOf(intStr);
    }

    public static String printInteger(Integer intVal) {
        return intVal.toString();
    }

    public static Boolean parseBoolean(String boolStr) {
        return Boolean.valueOf(boolStr);
    }

    public static String printBoolean(Boolean boolVal) {
        return boolVal.toString();
    }

    public static Float parseFloat(String floatStr) {
        return Float.valueOf(floatStr);
    }

    public static String printFloat(Float floatVal) {
        return floatVal.toString();
    }

    public static Double parseDouble(String doubleStr) {
        return Double.valueOf(doubleStr);
    }

    public static String printDouble(Double doubleVal) {
        return doubleVal.toString();
    }

    public static DateTime parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm dd-MM-yyyy");
        return dtf.parseDateTime(date);
    }

    public static String printDate(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm:ss");
        return dtf.print(dateTime);
    }
}
