package task8.part1.container;

import task8.part1.AbstractFinder;
import task8.part1.finder.common.executor.ExecutorFinderByCommon;
import task8.part1.finder.common.thread.ThreadFinderByCommon;
import task8.part1.finder.local.executor.ExecutorFinderByLocal;
import task8.part1.finder.local.thread.ThreadFinderByLocal;

import java.util.HashMap;
import java.util.Map;

public class FinderContainer {

    private Map<String, AbstractFinder> finders = new HashMap<>();

    public FinderContainer() {
        findersInit();
    }

    private void findersInit() {
        finders.put("1", new ThreadFinderByCommon());
        finders.put("2", new ThreadFinderByLocal());
        finders.put("3", new ExecutorFinderByLocal());
        finders.put("4", new ExecutorFinderByCommon());
    }

    public AbstractFinder getFinder(String choice) {
        return finders.get(choice);
    }
}

