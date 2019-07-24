package task8.part1.finder.local;

import task8.part1.finder.AbstractFindProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class LocalFinder extends AbstractFindProcess implements Callable<List<Integer>> {

    private List<Integer> simpleNumbers;

    public LocalFinder(int serialNumberOfThread, int threadCount, int start, int finish, List<Integer> simpleNumbers) {
        super(threadCount, start + serialNumberOfThread, finish);
        this.simpleNumbers = new ArrayList<>();
    }

    @Override
    public List<Integer> call() {
        addElementsIntoList(simpleNumbers);
        return simpleNumbers;
    }
}
