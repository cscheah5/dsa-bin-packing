package app.model;

public interface Item {
	String getType();
	void setType(String type);
	double getWeight();
	void setWeight(double weight);
	boolean isFragile();
	void setFragile(boolean fragile);
}
