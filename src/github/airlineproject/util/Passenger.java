package github.airlineproject.util;

/**
 *
 * @author
 */
public class Passenger {

    private String ID;  // Either Drivers License Numver, Passport ID, or generated ID.

    private String firstName;   // First name used in the full name
    private String lastName;    // Last name used in the full name

    /**
     * seatNum should be formatted as [number][char]. Example: 2a Stores the
     * seat the passenger booked / is on
     */
    private String seatNum;

    /**
     * flightNum should be formatted as [airline code][airline flight number].
     * Example: AA1230 Stores the flight the passenger booked / is on
     */
    private String flightNum;

    /**
     * Argument Constructor to initialize a Passenger
     *
     * @param ID: The ID of the user. Can be a Driver License, Passport ID, or
     * generated ID
     * @param firstName: The first name of the Passenger
     * @param lastName: The last name of the Passenger
     * @param seatNumber: The reserved seat
     * @param flightNumber : The flight the reserved seat is on.
     */
    public Passenger(String ID, String firstName, String lastName, String seatNumber, String flightNumber) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        seatNum = seatNumber;
        flightNum = flightNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The full name of the Passenger. Formatted as: firstName + " " + lastName 
     */
    public String getFullName(){
        return firstName + " " + lastName;
    }
    
    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        
        this.seatNum = seatNum.toUpperCase();   // Capitalizes the seat position
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum.toUpperCase();   // Capitalizes the flight code
    }

    
}
