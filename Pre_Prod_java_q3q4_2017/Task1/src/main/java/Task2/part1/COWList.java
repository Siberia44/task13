package Task2.part1;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A copy on write variant of {@link java.util.ArrayList} in which all mutative
 * operations ({@code add}, {@code set}, and so on) are implemented by
 * making a fresh copy of the underlying array.
 * <p>
 * The container matches all the contracts of the list, but,
 * if you use any method that changes the array, the array field is copied.
 * Thus, the data does not change when working with an iterator, since it uses snapshot
 *
 * @param <T> the type of elements held in this collection
 */
public class COWList<T> implements List<Object> {
    private Object[] elementData;

    public COWList() {
        elementData = new Object[0];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return elementData.length;
    }

    /**
     * @return 'True' if array don't have any
     * Object inside. 'False' if one or more
     * elements inside
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param element element whose presence in this list is to be tested
     * @return 'True' if this list contains the specified element
     */
    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(elementData);
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, elementData.length);
    }

    /**
     * @param elementData the array into which the elements of the list are to
     *                    be stored, if it is big enough; otherwise, a new array of the
     *                    same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public <T> T[] toArray(T[] elementData) {
        Object[] tmpArray = elementData;
        if (elementData.length < tmpArray.length)
            return (T[]) Arrays.copyOf(tmpArray, tmpArray.length, elementData.getClass());
        System.arraycopy(tmpArray, 0, elementData, 0, tmpArray.length);
        if (elementData.length > tmpArray.length)
            elementData[tmpArray.length] = null;
        return (T[]) tmpArray;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @return 'True'
     */
    @Override
    public boolean add(Object element) {
        Object[] tmpArray = elementData;
        tmpArray = Arrays.copyOf(tmpArray, tmpArray.length + 1);
        tmpArray[tmpArray.length - 1] = element;
        elementData = tmpArray;
        return true;
    }

    /**
     * Delete specified element form array and
     * move all elements, who was after him, on
     * one cell left.
     *
     * @param element which need to delete from array
     */
    @Override
    public boolean remove(Object element) {
        Object[] tmpArray = elementData;
        int foundIndex = indexOf(element);
        if (foundIndex != -1) {
            Object[] newArray = new Object[elementData.length - 1];
            System.arraycopy(tmpArray, foundIndex + 1, newArray, foundIndex, tmpArray.length - 1 - foundIndex);
            elementData = newArray;
            return true;
        }
        return false;
    }

    /**
     * Returns 'true' if this list contains the specified elements
     * from specified collection in proper sequence (from first to
     * last element).
     *
     * @param inputCollection specified collection
     * @return 'true' when all elements from collection equals in
     * proper sequence
     * @throws NullPointerException
     */
    @Override
    public boolean containsAll(Collection inputCollection) {
        for (Object object : inputCollection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @return 'true' if this list changed as a result of the call
     * @throws NullPointerException
     */
    @Override
    public boolean addAll(Collection collection) {
        Object[] tmpArray = elementData;
        Object[] newArray = Arrays.copyOf(tmpArray, tmpArray.length + collection.size());
        System.arraycopy(collection.toArray(), 0, newArray, tmpArray.length, collection.size());
        elementData = newArray;
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index      index at which to insert the first element from the
     *                   specified collection
     * @param collection collection containing elements to be added to this list
     * @return 'true' if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if index > size || index < 0
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection collection) {
        checkIndex(index);
        Object[] tmpArray = elementData;
        int newLength = tmpArray.length + collection.size();
        Object[] newArray = Arrays.copyOf(tmpArray, newLength);
        System.arraycopy(newArray, index, newArray, index + collection.size(), tmpArray.length - index);
        System.arraycopy(collection.toArray(), 0, newArray, index, collection.size());
        elementData = newArray;
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param collection All elements from list which equals
     *                   with elements from collection will
     *                   be removed
     * @return 'true' if this list changed as a result of the call
     * @throws NullPointerException
     */
    @Override
    public boolean removeAll(Collection collection) {
        Object[] tmpArray = elementData;
        Object[] newArray = new Object[tmpArray.length];
        int length = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (!collection.contains(tmpArray[i]))
                newArray[length++] = tmpArray[i];
        }
        elementData = Arrays.copyOf(newArray, length);
        return true;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.
     *
     * @param collection collection containing elements to be retained in this list
     * @return 'true' if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection collection) {
        Object[] tmpArray = elementData;
        Object[] newArray = new Object[tmpArray.length];
        int length = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (collection.contains(tmpArray[i])) {
                newArray[length++] = tmpArray[i];
            }
        }
        elementData = Arrays.copyOf(newArray, length);
        return true;
    }

    /**
     * Removes all of the elements from this list
     */
    @Override
    public void clear() {
        elementData = new Object[0];
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Object get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Object set(int index, Object element) {
        checkIndex(index);
        Object[] tmpArray = Arrays.copyOf(elementData, elementData.length);
        Object previousElement = tmpArray[index];
        tmpArray[index] = element;
        elementData = tmpArray;
        return previousElement;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, Object element) {
        checkIndex(index);
        Object[] tmpArray = elementData;
        Object[] newArray = new Object[tmpArray.length + 1];
        System.arraycopy(tmpArray, index, newArray, index + 1, tmpArray.length - index);
        newArray[index] = element;
        elementData = newArray;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object[] tmpArray = elementData;
        Object deletedElement = tmpArray[index];
        System.arraycopy(tmpArray, index + 1, tmpArray, index, tmpArray.length - index - 1);
        tmpArray = Arrays.copyOf(tmpArray, tmpArray.length - 1);
        elementData = tmpArray;
        return deletedElement;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(Object element) {
        if (isNull(element)) {
            for (int i = 0; i < elementData.length; i++) {
                if (isNull(elementData[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elementData.length; i++) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int lastIndexOf(Object element) {
        if (!nonNull(element)) {
            for (int i = elementData.length - 1; i >= 0; i--) {
                if (!nonNull(elementData[i])) {
                    return i;
                }
            }
        } else {
            for (int i = elementData.length - 1; i >= 0; i--) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int index) {
        if (index > elementData.length || index < 0) {
            throw new IndexOutOfBoundsException("Index is " + index + ", but size is " + elementData.length);
        }
    }

    /**
     * An iterator over a collection.  {@code Iterator} takes the place of
     * {@link Enumeration} in the Java Collections Framework.  Iterators
     * differ from enumerations in two ways:
     * <p>
     * <p>
     * An iterator working with snapshot. When trying to change data in a class,
     * the state of the data in the iterator does not change
     *
     * @param <T> the type of elements returned by this iterator
     * @see Collection
     * @see ListIterator
     * @see Iterable
     */
    class IteratorImpl<T> implements Iterator<T> {
        private int currentIndex = 0;
        private Object[] snapshot;

        public IteratorImpl(Object[] snapshot) {
            this.snapshot = snapshot;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < snapshot.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) snapshot[currentIndex++];
        }
    }
}