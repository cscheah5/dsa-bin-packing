package app.model;

/**
 * Represents a container that follows the Last-In, First-Out (LIFO) principle,
 * similar to a stack. It defines operations for adding, removing, and
 * inspecting elements, as well as checking its capacity and state.
 *
 * @param <E> the type of elements held in this container
 */
public interface StackContainer<E> {

    /**
     * Retrieves, but does not remove, the element at the top of this container.
     *
     * @return the element at the top of this container, or {@code null} if this
     * container is empty
     */
    E peek();

    /**
     * Removes and returns the element at the top of this container.
     *
     * @return the element at the top of this container, or {@code null} if this
     * container is empty
     */
    E pop();

    /**
     * Adds an element to the top of this container.
     *
     * @param object the element to add
     */
    void push(E object);

    /**
     * Returns the number of elements in this container.
     *
     * @return the number of elements in this container
     */
    public int getSize();

    /**
     * Checks if this container is empty.
     *
     * @return {@code true} if this container contains no elements,
     * {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Checks if the given item can fit into this container based on certain
     * criteria (e.g., weight capacity).
     *
     * @param item the item to check
     * @return {@code true} if the item can fit, {@code false} otherwise
     */
    boolean canFit(Item item);
}
