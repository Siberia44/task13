package task4.dao;

import task4.entity.Beer;

import java.util.HashMap;
import java.util.Map;

public interface IDAOCart {
    /**
     * Put element into storage
     *
     * @param beer            element to be appended to the storage.
     *                        Key for storage;
     * @param countOfProducts count of products.
     *                        Value for storage;
     * @return true if the item is added
     */
    boolean addProduct(Beer beer, int countOfProducts);

    /**
     * Delete all elements from storage;
     *
     * @return deleted storage
     */
    HashMap<Beer, Integer> removeAllProducts();

    /**
     * Return count of products by key;
     *
     * @param beer key for storage
     * @return number of values ​​by key
     */
    int getCountOfProducts(Beer beer);

    /**
     * @return shopping cart
     */
    Map<Beer, Integer> getShoppingCart();

    /**
     * Get shopping cart storage.
     * Storage for all products the
     * user has purchased ever
     *
     * @return shopping cart storage
     */
    HashMap<Beer, Integer> getShoppingCartStorage();

    /**
     * Set shopping cart from param;
     *
     * @param linkedHashMap storage on which we replace the storage
     */
    void updateShoppingCartStorage(HashMap<Beer, Integer> linkedHashMap);

    /**
     * Put element into storage
     * with all users
     * purchases
     *
     * @param beer           element to be appended to the storage.
     *                       Key for storage;
     * @param countOfProduct count of products.
     *                       Value for storage;
     */
    void addProductIntoShoppingCartStorage(Beer beer, int countOfProduct);

    /**
     * Delete element from shopping cart storage
     *
     * @param beer deleted element
     */
    void removeElementFromMap(Beer beer);

}
