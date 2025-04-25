package app.compare;

import java.io.PrintStream;
import java.util.*;

import app.model.Parcel;
import app.model.Truck;
import app.model.TruckLoadingProblem;
import app.strategy.TruckLoadingStrategy;

public class CompareStrategy {

	public static void compareTimeComplexity(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit,
			TruckLoadingProblem problem) {

		for (int size = 20; size <= 100; size += 20) {
			// getting parcels from 0 to size
			List<Parcel> subParcels = problem.getParcels().subList(0, size);
			TruckLoadingProblem subProblem = new TruckLoadingProblem(problem.getBinCapacity(), subParcels);

			long startFirst = System.nanoTime();
			solveSilently(firstFit, subProblem);
			long endFirst = System.nanoTime();

			long startBest = System.nanoTime();
			solveSilently(bestFit, subProblem);
			long endBest = System.nanoTime();

//			System.out.printf("Size: %d - FirstFit: %d ns, BestFit: %d ns%n", size, endFirst - startFirst,
//					endBest - startBest);
			System.out.printf("Size: %d - FirstFit: %.3f ms, BestFit: %.3f ms%n", size,
					(endFirst - startFirst) / 1_000_000.0, (endBest - startBest) / 1_000_000.0);
		}
		//
	}

	// it suppress the output from solving the problem
	public static List<Truck> solveSilently(TruckLoadingStrategy strategy, TruckLoadingProblem problem) {
		List<Truck> result;
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() {
			public void write(int b) {
			}
		}));

		try {
			result = strategy.solve(problem);
		} finally {
			System.setOut(originalOut);
		}
		return result;
	}

}
