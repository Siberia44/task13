package Task3.part1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Contains elements without duplicates.
 */

public class SetList extends ArrayList {

    /**
     * Add new element into list
     *
     * @param element
     * @return
     * @throws IllegalStateException if the item being added to
     *                               the collection already exists
     */
    @Override
    public boolean add(Object element) {
        checkContainsElement(element);
        return super.add(element);
    }

    /**
     * Adds an element by index
     *
     * @param index
     * @param element
     * @throws IllegalStateException if the item being added to
     *                               the collection already exists
     */
    @Override
    public void add(int index, Object element) {
        checkContainsElement(element);
        super.add(index, element);
    }

    /**
     * Add all elements from collection in param into list
     *
     * @param collection
     * @return
     * @throws IllegalStateException if the collection in param
     *                               contains duplicates
     */
    @Override
    public boolean addAll(Collection collection) {
        checkEqualsElements(collection);
        for (Object object : collection) {
            checkContainsElement(object);
        }
        return super.addAll(collection);
    }

    /**
     * Add all elements from collection in param into list
     * by index
     *
     * @param index
     * @param collection
     * @return
     * @throws IllegalStateException if the collection in param
     *                               contains duplicates
     */
    @Override
    public boolean addAll(int index, Collection collection) {
        checkEqualsElements(collection);
        for (Object listElement : collection) {
            checkContainsElement(listElement);
        }
        return super.addAll(index, collection);
    }

    /**
     * Input element into list by index
     *
     * @param index
     * @param element
     * @return
     * @throws IllegalStateException if the item being added to
     *                               the collection already exists
     */
    @Override
    public Object set(int index, Object element) {
        checkContainsElement(element);
        return super.set(index, element);
    }

    private void checkEqualsElements(Collection collection) {
        if (collection.stream().distinct().count() != collection.size()) {
            throw new IllegalStateException("Collection have duplicates");
        }
    }

    private void checkContainsElement(Object object) {
        if (contains(object)) {
            throw new IllegalStateException("This item is already in the collection.");
        }
    }
}

