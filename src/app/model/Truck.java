package app.model;

import java.util.List;

public class Truck extends AbstractStackContainer<Parcel> implements Comparable<Truck>{
	private final int index; // To track the truck(bin)
    private final double capacity;
    private double remainingCapacity;
    
    public Truck(int index, double capacity) {
    	super(); // it will create items arraylist
    	this.index = index;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
    }
    
    public int getIndex() {
		return index;
	}
    
    public List<Parcel> getParcels() {
        return items;
    }
    
    public double getCapacity() {
		return capacity;
	}
    
    public double getRemainingCapacity() {
		return remainingCapacity;
	}
    
    public double getUsedCapacity() {
		return capacity - remainingCapacity;
	}
    
    public boolean addParcel(Parcel parcel) {
        if (canFit(parcel)) {
        	push(parcel);
            remainingCapacity -= parcel.getWeight();
            return true;
        }
        return false;
    }
    
    // NOTE: NO POINT PASSING IN PARCEL
    public boolean removeParcel(Parcel parcel) {
    	if(!isEmpty()) {
    		pop();
    		return true;
    	}
    	return false;
    }
    
    public Parcel getParcel() {
    	if(!isEmpty()) {
    		return peek();
    	}
    	return null;
    }
    
    @Override
	public boolean canFit(Item item) {
		return remainingCapacity >= item.getWeight();
	}
    
    @Override
    public String toString() {
    	return "Truck " + this.index + ", Parcels: " + this.items;
    }

    /** Compare the remaining capacity of trucks */
    public int compareTo(Truck other) {
        if (this.remainingCapacity > other.remainingCapacity) return 1;
        if (this.remainingCapacity < other.remainingCapacity) return -1;
        // Tie-breaker: lower index wins 
        return Integer.compare(this.index, other.index);
    }
    
}
