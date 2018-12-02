package github.airlineproject.util;

import java.util.ArrayList;

/**
 * Class for all data related to an airline flight
 *
 * @author William and Chente
 */
public final class Flight {

    /**
     * Number of rows in a seat map
     */
    public static final int SEAT_MAP_ROW = 10;
    /**
     * Number of columns in a seat map;
     */
    public static final int SEAT_MAP_COL = 7;

    private final String flightCode;    // 2 unique characters to describe the host airline. Example: AA for American Airlines
    private final String number;        // 4-digit flight number
    private String date;                // date of the departing flight. Formatted as ddMonyy
    private String departureTime;             // The time of departure. Formatted as hh:mm on a 24 hr scale
    private String arrivalTime;         // The time of arrivale. Formatted as hh:mm on a 24 hr scale
    private String departCity;          // The city the flight is departing from. Formatted as uppercase
    private String destCity;            // The city the flight is destined for. Formatted as uppercase
    private int availableSeats;         // The total number of available seats
    private char[][] seatMap;        // Seat map of the flight

    /**
     * Default Constructor for a Flight object. Mostly used for testing purposes
     */
    public Flight() {
        this("TS1234", "01/02/2018", "02:45", "05:45", "Orlando", "Miami", 70);
        this.setSeatMap();
    }

    /**
     * Convenience constructor for a flight object with the flight code and
     * flight number in the same string
     *
     * @param flightNumber
     * @param date
     * @param depTime
     * @param arrivalTime
     * @param departCity
     * @param destCity
     * @param availableSeats
     * @param seatMap
     */
    public Flight(String flightNumber, String date, String depTime, String arrivalTime,
            String departCity, String destCity, int availableSeats, char[][] seatMap) {

        this(flightNumber.substring(0, 2), flightNumber.substring(2), date, depTime,
                arrivalTime, departCity, destCity, availableSeats, seatMap);
    }

    /**
     * Convenience constructor for a flight object with the flight code and
     * flight number in the same string, and no inital seatMap
     *
     * @param flightNumber
     * @param date
     * @param departureTime
     * @param arrivalTime
     * @param departCity
     * @param destCity
     * @param availableSeats
     */
    public Flight(String flightNumber, String date, String departureTime,
            String arrivalTime, String departCity, String destCity, int availableSeats) {
        this(flightNumber, date, departureTime, arrivalTime, departCity, destCity, availableSeats, new char[SEAT_MAP_ROW][SEAT_MAP_COL]);
        this.setSeatMap();
    }

    /**
     * Argument Constructor to create a user defined Flight object
     *
     * @param flightCode
     * @param number
     * @param date
     * @param depTime
     * @param arrivalTime
     * @param departCity
     * @param destCity
     * @param availableSeats
     */
    public Flight(String flightCode, String number, String date, String depTime,
            String arrivalTime, String departCity, String destCity, int availableSeats,
            char[][] seatMap) {
        this.flightCode = flightCode.toUpperCase();
        this.number = getFormattedNumber(number);
        this.date = date;
        this.departureTime = depTime;
        this.arrivalTime = arrivalTime;
        setDepartCity(departCity); // Set the departing city in proper format
        setDestCity(destCity);  // Set the dest city in proper format

        if (availableSeats < 0) {
            throw new IllegalArgumentException("Available seats can't be negative");
        }
        this.availableSeats = availableSeats;

        if (!checkSeatMapSize(seatMap)) {
            throw new IllegalArgumentException(String.format("Seat map needs to be of type char[%d][%d]",
                    SEAT_MAP_ROW, SEAT_MAP_COL));
        }
        this.seatMap = seatMap;
    }

    // Getters for every private property
    public String getFlightCode() {
        return flightCode;
    }

    public String getNumber() {
        return number;
    }

    public String getFlight() { // Full flight number
        return flightCode + number;
    }

