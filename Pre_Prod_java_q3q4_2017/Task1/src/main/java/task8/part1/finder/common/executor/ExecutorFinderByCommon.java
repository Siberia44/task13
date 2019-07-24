package task8.part1.finder.common.executor;

import task8.part1.AbstractFinder;
import task8.part1.finder.common.CommonFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFinderByCommon extends AbstractFinder {
    private ExecutorService service;
    private volatile List<Thread> threads;
    private List<Integer> simpleNumbersContainer = new ArrayList<>();

    @Override
    public List<Integer> getPrimeNumbers() {
        service = Executors.newFixedThreadPool(threadCount);
        findSimpleNumbers();
        simpleNumbersContainer.sort(Integer::compareTo);
        return simpleNumbersContainer;
    }

    private void findSimpleNumbers() {
        createThreads();
        threads.forEach(service::submit);
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createThreads() {
        threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(new CommonFinder(i, threadCount, startRange, finishRange, simpleNumbersContainer)));
        }
    }
}
