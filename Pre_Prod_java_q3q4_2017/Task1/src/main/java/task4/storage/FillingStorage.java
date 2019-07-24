package task4.storage;

import task4.entity.Beer;

import java.util.ArrayList;

public class FillingStorage {
    public ArrayList<Beer> setArrayListByTestData() {
        ArrayList<Beer> testStorage = new ArrayList<>();
        testStorage.add(new Beer(1, 300, "1", 30, "Ukraine"));
        testStorage.add(new Beer(2, 320, "2", 12, "Germany"));
        testStorage.add(new Beer(3, 123, "3", 14, "England"));
        testStorage.add(new Beer(4, 123, "4", 16, "England"));
        testStorage.add(new Beer(5, 123, "5", 17, "England"));
        testStorage.add(new Beer(6, 123, "6", 15, "England"));
        return testStorage;
    }
}



