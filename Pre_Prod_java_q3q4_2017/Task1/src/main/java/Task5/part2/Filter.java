package Task5.part2;

import java.io.File;
import java.util.Scanner;

public abstract class Filter {
    Filter next;

    public Filter(Filter next) {
        this.next = next;
    }

    /**
     * Checks whether the files satisfy the condition
     *
     * @param scanner
     * @param file    file that is checked
     * @return
     */
    public abstract boolean check(Scanner scanner, File file);

    protected boolean checkNext(Scanner scanner, File file) {
        if (next == null) {
            return true;
        }
        return next.check(scanner, file);
    }
}
