package task8.part1.finder.local.thread;

import task8.part1.AbstractFinder;
import task8.part1.exception.InabilityPerformOperationException;
import task8.part1.finder.local.LocalFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadFinderByLocal extends AbstractFinder {
    private List<Integer> simpleNumbersContainer = new ArrayList<>();

    @Override
    public List<Integer> getPrimeNumbers() {
        try {
            errorHandleDuringFindingNumbers();
        } catch (InabilityPerformOperationException e) {
            return Collections.emptyList();
        }
        simpleNumbersContainer.sort(Integer::compareTo);
        return simpleNumbersContainer;
    }

    private void errorHandleDuringFindingNumbers() throws InabilityPerformOperationException {
        try {
            findSimpleNumbers();
        } catch (ExecutionException e) {
            System.out.println("Exception happened while getting result from future thread");
        }
    }

    private void findSimpleNumbers() throws ExecutionException, InabilityPerformOperationException {
        List<FutureTask<List<Integer>>> futureThreads = createAndStartThreads();
        for (FutureTask<List<Integer>> thread : futureThreads) {
            List<Integer> simpleNumbers;
            try {
                simpleNumbers = thread.get();
            } catch (InterruptedException e) {
                closeAllThreads(futureThreads);
                System.out.println("Threads were closed because of exception while getting result");
                throw new InabilityPerformOperationException();
            }
            simpleNumbersContainer.addAll(simpleNumbers);
        }
    }

    private List<FutureTask<List<Integer>>> createAndStartThreads() {
        List<FutureTask<List<Integer>>> futureThreads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            FutureTask<List<Integer>> thread =
                    new FutureTask<>(new LocalFinder(i, threadCount, startRange, finishRange, simpleNumbersContainer));
            futureThreads.add(thread);
            new Thread(thread).start();
        }
        return futureThreads;
    }

    private void closeAllThreads(List<FutureTask<List<Integer>>> futureThreads) {
        for (FutureTask<List<Integer>> futureThread : futureThreads) {
            futureThread.cancel(true);
        }
    }
}
