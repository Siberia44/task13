package task4.web.server.tcp;

import task4.service.ShoppingStorageService;
import task4.web.server.AbstractCommand;

public class TcpShowCountOfProducts extends AbstractCommand {

    public TcpShowCountOfProducts(ShoppingStorageService shoppingStorageService) {
        super(shoppingStorageService);
    }

    @Override
    public String execute(String request) {
        return String.valueOf(shoppingStorageService.getCountOfProducts());
    }
}
