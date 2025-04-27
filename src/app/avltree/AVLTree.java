package app.avltree;

public class AVLTree<E extends Comparable<E>> {
    public AVLTreeNode<E> root;

    public void printInorder(AVLTreeNode<E> node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.println(node.toString());
        printInorder(node.right);
    }

    int height(AVLTreeNode<E> N) {
        if (N == null) return 0;
        return N.height;
    }

    public int find(E item) {
        return find(this.root, item);
    }

    private int find(AVLTreeNode<E> node, E item) {
        if (node == null) return Integer.MAX_VALUE;
        
        int cmp = item.compareTo(node.value);
        
        if (cmp > 0) return find(node.right, item);
        if (cmp < 0) {
            int rightMinIndex = node.right == null ? Integer.MAX_VALUE : node.right.minIndex;
            int minIndex = Math.min(node.index, rightMinIndex);
            minIndex = Math.min(minIndex, find(node.left, item));
            return minIndex;
        }

        int minIndex = Math.min(find(node.left, item), find(node.right, item));
        minIndex = Math.min(minIndex, node.index);
        return minIndex;
    }

    public void add(int index, E item) {
        this.root = add(this.root, index, item);
    }

    private AVLTreeNode<E> add(AVLTreeNode<E> node, int index, E item) {
        if (node == null) return new AVLTreeNode<>(item, index);

        int cmp = item.compareTo(node.value);
        if (cmp <= 0) {
            node.left = add(node.left, index, item);
        } else {
            node.right = add(node.right, index, item);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && item.compareTo(node.left.value) <= 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && item.compareTo(node.right.value) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && item.compareTo(node.left.value) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && item.compareTo(node.right.value) <= 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        adjustMinIndex(node);
        return node;
    }

    private AVLTreeNode<E> minValueNode(AVLTreeNode<E> node) {
        AVLTreeNode<E> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public void delete(int index, E item) {
        this.root = delete(this.root, index, item);
    }

    private AVLTreeNode<E> delete(AVLTreeNode<E> node, int index, E item) {
        if (node == null) return node;

        int cmp = item.compareTo(node.value);
        
        if (cmp < 0) {
            node.left = delete(node.left, index, item);
        } else if (cmp > 0) {
            node.right = delete(node.right, index, item);
        } else if (index != node.index) {
            node.left = delete(node.left, index, item);
            node.right = delete(node.right, index, item);
        } else {
            if (node.left == null || node.right == null) {
                AVLTreeNode<E> temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLTreeNode<E> temp = minValueNode(node.right);
                AVLTreeNode.copyTo(temp, node);
                node.right = delete(node.right, temp.index, temp.value);
            }
        }

        if (node == null) return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        adjustMinIndex(node);
        return node;
    }

    AVLTreeNode<E> rightRotate(AVLTreeNode<E> y) {
        if (y.left == null) return y;

        AVLTreeNode<E> x = y.left;
        AVLTreeNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        adjustMinIndex(y);
        adjustMinIndex(x);

        return x;
    }

    AVLTreeNode<E> leftRotate(AVLTreeNode<E> x) {
        if (x.right == null) return x;

        AVLTreeNode<E> y = x.right;
        AVLTreeNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        adjustMinIndex(x);
        adjustMinIndex(y);

        return y;
    }

    int getBalance(AVLTreeNode<E> N) {
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }

    private int adjustMinIndex(AVLTreeNode<E> node) {
        if (node == null) return Integer.MAX_VALUE;
        int minIndex = node.index;
        int leftMinIndex = node.left == null ? Integer.MAX_VALUE : node.left.minIndex;
        int rightMinIndex = node.right == null ? Integer.MAX_VALUE : node.right.minIndex;
        minIndex = Math.min(minIndex, leftMinIndex);
        minIndex = Math.min(minIndex, rightMinIndex);
        node.minIndex = minIndex;
        return minIndex;
    }

}