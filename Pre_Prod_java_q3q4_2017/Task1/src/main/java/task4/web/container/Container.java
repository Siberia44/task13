package task4.web.container;

import task4.service.ShoppingStorageService;
import task4.web.server.AbstractCommand;
import task4.web.server.http.HttpShowCountOfProducts;
import task4.web.server.http.HttpShowNameAndPriceById;
import task4.web.server.tcp.TcpShowCountOfProducts;
import task4.web.server.tcp.TcpShowNameAndPriceById;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private ShoppingStorageService shoppingStorageService;
    private Map<String, AbstractCommand> commandParam;

    public Container(ShoppingStorageService shoppingStorageService) {
        this.shoppingStorageService = shoppingStorageService;
        commandParam = initCommandParam();
    }

    public Map<String, AbstractCommand> getCommandParam() {
        return commandParam;
    }

    private Map<String, AbstractCommand> initCommandParam() {
        Map<String, AbstractCommand> commands = new HashMap<>();
        commands.put("get count", new TcpShowCountOfProducts(shoppingStorageService));
        commands.put("get item", new TcpShowNameAndPriceById(shoppingStorageService));
        commands.put("^GET /shop/count HTTP/1\\.1$", new HttpShowCountOfProducts(shoppingStorageService));
        commands.put(".+/item\\?get_info=.+", new HttpShowNameAndPriceById(shoppingStorageService));
        return commands;
    }
}
