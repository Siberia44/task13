package task4.command.impl;

import task4.command.Command;
import task4.service.CartService;
import task4.util.InputUtil;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class BuyAllProductsFromCard implements Command {
    private CartService cartService;

    public BuyAllProductsFromCard(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input date (dd/MM/yyyy): ");
        String date = sc.nextLine();
        try {
            LocalDate localDate = InputUtil.fromStringToDate(date);
            System.out.println(cartService.makeOrder(localDate));
        } catch (DateTimeException e) {
            System.out.println("IncorrectData");
        }
    }

}
