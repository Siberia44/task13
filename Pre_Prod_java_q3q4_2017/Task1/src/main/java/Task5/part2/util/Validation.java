package Task5.part2.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Validation() {
        throw new AssertionError("Attempt to call private constructor");
    }

    public static boolean isNumberValid(Scanner scanner) {
        String number;
        while (scanner.hasNext()) {
            number = scanner.next();
            if (number.equals("1")) {
                return true;
            }
            if (number.equals("0")) {
                return false;
            }
            System.out.println("Incorrect Number");
        }
        return false;
    }

    public static String validExtension(Scanner scanner) {
        Pattern pattern = Pattern.compile("\\.\\S+");
        String extension;
        while (scanner.hasNext()) {
            extension = scanner.next();
            Matcher matcher = pattern.matcher(extension);
            if (matcher.find()) {
                return extension;
            }
        }
        return null;
    }

    public static long validLongInput(Scanner scanner) {
        long number = -1;
        try {
            number = Long.parseLong(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            validLongInput(scanner);
        }
        return number;
    }

    public static long validDateInput(Scanner scanner) {
        LocalDate localDate = null;
        try {
            String date = scanner.next();
            localDate = LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeException e) {
            System.out.println("Incorrect date");
            validDateInput(scanner);
        }
        return localDate.toEpochDay();
    }
}
