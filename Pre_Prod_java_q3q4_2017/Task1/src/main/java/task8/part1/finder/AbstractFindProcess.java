package task8.part1.finder;

import java.util.List;

public abstract class AbstractFindProcess {
    private int threadSequenceNumber;
    private int start;
    private int finish;

    public AbstractFindProcess(int threadSequenceNumber, int start, int finish) {
        this.threadSequenceNumber = threadSequenceNumber;
        this.start = start;
        this.finish = finish;
    }

    private boolean isSimpleNumber(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void addElementsIntoList(List<Integer> simpleNumbers) {
        for (int i = start; i < finish; i += threadSequenceNumber) {
            if (isSimpleNumber(i)) {
                simpleNumbers.add(i);
            }
        }
    }
}
