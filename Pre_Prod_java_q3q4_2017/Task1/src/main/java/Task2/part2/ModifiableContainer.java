package Task2.part2;

import Task2.part2.exception.UnmodifiablePartException;

import java.util.*;

/**
 * <p>
 * <p>
 * The container stores two lists (unchangeable and changeable).
 * They form into one (first unchanged, then changeable).
 * Throws an exception when the method involves changing the first part of the list.
 *
 * @param <A> the type of elements held in this collection
 */
public class ModifiableContainer<A> implements List<A> {
    private List<A> modifiableList;
    private List<A> unmodifiableList;

    /**
     * @param unmodifiableList list with unmodifiable content
     * @param modifiableList   list with modifiable content
     * @throws IllegalArgumentException if one of two lists is null
     */
    public ModifiableContainer(List<A> unmodifiableList, List<A> modifiableList) {
        if (modifiableList == null || unmodifiableList == null) {
            throw new IllegalArgumentException();
        }
        this.modifiableList = modifiableList;
        this.unmodifiableList = unmodifiableList;
    }

    /**
     * Returns the sum of elements modifiable and unmodifiable lists.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return modifiableList.size() + unmodifiableList.size();
    }

    /**
     * Returns <tt>true</tt> if both lists contains no elements.
     *
     * @return <tt>true</tt> if both lists contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return (unmodifiableList.size() + modifiableList.size()) == 0;
    }

    /**
     * Returns <tt>true</tt> if both lists contains the specified element.
     * More formally, returns <tt>true</tt> if and only if any of the lists contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param object element whose presence in this list is to be tested
     * @return <tt>true</tt> if both of lists contains the specified element
     * @throws ClassCastException if the type of the specified element
     *                            is incompatible with this list
     *                            (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<A> iterator() {
        return new IteratorImpl<A>();
    }

    /**
     * Returns an array containing all of the elements in both lists
     * in proper sequence (first - unmodified list, second - modified list).
     *
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public Object[] toArray() {
        List<A> list = additionLists();
        return list.toArray();
    }

    /**
     * @param collection the array into which the elements of the list are to
     *                   be stored, if it is big enough; otherwise, a new array of the
     *                   same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     */
    @Override
    public <T> T[] toArray(T[] collection) {
        List<A> list = additionLists();
        if (collection.length < list.size()) {
            return (T[]) Arrays.copyOf(list.toArray(), list.size(), collection.getClass());
        }
        System.arraycopy(list, 0, collection, 0, list.size());
        if (collection.length > list.size()) {
            collection[size()] = null;
        }
        return collection;
    }

