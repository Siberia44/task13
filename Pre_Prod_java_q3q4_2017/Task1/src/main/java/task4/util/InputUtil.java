package task4.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputUtil {
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate fromStringToDate(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
