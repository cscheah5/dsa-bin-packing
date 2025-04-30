package app.strategy;

import app.avltree.*;
import app.model.Parcel;
import app.model.Truck;

/**
 * Strategy that packs parcels into the first truck that can accommodate them.
 * Uses an AVLTree to track trucks by remaining capacity for efficient search.
 */
public class FirstFitStrategy extends AbstractTruckLoadingStrategy {

    protected AVLTree<Truck> tree;
    private final String name = "First Fit Strategy";

    public FirstFitStrategy() {
        super();
        this.tree = new AVLTree<>();
    }

    /**
     * Packs a parcel into the first available truck that can fit it using an AVL tree for efficient search.
     * @param parcel the parcel to be packed
     */
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

    /**
     * Returns the name of this strategy.
     * @return the strategy name
     */
	@Override
	public String getName() {
		return name;
	}
}
