package task4.web.server.http;

import org.json.JSONObject;
import task4.service.ShoppingStorageService;
import task4.web.server.AbstractCommand;

public class HttpShowCountOfProducts extends AbstractCommand {

    public HttpShowCountOfProducts(ShoppingStorageService shoppingStorageService) {
        super(shoppingStorageService);
    }

    @Override
    public String execute(String request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count ", shoppingStorageService.getCountOfProducts());
        return jsonObject.toString();
    }
}
