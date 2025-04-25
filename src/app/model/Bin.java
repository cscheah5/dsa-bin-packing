package app.model;

import java.util.List;

public interface Bin {
	double getCapacity();
	double getUsedCapacity();
	double getRemainingCapacity();
	boolean canFit(Item item);
	boolean addItem(Item item);
	List<Item> getItems();
	String getBinType();
}
