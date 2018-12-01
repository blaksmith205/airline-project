package github.airlineproject.util;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class FileIOTest {

    public FileIOTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of fileWriter method, of class FileIO.
     */
    @Test
    public void testFileWriter() {
        Passenger test = new Passenger("1035093287", "George", "Eric", "2a", "AA1230");
        Passenger test2 = new Passenger("E8742784", "Tom", "Ed", "1c", "AA1150");
        String[] lines = {FileIO.RESERVATION_HEADER, test.toFileString(),
            test2.toFileString()};
        FileIO.fileWriter(FileIO.FILE_DIR, "reservations.txt", lines);

        System.out.println("Successfully wrote to reservations.txt");

        ArrayList<String> flightLines = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            flightLines.add(i + "\t" + FileIO.SEAT_MAP_ROW);
        }
        FileIO.fileWriter(FileIO.FLIGHT_DIR, "AA1230.txt", flightLines.toArray(new String[0]));
        System.out.println("Successfully created AA1230 seat map");
    }

    /**
     * Test of fileAppender method, of class FileIO.
     */
    @Test
    public void testFileAppender() {
    }

    /**
     * Test of fileReader method, of class FileIO.
     */
    @Test
    public void testFileReader() {
        ArrayList<String> lines = FileIO.fileReader(FileIO.FILE_DIR, "reservations.txt");
        ArrayList<Passenger> passengers = new ArrayList<>();
        lines.remove(0);    // Remove header
        for (String line : lines) {
            String[] passengerInfo = line.split("\t");
            passengers.add(new Passenger(passengerInfo[0].trim(), passengerInfo[1].trim(),
                    passengerInfo[2].trim(), passengerInfo[3].trim()));
        }

        for (Passenger p : passengers) {
            System.out.println(p);
        }
    }

}
