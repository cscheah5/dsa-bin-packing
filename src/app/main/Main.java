package app.main;

import app.io.CsvDataLoader;
import static app.io.OutputFormatter.*;
import app.model.*;
import app.strategy.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

        // Initialize the list of strategies and the results list
        ArrayList<TruckLoadingStrategy> strategies = new ArrayList<>(Arrays.asList(
                new BestFitStrategy(),
                new FirstFitStrategy()
        ));
        ArrayList<Result> results = new ArrayList<>();

        // Loop through each strategy and solve the problem
        for (TruckLoadingStrategy strategy : strategies) {
            List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 100);
            TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
            strategy.solve(problem);

            // Print results to console and file
            displayResults(strategy.getTrucks(), strategy.getName(), console);
            displayResults(strategy.getTrucks(), strategy.getName(), fileWriter);
        }

        // Time complexity analysis
        for (TruckLoadingStrategy strategy : strategies) {
            List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 10000);
            
            long startTime = System.nanoTime();
            TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
            strategy.solve(problem);
            long endTime = System.nanoTime();
            long durationNs = endTime - startTime;
            double durationMs = durationNs / 1_000_000.0;

            // Print results to console and file
            displayResults(strategy.getTrucks(), strategy.getName(), console);
            displayResults(strategy.getTrucks(), strategy.getName(), fileWriter);
            console.println("Execution time: " + durationMs + " ms");
            fileWriter.println("Execution time: " + durationMs + " ms");

            results.add(new Result(strategy.getName(), durationMs, 0, strategy.getTrucks()));
        }

        displayComparison(results, console);
        displayComparison(results, fileWriter);

        fileWriter.close();
    }
}
