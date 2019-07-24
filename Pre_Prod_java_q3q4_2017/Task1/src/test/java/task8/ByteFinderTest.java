package task8;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import task8.part2.ByteController;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ByteFinderTest {

    private static final String VALID_FILENAME = "src\\main\\resources\\1.txt";
    private static final String INVALID_FILENAME = "www";
    private static final String GET_THREAD_INFO = "1";
    private static final Integer FIRST_INDEX = 5;
    private static final Integer LAST_INDEX = 4;

    @Rule
    public final TextFromStandardInputStream systemInMock =
            emptyStandardInputStream();
    private ByteController controller;

    @Before
    public void setUp() {
        controller = new ByteController();
    }

    @Test
    public void shouldFindRepeatingByteSequenceFromFile_whenEnterFilename() throws InterruptedException {
        systemInMock.provideLines(VALID_FILENAME, GET_THREAD_INFO, INVALID_FILENAME);
        controller.startProcessing();
        Thread.sleep(2000);
        assertEquals(FIRST_INDEX, controller.getFirstOccurrenceIndex());
        assertEquals(LAST_INDEX, controller.getSecondOccurrenceIndex());
    }
}

