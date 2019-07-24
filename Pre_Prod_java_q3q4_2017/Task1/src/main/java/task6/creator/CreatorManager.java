package task6.creator;

import task4.entity.Beer;
import task6.creator.impl.BeerCreator;
import task6.creator.impl.DarkBeerCreator;
import task6.creator.impl.HoegardenCreator;
import task6.creator.impl.LightBeerCreator;
import task6.input.InputSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreatorManager {
    private InputSource inputSource;

    public CreatorManager(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    /**
     * selects the object to create
     *
     * @return created object
     */
    public Beer createBeer() {
        consoleInformation();
        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        Map<String, CreatorSource> creatorMap = fillingMap();
        if (creatorMap.containsKey(key)) {
            return (Beer) creatorMap.get(key).create(inputSource);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Print information into console
     */
    private void consoleInformation() {
        System.out.println("1 - Beer");
        System.out.println("2 - Dark beer");
        System.out.println("3 - Light beer");
        System.out.println("4 - Hoegarden");
        System.out.println();
        System.out.println("Input key");
    }

    /**
     * filling map with commands
     *
     * @return map with commands
     */
    private Map<String, CreatorSource> fillingMap() {
        Map<String, CreatorSource> creatorMap = new HashMap<>();
        creatorMap.put("1", new BeerCreator());
        creatorMap.put("2", new DarkBeerCreator());
        creatorMap.put("3", new LightBeerCreator());
        creatorMap.put("4", new HoegardenCreator());
        return creatorMap;
    }

}

