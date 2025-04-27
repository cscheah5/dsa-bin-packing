package app.avltree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for tree implementations. Provides common functionality
 * like size tracking and inorder iteration.
 *
 * @param <E> the type of elements stored in the tree, must be comparable
 * @param <N> the type of the nodes used in the tree implementation
 */
public abstract class AbstractTree<E extends Comparable<E>, N> implements Tree<E> {

    /**
     * The number of elements in the tree.
     */
    protected int size = 0;

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the tree in inorder sequence.
     *
     * @return an inorder iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    /**
     * Inner class providing an inorder iterator implementation.
     */
    private class InorderIterator implements Iterator<E> {

        // Stores the elements in inorder sequence
        private final List<E> elements = getInorderList();
        // Tracks the current position in the list
        private int current = 0;

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return current < elements.size();
        }

        /**
         * Returns the next element in the inorder sequence.
         *
         * @return the next element.
         */
        @Override
        public E next() {
            return elements.get(current++);
        }
    }

    /**
     * Helper method to get a list of elements in inorder sequence.
     *
     * @return a list containing elements in inorder.
     */
    protected List<E> getInorderList() {
        List<E> list = new ArrayList<>();
        // Use correctly typed methods
        inorderTraversal(list, getRoot());
        return list;
    }

    /**
     * Abstract method to perform an inorder traversal starting from a given
     * node. Concrete subclasses must implement this method.
     *
     * @param list the list to add elements to during traversal.
     * @param node the starting node for the traversal.
     */
    protected abstract void inorderTraversal(List<E> list, N node);

    /**
     * Abstract method to get the root node of the tree. Concrete subclasses
     * must implement this method.
     *
     * @return the root node.
     */
    protected abstract N getRoot();
}
