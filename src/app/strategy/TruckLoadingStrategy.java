package app.strategy;

import java.util.List;

import app.model.Parcel;
import app.model.Truck;

public interface TruckLoadingStrategy {

	List<Truck> loadParcels(List<Parcel> parcels, int truckCapacity);

}
