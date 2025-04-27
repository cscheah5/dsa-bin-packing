package app.model;

import java.util.List;

public class TruckLoadingProblem {

    private final double binCapacity;
    private final List<Parcel> parcels;

    public TruckLoadingProblem(double binCapacity, List<Parcel> parcels) {
        this.binCapacity = binCapacity;
        this.parcels = parcels;
    }

    public double getBinCapacity() {
        return binCapacity;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    @Override
    public String toString() {
        return "Capacity: " + binCapacity + "\nParcels: " + parcels;
    }
}
