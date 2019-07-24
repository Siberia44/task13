package task6.input;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class LocaleSetter {

    private Map<String, Locale> locale = new HashMap<>();
    private Locale enLocale = Locale.ENGLISH;
    private Locale ruLocale = new Locale("ru");

    private void fillingMap() {
        locale.put("1", enLocale);
        locale.put("2", ruLocale);
    }

    public Locale chooseWhichLocalToUse() {
        fillingMap();
        listOfLocales();
        Scanner scanner = new Scanner(System.in);
        return locale.get(scanner.next());
    }

    private void listOfLocales() {
        System.out.println("Choose language");
        for (Map.Entry<String, Locale> entry : locale.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
