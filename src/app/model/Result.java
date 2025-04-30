package app.model;

/**
 * Represents the result of a truck loading strategy execution, including
 * performance metrics and the list of trucks.
 */
public class Result {

    /**
     * The name of the truck loading strategy.
     */
    public String name;
    /**
     * The number of parcels processed.
     */
    public int parcelCount;
    /**
     * The execution time in milliseconds.
     */
    public double timeMs;

    /**
     * Constructs a Result with the specified metadata and truck list.
     *
     * @param name the name of the strategy
     * @param parcelCount the number of parcels processed
     * @param timeMs execution time in milliseconds
     */
    public Result(String name, int parcelCount, double timeMs) {
        this.name = name;
        this.parcelCount = parcelCount;
        this.timeMs = timeMs;
    }
}
