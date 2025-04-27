package app.io;

import app.model.Parcel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLoader {

    private static final String DELIMITER = ",";  // CSV delimiter
    private static final int EXPECTED_COLUMNS = 4; // Define expected number of columns

    public static List<Parcel> readCSV(String filePath, int numRecordsToRead) throws IOException {
        String line;
        List<Parcel> parcels = new ArrayList<>();
        int parsedRecord = 0;
        int lineNumber = 0; // Keep track of line number for error messages

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            // Read and skip the header
            String header = br.readLine();
            lineNumber++;
            if (header == null) {
                System.err.println("Warning: CSV file is empty or contains only a header.");
                return parcels; // Return empty list
            }

            // Read the CSV content line by line
            while ((line = br.readLine()) != null && parsedRecord < numRecordsToRead) {
                lineNumber++;
                String[] data = line.split(DELIMITER);

                try {
                    // Validate number of columns
                    if (data.length < EXPECTED_COLUMNS) {
                        throw new IllegalArgumentException("Incorrect number of columns found.");
                    }

                    // Attempt to parse data - catches NumberFormatException
                    double weight = Double.parseDouble(data[1].trim()); // Use trim() for robustness
                    boolean fragile = Boolean.parseBoolean(data[2].trim());

                    // Create and add parcel - catches ArrayIndexOutOfBounds implicitly if validation above fails
                    parcels.add(new Parcel(parsedRecord, data[0].trim(), weight, fragile, data[3].trim()));
                    parsedRecord++;

                } catch (NumberFormatException e) {
                    // Log or print warning for bad number format and skip line
                    System.err.printf("Warning: Skipping line %d due to invalid number format: %s%n", lineNumber, line);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    // Log or print warning for incorrect column count or other parsing issues
                    System.err.printf("Warning: Skipping line %d due to invalid format (%s): %s%n", lineNumber, e.getMessage(), line);
                }
            }
        }

        if (parsedRecord < numRecordsToRead && (line == null)) {
            System.out.println("Info: Reached end of file before reading the requested " + numRecordsToRead + " records. Read " + parsedRecord + " records.");
        }

        return parcels;
    }
}
