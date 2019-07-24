package task8.part1.finder.common.thread;

import task8.part1.AbstractFinder;
import task8.part1.exception.InabilityPerformOperationException;
import task8.part1.finder.common.CommonFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadFinderByCommon extends AbstractFinder {

    private List<Thread> threads;
    private List<Integer> simpleNumbersContainer = new ArrayList<>();

    @Override
    public List<Integer> getPrimeNumbers() {
        startFinders();
        try {
            waitForAllThreadsEnd();
        } catch (InabilityPerformOperationException e) {
            return Collections.emptyList();
        }
        simpleNumbersContainer.sort(Integer::compareTo);
        return simpleNumbersContainer;
    }

    private void startFinders() {
        createThreads();
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private void createThreads() {
        threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new CommonFinder(i, threadCount, startRange, finishRange, simpleNumbersContainer));
            threads.add(thread);
        }
    }

    private void waitForAllThreadsEnd() throws InabilityPerformOperationException {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                closeAllThread();
                System.out.println("Exception happened while waiting for ending threads");
                throw new InabilityPerformOperationException();
            }
        }
    }

    private void closeAllThread() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

