package task8.part1.finder.common;

import task8.part1.finder.AbstractFindProcess;

import java.util.List;

public class CommonFinder extends AbstractFindProcess implements Runnable {

    private List<Integer> simpleNumbers;

    public CommonFinder(int serialNumberOfThread, int threadCount, int start, int finish, List<Integer> simpleNumbers) {
        super(threadCount, start + serialNumberOfThread, finish);
        this.simpleNumbers = simpleNumbers;
    }

    @Override
    public void run() {
        synchronized (simpleNumbers) {
            addElementsIntoList(simpleNumbers);
        }
    }
}
