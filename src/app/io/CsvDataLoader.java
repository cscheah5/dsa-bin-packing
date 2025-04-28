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
	
	
	 public static List<Parcel> readCSV(String filePath, int numRecordsToRead) {
	        String line;
	        String delimiter = ",";  // CSV delimiter
	        List<Parcel> parcels = new ArrayList<>();
	        int parsedRecord = 0;
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            // Read the header (first line)
	            String header = br.readLine();
	            System.out.println("Header: " + header);

	            // Read the CSV content line by line
	            while ((line = br.readLine()) != null && parsedRecord<numRecordsToRead) {
	                String[] data = line.split(delimiter);
	                // Print each line (you can process the data as needed)
//	                System.out.println("Type: " + data[0] + ", Weight: " + data[1] + ", Fragile: " + data[2] + ", Destination: " + data[3]);
	                parcels.add(new Parcel(parsedRecord, data[0], Double.parseDouble(data[1]), Boolean.parseBoolean(data[2]), data[3]));
	                
	                parsedRecord++;
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return parcels;
	    }
	
	
	
//	public static List<TruckLoadingProblem> loadTruckLoadingProblems(String csvFile, String csvDelimeter) {
//		String line;
//		List<TruckLoadingProblem> binData = new ArrayList<>();
//
//		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//			while ((line = br.readLine()) != null) {
//				String[] values = line.split(csvDelimeter);
//				try {
//					// Parse truck capacity (column 1)
//					Double truckCapacity = Double.parseDouble(values[0].trim());
//
//					// Parse number of records (column 2)
//					int numRecords = Integer.parseInt(values[1].trim());
//
//					// Parse parcels (columns 3+)
//					List<Parcel> parcels = new ArrayList<>();
//					for (int i = 2; i < values.length; i += 2) {
//						double weight = Double.parseDouble(values[i].trim());
//						String type = values[i + 1].trim();
//						parcels.add(new Parcel(weight, type, (i-1)/2));
//					}
//
//					// Create TruckLoadingProblem instance
//					TruckLoadingProblem problem = new TruckLoadingProblem(truckCapacity, parcels);
//					binData.add(problem);
//
//				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
//					System.err.println("Error parsing line: " + line);
//					e.printStackTrace();
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return binData;
//	}
}
