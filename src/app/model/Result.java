package app.model;

import java.util.List;

/**
 * Represents the result of a truck loading strategy execution, including performance metrics and the list of trucks.
 */
public class Result {
    /** The name of the truck loading strategy. */
    public String name;
    /** The execution time in milliseconds. */
    public double timeMs;
    /** Memory usage in megabytes. */
    public double memoryUsageMb;
    /** The list of trucks produced by the strategy. */
    public List<Truck> trucks;

    /**
     * Constructs a Result with the specified metadata and truck list.
     *
     * @param name the name of the strategy
     * @param timeMs execution time in milliseconds
     * @param memoryUsageMb memory used in megabytes
     * @param trucks the list of loaded trucks
     */
    public Result(String name, double timeMs, double memoryUsageMb, List<Truck> trucks) {
        this.name = name;
        this.timeMs = timeMs;
        this.memoryUsageMb = memoryUsageMb;
        this.trucks = trucks;
    }
}
