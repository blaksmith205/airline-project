package github.airlineproject.util;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

/**
 * This class is responsible for file / object interaction. Data is read from
 * file and formatted into object. Formatted data is written to file
 *
 * @author William Blanc <test@email.com>
 */
public class DataFormatter {

    /**
     * Obtains all Passengers stored in reservations.txt
     *
     * @return ArrayList of stored flights.
     */
    public static ArrayList<Passenger> getPassengers() {
        // If reservations.txt exists
        if (FileIO.exists(FileIO.FILE_DIR, "reservations.txt")) {
            ArrayList<String> lines = FileIO.fileReader(FileIO.FILE_DIR, "reservations.txt");    // Obtain every line
            ArrayList<Passenger> passengers = new ArrayList<>();    // Create ArrayList for passengers
            lines.remove(0);    // Remove header
            for (String line : lines) {
                String[] passengerInfo = line.split("\t");
                passengers.add(new Passenger(passengerInfo[0].trim(), passengerInfo[1].trim(),
                        passengerInfo[2].trim(), passengerInfo[3].trim())); // Add the passengers from the stored information
            }
            return passengers;
        } else {
            return new ArrayList<Passenger>();
        }
    }

    /**
     * Obtains all Flights from flights.txt
     *
     * @return ArrayList of stored Flights
     */
    public static ArrayList<Flight> getFlights() {
        // If reservations.txt exists
        if (FileIO.exists(FileIO.FILE_DIR, "flight.txt")) {
            ArrayList<String> lines = FileIO.fileReader(FileIO.FILE_DIR, "flight.txt");    // Obtain every line
            ArrayList<Flight> flights = new ArrayList<>();    // Create ArrayList for flights
            lines.remove(0);    // Remove header
            for (String line : lines) {
                String[] flightInfo = line.split("\t");
                // Add the flights from the stored information
                flights.add(new Flight(flightInfo[0].trim(), flightInfo[1].trim(),
                        flightInfo[2].trim(), flightInfo[3].trim(), flightInfo[4].trim(),
                        flightInfo[5].trim(), Integer.parseInt(flightInfo[6].trim()),
                        DataFormatter.getSeatMapArray(flightInfo[0].trim() + ".txt")));
            }
            return flights;
        } else {
            return new ArrayList<Flight>();
        }
    }

    /**
     * Gets the Seat map as a a char array from the desired flight text file.
     *
     * @param dir: Directory of the file to obtain the seat map
     * @param flightFile: The name of the flight to obtain with the .txt format
     * @return Char array of the seat map
     */
    public static char[][] getSeatMapArray(String dir, String flightFile) {
        char[][] seats = new char[Flight.SEAT_MAP_ROW][Flight.SEAT_MAP_COL];

        // If file exists
        if (FileIO.exists(dir, flightFile)) {
            ArrayList<String> lines = FileIO.fileReader(dir, flightFile);
            for (int row = 0; row < lines.size(); row++) {
                String line = lines.get(row).replaceAll(" ", "").replaceAll("\t", ""); // Replace all spaces and tabs with nothing
                line = line.replaceAll("[0-9]", "");   // replace all digits with empty string
                seats[row] = line.toCharArray();
            }
        }

        return seats;
    }

    /**
     * Convenience method for obtaining the flight seat map, with directory of
     * FileIO.FLIGHT_DIR
     *
     * @param flightFile
     * @return Char array of the seat map
     */
    public static char[][] getSeatMapArray(String flightFile) {
        return getSeatMapArray(FileIO.FLIGHT_DIR, flightFile);
    }

    /**
     * Creates a new seat map file for the flightName
     *
     * @param flightName
     */
    public static void createFlightFile(String flightName) {
        ArrayList<String> flightLines = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            flightLines.add(i + "\t" + FileIO.SEAT_MAP_ROW);
        }
        FileIO.fileWriter(FileIO.FLIGHT_DIR, flightName, flightLines.toArray(new String[0]));
    }
}
