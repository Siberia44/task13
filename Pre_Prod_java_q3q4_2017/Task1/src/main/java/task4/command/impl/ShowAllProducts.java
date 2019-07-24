package task4.command.impl;

import task4.command.Command;
import task4.service.ShoppingStorageService;

public class ShowAllProducts implements Command {
    private ShoppingStorageService shoppingStorage;

    public ShowAllProducts(ShoppingStorageService shoppingStorage) {
        this.shoppingStorage = shoppingStorage;
    }

    @Override
    public void execute() {
        System.out.println(shoppingStorage.showAllProductsFromStorage());
    }
}
