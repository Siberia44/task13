package task4.command.impl;

import task4.command.Command;
import task4.entity.Beer;
import task4.service.ShoppingStorageService;
import task6.input.InputSource;
import task6.reflection.InputByReflection;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class AddProductIntoShopByReflection implements Command {
    private ShoppingStorageService shoppingStorageService;
    private InputSource inputSource;
    private ResourceBundle resourceBundle;
    private InputByReflection inputByReflection;

    public AddProductIntoShopByReflection(ShoppingStorageService shoppingStorageService, InputSource inputSource, ResourceBundle resourceBundle, InputByReflection inputByReflection) {
        this.shoppingStorageService = shoppingStorageService;
        this.inputSource = inputSource;
        this.resourceBundle = resourceBundle;
        this.inputByReflection = inputByReflection;
    }

    @Override

    public void execute() {
        try {
            Beer beer = inputByReflection.create();
            shoppingStorageService.addProduct(beer);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }
}
