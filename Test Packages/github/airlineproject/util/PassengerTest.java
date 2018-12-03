package github.airlineproject.util;

import org.junit.*;

/**
 *
 * @author William
 */
public class PassengerTest {

    private Passenger passenger;

    public PassengerTest() {
    }

    @Before
    public void setUp() throws Exception {
        passenger = new Passenger("222-22-2222", "John", "Smith", "2a", "AA1230");
    }

    @After
    public void tearDonw() {
        passenger = null;
    }

    /**
     * Test of setID method, of class Passenger.
     */
    @Test
    public void testSetSeatNum() {
        String[] prompt = {"setID with incorrect ID", "\nsetId with proper ID"};
        String[] seatNums = {"2ab", "4c"};
        Passenger testSet = new Passenger("222-22-2222", "John", "Smith", "2a", "AA1230");
        for (int i = 0; i < prompt.length; i++) {
            try {
                System.out.println(prompt[i]);
                testSet.setSeatNum(seatNums[i]);
            } catch (IllegalArgumentException e) {
                System.out.println("Could not set the ID of " + seatNums[i]);
            }
        }

    }

    /**
     * Test of toString method, of class Passenger.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        System.out.println(passenger);
    }

    /**
     * Test of toFileString method, of class Passenger.
     */
    @Test
    public void testToFileString() {
        System.out.println("toFileString");
        System.out.println(passenger.toFileString());
    }

}
