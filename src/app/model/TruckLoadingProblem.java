package app.model;

import java.util.List;

public class TruckLoadingProblem {
	private final double binCapacity;
	private final List<Item> parcels;
	
	public TruckLoadingProblem(double binCapacity, List<Item> parcels) {
		this.binCapacity = binCapacity;
		this.parcels = parcels;
	}
	
	public double getBinCapacity() {
		return binCapacity;
	}
	public List<Item> getParcels() {
		return parcels;
	}
	
	public String toString() {
		return "Capacity: " + binCapacity + "\nParcels: " + parcels;
	}
}
