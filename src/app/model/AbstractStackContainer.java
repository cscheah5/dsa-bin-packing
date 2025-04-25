package app.model;

import java.util.*;

public abstract class AbstractStackContainer<E extends Item> implements StackContainer<E> {
	protected ArrayList<E> items;

	public AbstractStackContainer() {
		items = new ArrayList<E>();
	}

	public AbstractStackContainer(ArrayList<E> items) {
		super();
		this.items = items;
	}

	@Override
	public boolean isEmpty() {
		if (items == null || getSize() == 0)
			return true;
		return false;
	}

	@Override
	public E peek() {
		return items.get(getSize() - 1);
	}

	@Override
	public E pop() {
		E o = items.get(getSize() - 1);
		items.remove(getSize() - 1);
		return o;
	}

	@Override
	public void push(E object) {
		items.add(object);
	}
	
	@Override
	public int getSize() {
		return items.size();
	}
}
