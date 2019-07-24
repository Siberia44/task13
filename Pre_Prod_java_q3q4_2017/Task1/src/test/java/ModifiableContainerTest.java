import Task2.part2.ModifiableContainer;
import Task2.part2.exception.UnmodifiablePartException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ModifiableContainerTest {
    ArrayList unmodify;
    ArrayList modify;
    private ModifiableContainer mixed;

    @Before
    public void initialize() {
        unmodify = new ArrayList();
        unmodify.add(1);
        unmodify.add(2);
        unmodify.add(3);
        modify = new ArrayList();
        modify.add(4);
        modify.add(5);
        mixed = new ModifiableContainer(unmodify, modify);
    }

    @Test
    public void sizeShouldReturnSizeOfBothLists() {
        assertEquals(5, mixed.size());
    }

    @Test
    public void isEmptyShouldReturnFalseIfCollectionNotIsNotExist() {
        assertFalse(mixed.isEmpty());
    }

    @Test
    public void containsShouldReturnTrueIfCollectionContainsObject() {
        assertTrue(mixed.contains(2));
    }

    @Test
    public void removeShouldRemoveObjectFromCollection() {
        mixed.remove(4);
        assertEquals(4, mixed.size());
    }

    @Test(expected = UnmodifiablePartException.class)
    public void removeShouldThrowExceptionIfYouTryRemoveObjectFromUnmodCollection() {
        mixed.remove(1);
    }

    @Test
    public void addShouldAddObjectAtTheEndOfTheList() {
        mixed.add(6);
        assertTrue(mixed.contains(6));
    }

    @Test(expected = UnmodifiablePartException.class)
    public void setShouldThrowException() {
        mixed.set(1, 3);
    }

    @Test
    public void addByIndexShouldAddObjectByIndex() {
        mixed.add(3, 10);
        assertTrue(mixed.contains(10));
    }

    @Test(expected = UnmodifiablePartException.class)
    public void addByIndexShouldThrowExceptionIfYouTryAddObjectInUnmodCollection() {
        mixed.add(2, 11);
    }

    @Test
    public void shouldIterator() {
        Iterator iterator = mixed.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
    }

    @Test
    public void removeByIndexShouldRemoveByIndex() {
        mixed.remove(4);
        assertEquals(4, mixed.size());
    }

    @Test
    public void getShouldGetObjectByIndex() {
        assertEquals(5, mixed.get(4));
    }

    @Test
    public void retainAllShouldSaveAllElementsFromCollection() {
        ArrayList collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        mixed.retainAll(collection);
        assertEquals(4, mixed.size());
    }

    @Test
    public void removeShouldDeleteElementByObject() {
        mixed.remove(4);
        assertEquals(4, mixed.size());
    }

    @Test(expected = UnmodifiablePartException.class)
    public void removeShouldThrowExceptionIfYouTryRemoveByObjectFromUnmodCollection() {
        mixed.remove(1);
    }

    @Test
    public void setShouldSetTheObjectByIndex() {
        mixed.set(3, 21);
        assertTrue(mixed.contains(21));
        assertEquals(3, mixed.indexOf(21));
    }

    @Test
    public void indexOfShouldReturnIndexObject() {
        assertEquals(2, mixed.indexOf(3));
        assertEquals(4, mixed.indexOf(5));
    }

    @Test
    public void lastIndexOfShouldReturnLastIndexObject() {
        assertEquals(2, mixed.lastIndexOf(3));
        assertEquals(4, mixed.lastIndexOf(5));
    }

    @Test
    public void indexOfShouldReturnNegativeNumberIfObjectNotContainsInCollection() {
        assertEquals(-1, mixed.indexOf(12));
        assertEquals(-1, mixed.indexOf(23));
    }

    @Test
    public void addAllShouldAddCollectionAtTheEndOfTheList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(32);
        arrayList.add(22);
        mixed.addAll(arrayList);
        assertEquals(7, mixed.size());
    }

    @Test(expected = UnmodifiablePartException.class)
    public void removeAllShouldThrowException() {
        ArrayList collection = new ArrayList();
        collection.add(3);
        collection.add(4);
        mixed.removeAll(collection);
    }

    @Test
    public void removeAllShouldRemoveAllWhatHaveIncomingCollectionFromCollection() {
        ArrayList collection = new ArrayList();
        collection.add(4);
        collection.add(5);
        mixed.removeAll(collection);
        assertEquals(3, mixed.size());
    }

    @Test
    public void shouldRemoveElementInIterator() {
        Iterator iterator = mixed.iterator();
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());
        iterator.remove();
        assertEquals(5, iterator.next());
    }

    @Test(expected = UnmodifiablePartException.class)
    public void shouldNotRemoveElementInIterator() {
        Iterator iterator = mixed.iterator();
        assertEquals(1, iterator.next());
        iterator.remove();
    }
}
