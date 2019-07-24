package task4.service.impl;

import task4.dao.IDAOOrder;
import task4.entity.Beer;
import task4.service.OrderService;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class OrderServiceImpl implements OrderService {
    private IDAOOrder daoOrder;

    public OrderServiceImpl(IDAOOrder daoOrder) {
        this.daoOrder = daoOrder;
    }

    @Override
    public void putInfoAboutOrderIntoMap(LocalDate date, Map map) {
        daoOrder.putOrderIntoOrderMap(date, map);
    }

    @Override
    public Map<Beer, Integer> getNearestOrder(LocalDate date) {
        TreeMap<LocalDate, Map<Beer, Integer>> map = daoOrder.getOrdersMap();
        LocalDate before = map.floorKey(date);
        LocalDate after = map.ceilingKey(date);
        if (before == null) {
            return map.get(after);
        }
        if (after == null) {
            return map.get(before);
        }
        return before.compareTo(date) < after.compareTo(date) ?
                map.get(after) : map.get(before);
    }

    @Override
    public Map<LocalDate, Map<Beer, Integer>> getOrdersInGap(LocalDate firstDate, LocalDate lastDate) {
        TreeMap<LocalDate, Map<Beer, Integer>> map = daoOrder.getOrdersMap();
        return map.subMap(firstDate, true, lastDate, true);
    }

}
