package app.strategy;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for implementing truck loading strategies in a bin
 * packing problem.
 *
 * This class provides the common structure and methods for different truck
 * loading algorithms. It implements the TruckLoadingStrategy interface and
 * manages the basic operations for processing parcels and loading them into
 * trucks based on weight constraints.
 *
 * Concrete subclasses must implement the packParcel method to define specific
 * packing strategies.
 *
 * @see TruckLoadingStrategy
 * @see Truck
 * @see Parcel
 * @see TruckLoadingProblem
 */
public abstract class AbstractTruckLoadingStrategy implements TruckLoadingStrategy {

    protected List<Truck> trucks; // Data structure to store the final list of truck
    protected double truckCapacity; //The maximum weight limit of each truck
    protected List<Parcel> parcels; // The list of parcels

    /**
     * Constructor, to load data from TruckLoadingProblem class
     */
    public AbstractTruckLoadingStrategy() {
        this.trucks = new ArrayList<>();
    }

    /**
     * Return the truck list, for visualization of data
     */
    @Override
    public List<Truck> getTrucks() {
        return trucks;
    }

    @Override
    /**
     * Pack all the parcels, by invoking the packParcel method
     */
    public void solve(TruckLoadingProblem problem) {
        this.truckCapacity = problem.getBinCapacity();
        this.parcels = problem.getParcels();

        for (Parcel parcel : parcels) {
            packParcel(parcel);
        }
    }

    /**
     * Add the parcel to the truck of a specific index If the specified index is
     * not available, create a new Truck, and then add the parcel to that new
     * truck
     */
    protected Truck addItemToTruck(Parcel parcel, int truckIndex) {
        int binSize = this.trucks.size();
        if (truckIndex < 0 || truckIndex >= binSize) { //Index not available
            truckIndex = binSize;
            this.trucks.add(new Truck(truckIndex, truckCapacity));
        }

        this.trucks.get(truckIndex).addParcel(parcel);
        return this.trucks.get(truckIndex);
    }

    protected void printTrucks() {
        System.out.println("\nPrinting Bins..");
        for (Truck truck : this.trucks) {
            System.out.println("\t" + truck.toString());
        }
    }

    @Override
    /**
     * Pack one of the parcel, and add it to Bin List
     */
    public abstract void packParcel(Parcel parcel);
}
