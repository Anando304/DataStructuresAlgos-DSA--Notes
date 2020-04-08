/**
 * Author: Anando Zaman
 * Revised: March 25, 2020
 * 
 * Description: JUnit Testing all of the modules
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import src.TestCellT;
import src.TestGameBoardT;
import src.TestController;


@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestCellT.class,
    TestGameBoardT.class,
	TestController.class
})

public class AllTests
{
}
