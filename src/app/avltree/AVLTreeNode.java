package app.avltree;

public class AVLTreeNode<E extends Comparable<E>> {

    public E value;
    public int height, index;
    public AVLTreeNode<E> left, right;

    public AVLTreeNode(E value, int index) {
        this.index = index;
        this.value = value;
        this.height = 1; // Initialize height to 1 for a new leaf node
    }

    public static <E extends Comparable<E>> void copyTo(AVLTreeNode<E> fromNode, AVLTreeNode<E> toNode) {
        if (fromNode == null || toNode == null) {
            return;
        }
        toNode.value = fromNode.value;
        toNode.index = fromNode.index;
        toNode.height = fromNode.height;
    }

    @Override
    public String toString() {
        return "Bin #" + Integer.toString(index) + ": " + value.toString();
    }
}
