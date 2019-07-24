package task4.service.impl;

import task4.dao.IDAOShopStorage;
import task4.entity.Beer;
import task4.exception.SerializationException;
import task4.service.ShoppingStorageService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingStorageServiceImpl implements ShoppingStorageService {
    private IDAOShopStorage daoShopStorage;

    public ShoppingStorageServiceImpl(IDAOShopStorage daoShopStorage) {
        this.daoShopStorage = daoShopStorage;
    }

    @Override
    public List<Beer> showAllProductsFromStorage() {
        return daoShopStorage.getAllProducts();
    }

    @Override
    public Beer getBeerByName(String name) {
        return daoShopStorage.getBeerByName(name);
    }

    @Override
    public void addAllElements(List<Beer> list) {
        daoShopStorage.addAllProducts(list);
    }

    @Override
    public void serialisation() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("serializable"))) {
            objectOutputStream.writeObject(daoShopStorage.getAllProducts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserialization() {
        List<Beer> listOfProducts = new ArrayList<>();
        try (ObjectInputStream objectOutputStream = new ObjectInputStream(
                new FileInputStream("serializable"))) {
            listOfProducts = (List<Beer>) objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException();
        }
        addAllElements(listOfProducts);
    }

    @Override
    public void addProduct(Beer beer) {
        daoShopStorage.addProduct(beer);
    }

    @Override
    public int getCountOfProducts() {
        return daoShopStorage.getCountOfProducts();
    }

    @Override
    public Beer getBeerById(int id) {
        return daoShopStorage.getBeerById(id);
    }
}
