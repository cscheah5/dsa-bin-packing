package app.model;

public class Parcel {
    private double weight;
    private String type;
    private int index;

    public Parcel(double weight, String type, int index) {
        this.weight = weight;
        this.type = type;
        this.index = index;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
    
    public int getIndex() {
		return index;
	}
    
    @Override
    public String toString() {
        return String.format("Parcel{weight=%.2f, type=%s, index=%d}", weight, type, index);
    }
}
