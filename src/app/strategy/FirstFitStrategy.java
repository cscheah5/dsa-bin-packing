package app.strategy;

import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import app.avltree.*;

public class FirstFitStrategy extends AbstractTruckLoading {
	protected AVLTree<Truck> tree;
	private int binSize;
	
    public FirstFitStrategy(TruckLoadingProblem problem) {
		super(problem);
		this.binSize = 0;
		this.tree = new AVLTree<Truck>();
	}

    @Override
    public void packParcel(Parcel parcel) {
        // Find the first truck that can fit the parcel
        Truck dummyTruck = new Truck(-1, parcel.getWeight());
        int truckIndex = tree.find(dummyTruck);
        
        if (truckIndex == Integer.MAX_VALUE) {
            // No existing truck can fit this parcel - create a new one
            Truck newTruck = new Truck(trucks.size(), binCapacity);
            newTruck.addParcel(parcel);
            trucks.add(newTruck);
            tree.add(newTruck.getIndex(), newTruck);
        } else {
            // Found a truck that can fit the parcel
            Truck existingTruck = trucks.get(truckIndex);
            
            // Remove the truck from the tree before modification
            tree.delete(existingTruck.getIndex(), existingTruck);
            
            // Add the parcel to the truck
            existingTruck.addParcel(parcel);
            
            // Reinsert the modified truck back into the tree
            tree.add(existingTruck.getIndex(), existingTruck);
        }
    }
}
