package app.strategy;

import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;

public class FirstFitStrategy extends AbstractTruckLoading {
    @Override
    public List<Truck> solve(TruckLoadingProblem problem) {
    	final double BIN_CAPACITY = problem.getBinCapacity();
    	List<Parcel> parcels = problem.getParcels();
    	
        trucks.add(new Truck(BIN_CAPACITY)); // Initialize first truck
        
        System.out.printf("Starting First-Fit with %d parcels and capacity %.2f%n",
                parcels.size(), problem.getBinCapacity());
        
        for (int i = 0; i < parcels.size(); i++) {
            Parcel parcel = parcels.get(i);
            boolean placed = false;
            
            System.out.printf("%nProcessing parcel %d: %.2fkg %s%n",
                    i+1, parcel.getWeight(), parcel.getType());
            
            // Try existing trucks
            for (int j = 0; j < trucks.size(); j++) {
                Truck truck = trucks.get(j);
                double remaining = truck.getRemainingCapacity();
                
                System.out.printf("  Checking truck %d (%.2f remaining)... ",
                        j+1, remaining);
                
                if (truck.canFit(parcel)) {
                    truck.addParcel(parcel);
                    System.out.printf("FIT! Added to truck %d%n", j+1);
                    placed = true;
                    break;
                } else {
                    System.out.printf("too heavy (needs %.2f)%n", parcel.getWeight());
                }
            }
            
            // Add new truck if needed
            if (!placed) {
                Truck newTruck = new Truck(problem.getBinCapacity());
                newTruck.addParcel(parcel);
                trucks.add(newTruck);
                System.out.printf("  Created NEW truck %d for this parcel%n", trucks.size());
            }
        }
        
        return trucks;
    }


}
