package task8.part1.console;

import java.util.Scanner;

public class Console {
    private Scanner scanner = new Scanner(System.in);

    public int initStartRange() {
        System.out.println("input start range");
        return initRange();
    }

    public int initFinishRange() {
        System.out.println("input finish range");
        return initRange();
    }

    public int initRange() {
        int range = 0;
        try {
            range = stringToInteger(getUserInput());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, please, try again");
            initRange();
        }
        return range;
    }

    private int stringToInteger(String string) throws NumberFormatException {
        return Integer.parseInt(string);
    }

    public String getUserInput() {
        String string = scanner.nextLine();
        if (string.equals("")) {
            getUserInput();
        }
        return string;
    }

    public int initThreadCount() {
        System.out.println("Enter thread count");
        int threadCount = 0;
        try {
            threadCount = stringToInteger(getUserInput());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, please try again");
            initThreadCount();
        }
        return threadCount;
    }

}
