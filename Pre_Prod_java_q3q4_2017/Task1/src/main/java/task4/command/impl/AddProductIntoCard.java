package task4.command.impl;

import task4.command.Command;
import task4.service.CartService;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddProductIntoCard implements Command {
    private CartService cart;

    public AddProductIntoCard(CartService cart) {
        this.cart = cart;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name");
        String productName = sc.next();
        System.out.println("Enter quantity");
        try {
            int countOfProducts = sc.nextInt();
            cart.addProductIntoCart(productName, countOfProducts);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect quantity");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
