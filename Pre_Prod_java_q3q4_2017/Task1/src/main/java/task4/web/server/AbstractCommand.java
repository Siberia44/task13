package task4.web.server;

import task4.service.ShoppingStorageService;
import task4.web.ServerCommand;

public abstract class AbstractCommand implements ServerCommand {
    protected ShoppingStorageService shoppingStorageService;

    public AbstractCommand(ShoppingStorageService shoppingStorageService) {
        this.shoppingStorageService = shoppingStorageService;
    }

    @Override
    public abstract String execute(String request);
}
