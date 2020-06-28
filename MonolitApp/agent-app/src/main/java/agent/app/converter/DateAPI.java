package agent.app.converter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateAPI {

    public static DateTime DateTimeNow() {
        return new DateTime();
    }

    public static String DateTimeTiStringDateTime(DateTime dateTime) {
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

    //    public static DateTime dateTimeNow() {
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");
//        DateTime date = new DateTime(DateTimeZone.UTC);
//        String dateStr = date.toString("HH:mm dd-MM-yyyy");
//        DateTime dateTime = DateTime.parse(dateStr, formatter);
//        return dateTime;
//    }

    //    //za datume sa fronta koji stizu u formatu: 2020-06-03T03:03
//    public static DateTime dateStringToDateTime(String date) {
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
////        DateFormat formatter = new SimpleDateFormat("hh:mm dd-MM-yyyy");
//        String dateString = date.replace("T", " ");
////        System.out.println(dateString);
//        String[] res = dateString.split(" ");
//        String datum = res[0];
////        System.out.println("DATUMMM : " + datum);
//        String[] dio = datum.split("-");
//        String yyyy = dio[0];
//        String MM = dio[1];
//        String dd = dio[2];
//
////        String vrijeme = res[1];
////        dio = vrijeme.split(":");
////        String hh = dio[0]; // 004
////        String mm = dio[1];
//
//        String newDate = dd + "-" + MM + "-" + yyyy;
//        System.out.println(newDate);
//        DateTime dateTime = DateTime.parse(newDate, formatter);
//
//        return dateTime;
//    }
}
