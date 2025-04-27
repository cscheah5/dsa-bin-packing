package app.avltree;

/**
 * Represents a node in an AVL Tree. Each node holds a value of type E, which
 * must be comparable, along with its height in the tree, an index (e.g., for
 * bin identification), and references to its left and right children.
 *
 * @param <E> the type of the value stored in the node, must extend Comparable
 */
public class AVLTreeNode<E extends Comparable<E>> {

    /**
     * The value stored in this node.
     */
    public E value;

    /**
     * The height of the subtree rooted at this node.
     */
    public int height;

    /**
     * An index associated with this node, often used for identification (e.g.,
     * bin number).
     */
    public int index;

    /**
     * The left child of this node.
     */
    public AVLTreeNode<E> left;

    /**
     * The right child of this node.
     */
    public AVLTreeNode<E> right;

    /**
     * Constructs a new AVLTreeNode with the given value and index. The height
     * is initialized to 1, assuming it's a leaf node initially. Left and right
     * children are initialized to null.
     *
     * @param value the value to store in the node
     * @param index the index associated with the node
     */
    public AVLTreeNode(E value, int index) {
        this.index = index;
        this.value = value;
        this.height = 1; // Initialize height to 1 for a new leaf node
        // left and right are implicitly null
    }

    /**
     * Copies the value, index, and height from one AVLTreeNode to another. Does
     * nothing if either node is null.
     *
     * @param <E> the type of the value stored in the nodes
     * @param fromNode the node to copy data from
     * @param toNode the node to copy data to
     */
    public static <E extends Comparable<E>> void copyTo(AVLTreeNode<E> fromNode, AVLTreeNode<E> toNode) {
        if (fromNode == null || toNode == null) {
            return;
        }
        toNode.value = fromNode.value;
        toNode.index = fromNode.index;
        toNode.height = fromNode.height;
    }

    /**
     * Returns a string representation of the node, typically showing its index
     * and value. Example: "Bin #1: [Value Details]"
     *
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "Bin #" + Integer.toString(index) + ": " + value.toString();
    }
}
