package app.strategy;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;

import java.util.*;

public class BestFitStrategy extends AbstractTruckLoading {
    private TreeMap<Double, Stack<Truck>> treeMap;
    // Use a map with capacity as key, stack to store multiple trucks with same capacity
    private int binSize = 0;

    public BestFitStrategy(TruckLoadingProblem problem) {
        super(problem);
        this.treeMap = new TreeMap<>();
    }

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