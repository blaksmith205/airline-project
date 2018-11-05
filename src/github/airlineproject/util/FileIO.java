package github.airlineproject.util;

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

    /**
     * Writes each string to the desired file. Overwrites the file.
     *
     * @param fileName: The name of the file to write to if available.
     * @param lines: The pre-formatted lines to write. This should include any
     * header lines.
     */
    public static void fileWritter(String fileName, String[] lines) {
        try (Formatter writer = new Formatter(fileName)) {
            if (lines.length != 0) {
                for (String line : lines) {
                    writer.format(line);    // Write the line to the file
                }
            }
        } catch (SecurityException | IOException ex) {
            System.err.println("Error occured when writing to " + fileName + "\n" + ex);
        }
    }

    /**
     * Returns every line in the specified file.
     *
     * @param fileName: The name of the file to read from.
     * @return: Every line as is from the file.
     */
    public static String[] fileReader(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(Paths.get(fileName))) {
            while (reader.hasNext()) {
                lines.add(reader.nextLine());
            }
        } catch (IOException ex) {
            System.err.println("Error occured when reading from " + fileName + "\n" + ex);
        }

        return (String[]) lines.toArray();
    }
}
