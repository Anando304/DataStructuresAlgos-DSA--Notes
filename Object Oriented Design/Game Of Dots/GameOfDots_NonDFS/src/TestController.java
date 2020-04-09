/** @file TestGameBoardT.java
 @author Anando Zaman
 @brief Test Driver for Controller module
 @date March 25,2020
 */
package src;
import org.junit.*;
import static org.junit.Assert.*;


public class TestController {

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
    public void test_get_boardT() {
        //MODEL: Create a new gameboard to play
        GameBoardT game = new GameBoardT();
        //Initialize the gameboard
        game.initialize_board();

        //VIEW: output the gameboard state variables information to the user
        View view = new View();

        //CONTROLLER: Update model
        Controller controller = new Controller(game,view);
        CellT[][] temp = {{CellT.R, CellT.R, CellT.R, CellT.B, CellT.R, CellT.R},
                {CellT.O, CellT.R, CellT.R, CellT.R, CellT.B, CellT.O},
                {CellT.B, CellT.B, CellT.O, CellT.G, CellT.G, CellT.G},
                {CellT.G, CellT.R, CellT.R, CellT.G, CellT.O, CellT.B},
                {CellT.B, CellT.B, CellT.R, CellT.O, CellT.G, CellT.B},
                {CellT.R, CellT.R, CellT.O, CellT.B, CellT.G, CellT.B}};
        assertEquals(controller.get_game_boardT(), temp);
    }

    @Test
    public void test_get_obj(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        Controller controller = new Controller(game,view);

        assertEquals(controller.get_game_obj().get(1), 6);
        assertEquals(controller.get_game_obj().get(0), CellT.R);
    }

    @Test
    public void test_get_score(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        Controller controller = new Controller(game,view);

        assertEquals(controller.get_game_score(), 0);
    }

    @Test
    public void test_get_moves(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        Controller controller = new Controller(game,view);

        assertEquals(controller.get_game_moves(), 4);
    }

    @Test
    public void test_game_over(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        Controller controller = new Controller(game,view);

        //Normal case, game is NOT OVER
        assertTrue(!controller.game_over());

        //Exception case: GAME IS OVER
        int[][] path = {{0,0},{0,1}};
        controller.execute_move(path);
        controller.updateView();


        int[][] path2 = {{1,1},{1,2},{0,2}};
        controller.execute_move(path2);
        controller.updateView();

        int[][] path3 = {{5,5},{4,5},{4,5}};
        controller.execute_move(path3);
        controller.updateView();

        try{
            //This should be the last move after which the number of moves is 0 AND the player has not reached objective target
            int[][] path4 = {{5,4},{4,4}};
            controller.execute_move(path4);
            controller.updateView();

            fail(); //if above does not raise exception, then this exception case failed
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        assertTrue(controller.game_over());
    }

    @Test
    public void test_is_win(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view0 = new View();
        Controller controller = new Controller(game,view0);

        //normal case: game is not yet won
        assertTrue(!controller.is_win());

        //normal case
        GameBoardT game2 = new GameBoardT();
        game2.initialize_board();
        View view = new View();
        Controller controller1 = new Controller(game2,view);
        controller1.updateView();

        int[][] path10 = {{0, 0}, {0, 1},{0,2},{1,2}};
        controller1.execute_move(path10);
        controller1.updateView();

        int[][] path11 = {{0, 4}, {0, 5}};
        controller1.execute_move(path11);
        controller1.updateView();
        assertTrue(game2.is_win()); //won the game
    }

    @Test()
    public void test_move(){
        CellT[][] old_board= {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
                {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
                {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
                {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
                {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
                {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};

        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        Controller controller = new Controller(game,view);
        controller.updateView();

        //Normal test case: After a valid move, the board should not be the same as before
        int[][] path = {{0,0},{0,1},{0,2}};
        controller.execute_move(path);
        controller.updateView();
        assertNotSame(old_board,controller.get_game_boardT()); //checks if board is updated

        //Normal test case:
        int[][] path1 = {{3,5},{4,5},{5,5}};
        controller.execute_move(path1);
        controller.updateView();
        view.print_board(controller.get_game_boardT());
        assertNotSame(old_board,controller.get_game_boardT()); //checks if board is updated

        //Exception test case: Check if invalid path is detected. Should raise IndexOutOfBoundsException
        try{
            int[][] path2 = {{3,3},{4,3},{5,3}};
            controller.execute_move(path2);

            //We expect above code to raise IndexOutOfBoundsException
            // if it somehow suceeded without errors and didn't raise exception, then fail this test case
            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }


        //Exception test case: Empty path. Expected to raise NullPointerException
        try{
            int[][] path3 = null;
            controller.execute_move(path3);

            //if didn't raise exception, test case failed
            fail();
        } catch (NullPointerException e) {
            //error raised as expected
            e.printStackTrace();
        }

        //Exception test case: Invalid startend coordinates. Outside the grid
        try{
            int[][] path4 = {{9,-5},{4,3},{5,3}};
            controller.execute_move(path4);

            //We expect above code to raise IndexOutOfBoundsException
            // if it somehow suceeded without errors and didn't raise exception, then fail this test case
            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }

        //Exception test case: Outside grid coordinates in path array. Outside the grid
        try{
            int[][] path5 = {{0,0},{9,0},{5,3}};
            controller.execute_move(path5);

            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }


        //Exception test case: If path contains cells of different colors instead of one color
        //Expecting IndexOutOfBounds Exception
        try{
            int[][] path6 = {{5,0},{5,1},{5,2},{5,3}};
            controller.execute_move(path6);

            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }

        //Exception test case: IllegalArgumentException: Invalid path. A diagonal occurred
        try{
            int[][] path7 = {{4,5},{3,4}};
            controller.execute_move(path7);

            fail();
        } catch (IllegalArgumentException e) {
            //Error raised as expected
            e.printStackTrace();
        }

        //Exception Case: IllegalArgumentException: Game Over. Ran out of moves.

        try {
            int[][] path8 = {{5, 5}, {5, 4}};
            controller.execute_move(path8);
            controller.updateView();

            int[][] path9 = {{4, 0}, {4, 1}};
            controller.execute_move(path9);
            controller.updateView();

            //if didn't raise exception, then test case failed
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        //NORMAL CASE
        //TEST CASE TO CHECK IF WON GAME. FIRST CREATE NEW BOARD SINCE LOST IN PREVIOUS GAME
        GameBoardT game2 = new GameBoardT();
        game2.initialize_board();
        View view2 = new View();
        Controller controller2 = new Controller(game2,view2);

        controller2.updateView();

        int[][] path10 = {{0, 0}, {0, 1},{0,2},{1,2}};
        controller2.execute_move(path10);
        controller2.updateView();

        int[][] path11 = {{0, 4}, {0, 5}};
        controller2.execute_move(path11);
        controller2.updateView();
        assertTrue(game2.is_win());
    }

}
