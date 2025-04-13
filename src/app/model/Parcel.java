package app.model;

public class Parcel {
    private double weight;
    private String type;

    public Parcel(double weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("Parcel{weight=%.2f, type='%s'}", weight, type);
    }
}
