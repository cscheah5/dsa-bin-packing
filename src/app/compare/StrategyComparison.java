package app.compare;

import app.model.Parcel;
import app.model.TruckLoadingProblem;
import app.strategy.TruckLoadingStrategy;
import java.util.*;

public class StrategyComparison {

    /**
     * Compares the time complexity of two different truck loading strategies on
     * increasingly larger problem sizes. This method tests both strategies with
     * subsets of the original problem, starting with 20 parcels and
     * incrementing by 40 until reaching 100 parcels (or the max available). For
     * each problem size, it measures and reports the average execution time of
     * both strategies in milliseconds and computes the percentage difference in
     * speed.
     *
     * @param strategy1 The first truck loading strategy to compare
     * @param strategy2 The second truck loading strategy to compare
     * @param problem The original truck loading problem that contains all
     * parcels
     */
    public static void compareTimeComplexity(TruckLoadingStrategy strategy1, TruckLoadingStrategy strategy2,
            TruckLoadingProblem problem) {

        final int ITERATIONS = 10; // Number of iterations for averaging

        System.out.println("\n--- Time Complexity Comparison ---");
        for (int size = 20; size <= 100 && size <= problem.getParcels().size(); size += 40) {
            List<Parcel> subParcels = problem.getParcels().subList(0, size);

            long totalTimeFirst = 0;
            long totalTimeBest = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                // Create fresh problem instances for each iteration to avoid cache effects
                TruckLoadingProblem subProblemFirst = new TruckLoadingProblem(problem.getBinCapacity(), new ArrayList<>(subParcels));
                TruckLoadingProblem subProblemBest = new TruckLoadingProblem(problem.getBinCapacity(), new ArrayList<>(subParcels));

                // Measure time for Strategy 1
                long startFirst = System.nanoTime();
                strategy1.solve(subProblemFirst);
                long endFirst = System.nanoTime();
                totalTimeFirst += (endFirst - startFirst);

                // Measure time for Strategy 2  
                long startBest = System.nanoTime();
                strategy2.solve(subProblemBest);
                long endBest = System.nanoTime();
                totalTimeBest += (endBest - startBest);
            }

            double avgTimeFirstMs = (totalTimeFirst / (double) ITERATIONS) / 1_000_000.0;
            double avgTimeBestMs = (totalTimeBest / (double) ITERATIONS) / 1_000_000.0;

            double percentageFaster = 0;
            if (avgTimeBestMs > 0) { // Avoid division by zero
                percentageFaster = ((avgTimeBestMs - avgTimeFirstMs) / avgTimeBestMs) * 100.0;
            }

            System.out.printf("Size: %d - %s avg: %.3f ms, BestFit avg: %.3f ms", size, strategy1.getName(), avgTimeFirstMs, avgTimeBestMs);
            if (avgTimeBestMs > 0) {
                System.out.printf(" (%s is %.2f%% faster)%n", strategy1.getName(), percentageFaster);
            } else {
                System.out.println(); // Just end the line if BestFit time is zero
            }
        }
    }

    /**
     * Compares the space complexity of two different truck loading strategies
     * on increasingly larger problem sizes. This method tests both strategies
     * with subsets of the original problem, starting with 20 parcels and
     * incrementing by 40 until reaching 100 parcels (or the max available). For
     * each problem size, it measures and reports the memory usage of both
     * strategies in bytes and computes the percentage difference in space
     * usage.
     *
     * @param strategy1 The first truck loading strategy to compare
     * @param strategy2 The second truck loading strategy to compare
     * @param problem The original truck loading problem that contains all
     * parcels
     */
    public static void compareSpaceComplexity(TruckLoadingStrategy strategy1, TruckLoadingStrategy strategy2,
            TruckLoadingProblem problem) {

        final int ITERATIONS = 10; // Run multiple iterations for more reliable measurement

        System.out.println("\n--- Space Complexity Comparison ---");
        for (int size = 20; size <= 100 && size <= problem.getParcels().size(); size += 40) {
            List<Parcel> subParcels = problem.getParcels().subList(0, size);

            // Memory usage for Strategy 1
            long totalMemoryUsedFirst = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                System.gc();

                TruckLoadingProblem subProblem = new TruckLoadingProblem(problem.getBinCapacity(),
                        new ArrayList<>(subParcels)); // Create a fresh copy each time

                long usedMemoryBeforeFirst = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                strategy1.solve(subProblem);
                long usedMemoryAfterFirst = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                totalMemoryUsedFirst += Math.max(0, usedMemoryAfterFirst - usedMemoryBeforeFirst);

                System.gc();
            }
            long avgMemoryUsedFirst = totalMemoryUsedFirst / ITERATIONS;

            // Memory usage for Strategy 2
            long totalMemoryUsedBest = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                System.gc();

                TruckLoadingProblem subProblem = new TruckLoadingProblem(problem.getBinCapacity(),
                        new ArrayList<>(subParcels)); // Create a fresh copy each time

                long usedMemoryBeforeBest = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                strategy2.solve(subProblem);
                long usedMemoryAfterBest = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                totalMemoryUsedBest += Math.max(0, usedMemoryAfterBest - usedMemoryBeforeBest);

                System.gc();
            }
            long avgMemoryUsedBest = totalMemoryUsedBest / ITERATIONS;

            // Calculate percentage difference
            double percentageLess = 0;
            if (avgMemoryUsedBest > 0) { // Avoid division by zero
                percentageLess = ((double) (avgMemoryUsedBest - avgMemoryUsedFirst) / avgMemoryUsedBest) * 100.0;
            }

            System.out.printf("Size: %d - %s avg: %d bytes, BestFit avg: %d bytes", size, strategy1.getName(), avgMemoryUsedFirst, avgMemoryUsedBest);
            if (avgMemoryUsedBest > 0) {
                System.out.printf(" (%s uses %.2f%% less space)%n", strategy1.getName(), percentageLess);
            } else {
                System.out.println(); // Just end the line if BestFit memory is zero
            }
        }
    }
}
