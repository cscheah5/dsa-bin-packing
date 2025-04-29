package app.main;

import app.io.CsvDataLoader;
import static app.io.OutputFormatter.*;
import app.model.*;
import app.strategy.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Initialize the list of strategies and the results list
        ArrayList<TruckLoadingStrategy> strategies = new ArrayList<>(Arrays.asList(
                new BestFitStrategy(),
                new FirstFitStrategy()
        ));
        ArrayList<Result> results = new ArrayList<>();

        // Loop through each strategy
        for (TruckLoadingStrategy strategy : strategies) {
            List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 100);

            // Clone parcels for each run and measure solve only
            long startTime = System.nanoTime();
            TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
            strategy.solve(problem);
            long endTime = System.nanoTime();
            long durationNs = endTime - startTime;
            double durationMs = durationNs / 1_000_000.0;
            displayResults(strategy.getTrucks(), strategy.getName());
            System.out.println("Execution time: " + durationMs + " ms");
            results.add(new Result(strategy.getName(), durationMs, 0, strategy.getTrucks()));
        }

        displayComparison(results);
    }
}
