package app.strategy;

import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;

public class BestFitStrategy extends AbstractTruckLoading {
    @Override
    public List<Truck> solve(TruckLoadingProblem problem) {
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(problem.getBinCapacity()));
        
        for (Parcel parcel : problem.getParcels()) {
            Truck bestTruck = null;
            double minRemainingSpace = Integer.MAX_VALUE;
            
            for (Truck truck : trucks) {
                double remainingSpace = truck.getRemainingCapacity() - parcel.getWeight();
                if (remainingSpace >= 0 && remainingSpace < minRemainingSpace) {
                    bestTruck = truck;
                    minRemainingSpace = remainingSpace;
                }
            }
            
            if (bestTruck != null) {
                bestTruck.addParcel(parcel);
            } else {
                Truck newTruck = new Truck(problem.getBinCapacity());
                newTruck.addParcel(parcel);
                trucks.add(newTruck);
            }
        }
        return trucks;
    }
}
