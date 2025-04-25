package app.model;

public class Parcel implements Item {
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
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public double getWeight() {
		return weight;
	}
	
	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public boolean isFragile() {
		return fragile;
	}
	
	@Override
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