    /**
     * Appends the specified element to the end of modifiable list (optional
     * operation).
     *
     * @param object element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(A object) {
        modifiableList.add(object);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation). If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that.
     *
     * @param object element to be removed from this list, if present
     * @return <tt>true</tt> if modifiable list contained the specified element
     * @throws UnmodifiablePartException if the the first occurrence of the specified element is in unmodifiable
     *                                   list
     */
    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index < unmodifiableList.size()) {
            throw new UnmodifiablePartException("Can't change unmodifiable part. " +
                    "Unmodifiable size: " + unmodifiableList.size() + ", index: " + index);
        }
        return modifiableList.remove(object);
    }

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * @param collection collection to be checked for containment in this list
     * @return <tt>true</tt> if this list contains all of the elements of the
     * specified collection
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        List<A> list = additionLists();
        return list.containsAll(collection);
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * modifiable list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param collection collection containing elements to be added to this list
     * @return <tt>true</tt> if modifiable list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends A> collection) {
        Objects.requireNonNull(collection);
        return modifiableList.addAll(collection);
    }

    /**
     * Inserts all of the elements in the specified collection into modifiable
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index      index at which to insert the first element from the
     *                   specified collection
     * @param collection collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnmodifiablePartException if the <tt>addAll</tt> operation
     *                                   try add elements in unmodifiable list
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends A> collection) {
        checkIndex(index);
        Objects.requireNonNull(collection);
        if (index >= unmodifiableList.size()) {
            return modifiableList.addAll(index - unmodifiableList.size(), collection);
        }
        throw new UnmodifiablePartException("Can't change unmodifiable part. " +
                "Unmodifiable size: " + unmodifiableList.size() + ", index: " + index);
    }

    /**
     * Removes from modifiable list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param collection collection containing elements to be removed from this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnmodifiablePartException if the <tt>removeAll</tt> operation
     *                                   try remove elements from unmodifiable list
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        for (Object object : collection) {
            if (unmodifiableList.contains(object)) {
                throw new UnmodifiablePartException("Unmodifiable part have object from input collection");
            }
        }
        return modifiableList.removeAll(collection);
    }

    /**
     * Retains only the elements in modifiable list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param collection collection containing elements to be retained in this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnmodifiablePartException if the <tt>retainAll</tt> operation
     *                                   try change unmodifiable collection
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (unmodifiableList.containsAll(collection) && !modifiableList.containsAll(collection)) {
            throw new UnmodifiablePartException("Method retainAll() is not illegal for unmodifiable modifIter!");
        }
        return modifiableList.retainAll(collection);
    }

    /**
     * Removes all of the elements from this list (optional operation)
     * if and only if unmodifiable list is empty
     *
     * @throws UnmodifiablePartException if unmodifiable list is not empty
     */
    @Override
    public void clear() {
        if (unmodifiableList.size() == 0) {
            modifiableList.clear();
            return;
        }
        throw new UnmodifiablePartException("Unmodifiable part have objects");
    }

    /**
     * Returns the element at the specified position in both list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public A get(int index) {
        checkIndex(index);
        if (index < unmodifiableList.size()) {
            return unmodifiableList.get(index);
        }
        return modifiableList.get(index - unmodifiableList.size());
    }

    /**
     * Replaces the element at the specified position in modifiable list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnmodifiablePartException if the <tt>set</tt> operation
     *                                   try set element in unmodifiable list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public A set(int index, A element) {
        checkIndex(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.set(index - unmodifiableList.size(), element);
        }
        throw new UnmodifiablePartException("Can't change unmodifiable part." +
                " Unmodifiable size: " + unmodifiableList.size() + ", index: " + index);
    }

    /**
     * Inserts the specified element at the specified position in modifiable list
     * (optional operation). Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnmodifiablePartException if the <tt>add</tt> operation
     *                                   try to add element in unmodifiable list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, A element) {
        checkIndex(index);
        if (index >= unmodifiableList.size()) {
            modifiableList.add(index - unmodifiableList.size(), element);
        } else {
            throw new UnmodifiablePartException("Can't change unmodifiable part." +
                    "Unmodifiable size: " + unmodifiableList.size() + ", index: " + index);
        }
    }

    /**
     * Removes the element at the specified position in modifiable list (optional
     * operation). Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       try delete element from unmodifiable
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public A remove(int index) {
        checkIndex(index);
        if (index >= unmodifiableList.size()) {
            return modifiableList.remove(index - unmodifiableList.size());
        }
        throw new UnmodifiablePartException("Can't change unmodifiable part." +
                "Unmodifiable size: " + unmodifiableList.size() + ", index: " + index);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in both of list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param object element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object object) {
        int index = unmodifiableList.indexOf(object);
        if (index != -1) {
            return index;
        }
        index = modifiableList.indexOf(object);
        return index != -1 ? index + unmodifiableList.size() : -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in both of lists, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param object element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object object) {
        int index = modifiableList.lastIndexOf(object);
        if (index != -1) {
            return index + unmodifiableList.size();
        }
        return unmodifiableList.lastIndexOf(object);
    }

    @Override
    public ListIterator<A> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<A> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<A> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private List<A> additionLists() {
        List<A> list = new ArrayList<>(unmodifiableList);
        list.addAll(modifiableList);
        return list;
    }

    private void checkIndex(int index) {
        if (0 > index || index >= modifiableList.size() + unmodifiableList.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " go out of limit from list");
        }
    }

    private class IteratorImpl<A> implements Iterator<A> {
        Iterator readOnlyList = unmodifiableList.iterator();
        Iterator list = modifiableList.iterator();
        boolean flagForDelete;

        @Override
        public boolean hasNext() {
            return readOnlyList.hasNext() || list.hasNext();
        }

        @Override
        public A next() {
            if (readOnlyList.hasNext()) {
                return (A) readOnlyList.next();
            }
            if (list.hasNext()) {
                flagForDelete = true;
                return (A) list.next();
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (flagForDelete) {
                list.remove();
            } else {
                throw new UnmodifiablePartException("Unmodifiable part");
            }
        }
    }
}
