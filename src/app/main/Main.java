package app.main;

import java.util.*;

import app.io.CsvDataLoader;
import app.model.*;
import app.strategy.*;

public class Main {
	public static void main(String[] args) {
		List<TruckLoadingProblem> problems = CsvDataLoader.loadTruckLoadingProblems("truck_loading_data.csv", ",");

		//problems.forEach(System.out::println);
		System.out.println(problems.get(0));

		TruckLoadingStrategy strategy = new FirstFitStrategy();

		List<Truck> trucks = strategy.solve(problems.get(0));

		displayResults(trucks, "FIRST FIT");
	}

	public static void displayResults(List<Truck> trucks, String strategyName) {
		System.out.println("\n══════════════════════════════════════");
		System.out.println(" STRATEGY: " + strategyName.toUpperCase());
		System.out.println("══════════════════════════════════════");

		printSummary(trucks);
		printVisualization(trucks);
		printDetailedTrucks(trucks);
	}

	private static void printSummary(List<Truck> trucks) {
		double totalCapacity = trucks.get(0).getCapacity() * trucks.size();
		double usedCapacity = trucks.stream().mapToDouble(Truck::getUsedCapacity).sum();
		double utilization = (usedCapacity / totalCapacity) * 100;

		System.out.printf("\nSUMMARY:%n");
		System.out.printf("• Trucks used: %d%n", trucks.size());
		System.out.printf("• Total capacity: %.2f/%.2f (%.1f%% utilized)%n", usedCapacity, totalCapacity, utilization);
		System.out.printf("• Average truck utilization: %.1f%%%n",
				trucks.stream().mapToDouble(t -> t.getUsedCapacity() / t.getCapacity() * 100).average().orElse(0));
	}

	private static void printVisualization(List<Truck> trucks) {
		System.out.println("\nVISUALIZATION:");
		for (int i = 0; i < trucks.size(); i++) {
			Truck truck = trucks.get(i);
			int filledWidth = (int) ((truck.getUsedCapacity() / truck.getCapacity()) * 30);

			System.out.printf("Truck %2d: |%s%s| %.1f%%%n", i + 1, "█".repeat(filledWidth),
					" ".repeat(30 - filledWidth), (truck.getUsedCapacity() / truck.getCapacity()) * 100);
		}
	}

	private static void printDetailedTrucks(List<Truck> trucks) {
		System.out.println("\nDETAILED LOADING:");
		for (int i = 0; i < trucks.size(); i++) {
			Truck truck = trucks.get(i);
			System.out.printf("%nTRUCK %d (%.2f/%.2f):%n", i + 1, truck.getUsedCapacity(), truck.getCapacity());

			truck.getParcels().forEach(parcel -> System.out.printf("  → %s%n", parcel));

			System.out.printf("  Remaining space: %.2f%n", truck.getRemainingCapacity());
		}
	}
}
