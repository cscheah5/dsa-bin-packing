package app.model;

import java.util.List;
import java.util.ArrayList;

public class Truck {
    private double capacity;
    private double remainingCapacity;
    private List<Parcel> parcels;
    private int index;

    public Truck(double capacity) {
        this.capacity = capacity;
        this.parcels = new ArrayList<>();
        this.remainingCapacity = capacity;
    }
    
    public List<Parcel> getParcels() {
        return parcels;
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
    
    public int getIndex() {
		return index;
	}

    public boolean addParcel(Parcel parcel) {
        if (remainingCapacity >= parcel.getWeight()) {
            parcels.add(parcel);
            remainingCapacity -= parcel.getWeight();
            return true;
        }
        return false;
    }

	public boolean canFit(Parcel parcel) {
		return remainingCapacity >= parcel.getWeight();
	}
}
