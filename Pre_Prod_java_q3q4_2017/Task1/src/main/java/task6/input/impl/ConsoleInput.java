package task6.input.impl;

import task6.input.InputSource;

import java.util.Scanner;

public class ConsoleInput implements InputSource {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    @Override
    public int getInt(String message) {
        System.out.println(message);
        String result;
        try {
            while (true) {
                result = scanner.next();
                return Integer.valueOf(result);
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input");
            return getInt(message);
        }
    }
}
