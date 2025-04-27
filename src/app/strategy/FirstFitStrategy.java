package app.strategy;

import app.avltree.*;
import app.model.Parcel;
import app.model.Truck;

/**
 * Implements the First Fit bin packing strategy. This strategy attempts to
 * place each incoming parcel into the first truck found that has enough
 * remaining capacity. It utilizes an AVL tree to efficiently update trucks
 * based on their remaining capacity.
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
     * It iterates through the list of trucks in order and assigns the parcel to
     * the first truck found with enough remaining capacity. The AVL tree is used
     * for fast updates to the truck's position based on its new remaining capacity.
     * If no suitable truck exists, a new truck is created, the parcel is added to
     * it, and the new truck is inserted into the AVL tree.
     *
     * @param parcel The parcel to be packed.
     */
    @Override
    public void packParcel(Parcel parcel) {
        // First Fit: iterate trucks in order, assign to first with enough space
        boolean placed = false;
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck = trucks.get(i);
            if (truck.getRemainingCapacity() >= parcel.getWeight()) {
                // Remove from tree before modification (if AVL tree is used for fast updates)
                tree.delete(truck.getIndex(), truck);
                this.addItemToTruck(parcel, i);
                Truck updatedTruck = trucks.get(i);
                tree.add(updatedTruck.getIndex(), updatedTruck);
                placed = true;
                break;
            }
        }
        if (!placed) {
            // No existing truck can fit this parcel - create a new one
            int newIndex = trucks.size();
            this.addItemToTruck(parcel, newIndex);
            Truck newTruck = trucks.get(newIndex);
            tree.add(newTruck.getIndex(), newTruck);
        }
    }
}
