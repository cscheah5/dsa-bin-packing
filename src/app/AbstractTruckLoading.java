package app;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractTruckLoading implements TruckLoadingStrategy {
    protected List<Truck> trucks = new ArrayList<>();

    protected Truck getOrCreateTruck(int truckCapacity) {
        for (Truck truck : trucks) {
            if (truck.getRemainingCapacity() > 0) {
                return truck;
            }
        }
        Truck newTruck = new Truck(truckCapacity);
        trucks.add(newTruck);
        return newTruck;
    }

    @Override
    public abstract List<Truck> loadParcels(List<Parcel> parcels, int truckCapacity);
}
