package Task5.part2;

import java.io.File;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager();
        filterManager.enterDirectory();
        List<File> files = filterManager.fill();
        for (File file : files) {
            System.out.println(file);
        }
    }
}
