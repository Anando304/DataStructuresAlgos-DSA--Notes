/** @file TestLanduseMapT.java
 @author Anando Zaman
 @brief Test Driver for LanduseMapT module
 @date March 12,2020
 */
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import src.LanduseMapT;
import src.LuT;
import src.PointT;

public class TestLanduseMapT{

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
    public void test_getNumRow() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);
        //System.out.println(LuMap.getNumRow());
        assertEquals(LuMap.getNumRow(),2);
    }

    @Test
    public void test_getNumCol() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);
        //System.out.println(LuMap.getNumRow());
        assertEquals(LuMap.getNumCol(),3);
    }

    @Test
    public void test_getNumScale() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);
        //System.out.println(LuMap.getNumRow());
        boolean state = LuMap.getScale() == 1.0;
        assertTrue(state);
    }


    @Test(expected=IndexOutOfBoundsException.class)
    public void test_set() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);

        //Case 1: Standard case
        PointT coord = new PointT(1,1);
        LuMap.set(coord, LuT.R);
        assertEquals(LuMap.get(coord),src.LuT.R);

        //Case 2: Standard case
        PointT coord0 = new PointT(1,1);
        LuMap.set(coord0, LuT.T);
        //System.out.println(LuMap.get(coord0));
        assertEquals(LuMap.get(coord0),src.LuT.T);

        //Case 3: Raises IndexOutofBounds Exception
        PointT coord1 = new PointT(1,7);
        LuMap.set(coord1, LuT.R);
        assertEquals(LuMap.get(coord1),src.LuT.R);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_get() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);

        //Case 1: Standard case
        PointT coord = new PointT(1,1);
        assertEquals(LuMap.get(coord),src.LuT.A);

        //Case 2: Standard case
        PointT coord0 = new PointT(0, 2);
        assertEquals(LuMap.get(coord0),src.LuT.R);

        //Case 3: Raises IndexOutofBounds Exception
        PointT coord1 = new PointT(1,7);
        assertEquals(LuMap.get(coord1),src.LuT.R);
    }

    @Test
    public void test_count() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.T, src.LuT.T)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);

        //Case 1: Standard case
        assertEquals(LuMap.count(src.LuT.A),2);

        //Case 2: Standard case
        assertEquals(LuMap.count(src.LuT.R),2);

        //Case 3:
        assertEquals(LuMap.count(src.LuT.T),4);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_countRow() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.T, src.LuT.T)));
        LanduseMapT LuMap = new LanduseMapT(map, 1.0);

        //Case 1: Standard case
        assertEquals(LuMap.countRow(src.LuT.A,0),0);

        //Case 2: Standard case
        assertEquals(LuMap.countRow(src.LuT.R, 0),2);

        //Case 3:
        assertEquals(LuMap.countRow(src.LuT.T, 2),3);

        //Case 4: Boundary/Exception case
        assertEquals(LuMap.countRow(src.LuT.T, 5),3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_area() throws IOException {
        ArrayList<ArrayList<src.LuT>> map = new ArrayList<ArrayList<src.LuT>>();
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.R, src.LuT.C, src.LuT.R)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.A, src.LuT.A)));
        map.add(new ArrayList<src.LuT>(Arrays.asList(src.LuT.T, src.LuT.T, src.LuT.T)));
        LanduseMapT LuMap = new LanduseMapT(map, 2);

        //Case 1: Standard case
        double out = LuMap.count(src.LuT.A) * LuMap.getScale() * LuMap.getScale();
        boolean equal = out == 8.0;
        assertTrue(equal);

        //Case 2: Standard case
        out = LuMap.count(src.LuT.T) * LuMap.getScale() * LuMap.getScale();
        equal = out == 16.0;
        assertTrue(equal);

        //Case 3: Exception/Boundary case, using a very large number for scale
        LanduseMapT LuMap2 = new LanduseMapT(map, 21474836477778928392831232931293823898391332323232.32);
        out = LuMap2.count(src.LuT.T) * LuMap2.getScale() * LuMap2.getScale();
        equal = out == 1.8446744069893778E99;
        assertTrue(equal);

        //Case 4: Exception/Boundary case, using a negative number for scale.
        //Should result in IllegalArgumentException as cannot have a negative scale value
        double val = -21474836477778928392831232931293823898391332323232.32;
        LanduseMapT LuMap3 = new LanduseMapT(map,val);
        out = LuMap3.count(src.LuT.T) * LuMap3.getScale() * LuMap3.getScale();
        System.out.println(out);
        equal = out == 1.8446744069893778E99;
        assertTrue(equal);

    }

}