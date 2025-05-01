package app.avltree;

import java.util.List;

/**
 * AVLTree class implementing a self-balancing binary search tree.
 *
 * @param <E> the type of elements maintained by this tree
 */
public class AVLTree<E extends Comparable<E>> extends AbstractTree<E, AVLTreeNode<E>> implements Tree<E> {

    /**
     * The root node of the AVL tree.
     */
    public AVLTreeNode<E> root;

    /**
     * Performs an inorder traversal of the AVL tree, printing each node.
     *
     * @param node the starting node for traversal
     */
    @Override
    public void inorder(AVLTreeNode<E> node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.toString());
        inorder(node.right);
    }

    /**
     * Returns the height of a given node.
     *
     * @param N the node
     * @return the height of the node, or 0 if null
     */
    int height(AVLTreeNode<E> N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    /**
     * Finds the minimum index of a node with value >= item.
     *
     * @param item the value to search for
     * @return the minimum index, or Integer.MAX_VALUE if not found
     */
    @Override
    public int find(E item) {
        return find(this.root, item);
    }

    /**
     * Helper method for find operation.
     *
     * @param node the current node
     * @param item the value to search for
     * @return the minimum index, or Integer.MAX_VALUE if not found
     */
    private int find(AVLTreeNode<E> node, E item) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int cmp = item.compareTo(node.value);

        if (cmp > 0) {
            return find(node.right, item);
        }
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

    /**
     * Adds a new node with the given index and value to the AVL tree.
     *
     * @param index the index associated with the value
     * @param item the value to add
     */
    public void add(int index, E item) {
        this.root = add(this.root, index, item);
    }

    /**
     * Helper method for add operation. Inserts a node and rebalances the tree
     * if necessary.
     *
     * @param node the current node
     * @param index the index associated with the value
     * @param item the value to add
     * @return the new root of the subtree
     */
    private AVLTreeNode<E> add(AVLTreeNode<E> node, int index, E item) {
        if (node == null) {
            return new AVLTreeNode<>(item, index);
        }

        int cmp = item.compareTo(node.value);
        if (cmp <= 0) {
            node.left = add(node.left, index, item);
        } else {
            node.right = add(node.right, index, item);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && item.compareTo(node.left.value) <= 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && item.compareTo(node.right.value) > 0) {
            return leftRotate(node);
        }

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

    /**
     * Returns the node with the minimum value in the given subtree.
     *
     * @param node the root of the subtree
     * @return the node with the minimum value
     */
    private AVLTreeNode<E> minValueNode(AVLTreeNode<E> node) {
        AVLTreeNode<E> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Deletes a node with the given index and value from the AVL tree.
     *
     * @param index the index associated with the value
     * @param item the value to delete
     */
    @Override
    public void delete(int index, E item) {
        this.root = delete(this.root, index, item);
    }

    /**
     * Helper method for delete operation. Removes a node and rebalances the
     * tree if necessary.
     *
     * @param node the current node
     * @param index the index associated with the value
     * @param item the value to delete
     * @return the new root of the subtree
     */
    private AVLTreeNode<E> delete(AVLTreeNode<E> node, int index, E item) {
        if (node == null) {
            return node;
        }

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

        if (node == null) {
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        adjustMinIndex(node);
        return node;
    }

    /**
     * Performs a right rotation on the given node.
     *
     * @param y the node to rotate
     * @return the new root after rotation
     */
    AVLTreeNode<E> rightRotate(AVLTreeNode<E> y) {
        if (y.left == null) {
            return y;
        }

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

    /**
     * Performs a left rotation on the given node.
     *
     * @param x the node to rotate
     * @return the new root after rotation
     */
    AVLTreeNode<E> leftRotate(AVLTreeNode<E> x) {
        if (x.right == null) {
            return x;
        }

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

    /**
     * Returns the balance factor of the given node.
     *
     * @param N the node
     * @return the balance factor
     */
    int getBalance(AVLTreeNode<E> N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    /**
     * Adjusts the minIndex property for the given node and its subtree.
     *
     * @param node the node to adjust
     * @return the minimum index in the subtree
     */
    private int adjustMinIndex(AVLTreeNode<E> node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int minIndex = node.index;
        int leftMinIndex = node.left == null ? Integer.MAX_VALUE : node.left.minIndex;
        int rightMinIndex = node.right == null ? Integer.MAX_VALUE : node.right.minIndex;
        minIndex = Math.min(minIndex, leftMinIndex);
        minIndex = Math.min(minIndex, rightMinIndex);
        node.minIndex = minIndex;
        return minIndex;
    }

    /**
     * Returns the height of the AVL tree.
     *
     * @return the height of the tree
     */
    @Override
    public int getHeight() {
        return height(this.root);
    }

    /**
     * Performs an inorder traversal and adds node values to the provided list.
     *
     * @param list the list to add values to
     * @param node the starting node for traversal
     */
    @Override
    protected void inorderTraversal(List<E> list, AVLTreeNode<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(list, node.left);
        list.add(node.value);
        inorderTraversal(list, node.right);
    }

    /**
     * Returns the root node of the AVL tree.
     *
     * @return the root node
     */
    @Override
    protected AVLTreeNode<E> getRoot() {
        return this.root;
    }
}
