package app.model;

/**
 * Represents a parcel, which is a specific type of item with additional
 * properties like destination. Implements the Item interface and is comparable
 * based on weight.
 */
public class Parcel implements Item, Comparable<Parcel> {

    private final int index; // To track the parcel
    private String type;
    private double weight;
    private boolean fragile;
    private String destination;

    /**
     * Constructs a new Parcel object.
     *
     * @param index The index of the parcel.
     * @param type The type of the parcel.
     * @param weight The weight of the parcel.
     * @param fragile Whether the parcel is fragile.
     * @param destination The destination of the parcel.
     */
    public Parcel(int index, String type, double weight, boolean fragile, String destination) {
        this.index = index;
        this.type = type;
        this.weight = weight;
        this.fragile = fragile;
        this.destination = destination;
    }

    /**
     * Gets the index of the parcel.
     *
     * @return The index of the parcel.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the type of the parcel.
     *
     * @return The type of the parcel.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the parcel.
     *
     * @param type The new type for the parcel.
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the weight of the parcel.
     *
     * @return The weight of the parcel.
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the parcel.
     *
     * @param weight The new weight for the parcel.
     */
    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Checks if the parcel is fragile.
     *
     * @return true if the parcel is fragile, false otherwise.
     */
    @Override
    public boolean isFragile() {
        return fragile;
    }

    /**
     * Sets the fragile status of the parcel.
     *
     * @param fragile The new fragile status for the parcel.
     */
    @Override
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    /**
     * Gets the destination of the parcel.
     *
     * @return The destination of the parcel.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the parcel.
     *
     * @param destination The new destination for the parcel.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns a string representation of the Parcel object.
     *
     * @return A string containing the parcel's details.
     */
    @Override
    public String toString() {
        return "Parcel " + (index + 1) + ": [type=" + type + ", weight=" + weight + ", fragile=" + fragile + ", destination=" + destination
                + "]";
    }

    /**
     * Compares this parcel with another parcel based on weight.
     *
     * @param other The other parcel to compare to.
     * @return a negative integer, zero, or a positive integer as this parcel's
     * weight is less than, equal to, or greater than the specified parcel's
     * weight.
     */
    @Override
    public int compareTo(Parcel other) {
        return Double.compare(this.weight, other.weight);
    }

}
