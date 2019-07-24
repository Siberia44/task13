package task4.web.server.tcp;

import task4.entity.Beer;
import task4.service.ShoppingStorageService;
import task4.web.server.AbstractCommand;

public class TcpShowNameAndPriceById extends AbstractCommand {
    public TcpShowNameAndPriceById(ShoppingStorageService shoppingStorageService) {
        super(shoppingStorageService);
    }

    @Override
    public String execute(String request) {
        Beer beer = shoppingStorageService.getBeerById(Integer.parseInt(request));
        return beer.getName() + " | " + beer.getCost();
    }
}
