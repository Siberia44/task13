package task4.command.impl;

import task4.command.Command;
import task4.service.CartService;

public class ShowLast5Products implements Command {
    private CartService cartService;

    public ShowLast5Products(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        System.out.println(cartService.showInformationAbout5LatestProductsFromCart());
    }
}
