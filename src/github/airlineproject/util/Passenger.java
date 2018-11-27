package github.airlineproject.util;

/**
 *
 * @author
 */
public class Passenger {

    private String ID;  // Either Drivers License Number, Passport ID, or generated ID.

    private final String firstName;   // First name used in the full name
    private final String lastName;    // Last name used in the full name

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
        setSeatNum(seatNumber);
        flightNum = flightNumber;
    }

    /**
     * Convenience constructor with a given full name. Splits the full name at the space
     * and sets the value of firstName and lastName from respective elements. Full name should
     * be formatted as "firstName lastName" to work.
     * @param ID: The ID of the user. Can be a Driver License, Passport ID, or
     * generated ID
     * @param fullName: Full name of the Passenger. Formatted as "firstName lastName"
     * @param seatNumber: The reserved seat
     * @param flightNumber : The flight the reserved seat is on.
     */
    public Passenger(String ID, String fullName, String seatNumber, String flightNumber){
        String[] names = fullName.split(" ");
        this.ID = ID;
        this.firstName = names[0].trim();
        this.lastName = names[1].trim();
        setSeatNum(seatNumber);
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

    public String getLastName() {
        return lastName;
    }

    /**
     * @return The full name of the Passenger. Formatted as: firstName + " " +
     * lastName
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSeatNum() {
        return seatNum;
    }

    /**
     * Sets the chosen seat. Seats should be formatted as [number][letter] and 
     * should only be 2 characters long. IllegalArgumentException is thrown otherwise
     * @param seatNum 
     */
    public final void setSeatNum(String seatNum) {
        if (seatNum.length() > 2) {
            throw new IllegalArgumentException("Seat number should be only 2 characters, of the form [number][letter]");
        }

        this.seatNum = seatNum.toUpperCase();   // Capitalizes the seat position
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum.toUpperCase();   // Capitalizes the flight code
    }

    /**
     * toString method override for debugging
     * @return A formatted string of all Passenger Information
     */
    @Override
    public String toString() {
        return String.format("%s: %s %s\n%s: %s\n%s: %s\n%s: %s\n",
                "Passenger", firstName, lastName, "ID", ID, "Flight", flightNum,
                "Seat", seatNum);
    }

    /**
     * Used to easily insert the passenger into the reservations.txt file
     * @return Formatted string separated with tabs for easy splitting
     */
    public String toFileString() {
        return String.format("%-10s\t%-15s\t%-10s\t%-7s\n", ID, getFullName(), seatNum, flightNum);
    }
}
