package app.strategy;

import java.util.List;

import app.model.Truck;
import app.model.TruckLoadingProblem;

public interface TruckLoadingStrategy {
	List<Truck> solve(TruckLoadingProblem problem);
}
