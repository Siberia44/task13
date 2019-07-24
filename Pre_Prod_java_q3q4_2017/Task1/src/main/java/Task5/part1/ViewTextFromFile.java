package Task5.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ViewTextFromFile implements Iterable {
    private String fileName;
    private List<String> lines;

    public ViewTextFromFile(String fileName) {
        this.fileName = fileName;
    }

    public void viewText() {
        try {
            for (Iterator iter = iterator(); iter.hasNext(); ) {
                System.out.println(iter.next());
            }
        } catch (CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
        return new Iterator() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < lines.size();
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return lines.get(currentIndex++);
            }
        };
    }
}
