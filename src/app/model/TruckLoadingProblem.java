package app.model;

import java.util.List;

/**
 * Represents the problem definition for truck loading, encapsulating the
 * capacity of the trucks (bins) and the list of parcels (items) to be loaded.
 */
public class TruckLoadingProblem {

    private final double binCapacity;
    private final List<Parcel> parcels;

    /**
     * Constructs a new TruckLoadingProblem instance.
     *
     * @param binCapacity The maximum weight capacity of each truck (bin).
     * @param parcels The list of parcels (items) that need to be loaded.
     */
    public TruckLoadingProblem(double binCapacity, List<Parcel> parcels) {
        if (binCapacity <= 0) {
            throw new IllegalArgumentException("Bin capacity must be greater than zero.");
        }
        if (parcels == null || parcels.isEmpty()) {
            throw new IllegalArgumentException("Parcels list cannot be null or empty.");
        }
        // Check if any parcel is too large for the bin
        double maxParcelWeight = parcels.stream().mapToDouble(Parcel::getWeight).max().orElse(0);
        if (binCapacity < maxParcelWeight) {
            System.err.printf("Unsolvable: Bin capacity %.2f is smaller than the largest parcel weight %.2f.\n", binCapacity, maxParcelWeight);
            throw new IllegalArgumentException("Problem is unsolvable: bin capacity is smaller than the largest parcel.");
        }
        this.binCapacity = binCapacity;
        this.parcels = parcels;
    }

    /**
     * Returns the capacity of the trucks (bins).
     *
     * @return The maximum weight capacity of each truck.
     */
    public double getBinCapacity() {
        return binCapacity;
    }

    /**
     * Returns the list of parcels to be loaded.
     *
     * @return A list of {@link Parcel} objects.
     */
    public List<Parcel> getParcels() {
        return parcels;
    }

    /**
     * Returns a string representation of the truck loading problem, including
     * the bin capacity and the list of parcels.
     *
     * @return A string describing the problem.
     */
    @Override
    public String toString() {
        return "Capacity: " + binCapacity + "\nParcels: " + parcels;
    }
}
