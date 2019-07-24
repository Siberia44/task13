package Task5.part2.filter;

import Task5.part2.Filter;

import java.io.File;
import java.util.Scanner;

/**
 * Performs file type comparison
 */
public class SearchByExtension extends Filter {
    private String extension;

    public SearchByExtension(Filter filter) {
        super(filter);
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean check(Scanner scanner, File file) {
        return file.getName().endsWith(extension) && checkNext(scanner, file);
    }
}
