package app.strategy;

import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import app.avltree.*;

public class FirstFitStrategy extends AbstractTruckLoading {
	protected AVLTree<Truck> tree;

	public FirstFitStrategy(TruckLoadingProblem problem) {
		super(problem);
		this.tree = new AVLTree<Truck>();
	}

	@Override
	public void packParcel(Parcel parcel) {
		// Find the first truck that can fit the parcel
		// Create a truck of weight as capacity, and use it to
		// compare to other truck with it as remaining capacity
		Truck dummyTruck = new Truck(-1, parcel.getWeight()); 
		int truckIndex = tree.find(dummyTruck);

		if (truckIndex == Integer.MAX_VALUE) {
			// No existing truck can fit this parcel - create a new one
			int newIndex = trucks.size();
			this.addItemToTruck(parcel, newIndex);

			// Add the new truck to the AVL tree
			Truck newTruck = trucks.get(newIndex);
			tree.add(newTruck.getIndex(), newTruck);
		} else {
			// Found a truck that can fit
			Truck existingTruck = trucks.get(truckIndex);

			// Remove from tree before modification
			tree.delete(existingTruck.getIndex(), existingTruck);

			// Use addItem to handle the parcel addition
			this.addItemToTruck(parcel, truckIndex);

			// Get the updated truck reference (in case addItem created a new one)
			Truck updatedTruck = trucks.get(truckIndex);

			// Add back to tree with updated capacity
			tree.add(updatedTruck.getIndex(), updatedTruck);
		}
	}
}
