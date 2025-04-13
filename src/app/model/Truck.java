package app.model;

import java.util.List;
import java.util.ArrayList;

public class Truck {
    private int capacity;
    private List<Parcel> parcels = new ArrayList<>();

    public Truck(int capacity) {
        this.capacity = capacity;
    }

    public boolean addParcel(Parcel parcel) {
        if (getRemainingCapacity() >= parcel.getWeight()) {
            parcels.add(parcel);
            return true;
        }
        return false;
    }

    public double getRemainingCapacity() {
        double usedSpace = parcels.stream().mapToDouble(Parcel::getWeight).sum();
        return capacity - usedSpace;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }
}
