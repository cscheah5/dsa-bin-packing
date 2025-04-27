package app.model;

/**
 * Represents an item that can be placed into a container. Defines the basic
 * properties common to all items, such as type, weight, and fragility.
 */
public interface Item {

    /**
     * Gets the type of the item.
     *
     * @return The type of the item as a String.
     */
    String getType();

    /**
     * Sets the type of the item.
     *
     * @param type The new type for the item.
     */
    void setType(String type);

    /**
     * Gets the weight of the item.
     *
     * @return The weight of the item as a double.
     */
    double getWeight();

    /**
     * Sets the weight of the item.
     *
     * @param weight The new weight for the item.
     */
    void setWeight(double weight);

    /**
     * Checks if the item is fragile.
     *
     * @return true if the item is fragile, false otherwise.
     */
    boolean isFragile();

    /**
     * Sets the fragile status of the item.
     *
     * @param fragile The new fragile status for the item.
     */
    void setFragile(boolean fragile);
}
