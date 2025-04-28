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
                new FirstFitStrategy(),
                new BestFitStrategy()
        ));
        ArrayList<Result> results = new ArrayList<>();

        // Loop through each strategy
        for (TruckLoadingStrategy strategy : strategies) {
            double startTime = System.currentTimeMillis();

            // Read data from parcel_data.csv, and load 100 records
            List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 1000);

            TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
            strategy.solve(problem);
            displayResults(strategy.getTrucks(), "BEST FIT");

            double endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + " ms");

            results.add(new Result(strategy.getName(), endTime - startTime, 0, strategy.getTrucks()));
        }

        displayComparison(results);
    }
}
