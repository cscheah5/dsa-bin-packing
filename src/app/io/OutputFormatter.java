package app.io;

import app.model.Result;
import app.model.Truck;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputFormatter {

    public static void displayResults(List<Truck> trucks, String strategyName, PrintWriter writer) {
        writer.println("\n══════════════════════════════════════");
        writer.println(" STRATEGY: " + strategyName.toUpperCase());
        writer.println("══════════════════════════════════════");

        printSummary(trucks, writer);
        printVisualization(trucks, writer);
        printDetailedTrucks(trucks, writer);
    }

    private static void printSummary(List<Truck> trucks, PrintWriter writer) {
        double totalCapacity = trucks.get(0).getCapacity() * trucks.size();
        double usedCapacity = trucks.stream().mapToDouble(Truck::getUsedCapacity).sum();
        double utilization = (usedCapacity / totalCapacity) * 100;

        writer.printf("\nSUMMARY:%n");
        writer.printf("• Trucks used: %d%n", trucks.size());
        writer.printf("• Total capacity: %.2f/%.2f (%.1f%% utilized)%n", usedCapacity, totalCapacity, utilization);
        writer.printf("• Average truck utilization: %.1f%%%n",
                trucks.stream().mapToDouble(t -> t.getUsedCapacity() / t.getCapacity() * 100).average().orElse(0));
    }

    private static void printVisualization(List<Truck> trucks, PrintWriter writer) {
        writer.println("\nVISUALIZATION:");
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck = trucks.get(i);
            int filledWidth = (int) ((truck.getUsedCapacity() / truck.getCapacity()) * 30);

            writer.printf("Truck %2d: |%s%s| %.1f%%%n", i + 1, "█".repeat(filledWidth),
                    " ".repeat(30 - filledWidth), (truck.getUsedCapacity() / truck.getCapacity()) * 100);
        }
    }

    private static void printDetailedTrucks(List<Truck> trucks, PrintWriter writer) {
        writer.println("\nDETAILED LOADING:");
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck = trucks.get(i);
            writer.printf("%nTRUCK %d (%.2f/%.2f):%n", i + 1, truck.getUsedCapacity(), truck.getCapacity());

            truck.getParcels().forEach(parcel -> writer.printf("  → %s%n", parcel));

            writer.printf("  Remaining space: %.2f%n", truck.getRemainingCapacity());
        }
    }

    /**
     * Displays a comparison of strategy performance across different parcel
     * counts, including execution times and estimated growth rates.
     *
     * @param resultsMap A map where keys are strategy names and values are
     * lists of Results (each Result corresponding to a specific parcel count
     * run).
     * @param writer The PrintWriter to write the output to.
     */
    public static void displayComparison(Map<String, ArrayList<Result>> resultsMap, PrintWriter writer) {
        if (resultsMap == null || resultsMap.isEmpty()) {
            writer.println("\nNo comparison results to display.");
            return;
        }

        writer.println("\n=== Strategy Time Complexity Comparison ===");
        int maxParcelCount = resultsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(r -> r.parcelCount)
                .max().orElse(0);
        Map<String, Double> maxTimes = new HashMap<>();

        for (Map.Entry<String, ArrayList<Result>> entry : resultsMap.entrySet()) {
            String strategyName = entry.getKey();
            ArrayList<Result> resultsList = entry.getValue();
            resultsList.sort(Comparator.comparingInt(r -> r.parcelCount));

            writer.printf("\n--- Strategy: %s ---%n", strategyName);
            writer.printf("%-15s %-15s %-15s %-15s%n", "Parcel Count", "Time (ms)", "Time Ratio", "log Ratio");
            writer.println("---------------------------------------------------------------");

            Result prev = null;
            double growthSum = 0;
            double logSum = 0;
            int growthCount = 0;
            for (Result curr : resultsList) {
                String ratioStr = "-";
                String logStr = "-";
                if (prev != null && prev.timeMs > 0 && prev.parcelCount > 0) {
                    double ratio = curr.timeMs / prev.timeMs;
                    double countRatio = (double) curr.parcelCount / prev.parcelCount;
                    ratioStr = String.format("%.2fx", ratio);
                    if (ratio > 0 && countRatio > 0) {
                        double logRatio = Math.log(ratio) / Math.log(countRatio);
                        logStr = String.format("%.2f", logRatio);
                        logSum += logRatio;
                    }
                    growthSum += ratio;
                    growthCount++;
                }
                writer.printf("%-15d %-15.3f %-15s %-15s%n", curr.parcelCount, curr.timeMs, ratioStr, logStr);
                prev = curr;
                if (curr.parcelCount == maxParcelCount) {
                    maxTimes.put(strategyName, curr.timeMs);
                }
            }
            if (growthCount > 0) {
                double avgGrowth = growthSum / growthCount;
                double avgLog = logSum / growthCount;
                writer.printf("Estimated growth rate: %.2fx per 10x parcels\n", avgGrowth);
                writer.printf("Estimated O(N^k) exponent: k ≈ %.2f\n", avgLog);
            }
        }
        if (maxTimes.size() > 1) {
            String slowest = Collections.max(maxTimes.entrySet(), Map.Entry.comparingByValue()).getKey();
            String fastest = Collections.min(maxTimes.entrySet(), Map.Entry.comparingByValue()).getKey();
            double slowestTime = maxTimes.get(slowest);
            double fastestTime = maxTimes.get(fastest);
            double speedup = (slowestTime - fastestTime) / slowestTime * 100.0;
            writer.printf("\n%s is approximately %.1f%% faster than %s for N=%d.\n",
                    fastest, speedup, slowest, maxParcelCount);
        }
        writer.println("\nNote: 'Time Ratio' shows T(N) / T(Previous N). 'log Ratio' estimates the exponent k in O(N^k).\n");
    }
}
