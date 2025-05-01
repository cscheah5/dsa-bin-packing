package app.avltree;

/**
 * AVLTreeNode class for AVL tree structure.
 *
 * @param <E> the type of element stored in the node
 */
public class AVLTreeNode<E extends Comparable<E>> {

    /**
     * The value stored in the node.
     */
    public E value;
    /**
     * The minimum index in the subtree rooted at this node.
     */
    public int minIndex;
    /**
     * The height of the node in the AVL tree.
     */
    public int height;
    /**
     * The index associated with the value.
     */
    public int index;
    /**
     * Reference to the left child node.
     */
    public AVLTreeNode<E> left;
    /**
     * Reference to the right child node.
     */
    public AVLTreeNode<E> right;

    /**
     * Constructs a new AVLTreeNode with the given value and index.
     *
     * @param value the value to store
     * @param index the index associated with the value
     */
    public AVLTreeNode(E value, int index) {
        this.index = index;
        this.minIndex = index;
        this.value = value;
    }

    /**
     * Copies the value, index, height, and minIndex from one node to another.
     *
     * @param fromNode the node to copy from
     * @param toNode the node to copy to
     * @param <E> the type of element stored in the node
     */
    public static <E extends Comparable<E>> void copyTo(AVLTreeNode<E> fromNode, AVLTreeNode<E> toNode) {
        if (fromNode == null || toNode == null) {
            return;
        }
        toNode.value = fromNode.value;
        toNode.index = fromNode.index;
        toNode.height = fromNode.height;  // Copy height
        toNode.minIndex = fromNode.minIndex;  // Copy minIndex
    }

    /**
     * Returns a string representation of the node.
     *
     * @return a string in the format "Bin #index: value"
     */
    @Override
    public String toString() {
        return "Bin #" + Integer.toString(index) + ": " + value.toString();
    }
}
