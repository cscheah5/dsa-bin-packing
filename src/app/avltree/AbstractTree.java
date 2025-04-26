package app.avltree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {
    protected int size = 0;

    @Override
    public void inorder() {
        // Default implementation can be overridden
    }

    @Override
    public void postorder() {
        // Default implementation can be overridden
    }

    @Override
    public void preorder() {
        // Default implementation can be overridden
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Helper method for iterator implementations
    protected List<E> getInorderList() {
        List<E> list = new ArrayList<>();
        inorderTraversal(list, getRoot());
        return list;
    }

    // Abstract method to be implemented by concrete trees
    protected abstract void inorderTraversal(List<E> list, Object node);

    // Abstract method to get root (implementation specific)
    protected abstract Object getRoot();

    // Inner class for inorder iterator
    private class InorderIterator implements Iterator<E> {
        private final List<E> elements = getInorderList();
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < elements.size();
        }

        @Override
        public E next() {
            return elements.get(current++);
        }
    }
}