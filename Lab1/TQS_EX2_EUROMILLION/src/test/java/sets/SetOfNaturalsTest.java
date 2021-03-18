/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");
    }

    @Test
    public void testNoIntersectForIntersection() {
        assertTrue(setB.intersects(setD), "intersection but was reported as non-existing");
    }

    // ADDED TESTS
    @Test
    public void testAddDuplicateElement(){
        setA.add(100);
        int size = setA.size();

        assertThrows(IllegalArgumentException.class, () -> setA.add(100));
        assertEquals(size, setA.size(), "size reported as different from expected");
    }

    @Test
    public void testAddNegativeElement(){
        int size = setB.size();
        assertThrows(IllegalArgumentException.class, () -> setA.add(-20));
        assertEquals(size, setB.size(), "size reported as different from expected");

        // 0 is not a natural number as well
        assertThrows(IllegalArgumentException.class, () -> setA.add(0));
        assertEquals(size, setB.size(), "size reported as different from expected");
    }

    @Test
    public void testAddDuplicateElementInArray(){
        int[] elems = new int[]{10, 15};
        int size = setB.size();
        assertThrows(IllegalArgumentException.class, () -> setB.add(elems));
        assertEquals(size, setB.size(), "size reported as different from expected");
    }

    @Test
    public void testSizeAfterAddElement(){
        int size = setB.size();
        setB.add(25);
        assertEquals(size + 1, setB.size(), "size reported as different from expected");
    }

    @Test
    public void testSizeAfterAddArray(){
        int size = setD.size();
        int [] addArray = new int[]{70,80};
        setD.add(addArray);
        assertEquals(size + addArray.length, setD.size(), "size reported as different from expected");
    }

    @Test
    public void testDuplicateElementsFromArray(){
        int[] arrayToSet = new int[]{10,20,30,20};
        assertThrows(IllegalArgumentException.class, () -> setA.fromArray(arrayToSet));
    }

    @Test
    public void testNegativeElementsFromArray(){
        int[] arrayToSet = new int[]{10,-20,30};
        assertThrows(IllegalArgumentException.class, () -> setA.fromArray(arrayToSet));

        int[] arrayToSet2 = new int[]{10,0,30};
        assertThrows(IllegalArgumentException.class, () -> setA.fromArray(arrayToSet2));
    }

    @Test
    public void testSizeSetFromArray(){
        int[] arrayToSet = new int[]{10,20,30,40,50};
        int arraySize = arrayToSet.length;
        assertEquals(arraySize, setA.fromArray(arrayToSet).size(), "size reported as different from expected");
    }

}
