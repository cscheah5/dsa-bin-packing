package app.strategy;

import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;

public class BestFitStrategy extends AbstractTruckLoading {
    public BestFitStrategy(TruckLoadingProblem problem) {
		super(problem);
	}

//	@Override
//    public void solve() {
//        trucks.add(new Truck(0, binCapacity));
//        
//        for (Parcel parcel : problem.getParcels()) {
//            Truck bestTruck = null;
//            double minRemainingSpace = Integer.MAX_VALUE;
//            
//            for (Truck truck : trucks) {
//                double remainingSpace = truck.getRemainingCapacity() - parcel.getWeight();
//                if (remainingSpace >= 0 && remainingSpace < minRemainingSpace) {
//                    bestTruck = truck;
//                    minRemainingSpace = remainingSpace;
//                }
//            }
//            
//            if (bestTruck != null) {
//                bestTruck.addParcel(parcel);
//            } else {
//                Truck newTruck = new Truck(problem.getBinCapacity());
//                newTruck.addParcel(parcel);
//                trucks.add(newTruck);
//            }
//        }
//    }

	@Override
	public void packParcel(Parcel parcel) {
		// TODO Auto-generated method stub
		
	}
}
