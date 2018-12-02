package github.airlineproject.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author William Blanc <test@email.com>
 */
public class FileIO {

    public static final String FILE_DIR = Paths.get("").toAbsolutePath().toString() + "/Text Files/";

    public static final String FLIGHT_DIR = FILE_DIR + "Flights/";

    public static final String FLIGHT_HEADER = String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-20s\n",
            "Flight#", "FlightDate", "DepartTime", "ArriveTime", "DepartCity", "DestCity", "AvailableSeats");

    public static final String RESERVATION_HEADER = String.format("%-10s\t%-15s\t%-10s\t%-7s\n",
            "ID", "Name", "SeatNumber", "Flight#");

    public static final String SEAT_MAP_ROW = String.format("%s %s\t%s %s %s\t%s %s\n",
            "A", "B", "C", "D", "E", "F", "G");

    /**
     * Writes each string to the desired file. Overwrites the file.
     *
     * @param dir: Directory of the file to write to
     * @param fileName: The name of the file to write to if available.
     * @param lines: The pre-formatted lines to write. This should include any
     * header lines.
     */
    public static void fileWriter(String dir, String fileName, String... lines) {
        try (Formatter writer = new Formatter(new BufferedWriter(new FileWriter(new File(dir, fileName))))) {
            for (String line : lines) {
                writer.format(line);    // Write the line to the file
            }
        } catch (SecurityException | IOException ex) {
            System.err.println("Error occured when writing to " + fileName + "\n" + ex);
        }
    }

    /**
     * Writes each string to the desired file. Appends the file.
     *
     * @param dir: Directory of the file to write to
     * @param fileName: The name of the file to write to if available.
     * @param lines: The new pre-formatted lines to add to the file
     */
    public static void fileAppender(String dir, String fileName, String... lines) {
        // Create a formatter that appends the file
        try (Formatter appender = new Formatter(new BufferedWriter(new FileWriter(new File(dir, fileName), true)))) {
            for (String line : lines) {
                appender.format(line);    // Write the line to the file
            }
        } catch (IOException ex) {
            System.err.println("Error occured when appending " + fileName + "\n" + ex);
        }
    }

    /**
     * Returns every line in the specified file.
     *
     * @param dir: Directory of the file to write to
     * @param fileName: The name of the file to read from.
     * @return Array containing every raw line as is from the file.
     */
    public static ArrayList<String> fileReader(String dir, String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(Paths.get(dir + fileName))) {
            while (reader.hasNext()) {
                lines.add(reader.nextLine());
            }
        } catch (IOException ex) {
            System.err.println("Error occured when reading from " + fileName + "\n" + ex);
        }

        return lines;
    }

    /**
     * Tests whether the specified file exists.
     *
     * @param fileName: Name of the file to check
     * @return true if the file exists
     */
    public static boolean exists(String dir, String fileName) {
        return Paths.get(dir + fileName).toFile().exists();
    }
}
