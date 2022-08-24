package vn.anzi.utils;

public class TimeUtil {
    public static long convertDateToMilis(int date) {
        return date * 86400000L;
    }

    public static long convertHourToMilis(int hour) {
        return hour * 3600000L;
    }

    public static int convertHourToSecond(int hour) {
        return hour * 3600;
    }
}
