package app.model;

import java.util.List;

public class TruckLoadingProblem {
	private final int binCapacity;
	private final List<Parcel> parcels;
	
	public TruckLoadingProblem(int binCapacity, List<Parcel> parcels) {
		this.binCapacity = binCapacity;
		this.parcels = parcels;
	}
	
	public int getBinCapacity() {
		return binCapacity;
	}
	public List<Parcel> getParcels() {
		return parcels;
	}
	
	public String toString() {
		return "Capacity: " + binCapacity + "\nParcels: " + parcels;
	}
}
