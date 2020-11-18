package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class to Convert Dates to String and vice versa
 */
public class DateConverter {
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        return LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
    }

    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        return LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
    }
}
