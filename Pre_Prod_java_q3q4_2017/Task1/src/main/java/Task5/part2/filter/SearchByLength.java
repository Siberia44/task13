package Task5.part2.filter;

import Task5.part2.Filter;

import java.io.File;
import java.util.Scanner;

/**
 * Performs file size comparison.
 */
public class SearchByLength extends Filter {
    private long sizeMin;
    private long sizeMax;

    public SearchByLength(Filter filter) {
        super(filter);
    }

    public void setSizeMin(long sizeMin) {
        this.sizeMin = sizeMin;
    }

    public void setSizeMax(long sizeMax) {
        this.sizeMax = sizeMax;
    }

    @Override
    public boolean check(Scanner scanner, File file) {
        return file.length() >= sizeMin && file.length() <= sizeMax && checkNext(scanner, file);
    }
}
