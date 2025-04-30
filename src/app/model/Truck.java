package app.model;

import java.util.List;

/**
 * Represents a truck for storing Parcels with a fixed capacity.
 */
public class Truck extends AbstractStackContainer<Parcel> implements Comparable<Truck> {

    /** Index identifier for the truck. */
    private final int index; // To track the truck(bin)
    /** Maximum capacity of the truck. */
    private final double capacity;
    /** Remaining capacity of the truck. */
    private double remainingCapacity;

    /**
     * Constructs a Truck with the specified index and capacity.
     * @param index identifier for this truck
     * @param capacity maximum weight capacity
     */
    public Truck(int index, double capacity) {
        super(); // it will create items arraylist
        this.index = index;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
    }

    /**
     * Returns the index of the truck.
     * @return the truck's index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the list of parcels in this truck.
     * @return list of parcels
     */
    public List<Parcel> getParcels() {
        return items;
    }

    /**
     * Returns the maximum capacity of the truck.
     * @return truck capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Returns the remaining capacity of the truck.
     * @return remaining capacity
     */
    public double getRemainingCapacity() {
        return remainingCapacity;
    }

    /**
     * Returns the used capacity of the truck.
     * @return used capacity
     */
    public double getUsedCapacity() {
        return capacity - remainingCapacity;
    }

    /**
     * Attempts to add a parcel to the truck if it fits.
     * @param parcel parcel to add
     * @return true if added, false otherwise
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
     * Removes the most recently added parcel from the truck.
     * @return true if a parcel was removed, false if empty
     */
    public boolean removeParcel() {
        if (!isEmpty()) {
            pop();
            return true;
        }
        return false;
    }

    /**
     * Retrieves the top parcel without removing it.
     * @return top parcel or null if empty
     */
    public Parcel getParcel() {
        if (!isEmpty()) {
            return peek();
        }
        return null;
    }

    /**
     * Checks if the given item can fit in the remaining capacity.
     * @param item item to check
     * @return true if it fits, false otherwise
     */
    @Override
    public boolean canFit(Item item) {
        return remainingCapacity >= item.getWeight();
    }

    /**
     * Returns a string representation of the truck.
     * @return string with truck index and parcels
     */
    @Override
    public String toString() {
        return "Truck " + this.index + ", Parcels: " + this.items;
    }

    /**
     * Compares trucks by remaining capacity, using index as tiebreaker.
     * @param other other truck to compare
     * @return positive if this has more remaining capacity, negative if less, or compare index if equal
     */
    @Override
    public int compareTo(Truck other) {
        if (this.remainingCapacity > other.remainingCapacity) {
            return 1;
        }
        if (this.remainingCapacity < other.remainingCapacity) {
            return -1;
        }
        // Tie-breaker: lower index wins 
        return Integer.compare(this.index, other.index);
    }

}
