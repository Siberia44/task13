package task4.service;

import task4.entity.Beer;

import java.time.LocalDate;
import java.util.Map;

public interface OrderService {

    /**
     * when buying from shopping cart -
     * they are added to the map of orders
     *
     * @param date date of purchase
     * @param map  shopping cart with all products
     */
    void putInfoAboutOrderIntoMap(LocalDate date, Map map);

    /**
     * get the nearest order by date
     *
     * @param date the date we search for
     * @return closest purchase by date
     */
    Map<Beer, Integer> getNearestOrder(LocalDate date);

    /**
     * Returns storage between dates.
     *
     * @param firstDate
     * @param lastDate
     * @return storage between dates
     */
    Map<LocalDate, Map<Beer, Integer>> getOrdersInGap(LocalDate firstDate, LocalDate lastDate);
}
