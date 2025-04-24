package app.compare;
import java.util.*;

import app.model.Truck;
import app.model.TruckLoadingProblem;
import app.strategy.TruckLoadingStrategy;

public class CompareStrategy {
	// TODO
	public static void compareTimeComplexity(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit, TruckLoadingProblem problem) {
		List<Truck> firstFitTruck = firstFit.solve(problem);
		List<Truck> bestFitTruck = bestFit.solve(problem);
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
