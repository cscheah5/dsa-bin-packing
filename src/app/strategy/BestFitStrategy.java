package app.strategy;

import app.model.Parcel;
import app.model.Truck;
import java.util.*;

/**
 * Implements the Best Fit bin packing strategy. This strategy attempts to place
 * each parcel into the truck that has the smallest remaining capacity
 * sufficient to hold the parcel. It uses a TreeMap to efficiently find the
 * best-fitting truck.
 */
public class BestFitStrategy extends AbstractTruckLoadingStrategy {

    private final TreeMap<Double, Stack<Truck>> treeMap;
    private final String name = "Best Fit Strategy";
    private int binSize = 0;

    public BestFitStrategy() {
        this.treeMap = new TreeMap<>();
    }

    /**
     * Gets the name of the packing strategy.
     *
     * @return The name of this bin packing strategy
     */
    public String getName() {
        return name;
    }

    /**
     * Packs a given parcel into a truck using the Best Fit strategy. It finds
     * the truck with the smallest remaining capacity that can accommodate the
     * parcel's weight. If no suitable truck exists, a new truck is created. The
     * truck's position in the internal TreeMap is updated after adding the
     * parcel.
     *
     * @param parcel The parcel to be packed.
     */
    @Override
    public void packParcel(Parcel parcel) {
        // Find the smallest remaining capacity that can fit this parcel
        Double suitableCapacity = treeMap.ceilingKey(parcel.getWeight());

        Truck truck;
        if (suitableCapacity == null || treeMap.get(suitableCapacity).isEmpty()) {
            // No existing truck can fit - create new one
            truck = new Truck(binSize++, binCapacity);
            trucks.add(truck);
        } else {
            // Get the best-fit truck from the stack
            Stack<Truck> truckStack = treeMap.get(suitableCapacity);
            truck = truckStack.pop();

            // Clean up empty stacks (no other truck with same capacity)
            if (truckStack.isEmpty()) {
                treeMap.remove(suitableCapacity);
            }
        }

        // Remove truck from its old capacity entry (if it existed)
        double oldCapacity = truck.getRemainingCapacity() + parcel.getWeight();
        if (treeMap.containsKey(oldCapacity)) {
            treeMap.get(oldCapacity).remove(truck);
            if (treeMap.get(oldCapacity).isEmpty()) {
                treeMap.remove(oldCapacity);
            }
        }

        // Add the parcel to the truck
        truck.addParcel(parcel);

        // Update the truck's position in the TreeMap with new capacity
        Stack<Truck> trucksWithCapacity = treeMap.getOrDefault(
                truck.getRemainingCapacity(),
                new Stack<>()
        );
        trucksWithCapacity.push(truck);
        treeMap.put(truck.getRemainingCapacity(), trucksWithCapacity);
    }
}
