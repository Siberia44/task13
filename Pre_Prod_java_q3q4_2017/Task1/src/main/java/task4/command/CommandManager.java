package task4.command;

import task4.command.impl.*;
import task4.dao.IDAOCart;
import task4.dao.IDAOOrder;
import task4.dao.IDAOShopStorage;
import task4.dao.impl.DaoCartImpl;
import task4.dao.impl.DaoOrderImpl;
import task4.dao.impl.DaoShopStorageImpl;
import task4.entity.Beer;
import task4.exception.SerializationException;
import task4.service.CartService;
import task4.service.OrderService;
import task4.service.ShoppingStorageService;
import task4.service.impl.CartServiceImpl;
import task4.service.impl.OrderServiceImpl;
import task4.service.impl.ShoppingStorageServiceImpl;
import task4.storage.FillingStorage;
import task4.web.container.Container;
import task4.web.server.AbstractCommand;
import task4.web.server.Server;
import task4.web.threads.httpfactory.HttpFactory;
import task4.web.threads.tcpfactory.TcpFactory;
import task6.input.InputManager;
import task6.input.InputSource;
import task6.input.LocaleSetter;
import task6.reflection.InputByReflection;

import java.util.*;

public class CommandManager {
    private Map<String, Command> map = new HashMap<>();
    private ArrayList<Beer> initProducts = new FillingStorage().setArrayListByTestData();
    private IDAOShopStorage daoShopStorage = new DaoShopStorageImpl();
    private IDAOCart daoCart = new DaoCartImpl();
    private IDAOOrder daoOrder = new DaoOrderImpl();
    private ShoppingStorageService shoppingStorage = new ShoppingStorageServiceImpl(daoShopStorage);
    private OrderService order = new OrderServiceImpl(daoOrder);
    private CartService cart = new CartServiceImpl(daoCart, shoppingStorage, order);
    private InputSource inputSource = new InputManager().getInputStrategy();
    private Locale inputLocal = new LocaleSetter().chooseWhichLocalToUse();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", inputLocal);
    private InputByReflection inputByReflection = new InputByReflection(inputSource, resourceBundle);
    private Map<String, AbstractCommand> abstractCommandMap = new Container(shoppingStorage).getCommandParam();

    public CommandManager() {
        map.put("1", new ShowAllProducts(shoppingStorage));
        map.put("2", new ShowProductInCard(cart));
        map.put("3", new AddProductIntoCard(cart));
        map.put("4", new BuyAllProductsFromCard(cart));
        map.put("5", new ShowLast5Products(cart));
        map.put("6", new GetNearestDate(order));
        map.put("7", new GetOrdersInGap(order));
        map.put("8", new AddProductIntoShop(shoppingStorage, inputSource));
        map.put("9", new AddProductIntoShopByReflection(shoppingStorage, inputSource, resourceBundle, inputByReflection));
        map.put("0", new Exit(shoppingStorage));
    }

    public void showMenu() {
        startServer();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show all products");
            System.out.println("2. Show products in cart");
            System.out.println("3. Add product in card");
            System.out.println("4. Buy all products from cart");
            System.out.println("5. Show 5 latest products");
            System.out.println("6. Show order by nearest date");
            System.out.println("7. Show order list in gap");
            System.out.println("8. Put element");
            System.out.println("9. Put element by reflection");
            System.out.println("0. Exit");
            String number = sc.nextLine();
            if (map.containsKey(number)) {
                map.get(number).execute();
            } else {
                System.out.println("Wrong command");
            }
        }
    }

    public void setStorage() {
        try {
            shoppingStorage.deserialization();
        } catch (SerializationException e) {
            shoppingStorage.addAllElements(initProducts);
        }
    }

    public void startServer() {
        new Thread(new Server(3000, new TcpFactory(), abstractCommandMap)).start();
        new Thread(new Server(8080, new HttpFactory(), abstractCommandMap)).start();
    }
}
