/** @file TestDemT.java
 @author Anando Zaman
 @brief Test Driver for DemT module
 @date March 12,2020
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

import src.DemT;

public class TestDemT{

    @Before
    //Used to repeat code before each test
    public void setUp() throws Exception{

    }

    @After
    //Used to repeat code after each test
    public void tearDown() throws Exception {
        //For any clean-up operations necessary after the testing is done
    }

    @Test
    public void test_total(){
        //Case 1: normal case
        ArrayList<ArrayList<Integer>> d = new ArrayList<ArrayList<Integer>>();
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3)));
        d.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5, 4)));
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 6, 2)));
        DemT dem = new DemT(d, 1.0);

        assertEquals(dem.total(),42);

        //Case 2: Edge/boundary case: Use of large max size integers
        ArrayList<ArrayList<Integer>> d1 = new ArrayList<ArrayList<Integer>>();
        d1.add(new ArrayList<Integer>(Arrays.asList(2147483647, 2147483647, 2147483647, 2147483647)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -2, -4, -3)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -3, -5, -4)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -2, -6, -2)));
        DemT dem1 = new DemT(d1, 1.0);
        assertEquals(dem1.total(),-38);

        //Case 3: Normal case
        ArrayList<ArrayList<Integer>> d2 = new ArrayList<ArrayList<Integer>>();
        d2.add(new ArrayList<Integer>(Arrays.asList(-100, 100, -100, 100)));
        d2.add(new ArrayList<Integer>(Arrays.asList(0, -0, 0, -0)));
        d2.add(new ArrayList<Integer>(Arrays.asList(-1, 1, -1, 1)));
        d2.add(new ArrayList<Integer>(Arrays.asList(-2, 2, 2, -2)));
        DemT dem2 = new DemT(d2, 1.0);
        assertEquals(dem2.total(),0);

    }

    @Test
    public void test_max(){
        //Case 1: normal case
        ArrayList<ArrayList<Integer>> d = new ArrayList<ArrayList<Integer>>();
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3)));
        d.add(new ArrayList<Integer>(Arrays.asList(-1, 3, 5, 4)));
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 6, 2)));
        DemT dem = new DemT(d, 1.0);

        assertEquals(dem.max(),6);

        //Case 2: Edge/boundary case: Use of large max size integers
        ArrayList<ArrayList<Integer>> d1 = new ArrayList<ArrayList<Integer>>();
        d1.add(new ArrayList<Integer>(Arrays.asList(2147483647, 2147483647, 2147483647, 2147483647)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -2, -4, -3)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -3, -5, -4)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-1, -2, -6, -2)));
        DemT dem1 = new DemT(d1, 1.0);
        assertEquals(dem1.max(),2147483647);

        //Case 3: Normal case
        ArrayList<ArrayList<Integer>> d2 = new ArrayList<ArrayList<Integer>>();
        d2.add(new ArrayList<Integer>(Arrays.asList(-100, -100, -100, -100)));
        d2.add(new ArrayList<Integer>(Arrays.asList(-0, -0, -0, -0)));
        d2.add(new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1)));
        d2.add(new ArrayList<Integer>(Arrays.asList(-2, -2, -2, -2)));
        DemT dem2 = new DemT(d2, 1.0);
        assertEquals(dem2.max(),0);

    }

    @Test
    public void test_ascendingRows(){
        //Case 1: normal case
        ArrayList<ArrayList<Integer>> d = new ArrayList<ArrayList<Integer>>();
        d.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        d.add(new ArrayList<Integer>(Arrays.asList(4, 5, 7, 8)));
        d.add(new ArrayList<Integer>(Arrays.asList(9, 10, 11, 12)));
        d.add(new ArrayList<Integer>(Arrays.asList(13, 14, 15, 16)));
        DemT dem = new DemT(d, 1.0);

        assertTrue(dem.ascendingRows());

        //Case 2: normal case
        ArrayList<ArrayList<Integer>> d1 = new ArrayList<ArrayList<Integer>>();
        d1.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        d1.add(new ArrayList<Integer>(Arrays.asList(-4, -5, -7, -8)));
        d1.add(new ArrayList<Integer>(Arrays.asList(9, 10, 11, 12)));
        d1.add(new ArrayList<Integer>(Arrays.asList(13, 14, 15, 16)));
        DemT dem1 = new DemT(d1, 1.0);
        assertTrue(!dem1.ascendingRows());

        //Case 3: Edge case, when all rows are the same
        ArrayList<ArrayList<Integer>> d3 = new ArrayList<ArrayList<Integer>>();
        d3.add(new ArrayList<Integer>(Arrays.asList(-100, 50, 25, 25)));
        d3.add(new ArrayList<Integer>(Arrays.asList(-100, 50, 25, 25)));
        d3.add(new ArrayList<Integer>(Arrays.asList(-100, 50, 25, 25)));
        d3.add(new ArrayList<Integer>(Arrays.asList(-100, 50, 25, 25)));
        DemT dem3 = new DemT(d1, 1.0);
        assertTrue(!dem3.ascendingRows());

        //Case 4: Edge Case for large Integer numbers near max 2147483647
        //If adding two max-size integers(2147483647), this overflows and becomes negative. So
        //technically by the method of summation and comparison, this results in false although
        //the size of the the last sequence is still increasing. 2147483644, 2147483645, 2147483646, 2147483647
        ArrayList<ArrayList<Integer>> d2 = new ArrayList<ArrayList<Integer>>();
        d2.add(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
        d2.add(new ArrayList<Integer>(Arrays.asList(5,6,7,8)));
        d2.add(new ArrayList<Integer>(Arrays.asList(9,10,11,12)));
        d2.add(new ArrayList<Integer>(Arrays.asList(2147483644, 2147483645, 2147483646, 2147483647)));
        DemT dem2 = new DemT(d2, 1.0);
        assertTrue(!dem2.ascendingRows());

    }


}