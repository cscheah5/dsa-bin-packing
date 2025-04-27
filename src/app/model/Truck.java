package app.model;

import java.util.List;

/**
 * Represents a truck used for transporting parcels. Each truck has a fixed
 * capacity and can hold multiple parcels. Implements Comparable to allow
 * sorting trucks based on remaining capacity.
 */
public class Truck extends AbstractStackContainer<Parcel> implements Comparable<Truck> {

    private final int index; // To track the truck(bin)
    private final double capacity;
    private double remainingCapacity;

    /**
     * Constructs a new Truck with a specified index and capacity.
     *
     * @param index The unique identifier for the truck.
     * @param capacity The maximum weight capacity of the truck.
     */
    public Truck(int index, double capacity) {
        super(); // it will create items arraylist
        this.index = index;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
    }

    /**
     * Gets the index of the truck.
     *
     * @return The index of the truck.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the list of parcels currently loaded onto the truck.
     *
     * @return A list of parcels.
     */
    public List<Parcel> getParcels() {
        return items;
    }

    /**
     * Gets the maximum weight capacity of the truck.
     *
     * @return The capacity of the truck.
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Gets the remaining weight capacity of the truck.
     *
     * @return The remaining capacity.
     */
    public double getRemainingCapacity() {
        return remainingCapacity;
    }

    /**
     * Gets the currently used weight capacity of the truck.
     *
     * @return The used capacity.
     */
    public double getUsedCapacity() {
        return capacity - remainingCapacity;
    }

    /**
     * Adds a parcel to the truck if it fits.
     *
     * @param parcel The parcel to add.
     * @return true if the parcel was added successfully, false otherwise.
     */
    public boolean addParcel(Parcel parcel) {
        if (canFit(parcel)) {
            push(parcel);
            remainingCapacity -= parcel.getWeight();
            return true;
        }
        return false;
    }

    /**
     * Removes the last added parcel from the truck.
     *
     * @param parcel The parcel to remove (Note: This parameter is currently
     * unused).
     * @return true if a parcel was removed, false if the truck was empty.
     */
    // NOTE: NO POINT PASSING IN PARCEL
    public boolean removeParcel(Parcel parcel) {
        if (!isEmpty()) {
            // Retrieve the parcel being removed to update remaining capacity
            Parcel removedParcel = pop();
            if (removedParcel != null) {
                remainingCapacity += removedParcel.getWeight();
            }
            return true;
        }
        return false;
    }

    /**
     * Gets the last added parcel without removing it.
     *
     * @return The last added parcel, or null if the truck is empty.
     */
    public Parcel getParcel() {
        if (!isEmpty()) {
            return peek();
        }
        return null;
    }

    /**
     * Checks if an item can fit into the truck based on its weight and the
     * truck's remaining capacity.
     *
     * @param item The item to check.
     * @return true if the item fits, false otherwise.
     */
    @Override
    public boolean canFit(Item item) {
        return remainingCapacity >= item.getWeight();
    }

    /**
     * Returns a string representation of the truck, including its index and the
     * parcels it contains.
     *
     * @return A string representation of the truck.
     */
    @Override
    public String toString() {
        return "Truck " + this.index + ", Parcels: " + this.items;
    }

    /**
     * Compares this truck to another truck based on their remaining capacity.
     * Trucks with more remaining capacity are considered "smaller" (come first
     * in sorting). If remaining capacities are equal, the truck with the lower
     * index is considered smaller.
     *
     * @param other The other truck to compare to.
     * @return A negative integer, zero, or a positive integer as this truck has
     * more, equal to, or less remaining capacity than the specified truck. If
     * capacities are equal, it returns the comparison of indices.
     */
    public int compareTo(Truck other) {
        // Compare remaining capacity in descending order (more remaining capacity first)
        int capacityCompare = Double.compare(other.remainingCapacity, this.remainingCapacity);
        if (capacityCompare != 0) {
            return capacityCompare;
        }
        // Tie-breaker: lower index wins (ascending order)
        return Integer.compare(this.index, other.index);
    }

}
