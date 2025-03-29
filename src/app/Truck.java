package app;

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

    public int getRemainingCapacity() {
        int usedSpace = parcels.stream().mapToInt(Parcel::getWeight).sum();
        return capacity - usedSpace;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }
}