    public String getDate() {
        return date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartCity() {
        return departCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public char[][] getSeatMap() {
        return seatMap;
    }

    // Setters with formatting.
    public void setDate(String date) {
        this.date = date;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity.replaceAll(" ", "_").toUpperCase();
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity.replaceAll(" ", "_").toUpperCase();
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setSeatMap(char[][] seatMap) {
        this.seatMap = seatMap;
    }

    /**
     * Convenience method for setting a new seatMap;
     */
    public void setSeatMap() {
        seatMap = new char[SEAT_MAP_ROW][SEAT_MAP_COL];

        for (int i = 0; i < seatMap.length; i++) {
            for (int j = 0; j < seatMap[i].length; j++) {
                seatMap[i][j] = getChar(j);
            }
        }
    }

    /**
     * Updates the desired seat map location
     *
     * @param row: row of the seat
     * @param col: col of the seat
     * @param character: new character to set
     */
    public void updateSeatMap(int row, int col, char character) {
        if (availableSeats > 0) {
            if (seatMap[row][col] == 'X') {
                throw new IllegalArgumentException("This seat is already taken, please select another");
            } else {
                seatMap[row][col] = character;
                availableSeats -= 1;
            }
        } else {
            throw new IllegalArgumentException("There are no more available seats on flight# " + getFlight());
        }
    }

    // String methods
    /**
     * toString method override for debugging
     *
     * @return A formatted string of all Flight Information
     */
    @Override
    public String toString() {
        return String.format("%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %s\n%s: %d\n%s:\n%s\n",
                "Flight", getFlight(), "Date", date, "Departure Time", departureTime,
                "Arrival Time", arrivalTime, "From", departCity, "To", destCity,
                "Available Seats", availableSeats, "Seat Map", seatMapString());
    }

    /**
     * Used to easily insert the flight into the flight.txt file
     *
     * @return Formatted string separated with tabs for easy splitting
     */
    public String toFileString() {
        return String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-20s\n",
                getFlight(), date, departureTime, arrivalTime, departCity, destCity, availableSeats);
    }

    /**
     * Obtains the rows for the seatMap
     *
     * @return
     */
    public String[] getSeatMapRows() {
        ArrayList<String> rows = new ArrayList<String>();
        for (int row = 0; row < seatMap.length; row++) {
            rows.add(String.format("%c %c\t%c %c %c\t%c %c\n", seatMap[row][0], seatMap[row][1],
                    seatMap[row][2], seatMap[row][3], seatMap[row][4], seatMap[row][5], seatMap[row][6]));
        }
        return rows.toArray(new String[0]);
    }

    // Other methods
    // Private methods to assist in formatting
    /**
     * Appends 0 to the beginning of the entered String representation of a
     * number
     *
     * @param number
     * @return The formatted String
     */
    private String getFormattedNumber(String number) {
        switch (number.length()) {
            case 1:
                number = "0".concat(number);
            case 2:
                number = "0".concat(number);
            case 3:
                number = "0".concat(number);
                break;
        }
        return number;
    }

    /**
     * Check if the seatmap is the right size
     *
     * @param seatMap: The seat map to check
     * @return True if the array is char[10][7]
     */
    private boolean checkSeatMapSize(char[][] seatMap) {
        if (seatMap.length == SEAT_MAP_ROW) {
            if (seatMap[0].length == SEAT_MAP_COL) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtains the seat map from the array
     *
     * @return
     */
    private String seatMapString() {
        String mapString = "";
        for (int row = 0; row < seatMap.length; row++) {
            for (int col = 0; col < seatMap[row].length; col++) {
                mapString += String.format("%c ", seatMap[row][col]);
            }
            mapString += "\n";
        }
        return mapString;
    }

    /**
     * Maps numbers to chars
     *
     * @param num
     * @return
     */
    public static char getChar(int num) {
        switch (num) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            default:
                return 'X';
        }
    }
}
