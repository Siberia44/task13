package task4;

import task4.command.CommandManager;

public class Demo {
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        commandManager.setStorage();
        commandManager.showMenu();
    }
}
