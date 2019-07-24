package task4.dao.impl;

import task4.dao.IDAOOrder;
import task4.entity.Beer;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class DaoOrderImpl implements IDAOOrder {
    private TreeMap<LocalDate, Map<Beer, Integer>> order = new TreeMap<>();

    @Override
    public void putOrderIntoOrderMap(LocalDate date, Map map) {
        order.put(date, map);
    }

    @Override
    public TreeMap<LocalDate, Map<Beer, Integer>> getOrdersMap() {
        return order;
    }
}
