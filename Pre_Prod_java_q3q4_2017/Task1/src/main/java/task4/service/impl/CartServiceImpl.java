package task4.service.impl;

import task4.dao.IDAOCart;
import task4.entity.Beer;
import task4.service.CartService;
import task4.service.OrderService;
import task4.service.ShoppingStorageService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CartServiceImpl implements CartService {
    private IDAOCart daoCart;
    private ShoppingStorageService shoppingStorageService;
    private OrderService order;

    public CartServiceImpl(IDAOCart daoCart, ShoppingStorageService shoppingStorageService, OrderService order) {
        this.daoCart = daoCart;
        this.shoppingStorageService = shoppingStorageService;
        this.order = order;
    }

    @Override
    public void addProductIntoCart(String name, int countOfProducts) {
        if (!checkIndex(countOfProducts)) {
            throw new IllegalArgumentException("count of products is not correct");
        }
        Beer beer = shoppingStorageService.getBeerByName(name);
        if (Objects.isNull(beer)) {
            throw new NoSuchElementException("Product is not exist");
        } else {
            addProductsIntoShoppingCart(beer, countOfProducts);
            addProductsIntoShoppingCartStorage(beer, countOfProducts);
        }
    }

    @Override
    public HashMap<Beer, Integer> showInformationAbout5LatestProductsFromCart() {
        return daoCart.getShoppingCartStorage();
    }

    @Override
    public Map<Beer, Integer> showAllProductsInCard() {
        return daoCart.getShoppingCart();
    }

    @Override
    public int makeOrder(LocalDate date) {
        int totalOrderValue = getTotalOrderValue();
        order.putInfoAboutOrderIntoMap(date, removeAllProductsFromCart());
        return totalOrderValue;
    }

    @Override
    public Map<Beer, Integer> removeAllProductsFromCart() {
        return daoCart.removeAllProducts();
    }

    private boolean checkIndex(int countOfProducts) {
        if (countOfProducts <= 0) {
            return false;
        }
        return true;
    }

    private int getTotalOrderValue() {
        Map<Beer, Integer> shoppingCart = daoCart.getShoppingCart();
        int totalCost = 0;
        for (Beer key : shoppingCart.keySet()) {
            totalCost += key.getCost() * shoppingCart.get(key);
        }
        return totalCost;
    }

    private void addProductsIntoShoppingCart(Beer beer, int countOfProducts) {
        daoCart.addProduct(beer, daoCart.getCountOfProducts(beer) + countOfProducts);
    }

    private void addProductsIntoShoppingCartStorage(Beer beer, int countOfProducts) {
        daoCart.addProductIntoShoppingCartStorage(beer, countOfProducts);
    }
}
