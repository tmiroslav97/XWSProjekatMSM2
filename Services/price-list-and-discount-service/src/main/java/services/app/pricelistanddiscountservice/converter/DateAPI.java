package services.app.pricelistanddiscountservice.converter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateAPI {

    public static DateTime DateTimeNow() {
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        String date = dateTime.toString();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");
        String dateString = date.replace("T", " ");
        String[] res = dateString.split(" ");

        String datum = res[0];
        String[] dio = datum.split("-");
        String yyyy = dio[0];
        String MM = dio[1];
        String dd = dio[2];

        String vreme = res[1];
        dio = vreme.split(":");
        String hh = dio[0];
        String mm = dio[1];
        String ostatak = dio[2];

        String newDate = hh+ ":" + mm + " " + dd + "-" + MM + "-" + yyyy;
        System.out.println(newDate);

        DateTime dateTime1 = DateTime.parse(newDate, formatter);
        System.out.println("datum nakon parsiranja: " + dateTime1.toString());
        return dateTime1;
    }

    public static DateTime DateTimeFromString(String date) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm dd-MM-yyyy");
        return dtf.parseDateTime(date);
    }

    public static DateTime DateTimeFromDateString(String date) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtf.parseDateTime(date);
    }

    //za datume sa fronta koji stizu u formatu: 2020-06-03T03:03
    public static DateTime dateStringToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm dd-MM-yyyy");
//        DateFormat formatter = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        String dateString = date.replace("T", " ");
//        System.out.println(dateString);
        String[] res = dateString.split(" ");
        String datum = res[0];
//        System.out.println("DATUMMM : " + datum);
        String[] dio = datum.split("-");
        String yyyy = dio[0];
        String MM = dio[1];
        String dd = dio[2];

        String vrijeme = res[1];
        dio = vrijeme.split(":");
        String hh = dio[0]; // 004
        String mm = dio[1];

        String newDate = vrijeme + " " + dd + "-" + MM + "-" + yyyy;
        System.out.println(newDate);
        DateTime dateTime = DateTime.parse(newDate, formatter);

        return dateTime;
    }
}
