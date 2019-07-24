package task4.command.impl;

import task4.command.Command;
import task4.service.ShoppingStorageService;
import task6.creator.CreatorManager;
import task6.input.InputSource;

public class AddProductIntoShop implements Command {
    private ShoppingStorageService shoppingStorageService;
    private InputSource inputSource;

    public AddProductIntoShop(ShoppingStorageService shoppingStorageService, InputSource inputSource) {
        this.shoppingStorageService = shoppingStorageService;
        this.inputSource = inputSource;
    }

    @Override
    public void execute() {
        try {
            shoppingStorageService.addProduct(new CreatorManager(inputSource).createBeer());
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect input");
            execute();
        }
    }
}
