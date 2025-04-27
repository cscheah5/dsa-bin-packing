package app.model;

import java.util.*;

/**
 * Abstract base class for implementing a stack-based container for items.
 * This class provides a basic implementation of the StackContainer interface using an ArrayList.
 *
 * @param <E> the type of Item this container holds
 */
public abstract class AbstractStackContainer<E extends Item> implements StackContainer<E> {

    /**
     * The list holding the items in the stack.
     */
    protected ArrayList<E> items;

    /**
     * Constructs an empty AbstractStackContainer.
     */
    public AbstractStackContainer() {
        items = new ArrayList<>();
    }

    /**
     * Constructs an AbstractStackContainer initialized with the given list of items.
     *
     * @param items The initial list of items.
     */
    public AbstractStackContainer(ArrayList<E> items) {
        super();
        this.items = items;
    }

    /**
     * Checks if the stack container is empty.
     *
     * @return true if the container is empty or the underlying list is null, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return items == null || getSize() == 0;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     * Assumes the stack is not empty.
     *
     * @return The item at the top of the stack.
     */
    @Override
    public E peek() {
        return items.get(getSize() - 1);
    }

    /**
     * Removes and returns the item at the top of the stack.
     * Assumes the stack is not empty.
     *
     * @return The item removed from the top of the stack.
     */
    @Override
    public E pop() {
        E o = items.get(getSize() - 1);
        items.remove(getSize() - 1);
        return o;
    }

    /**
     * Adds an item to the top of the stack.
     *
     * @param object The item to be pushed onto the stack.
     */
    @Override
    public void push(E object) {
        items.add(object);
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return The size of the stack.
     */
    @Override
    public int getSize() {
        return items.size();
    }
}
