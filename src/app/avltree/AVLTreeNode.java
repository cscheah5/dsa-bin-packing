package app.avltree;

public class AVLTreeNode<E extends Comparable<E>> {
    public E value;
    public int minIndex, height, index;
    public AVLTreeNode<E> left, right;

    public AVLTreeNode(E value, int index) {
        this.index = index;
        this.minIndex = index;
        this.value = value;
    }

    public static <E extends Comparable<E>> void copyTo(AVLTreeNode<E> fromNode, AVLTreeNode<E> toNode) {
        if (fromNode == null || toNode == null) return;
        toNode.value = fromNode.value;
        toNode.index = fromNode.index;
        toNode.height = fromNode.height;  // Copy height
        toNode.minIndex = fromNode.minIndex;  // Copy minIndex
    }

    @Override
    public String toString() {
        return "Bin #" + Integer.toString(index) + ": " + value.toString();
    }
}