package task4.web.server.http;

import org.json.JSONObject;
import task4.entity.Beer;
import task4.service.ShoppingStorageService;
import task4.web.server.AbstractCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpShowNameAndPriceById extends AbstractCommand {

    public HttpShowNameAndPriceById(ShoppingStorageService shoppingStorageService) {
        super(shoppingStorageService);
    }

    @Override
    public String execute(String request) {
        String number = getParametersFromRequest(request);
        Beer beer = shoppingStorageService.getBeerById(Integer.parseInt(number));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price ", beer.getCost());
        jsonObject.put("name ", beer.getName());
        return jsonObject.toString();
    }

    private String getParametersFromRequest(String request) {
        Matcher matcher = Pattern.compile(".*get_info=(\\d{1,5}).*").matcher(request);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
