/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.airlineproject.util;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author William Blanc <test@email.com>
 */
public class DataFormatterTest {
    
    public DataFormatterTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPassengers method, of class DataFormatter.
     */
    @Test
    public void testGetPassengers() {
        ArrayList<Passenger> passengerList = DataFormatter.getPassengers();
        for(Passenger p: passengerList){
            System.out.println(p);
        }
    }

    /**
     * Test of getSeatMapArray method, of class DataFormatter.
     */
    @Test
    public void testGetSeatMapArray() {
        char[][] testMap = DataFormatter.getSeatMapArray("AA1140.txt");
        System.out.println(Arrays.deepToString(testMap));
        for (int row = 0; row < testMap.length; row++) {
            for (int col = 0; col < testMap[row].length; col++) {
                System.out.printf("%c ",testMap[row][col]);
            }
            System.out.println("");
        }
    }
    
    @Test
    public void testGetFlights(){
        ArrayList<Flight> testFlights = DataFormatter.getFlights();
        for(Flight f: testFlights){
            System.out.println(f);
        }
    }
}
