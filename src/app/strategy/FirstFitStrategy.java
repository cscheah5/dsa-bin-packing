package app.strategy;

import java.util.List;

import app.model.Parcel;
import app.model.Truck;

public class FirstFitStrategy extends AbstractTruckLoading {

	@Override
	public List<Truck> loadParcels(List<Parcel> parcels, int truckCapacity) {
		return null;
	}

}
