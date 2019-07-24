package task4.dao.impl;

import task4.dao.IDAOShopStorage;
import task4.entity.Beer;

import java.util.List;
import java.util.Optional;

public class DaoShopStorageImpl implements IDAOShopStorage {
    private List<Beer> shoppingStorage;

    public DaoShopStorageImpl() {
    }

    @Override
    public void addProduct(Beer beer) {
        beer.setId(shoppingStorage.size() + 1);
        shoppingStorage.add(beer);
    }

    @Override
    public List<Beer> getAllProducts() {
        return shoppingStorage;
    }

    @Override
    public Beer getBeerByName(String name) {
        Optional<Beer> o1 = shoppingStorage.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
        return o1.orElse(null);
    }

    @Override
    public void addAllProducts(List<Beer> list) {
        shoppingStorage = list;
    }

    @Override
    public int getCountOfProducts() {
        return shoppingStorage.size();
    }

    @Override
    public Beer getBeerById(int id) {
        Optional<Beer> o1 = shoppingStorage.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return o1.orElse(null);
    }
}
