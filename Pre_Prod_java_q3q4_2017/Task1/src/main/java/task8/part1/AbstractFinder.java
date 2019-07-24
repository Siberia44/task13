package task8.part1;

import task8.part1.console.Console;

import java.util.List;

public abstract class AbstractFinder {

    protected int threadCount;
    protected int startRange;
    protected int finishRange;
    private Console console = new Console();

    public void getUserInfo() {
        startRange = console.initStartRange();
        finishRange = console.initFinishRange();
        threadCount = console.initThreadCount();
    }

    public abstract List<Integer> getPrimeNumbers();
}


