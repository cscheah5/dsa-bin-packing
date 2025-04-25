package app.avltree;

public class AVLTreeNode {
	public double value;
	public int minIndex, height, index;
	public AVLTreeNode left, right;

	public AVLTreeNode(double value, int index) {
			this.index = index;
			this.minIndex = index;
			this.value = value;
		}

	public static void copyTo(AVLTreeNode fromNode, AVLTreeNode toNode) {
		if (fromNode == null || toNode == null)
			return;
		toNode.value = fromNode.value;
		toNode.index = fromNode.index;
	}

	@Override
	public String toString() {
		return "Bin #" + Integer.toString(index) + ": " + Double.toString(value);
	}
}
