package app.main;

import app.compare.StrategyComparison;
import app.io.CsvDataLoader;
import app.model.*;
import app.strategy.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Parcel> parcels = new ArrayList<>();
        try {
            parcels = CsvDataLoader.readCSV("parcel_data.csv", 100);
            System.out.println(parcels);
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        System.out.println(parcels);

        TruckLoadingProblem problem1 = new TruckLoadingProblem(100, parcels);

        TruckLoadingStrategy firstFit = new FirstFitStrategy();
        firstFit.solve(problem1);
        displayResults(firstFit.getTrucks(), "FIRST FIT");

        TruckLoadingStrategy bestFit = new BestFitStrategy();
        bestFit.solve(problem1);
        displayResults(bestFit.getTrucks(), "BEST FIT");

        StrategyComparison.compareTimeComplexity(firstFit, bestFit, problem1);
        StrategyComparison.compareSpaceComplexity(firstFit, bestFit, problem1);
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

    // Print table helper method in case if needed
    public static void printTable(List<String> headers, List<List<String>> rows) {
        int[] columnWidths = new int[headers.size()];

        // Calculate max width for each column
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
            for (List<String> row : rows) {
                columnWidths[i] = Math.max(columnWidths[i], row.get(i).length());
            }
        }

        // Print header row
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("%-" + (columnWidths[i] + 2) + "s", headers.get(i));
        }
        System.out.println();

        // Print separator
        for (int width : columnWidths) {
            System.out.print("-".repeat(width + 2));
        }
        System.out.println();

        // Print data rows
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                System.out.printf("%-" + (columnWidths[i] + 2) + "s", row.get(i));
            }
            System.out.println();
        }
    }
}
