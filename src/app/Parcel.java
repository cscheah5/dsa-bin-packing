package app;

public class Parcel {
    private int weight;
    private String type;

    public Parcel(int weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}
