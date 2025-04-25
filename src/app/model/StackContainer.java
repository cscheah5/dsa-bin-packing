package app.model;
import java.util.*;

public interface StackContainer<E> {
	E peek();
	E pop();
	void push(E object);
	public int getSize();
	boolean isEmpty();
	boolean canFit(Item item);
}
