/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William Blanc <test@email.com>
 */
public class FlightTest {
    private Flight testFlight;
    public FlightTest() {
    }
    
    @Before
    public void setUp() {
        testFlight = DataFormatter.getFlights().get(0);
        System.out.println(DataFormatter.getSeatMapArray(testFlight.getFlight()+".txt"));
    }
    
    @After
    public void tearDown() {
        testFlight = null;
    }


    /**
     * Test of toString method, of class Flight.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        System.out.println(testFlight);
    }

    /**
     * Test of toFileString method, of class Flight.
     */
    @Test
    public void testToFileString() {
        System.out.println("toFileString");
        System.out.println(testFlight.toFileString());
    }
    
    @Test
    public void testGetSeatMapRows(){
        String[] rows = testFlight.getSeatMapRows();
        for(String row: rows){
            System.out.println(row);
        }
    }
}
