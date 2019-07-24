package Task5.part2;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles all existing file comparisons and searches for file comparisons.
 */
public class FilterManager {
    private Filter filter;
    private Scanner scanner = new Scanner(System.in);
    private List<File> list = new LinkedList<>();
    private String directory;
    private FilterController filterController = new FilterController();

    public FilterManager() {
    }

    /**
     * Asks to enter a directory to check
     */
    public void enterDirectory() {
        System.out.println("directory");
        directory = scanner.next();
    }

    /**
     * Fills the filter chain and calls the file search.
     *
     * @return a list that is filled with necessary files
     */
    public List<File> fill() {
        filter = filterController.date(scanner, filter);
        filter = filterController.extension(scanner, filter);
        filter = filterController.size(scanner, filter);
        filter = filterController.name(scanner, filter);
        filter = filterController.allFilesAreTrue(filter);
        return search(new File(directory));
    }

    /**
     * Searches files in the specified directory
     *
     * @param file
     * @return list of files that are suitable to compare
     */
    public List<File> search(File file) {
        File[] files = file.listFiles();
        if (Objects.nonNull(files) && files.length > 0) {
            for (File fileForSearch : files) {
                if (fileForSearch.isDirectory()) {
                    search(fileForSearch);
                } else if (filter != null && filter.check(scanner, fileForSearch)) {
                    list.add(fileForSearch);
                }
            }
        }
        return list;
    }
}
