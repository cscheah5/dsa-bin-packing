package app;

import java.util.List;

public interface TruckLoadingStrategy {

	List<Truck> loadParcels(List<Parcel> parcels, int truckCapacity);

}
