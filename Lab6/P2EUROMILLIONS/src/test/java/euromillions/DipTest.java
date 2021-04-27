/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        int[] count = {10, 20, 30, 40, 50};
        int[] stars = {1,2,3};
        assertThrows(IllegalArgumentException.class, () -> {new Dip(count,stars);});

        int[] count2 = {10, 20, 30, 40, 50, 60};
        int[] stars2 = {1,2};
        assertThrows(IllegalArgumentException.class, () -> {new Dip(count2,stars2);});
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @Test
    public void testConstructorFromBadRange(){
        //number great then 50
        assertThrows(IllegalArgumentException.class, () -> {new Dip(new int[]{10, 20, 30, 40, 51}, new int[]{1, 2});});
        //number less than equal 0
        assertThrows(IllegalArgumentException.class, () -> {new Dip(new int[]{0, 20, 30, 40, 50}, new int[]{1, 2});});
        //star less than equal 0
        assertThrows(IllegalArgumentException.class, () -> {new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{-1, 4});});
        //start greater than 10
        assertThrows(IllegalArgumentException.class, () -> {new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 14});});
    }
}
