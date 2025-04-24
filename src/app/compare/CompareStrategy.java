package app.compare;
import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import app.strategy.TruckLoadingStrategy;

public class CompareStrategy {
	// TODO
	public static void compareTimeComplexity(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit, TruckLoadingProblem problem) {
				
		for(int size = 20; size <= 100; size+=20) {
			// getting parcels from 0 to size
			List<Parcel> subParcels = problem.getParcels().subList(0, size);
			TruckLoadingProblem subProblem = new TruckLoadingProblem(problem.getBinCapacity(), subParcels);
			
			long startFirst = System.nanoTime();
			firstFit.solve(subProblem);
			long endFirst = System.nanoTime();
			
			long startBest = System.nanoTime();
			bestFit.solve(subProblem);
			long endBest = System.nanoTime();
			
			System.out.printf("Size: %d - FirstFit: %d ns, BestFit: %d ns%n", size, endFirst - startFirst, endBest - startBest);
		}
		//
	}

	// TODO
	public static void compareTime(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit, TruckLoadingProblem problem) {
		List<Truck> firstFitTruck = firstFit.solve(problem);
		List<Truck> bestFitTruck = bestFit.solve(problem);
	}

	// TODO
	public static void compareMemory(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit, TruckLoadingProblem problem) {
		List<Truck> firstFitTruck = firstFit.solve(problem);
		List<Truck> bestFitTruck = bestFit.solve(problem);
	}

}
