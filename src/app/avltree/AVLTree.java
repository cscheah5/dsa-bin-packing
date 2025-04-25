package app.avltree;

public class AVLTree {
	public AVLTreeNode root;

	public void printInorder(AVLTreeNode node) {
		if (node == null)
			return;
		printInorder(node.left);
		System.out.println(node.toString());
		printInorder(node.right);
	}

	int height(AVLTreeNode N) {
		if (N == null)
			return 0;

		return N.height;
	}

	public int find(double item) {
		int index = find(this.root, item);
		return index;
	}

	private int find(AVLTreeNode node, double item) {
		if (node == null)
			return Integer.MAX_VALUE;
		if (node.value < item)
			return find(node.right, item);
		if (node.value > item) {
			int rightMinIndex = node.right == null ? Integer.MAX_VALUE : node.right.minIndex;
			int minIndex = Math.min(node.index, rightMinIndex);
			minIndex = Math.min(minIndex, find(node.left, item));
			return minIndex;
		}

		int minIndex = Math.min(find(node.left, item), find(node.right, item));
		minIndex = Math.min(minIndex, node.index);
		return minIndex;
	}

	public void add(int index, double item) {
		this.root = add(this.root, index, item);
		// adjustMinIndex(this.root, true);
	}

	private AVLTreeNode add(AVLTreeNode node, int index, double item) {

		if (node == null)
			return new AVLTreeNode(item, index);

		if (item <= node.value)
			node.left = add(node.left, index, item);
		else
			node.right = add(node.right, index, item);

		node.height = 1 + Math.min(height(node.left), height(node.right));

		int balance = getBalance(node);

		if (balance > 1 && item < node.left.value)
			return rightRotate(node);

		if (balance < -1 && item > node.right.value)
			return leftRotate(node);

		if (balance > 1 && item > node.left.value) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && item < node.right.value) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		adjustMinIndex(node);
		return node;
	}

	private AVLTreeNode minValueNode(AVLTreeNode node) {
		AVLTreeNode current = node;

		while (current.left != null)
			current = current.left;

		return current;
	}

	public void delete(int index, double item) {
		this.root = this.delete(this.root, index, item);
		// adjustMinIndex(this.root, true);
	}

	private AVLTreeNode delete(AVLTreeNode node, int index, double item) {
		if (node == null)
			return node;

		if (item < node.value)
			node.left = delete(node.left, index, item);
		else if (item > node.value)
			node.right = delete(node.right, index, item);
		else if (index != node.index) {
			node.left = delete(node.left, index, item);
			node.right = delete(node.right, index, item);
		} else {

			if ((node.left == null) || (node.right == null)) {
				AVLTreeNode temp = null;
				if (temp == node.left)
					temp = node.right;
				else
					temp = node.left;

				if (temp == null) {
					temp = node;
					node = null;
				} else
					node = temp;

			} else {
				AVLTreeNode temp = minValueNode(node.right);

				AVLTreeNode.copyTo(temp, node);
				node.right = delete(node.right, temp.index, temp.value);
			}
		}

		if (node == null)
			return node;

		node.height = Math.max(height(node.left), height(node.right)) + 1;

		int balance = getBalance(node);

		if (balance > 1 && getBalance(node) >= 0)
			return rightRotate(node);

		if (balance > 1 && getBalance(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && getBalance(node.right) <= 0)
			return leftRotate(node);

		if (balance < -1 && getBalance(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		adjustMinIndex(node);
		return node;
	}

	AVLTreeNode rightRotate(AVLTreeNode y) {
		AVLTreeNode x = y.left;

		if (x == null) {
			return y;
		}

		AVLTreeNode T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		adjustMinIndex(y);
		adjustMinIndex(x);

		return x;
	}

	AVLTreeNode leftRotate(AVLTreeNode x) {
		AVLTreeNode y = x.right;

		if (y == null) {
			return x;
		}

		AVLTreeNode T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		adjustMinIndex(x);
		adjustMinIndex(y);

		return y;
	}

	int getBalance(AVLTreeNode N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	private int adjustMinIndex(AVLTreeNode node) {
		if (node == null)
			return Integer.MAX_VALUE;
		int minIndex = node.index;
		int leftMinIndex = node.left == null ? Integer.MAX_VALUE : node.left.minIndex;
		int rightMinIndex = node.right == null ? Integer.MAX_VALUE : node.right.minIndex;
		minIndex = Math.min(minIndex, leftMinIndex);
		minIndex = Math.min(minIndex, rightMinIndex);
		node.minIndex = minIndex;
		return minIndex;
	}
}
