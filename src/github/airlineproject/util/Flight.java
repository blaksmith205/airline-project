package github.airlineproject.util;

/**
 * @author William
 *
 */
public class Flight {

    private final String flightCode;    // 1-3 unique characters to described the host airline. Example: AA for American Airlines
    private final int number;           // 4-digit flight
    private String date;                // date of the departing flight. Formatted as dd/mm/yyyy
    private int depTime;                // The time of departure, measured in minutes. 0 is 12:00 am, 1439 is 11:59 pm
    private int arrivalTime;            // The time of arrivale, measured in minutes. 0 is 12:00 am, 1439 is 11:59 pm
    private String departCity;          // The city the flight is departing from. Formatted as uppercase
    private String destCity;            // The city the flight is destined for. Formatted as uppercase
    private int availableSeats;         // The total number of available seats

    public Flight(String flightCode, int number, String date, int depTime, int arrivalTime, String departCity, String destCity, int availableSeats) {
        this.flightCode = flightCode;
        this.number = number;
        this.date = date;
        this.depTime = depTime;
        this.arrivalTime = arrivalTime;
        this.departCity = departCity;
        this.destCity = destCity;
        this.availableSeats = availableSeats;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getNumber() {
        return flightCode + number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDepTime() {
        return depTime;
    }

    public void setDepTime(int depTime) {
        this.depTime = depTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

}
