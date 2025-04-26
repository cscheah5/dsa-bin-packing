package app.avltree;

public interface Tree<E extends Comparable<E>> extends Iterable<E> {
    /** Return true if the element is in the tree */
    boolean search(E e);

    /** Insert element into the tree with an index
     * Return true if the element is inserted successfully */
    boolean insert(int index, E e);

    /** Delete the specified element with given index from the tree
     * Return true if the element is deleted successfully */
    boolean delete(int index, E e);

    /** Find the smallest index of node with value >= item */
    int find(E item);

    /** Inorder traversal from the root */
    void inorder();

    /** Postorder traversal from the root */
    void postorder();

    /** Preorder traversal from the root */
    void preorder();

    /** Return true if the tree is empty */
    boolean isEmpty();

    /** Get the number of nodes in the tree */
    int getSize();

    /** Get the height of the tree */
    int getHeight();
}