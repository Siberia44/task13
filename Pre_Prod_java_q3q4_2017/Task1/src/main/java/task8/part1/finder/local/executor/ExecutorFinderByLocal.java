package task8.part1.finder.local.executor;

import task8.part1.AbstractFinder;
import task8.part1.exception.InabilityPerformOperationException;
import task8.part1.finder.local.LocalFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFinderByLocal extends AbstractFinder {
    private ExecutorService service;
    private List<Integer> simpleNumbersContainer = new ArrayList<>();

    @Override
    public List<Integer> getPrimeNumbers() {
        service = Executors.newFixedThreadPool(threadCount);
        try {
            findSimpleNumbers();
        } catch (InabilityPerformOperationException e) {
            return Collections.emptyList();
        }
        simpleNumbersContainer.sort(Integer::compareTo);
        return simpleNumbersContainer;
    }

    private void findSimpleNumbers() throws InabilityPerformOperationException {
        List<Future<List<Integer>>> futureThreads = createThreads();
        for (Future<List<Integer>> futureThread : futureThreads) {
            try {
                simpleNumbersContainer.addAll(futureThread.get());
            } catch (ExecutionException | InterruptedException e) {
                service.shutdownNow();
                System.out.println(("Threads were closed because of exception while getting result"));
                throw new InabilityPerformOperationException();
            }
        }
    }

    private List<Future<List<Integer>>> createThreads() {
        List<Future<List<Integer>>> futureThreads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            futureThreads.add(service.submit(new LocalFinder(i, threadCount, startRange, finishRange, simpleNumbersContainer)));
        }
        return futureThreads;
    }
}
