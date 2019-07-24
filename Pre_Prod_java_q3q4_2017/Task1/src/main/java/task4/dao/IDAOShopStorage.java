package task4.dao;

import task4.entity.Beer;

import java.util.List;

public interface IDAOShopStorage {

    /**
     * Get product list
     *
     * @return list with all products in shop
     */
    List<Beer> getAllProducts();

    /**
     * Get product by name
     *
     * @param name name of product
     * @return product
     */
    Beer getBeerByName(String name);

    void addAllProducts(List<Beer> list);

    void addProduct(Beer beer);

    int getCountOfProducts();

    Beer getBeerById(int id);
}
