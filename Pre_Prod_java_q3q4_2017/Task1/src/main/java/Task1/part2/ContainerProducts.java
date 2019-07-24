package Task1.part2;

import Task1.part1.Beer;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ContainerProducts<E extends Beer> implements List<E> {
    private Beer[] arrayOfProducts;
    private int size;
    private int arrayIncrease = 10;

    public ContainerProducts() {
        size = 0;
        arrayOfProducts = new Beer[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(Object element) {
        if (isNull(element)) {
            for (int i = 0; i < size; i++)
                if (isNull(arrayOfProducts[i]))
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (element.equals(arrayOfProducts[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayOfProducts, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            return (T[]) Arrays.copyOf(arrayOfProducts, size, array.getClass());
        System.arraycopy(arrayOfProducts, 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }

    @Override
    public boolean add(E element) {
        if (arrayOfProducts.length == size) {
            arrayOfProducts = Arrays.copyOf(arrayOfProducts, arrayOfProducts.length + arrayIncrease);
        }
        arrayOfProducts[size++] = element;
        return true;
    }

    @Override
    public boolean remove(Object inputObject) {
        int foundIndex = indexOf(inputObject);
        if (foundIndex != -1) {
            size--;
            System.arraycopy(arrayOfProducts, foundIndex + 1, arrayOfProducts, foundIndex, size - foundIndex);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        Object[] arrayForCollection = collection.toArray();
        checkIndex(index);
        int tmpIndex = index;
        int tmpSize = size();
        for (int i = 0; i < collection.size(); i++) {
            add(tmpIndex++, (E) arrayForCollection[i]);
        }
        return size() == tmpSize + collection.size();
    }

    @Override
    public boolean removeAll(Collection collection) {
        Objects.requireNonNull(collection);
        boolean flag = false;
        for (int i = 0; i < collection.size(); i++) {
            if (contains(collection.toArray()[i])) {
                remove(collection.toArray()[i]);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (!collection.contains(arrayOfProducts[i])) {
                remove(arrayOfProducts[i]);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        arrayOfProducts = new Beer[0];
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) arrayOfProducts[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Beer tmp = arrayOfProducts[index];
        arrayOfProducts[index] = element;
        return (E) tmp;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        add(element);
        removeLastElementToPosition(index);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Object deletedElement = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arrayOfProducts, index + 1, arrayOfProducts, index,
                    numMoved);
        }
        arrayOfProducts[--size] = null;
        return (E) deletedElement;
    }

    @Override
    public int lastIndexOf(Object element) {
        if (!nonNull(element)) {
            for (int i = size - 1; i >= 0; i--) {
                if (!nonNull(arrayOfProducts[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (element.equals(arrayOfProducts[i])) {
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

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator(p -> true);
    }

    public Iterator<E> iterator(Predicate<Beer> predicate) {
        return new CustomIterator(predicate);
    }

    public Iterator customIterator(Predicate predicate) {
        return new CustomIterator(predicate);
    }

    @Override
    public String toString() {
        return "ContainerProducts{" +
                "arrayOfProducts=" + Arrays.toString(arrayOfProducts) +
                ", size=" + size +
                '}';
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is " + index + ", but size is " + size);
        }
    }

    private void removeLastElementToPosition(int index) {
        checkIndex(index);
        for (int i = size - 1; i > index; i--) {
            Beer tmp = arrayOfProducts[i];
            arrayOfProducts[i] = arrayOfProducts[i - 1];
            arrayOfProducts[i - 1] = tmp;
        }
    }

    class CustomIterator implements Iterator {
        private Predicate<Beer> predicate;
        private int currentPointer;
        private int nextPointer;
        private boolean callFlag;

        CustomIterator(Predicate<Beer> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int i = currentPointer; i < size; i++) {
                if (predicate.test(arrayOfProducts[i])) {
                    result = true;
                    nextPointer = i;
                    break;
                }
            }
            return result;
        }

        @Override
        public E next() {
            if (hasNext()) {
                currentPointer = nextPointer + 1;
                return (E) arrayOfProducts[nextPointer];
            }
            throw new NoSuchElementException("Array has no next element!");
        }

        @Override
        public void remove() {
            if (callFlag) {
                throw new IllegalStateException();
            } else {
                callFlag = true;
                ContainerProducts.this.remove(currentPointer--);
            }
        }
    }
}