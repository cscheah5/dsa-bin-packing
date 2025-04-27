package app.avltree;

/**
 * Interface defining the basic operations for a tree data structure.
 *
 * @param <E> the type of elements stored in the tree
 */
public interface Tree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Searches for the specified element in the tree.
     * @param e the element to search for.
     * @return true if the element is found, false otherwise.
     */
    boolean search(E e);

    /**
     * Inserts the specified element into the tree with an index.
     * @param index the index at which the element should be inserted.
     * @param e the element to insert.
     * @return true if the element was inserted successfully, false otherwise.
     */
    boolean insert(int index, E e);

    /**
     * Deletes the specified element from the tree with a given index.
     * @param index the index of the element to delete.
     * @param e the element to delete.
     * @return true if the element was deleted successfully, false otherwise.
     */
    boolean delete(int index, E e);

    /**
     * Finds the smallest index of a node with a value greater than or equal to the specified item.
     * @param item the item to compare.
     * @return the smallest index of a node with a value >= item.
     */
    int find(E item);

    /**
     * Performs an inorder traversal starting from the root.
     */
    void inorder();

    /**
     * Performs a postorder traversal starting from the root.
     */
    void postorder();

    /**
     * Performs a preorder traversal starting from the root.
     */
    void preorder();

    /**
     * Checks if the tree is empty.
     * @return true if the tree contains no elements, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of nodes in the tree.
     * @return the size of the tree.
     */
    int getSize();

    /**
     * Returns the height of the tree.
     * @return the height of the tree.
     */
    int getHeight();
}
