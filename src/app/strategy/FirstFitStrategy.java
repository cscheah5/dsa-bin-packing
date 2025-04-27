package app.strategy;

import app.avltree.*;
import app.model.Parcel;
import app.model.Truck;

/**
 * Implements the First Fit bin packing strategy. This strategy attempts to
 * place each incoming parcel into the first truck found that has enough
 * remaining capacity. It utilizes an AVL tree to efficiently find suitable
 * trucks based on their remaining capacity.
 */
public class FirstFitStrategy extends AbstractTruckLoadingStrategy {

    protected AVLTree<Truck> tree;
    private final String name = "First Fit Strategy";

    /**
     * Constructs a new FirstFitStrategy. Initializes the list of trucks and the
     * AVL tree used for managing trucks.
     */
    public FirstFitStrategy() {
        super();
        this.tree = new AVLTree<>();
    }

    /**
     * Gets the name of the packing strategy.
     *
     * @return The name of this bin packing strategy
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Packs a given parcel into a truck using the First Fit strategy.
     *
     * It searches the AVL tree for the first truck (smallest index with
     * sufficient capacity) that can accommodate the parcel's weight. If a
     * suitable truck is found, the parcel is added to that truck, and the
     * truck's position in the AVL tree is updated based on its new remaining
     * capacity. If no suitable truck exists, a new truck is created, the parcel
     * is added to it, and the new truck is inserted into the AVL tree.
     *
     * @param parcel The parcel to be packed.
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
}
