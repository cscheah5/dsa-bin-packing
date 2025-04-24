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

	// TODO
	public static void compareTime(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit,
			TruckLoadingProblem problem) {

		long startFirst = System.nanoTime();
		solveSilently(firstFit, problem);
		long endFirst = System.nanoTime();

		long startBest = System.nanoTime();
		solveSilently(bestFit, problem);
		long endBest = System.nanoTime();

		System.out.printf("FirstFit time: %.3f ms", (endFirst - startFirst) / 1_000_000.0);
		System.out.println();
		System.out.printf("BestFit time: %.3f ms", (endBest - startBest) / 1_000_000.0);
		System.out.println();

	}

	// TODO
	public static void compareMemory(TruckLoadingStrategy firstFit, TruckLoadingStrategy bestFit,
			TruckLoadingProblem problem) {
		Runtime runtime = Runtime.getRuntime();

		// FirstFit memory
		System.gc(); // Suggest garbage collection clear unused memory before measuring (not guaranteed but helps).
		long beforeFirst = runtime.totalMemory() - runtime.freeMemory();
		solveSilently(firstFit, problem);
		long afterFirst = runtime.totalMemory() - runtime.freeMemory();
		long usedFirst = afterFirst - beforeFirst;

		// BestFit memory
		System.gc();
		long beforeBest = runtime.totalMemory() - runtime.freeMemory();
		solveSilently(bestFit, problem);
		long afterBest = runtime.totalMemory() - runtime.freeMemory();
		long usedBest = afterBest - beforeBest;

//		System.out.printf("FirstFit Memory Used: %d bytes%n", usedFirst);
//		System.out.printf("BestFit Memory Used: %d bytes%n", usedBest);
		System.out.printf("FirstFit Memory Used: %.2f KB%n", usedFirst / 1024.0);
		System.out.printf("BestFit Memory Used: %.2f KB%n", usedBest / 1024.0);

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
