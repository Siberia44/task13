package task8.part1.view;


import task8.part1.AbstractFinder;
import task8.part1.console.Console;
import task8.part1.container.FinderContainer;

public class Controller {

    private Console console = new Console();
    private FinderContainer container = new FinderContainer();

    public static void main(String[] args) {
        new Controller().startProgram();
    }

    private void startProgram() {
        printMenu();
        String choice = console.getUserInput();
        AbstractFinder finder = container.getFinder(choice);
        finder.getUserInfo();
        long startTime = System.currentTimeMillis();
        System.out.println(finder.getPrimeNumbers());
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }

    public void printMenu() {
        System.out.println("Find simple numbers by: ");
        System.out.println("1. common collection");
        System.out.println("2. different collections");
        System.out.println("3. common collections (executor)");
        System.out.println("4. different collections (executor)");
    }
}

