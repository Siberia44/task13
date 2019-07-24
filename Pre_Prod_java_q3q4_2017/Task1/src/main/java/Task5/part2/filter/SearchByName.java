package Task5.part2.filter;

import Task5.part2.Filter;

import java.io.File;
import java.util.Scanner;

/**
 * Compare files by name
 */
public class SearchByName extends Filter {
    private String fileName;

    public SearchByName(Filter filter) {
        super(filter);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean check(Scanner scanner, File file) {
        return file.getName().matches(fileName + ".*") && checkNext(scanner, file);
    }
}
