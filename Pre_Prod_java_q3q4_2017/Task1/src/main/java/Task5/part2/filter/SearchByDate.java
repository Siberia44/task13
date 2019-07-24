package Task5.part2.filter;

import Task5.part2.Filter;

import java.io.File;
import java.util.Scanner;

/**
 * Performs a time comparison of file changes.
 */
public class SearchByDate extends Filter {
    private long minTime;
    private long maxTime;

    public SearchByDate(Filter filter) {
        super(filter);
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public boolean check(Scanner scanner, File file) {
        return file.lastModified() >= minTime && file.lastModified() <= maxTime && checkNext(scanner, file);
    }
}
