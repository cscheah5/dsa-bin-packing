package app.avltree;

import java.util.List;

/**
 * Implementation of an AVL Tree, a self-balancing binary search tree.
 *
 * @param <E> the type of elements stored in the tree, must be comparable
 */
public class AVLTree<E extends Comparable<E>> extends AbstractTree<E, AVLTreeNode<E>> {

    /** The root node of the AVL tree. */
    public AVLTreeNode<E> root;

    /**
     * Returns the height of a given node.
     *
     * @param N the node whose height is to be calculated
     * @return the height of the node, or 0 if the node is null
     */
    int getHeight(AVLTreeNode<E> N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    /**
     * Searches for an element in the AVL tree.
     *
     * @param e the element to search for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean search(E e) {
        return search(this.root, e);
    }

    /**
     * Helper method for searching an element in the AVL tree.
     *
     * @param node the current node
     * @param item the element to search for
     * @return true if the element is found, false otherwise
     */
    private boolean search(AVLTreeNode<E> node, E item) {
        if (node == null) {
            return false;
        }

        int cmp = item.compareTo(node.value);

        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        } else { // cmp == 0
            return true; // Value found
        }
    }

    /**
     * Finds the smallest index greater than or equal to the given item.
     *
     * @param item the item to find
     * @return the smallest index greater than or equal to the item
     */
    @Override
    public int find(E item) {
        return find(this.root, item, Integer.MAX_VALUE);
    }

    /**
     * Helper method for finding the smallest index greater than or equal to the given item.
     *
     * @param node the current node
     * @param item the item to find
     * @param bestIndexSoFar the best index found so far
     * @return the smallest index greater than or equal to the item
     */
    private int find(AVLTreeNode<E> node, E item, int bestIndexSoFar) {
        if (node == null) {
            return bestIndexSoFar;
        }

        int cmp = item.compareTo(node.value);

        if (cmp <= 0) {
            bestIndexSoFar = Math.min(bestIndexSoFar, node.index);
            return find(node.left, item, bestIndexSoFar);
        } else { // cmp > 0
            return find(node.right, item, bestIndexSoFar);
        }
    }

    /**
     * Adds an element to the AVL tree.
     *
     * @param index the index of the element
     * @param item the element to add
     */
    public void add(int index, E item) {
        this.root = add(this.root, index, item);
        size++; // Increment size here after successful addition
    }

    /**
     * Helper method for adding an element to the AVL tree.
     *
     * @param node the current node
     * @param index the index of the element
     * @param item the element to add
     * @return the updated node
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

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

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

        return node;
    }

    /**
     * Finds the node with the minimum value in the subtree rooted at the given node.
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
     * Deletes an element from the AVL tree.
     *
     * @param index the index of the element
     * @param item the element to delete
     * @return true if the element was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(int index, E item) {
        int oldSize = getSize();
        this.root = delete(this.root, index, item);
        return getSize() < oldSize;
    }

    /**
     * Helper method for deleting an element from the AVL tree.
     *
     * @param node the current node
     * @param index the index of the element
     * @param item the element to delete
     * @return the updated node
     */
    private AVLTreeNode<E> delete(AVLTreeNode<E> node, int index, E item) {
        if (node == null) {
            return null; // Item not found
        }

        int cmp = item.compareTo(node.value);

        if (cmp < 0) {
            node.left = delete(node.left, index, item);
        } else if (cmp > 0) {
            node.right = delete(node.right, index, item);
        } else { // cmp == 0
            if (index != node.index) {
                node.left = delete(node.left, index, item);
                node.right = delete(node.right, index, item);
            } else {
                boolean nodeRemoved = false;
                if (node.left == null || node.right == null) {
                    AVLTreeNode<E> temp = (node.left != null) ? node.left : node.right;
                    node = temp; // Replace node with child or null
                    nodeRemoved = true;
                } else {
                    AVLTreeNode<E> temp = minValueNode(node.right);
                    AVLTreeNode.copyTo(temp, node); // Copy inorder successor's data
                    node.right = delete(node.right, temp.index, temp.value);
                }

                if (nodeRemoved) {
                    size--; // Decrement size as a node was removed
                }
            }
        }

        if (node == null) {
            return null;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
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

        return node;
    }

    /**
     * Performs a right rotation on the given node.
     *
     * @param y the node to rotate
     * @return the new root of the subtree
     */
    AVLTreeNode<E> rightRotate(AVLTreeNode<E> y) {
        if (y.left == null) {
            return y;
        }

        AVLTreeNode<E> x = y.left;
        AVLTreeNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * Performs a left rotation on the given node.
     *
     * @param x the node to rotate
     * @return the new root of the subtree
     */
    AVLTreeNode<E> leftRotate(AVLTreeNode<E> x) {
        if (x.right == null) {
            return x;
        }

        AVLTreeNode<E> y = x.right;
        AVLTreeNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    /**
     * Calculates the balance factor of a node.
     *
     * @param N the node
     * @return the balance factor of the node
     */
    int getBalance(AVLTreeNode<E> N) {
        if (N == null) {
            return 0;
        }
        return getHeight(N.left) - getHeight(N.right);
    }

    /**
     * Performs an inorder traversal of the AVL tree.
     *
     * @param list the list to store the elements
     * @param node the current node
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

    /**
     * Inserts an element into the AVL tree.
     *
     * @param index the index of the element
     * @param e the element to insert
     * @return true if the element was inserted successfully
     */
    @Override
    public boolean insert(int index, E e) {
        add(index, e);
        return true;
    }

    /**
     * Performs an inorder traversal of the AVL tree and prints the elements.
     */
    @Override
    public void inorder() {
        inorder(this.root);
    }

    /**
     * Helper method for performing an inorder traversal of the AVL tree.
     *
     * @param node the current node
     */
    public void inorder(AVLTreeNode<E> node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.toString());
        inorder(node.right);
    }

    /**
     * Performs a postorder traversal of the AVL tree and prints the elements.
     */
    @Override
    public void postorder() {
        postorder(this.root);
    }

    /**
     * Helper method for performing a postorder traversal of the AVL tree.
     *
     * @param node the current node
     */
    private void postorder(AVLTreeNode<E> node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.toString()); // Visit the node after its children
    }

    /**
     * Performs a preorder traversal of the AVL tree and prints the elements.
     */
    @Override
    public void preorder() {
        preorder(this.root);
    }

    /**
     * Helper method for performing a preorder traversal of the AVL tree.
     *
     * @param node the current node
     */
    private void preorder(AVLTreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.toString()); // Visit the node before its children
        preorder(node.left);
        preorder(node.right);
    }

    /**
     * Returns the height of the AVL tree.
     *
     * @return the height of the tree
     */
    @Override
    public int getHeight() {
        return getHeight(this.root);
    }
}
