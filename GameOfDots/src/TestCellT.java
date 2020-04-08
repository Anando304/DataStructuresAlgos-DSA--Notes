/** @file TestCellT.java
 @author Anando Zaman
 @brief Test Driver for CellT module
 @date March 25,2020
 */

package src;
import org.junit.*;
import static org.junit.Assert.*;


public class TestCellT {

    @Before
    //Used to repeat code before each test
    public void setUp() throws Exception {

    }

    @After
    //Used to repeat code after each test
    public void tearDown() throws Exception {
        //For any clean-up operations necessary after the testing is done
    }

    @Test
    public void test_getRandomCell(){

        //Generate 10 random CellT types using getRandomCell method
        //If Random is functioning properly, then all of the cells cannot
        //all be of same type. Yes some might be the randomly picked the same but chances of all
        //being randomly picked the same is low. Most likely different values picked
        CellT a = CellT.getRandomCell();
        CellT b = CellT.getRandomCell();
        CellT c = CellT.getRandomCell();
        CellT d = CellT.getRandomCell();
        CellT e = CellT.getRandomCell();
        CellT f = CellT.getRandomCell();
        CellT g = CellT.getRandomCell();
        CellT h = CellT.getRandomCell();
        CellT i = CellT.getRandomCell();
        CellT j = CellT.getRandomCell();

        boolean NotRandom = (a.equals(b) && a.equals(c) && a.equals(c) && a.equals(d) && a.equals(d) &&
                              a.equals(e) && a.equals(f) && a.equals(g) && a.equals(h) && a.equals(i) && a.equals(j));
        assertTrue(!NotRandom);
    }
}
