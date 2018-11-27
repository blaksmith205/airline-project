package github.airlineproject.util;

import java.util.ArrayList;

/**
 * This class cleans data obtained from files. For example, creates Passengers
 * objects from reservations.txt or obtains a char array for the seat map.
 *
 * @author William Blanc <test@email.com>
 */
public class DataFormatter {

    /**
     * Obtains all Passengers stored in reservations.txt
     *
     * @return ArrayList of stored passengers.
     */
    public static ArrayList<Passenger> getPassengers() {
        // If reservations.txt exists
        if (FileIO.exists("reservations.txt")) {
            ArrayList<String> lines = FileIO.fileReader("reservations.txt");    // Obtain every line
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
     * Gets the Seat map as a a char array from the desired flight text file.
     * @param flightFile: The name of the flight to obtain with the .txt format
     * @return Char array of the seat map
     */
    public static char[][] getSeatMapArray(String flightFile) {
        char[][] seats = new char[10][7];
        
        // If file exists
        if (FileIO.exists(flightFile)) {
            ArrayList<String> lines = FileIO.fileReader(flightFile);
            for (int row = 0; row < lines.size(); row++) {
                String line = lines.get(row).replaceAll(" ", "").replaceAll("\t", ""); // Replace all spaces and tabs with nothing
                line = line.replaceAll("[0-9]", "");   // replace all digits with empty string
                System.out.println(line);
                seats[row] = line.toCharArray();
            }
        }

        return seats;
    }
}
