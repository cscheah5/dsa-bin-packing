package app.main;

import java.util.*;

import app.io.CsvDataLoader;
import app.model.TruckLoadingProblem;
import app.strategy.FirstFitStrategy;
import app.strategy.TruckLoadingStrategy;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hi Eu");
		List<TruckLoadingProblem> problems = CsvDataLoader.loadTruckLoadingProblems("truck_loading_data.csv", ",");

		problems.forEach(System.out::println);
		
		TruckLoadingStrategy strategy = new FirstFitStrategy();
	}


}
