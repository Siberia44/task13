package task4.command.impl;

import task4.command.Command;
import task4.service.ShoppingStorageService;

public class Exit implements Command {
    private ShoppingStorageService shoppingStorageService;

    public Exit(ShoppingStorageService shoppingStorageService) {
        this.shoppingStorageService = shoppingStorageService;
    }

    @Override
    public void execute() {
        shoppingStorageService.serialisation();
        System.exit(0);
    }

}
