package app.main;

import app.io.CsvDataLoader;
import static app.io.OutputFormatter.*;
import app.model.*;
import app.strategy.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Set up the console output
        PrintWriter console = new PrintWriter(System.out, true);

        // Set up the file output
        PrintWriter fileWriter;
        try {
            fileWriter = new PrintWriter("output.txt");
        } catch (FileNotFoundException ex) {
            console.println("Error opening output file: " + ex.getMessage());
            return;
        }

        // Initialize the list of strategies
        List<Class<? extends TruckLoadingStrategy>> strategyClasses = Arrays.asList(BestFitStrategy.class, FirstFitStrategy.class);

        // Loop through each strategy and solve the problem with different bin sizes
        for (Class<? extends TruckLoadingStrategy> strategyClass : strategyClasses) {
            List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 100);

            for (int binSize : Arrays.asList(100, 1000)) {
                TruckLoadingProblem problem = new TruckLoadingProblem(binSize, parcels);
                TruckLoadingStrategy strategy;

                // Create an instance of the strategy class to reset strategy internal state
                try {
                    strategy = strategyClass.getDeclaredConstructor().newInstance();
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                strategy.solve(problem);

                // Print results to console and file
                displayResults(strategy.getTrucks(), strategy.getName(), console);
                displayResults(strategy.getTrucks(), strategy.getName(), fileWriter);
            }
        }

        // Time complexity analysis
        Map<String, ArrayList<Result>> results = new HashMap<>();

        for (Class<? extends TruckLoadingStrategy> strategyClass : strategyClasses) {
            List<Parcel> allParcels = CsvDataLoader.readCSV("parcel_data.csv", 10000);
            results.put(strategyClass.getSimpleName(), new ArrayList<>());

            // Test with different sizes of parcels to analyze the growth rate
            for (int parcelCount : Arrays.asList(10, 100, 1000, 10000)) {
                List<Parcel> parcels = allParcels.subList(0, parcelCount);

                long startTime = System.nanoTime();
                TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
                TruckLoadingStrategy strategy;

                // Create an instance of the strategy class to reset strategy internal state
                try {
                    strategy = strategyClass.getDeclaredConstructor().newInstance();
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                strategy.solve(problem);
                long endTime = System.nanoTime();
                long durationNs = endTime - startTime;
                double durationMs = durationNs / 1_000_000.0;

                results.get(strategyClass.getSimpleName()).add(new Result(strategyClass.getSimpleName(), parcelCount, durationMs));
            }
        }

        displayComparison(results, console);

        fileWriter.close();
    }
}
