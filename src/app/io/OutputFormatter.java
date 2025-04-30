package app.io;

import app.model.Result;
import app.model.Truck;
import java.io.PrintWriter;
import java.util.List;

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

    public static void displayComparison(List<Result> results, PrintWriter writer) {
        if (results == null || results.isEmpty()) {
            return;
        }
        // find baseline (slowest) time and its name
        double baselineTime = results.stream().mapToDouble(r -> r.timeMs).max().orElse(0);
        String baselineName = results.stream()
                .filter(r -> r.timeMs == baselineTime)
                .findFirst().map(r -> r.name).orElse("");

        writer.println("\n=== Strategy Speedup Comparison ===");
        writer.printf("%-20s %-15s %-15s%n", "Strategy", "Time (ms)", "Speed vs " + baselineName);
        for (Result r : results) {
            double speedup = baselineTime > 0 ? (baselineTime / r.timeMs) * 100.0 : 0;
            writer.printf("%-20s %-15.3f %-15.2f%%%n", r.name, r.timeMs, speedup);
        }
    }
}
