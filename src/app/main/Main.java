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

		// Initialize the list of strategies
		ArrayList<TruckLoadingStrategy> strategies = new ArrayList<>(
				Arrays.asList(new BestFitStrategy(), new FirstFitStrategy()));

		// Loop through each strategy and solve the problem with different bin sizes
		for (TruckLoadingStrategy strategy : strategies) {
			List<Parcel> parcels = CsvDataLoader.readCSV("parcel_data.csv", 100);

			for (int binSize : Arrays.asList(100, 1000)) {
				TruckLoadingProblem problem = new TruckLoadingProblem(binSize, parcels);
				strategy.solve(problem);

				// Print results to console and file
				displayResults(strategy.getTrucks(), strategy.getName(), console);
				displayResults(strategy.getTrucks(), strategy.getName(), fileWriter);
			}
		}

		// Time complexity analysis
		Map<String, ArrayList<Result>> results = new HashMap<>();

		for (TruckLoadingStrategy strategy : strategies) {
			List<Parcel> allParcels = CsvDataLoader.readCSV("parcel_data.csv", 10000);
			results.put(strategy.getName(), new ArrayList<>());

			// Test with different sizes of parcels to analyze the growth rate
			for (int parcelCount : Arrays.asList(10, 100, 1000, 10000)) {
				List<Parcel> parcels = allParcels.subList(0, parcelCount);

				long startTime = System.nanoTime();
				TruckLoadingProblem problem = new TruckLoadingProblem(100, parcels);
				strategy.solve(problem);
				long endTime = System.nanoTime();
				long durationNs = endTime - startTime;
				double durationMs = durationNs / 1_000_000.0;

				results.get(strategy.getName()).add(new Result(strategy.getName(), parcelCount, durationMs));
			}
		}

		displayComparison(results, console);

		fileWriter.close();
	}
}
