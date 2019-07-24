package task4.command.impl;

import task4.command.Command;
import task4.service.CartService;

public class ShowProductInCard implements Command {
    private CartService cart;

    public ShowProductInCard(CartService cart) {
        this.cart = cart;
    }

    @Override
    public void execute() {
        System.out.println(cart.showAllProductsInCard());
    }

}
