package task8;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import task8.part1.AbstractFinder;
import task8.part1.finder.common.executor.ExecutorFinderByCommon;
import task8.part1.finder.common.thread.ThreadFinderByCommon;
import task8.part1.finder.local.executor.ExecutorFinderByLocal;
import task8.part1.finder.local.thread.ThreadFinderByLocal;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class SimpleNumbersFinderTest {
    private static final String START_RANGE = "10";
    private static final String FINISH_RANGE = "100";
    private static final String THREAD_COUNT = "10";
    private static final Integer[] simpleNumbersRange100
            = new Integer[]{11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private static final int SIMPLE_NUMBERS_IN_RANGE_100 = 21;

    @Rule
    public final TextFromStandardInputStream systemInMock =
            emptyStandardInputStream();

    private AbstractFinder finder;

    @Test
    public void shouldReturnSimpleNumbers_whenEachThreadFindHisSimpleNumberCollection() {
        finder = new ThreadFinderByLocal();
        systemInMock.provideLines(START_RANGE, FINISH_RANGE, THREAD_COUNT);
        finder.getUserInfo();
        List<Integer> sortedList = finder.getPrimeNumbers();
        sortedList.sort(Integer::compareTo);
        assertEquals(Arrays.asList(simpleNumbersRange100), sortedList);
    }

    @Test
    public void shouldReturnSimpleNumbers_whenThreadsAddFindingSimpleNumbersInOneCollection() throws InterruptedException {
        finder = new ThreadFinderByCommon();
        systemInMock.provideLines(START_RANGE, FINISH_RANGE, THREAD_COUNT);
        finder.getUserInfo();
        Thread.sleep(2000);
        assertEquals(SIMPLE_NUMBERS_IN_RANGE_100, finder.getPrimeNumbers().size());
    }

    @Test
    public void shouldReturnSimpleNumbersInCommonList_whenThreadsAreLocatedInPoolConnection() {
        finder = new ExecutorFinderByCommon();
        systemInMock.provideLines(START_RANGE, FINISH_RANGE, THREAD_COUNT);
        finder.getUserInfo();
        List<Integer> sortedList = finder.getPrimeNumbers();
        sortedList.sort(Integer::compareTo);
        assertEquals(Arrays.asList(simpleNumbersRange100), sortedList);
    }

    @Test
    public void shouldReturnSimpleNumbersInDifferentLists_whenThreadsAreLocatedInPoolConnection() {
        finder = new ExecutorFinderByLocal();
        systemInMock.provideLines(START_RANGE, FINISH_RANGE, THREAD_COUNT);
        finder.getUserInfo();
        List<Integer> sortedList = finder.getPrimeNumbers();
        sortedList.sort(Integer::compareTo);
        assertEquals(Arrays.asList(simpleNumbersRange100), sortedList);
    }
}

