/** @file TestPointT.java
 @author Anando Zaman
 @brief Test Driver for PointT module
 @date March 12,2020
 */
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import src.PointT;

public class TestPointT{

        //private variables and constants for reuse
        //private PointT p;

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
    public void test_row() throws IOException {
        PointT point = new PointT(4,8); //test normal value
        PointT point1 = new PointT(2147483647, -3); //boundary case, test largest possible signed integer
        PointT point2 = new PointT(-3,-4);//test a negative number
        assertEquals(point.row(),4);
        assertEquals(point1.row(),2147483647);
        assertEquals(point2.row(),-3);
    }

    @Test
    public void test_col() throws IOException {
        PointT point = new PointT(4,8); //test normal value
        PointT point1 = new PointT(4, 2147483647); //boundary case, test largest possible signed integer
        PointT point2 = new PointT(-3,-4);//test a negative number
        assertEquals(point.col(),8);
        assertEquals(point1.col(),2147483647);
        assertEquals(point2.col(),-4);
    }

    @Test
    public void test_translate() throws IOException {
        PointT point = new PointT(4,8); //test normal value
        PointT point1 = new PointT(4, 2147483647); //boundary case, test largest possible signed integer

        PointT point_new = point.translate(3,3);
        PointT point1_new = point1.translate(-1,3);

        assertEquals(point_new.col(),11);
        assertEquals(point_new.row(),7);

        //Boundary case, when adding to max integer size
        boolean i = point1_new.col() < 2147483647; //because largest number, so adding onto it makes it go negative
        assertTrue(i);
        assertEquals(point1_new.row(),3);
    }
}