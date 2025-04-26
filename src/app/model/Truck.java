package app.model;

import java.util.List;
import java.util.ArrayList;

public class Truck extends AbstractStackContainer<Parcel>{
    private double capacity;
    private double remainingCapacity;
    
    public Truck(double capacity) {
    	super(); // it will create items arraylist
        this.capacity = capacity;
        this.remainingCapacity = capacity;
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

}
