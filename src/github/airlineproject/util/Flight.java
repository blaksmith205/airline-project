package github.airlineproject.util;

/**
 * @author William
 *
 */
public class Flight {

    private final String flightCode;    // 1-3 unique characters to describe the host airline. Example: AA for American Airlines
    private final String number;        // 4-digit flight number
    private String date;                // date of the departing flight. Formatted as dd/mm/yyyy
    private String depTime;             // The time of departure. Formatted as hh:mm on a 24 hr scale
    private String arrivalTime;         // The time of arrivale. Formatted as hh:mm on a 24 hr scale
    private String departCity;          // The city the flight is departing from. Formatted as uppercase
    private String destCity;            // The city the flight is destined for. Formatted as uppercase
    private int availableSeats;         // The total number of available seats

    /**
     * Default Constructor for a Flight object. Mostly used for testing purposes
     */
    public Flight() {
        this("TST", "1234", "01/02/2018", "02:45", "05:45", "Orlando", "Miami", 80);
    }

    /**
     * Argument Constructor to create a user defined Flight object
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
            String arrivalTime, String departCity, String destCity, int availableSeats) {
        this.flightCode = flightCode;
        this.number = number;
        this.date = date;
        this.depTime = depTime;
        this.arrivalTime = arrivalTime;
        this.departCity = departCity.toUpperCase();
        this.destCity = destCity.toUpperCase();
        this.availableSeats = availableSeats;
    }

    // Getters for every private property
    public String getFlightCode() {
        return flightCode;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getDepTime() {
        return depTime;
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

    // Setters with formatting.

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
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
    
    // Other methods
}
