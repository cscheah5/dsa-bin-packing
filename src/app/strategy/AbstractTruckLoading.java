package app.strategy;

import java.util.List;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;

import java.util.ArrayList;

public abstract class AbstractTruckLoading implements TruckLoadingStrategy {
    protected List<Truck> trucks; // Data structure to store the final list of truck
    
    protected double binCapacity; //The maximum weight limit of each truck
    protected int nParcels; // The number of parcels to be processed 
    protected List<Parcel> parcels; // The list of parcels
    
    /** Constructor, to load data from TruckLoadingProblem class */
    public AbstractTruckLoading(TruckLoadingProblem problem) {
    	this.trucks = new ArrayList<>();
    	
    	this.binCapacity = problem.getBinCapacity();
    	this.nParcels = problem.getParcels().size();
    	this.parcels = problem.getParcels();
    }
    
    /** Return the truck list, for visualization of data */
    public List<Truck> getTrucks(){
    	return trucks;
    }
    
    @Override
    /** Pack all the parcels, by invoking the packParcel method */
    public void solve(){
    	for (Parcel parcel : parcels) {
			packParcel(parcel);
		}
    }
    
    /** 
     *  Add the parcel to the truck of a specific index
     *  If the specified index is not available, create a new Truck,
     *  and then add the parcel to that new truck 
     */
	protected void addItem(Parcel parcel, int binIndex) {
		int binSize = this.trucks.size();
		if(binIndex < 0 || binIndex >= binSize) { //Index not available
			binIndex = binSize;
			this.trucks.add(new Truck(binIndex, binCapacity));
		}
		
		this.trucks.get(binIndex).addParcel(parcel);
	} 
	
	protected void printTrucks() {
		System.out.println("\nPrinting Bins..");
		for(Truck truck : this.trucks) {
			System.out.println("\t" + truck.toString());
		}
	}
    
    @Override
    /** Pack one of the parcel, and add it to Bin List */
    public abstract void packParcel(Parcel parcel);
}
