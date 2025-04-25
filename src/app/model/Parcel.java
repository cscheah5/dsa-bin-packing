package app.model;

public class Parcel {
	private String type;
	private double weight;
	private boolean fragile;
	private String destination;

	public Parcel(String type, double weight, boolean fragile, String destination) {
		this.type = type;
		this.weight = weight;
		this.fragile = fragile;
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Parcel [type=" + type + ", weight=" + weight + ", fragile=" + fragile + ", destination=" + destination
				+ "]";
	}

}
