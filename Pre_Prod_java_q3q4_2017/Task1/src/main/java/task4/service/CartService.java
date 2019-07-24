package task4.service;

import task4.entity.Beer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public interface CartService {
    /**
     * Add product into shopping cart
     * Add product into shopping cart storage
     * to save 5 latest user purchase
     *
     * @param productName     name of the product want to add
     * @param countOfProducts the count of products want to add
     */
    void addProductIntoCart(String productName, int countOfProducts);

    /**
     * Clears the card and returns the value of all purchases inside.
     *
     * @param date purchase date
     * @return total cost of all items in the cart
     */
    int makeOrder(LocalDate date);

    /**
     * Work with shopping cart storage.
     *
     * @return shopping cart storage
     */
    HashMap<Beer, Integer> showInformationAbout5LatestProductsFromCart();

    /**
     * @return shopping cart
     */
    Map<Beer, Integer> showAllProductsInCard();

    /**
     * Remove all elements from shopping cart
     *
     * @return cart with deleted elements
     */
    Map<Beer, Integer> removeAllProductsFromCart();
}
