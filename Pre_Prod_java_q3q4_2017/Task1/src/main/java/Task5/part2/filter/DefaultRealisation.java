package Task5.part2.filter;

import Task5.part2.Filter;

import java.io.File;
import java.util.Scanner;

/**
 * Default implementation of the filter. All files are suitable.
 */
public class DefaultRealisation extends Filter {

    public DefaultRealisation(Filter next) {
        super(next);
    }

    @Override
    public boolean check(Scanner scanner, File file) {
        return true;
    }
}
