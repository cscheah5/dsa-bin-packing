package app.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.model.Parcel;
import app.model.TruckLoadingProblem;

public class CsvDataLoader {
	public static List<TruckLoadingProblem> loadTruckLoadingProblems(String csvFile, String csvDelimeter) {
		String line;
		List<TruckLoadingProblem> binData = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(csvDelimeter);
				try {
					// Parse truck capacity (column 1)
					Double truckCapacity = Double.parseDouble(values[0].trim());

					// Parse number of records (column 2)
					int numRecords = Integer.parseInt(values[1].trim());

					// Parse parcels (columns 3+)
					List<Parcel> parcels = new ArrayList<>();
					for (int i = 2; i < values.length; i += 2) {
						double weight = Double.parseDouble(values[i].trim());
						String type = values[i + 1].trim();
						parcels.add(new Parcel(weight, type, (i-1)/2));
					}

					// Create TruckLoadingProblem instance
					TruckLoadingProblem problem = new TruckLoadingProblem(truckCapacity, parcels);
					binData.add(problem);

				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					System.err.println("Error parsing line: " + line);
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return binData;
	}
}
