package services.app.adservice.converter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateAPI {

    public static DateTime DateTimeNow() {
        return new DateTime(DateTimeZone.UTC);
    }

    public static String DateTimeToStringDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");
        return dtf.print(dateTime);
    }

    public static DateTime DateTimeStringToDateTime(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");
        return dtf.parseDateTime(dateStr);
    }

    //za datume sa fronta koji stizu u formatu: 2020-06-03T03:03
    public static DateTime DateStringToDateTimeFromFronted(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");

        String[] datumSplitted = dateStr.split("-");
        String yyyy = datumSplitted[0];
        String MM = datumSplitted[1];
        String dd = datumSplitted[2];

        String hh = "00";
        String mm = "00";

        String newDateStr = hh + ":" + mm + " " + dd + "-" + MM + "-" + yyyy;

        DateTime dateTime = DateTime.parse(newDateStr, dtf);

        return dateTime;
    }

    //za datume i vrijeme sa fronta koji stizu u formatu: 2020-06-03T03:03
    public static DateTime DateTimeStringToDateTimeFromFronted(String dateTimeStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");

        String[] res = dateTimeStr.split("T");

        String datum = res[0];
        String[] datumSplitted = datum.split("-");
        String yyyy = datumSplitted[0];
        String MM = datumSplitted[1];
        String dd = datumSplitted[2];

        String vrijeme = res[1];
        String[] vrijemeSpplited = vrijeme.split(":");
        String hh = vrijemeSpplited[0];
        String mm = vrijemeSpplited[1];

        String newDateStr = hh + ":" + mm + " " + dd + "-" + MM + "-" + yyyy;

        DateTime dateTime = DateTime.parse(newDateStr, dtf);

        return dateTime;
    }

}
