/** @file TestGameBoardT.java
 @author Anando Zaman
 @brief Test Driver for GameBoardT module
 @date March 25,2020
 */
package src;
import org.junit.*;
import static org.junit.Assert.*;

public class TestGameBoardT {

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
    public void test_get_boardT(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        CellT[][] temp = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
                {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
                {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
                {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
                {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
                {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};
        assertEquals(game.get_boardT(),temp);
    }

    @Test
    public void test_get_obj(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        assertEquals(game.get_obj().get(1), 6);
        assertEquals(game.get_obj().get(0), CellT.R);
    }

    @Test
    public void test_get_score(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        assertEquals(game.get_score(), 0);
    }

    @Test
    public void test_get_moves(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        assertEquals(game.get_moves(), 4);
    }


    @Test
    public void test_game_over(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();
        View view = new View();
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());
        //Normal case, game is NOT OVER
        assertTrue(!game.game_over());

        //Exception case: GAME IS OVER
        int[][] path = {{0,0},{0,1}};
        game.move(path);
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());

        int[][] path2 = {{1,1},{1,2},{0,2}};
        game.move(path2);
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());

        int[][] path3 = {{5,5},{4,5},{4,5}};
        game.move(path3);
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());

        try{
            //This should be the last move after which the number of moves is 0 AND the player has not reached objective target
            int[][] path4 = {{5,4},{4,4}};
            game.move(path4);
            view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
            view.print_board(game.get_boardT());

            fail(); //if above does not raise exception, then this exception case failed
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        
    }

    @Test
    public void test_is_win(){
        GameBoardT game = new GameBoardT();
        game.initialize_board();

        //normal case: game is not yet won
        assertTrue(!game.is_win());

        //normal case
        GameBoardT game2 = new GameBoardT();
        game2.initialize_board();
        View view = new View();
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());

        int[][] path10 = {{0, 0}, {0, 1},{0,2},{1,2}};
        game2.move(path10);
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());

        int[][] path11 = {{0, 4}, {0, 5}};
        game2.move(path11);
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());
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

        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());

        //Normal test case: After a valid move, the board should not be the same as before
        int[][] path = {{0,0},{0,1},{0,2}};
        game.move(path);
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());
        assertNotSame(old_board,game.get_boardT()); //checks if board is updated

        //Normal test case:
        int[][] path1 = {{3,5},{4,5},{5,5}};
        game.move(path1);
        view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
        view.print_board(game.get_boardT());
        assertNotSame(old_board,game.get_boardT()); //checks if board is updated

        //Exception test case: Check if invalid path is detected. Should raise IndexOutOfBoundsException
        try{
            int[][] path2 = {{3,3},{4,3},{5,3}};
            game.move(path2);

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
            game.move(path3);

            //if didn't raise exception, test case failed
            fail();
        } catch (NullPointerException e) {
            //error raised as expected
            e.printStackTrace();
        }

        //Exception test case: Invalid startend coordinates. Outside the grid
        try{
            int[][] path4 = {{9,-5},{4,3},{5,3}};
            game.move(path4);

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
            game.move(path5);

            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }


        //Exception test case: If path contains cells of different colors instead of one color
        //Expecting IndexOutOfBounds Exception
        try{
            int[][] path6 = {{5,0},{5,1},{5,2},{5,3}};
            game.move(path6);

            fail();
        } catch (IndexOutOfBoundsException e) {
            //Error raised as expected
            e.printStackTrace();
        }

        //Exception test case: IllegalArgumentException: Invalid path. A diagonal occurred
        try{
            int[][] path7 = {{4,5},{3,4}};
            game.move(path7);

            fail();
        } catch (IllegalArgumentException e) {
            //Error raised as expected
            e.printStackTrace();
        }

        //Exception Case: IllegalArgumentException: Game Over. Ran out of moves.

        try {
            int[][] path8 = {{5, 5}, {5, 4}};
            game.move(path8);
            view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
            view.print_board(game.get_boardT());

            int[][] path9 = {{4, 0}, {4, 1}};
            game.move(path9);
            view.print_info((CellT)game.get_obj().get(0),(int)game.get_obj().get(1),game.get_score(),game.get_moves());
            view.print_board(game.get_boardT());

            //if didn't raise exception, then test case failed
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        //NORMAL CASE
        //TEST CASE TO CHECK IF WON GAME. FIRST CREATE NEW BOARD SINCE LOST IN PREVIOUS GAME
        GameBoardT game2 = new GameBoardT();
        game2.initialize_board();
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());

        int[][] path10 = {{0, 0}, {0, 1},{0,2},{1,2}};
        game2.move(path10);
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());

        int[][] path11 = {{0, 4}, {0, 5}};
        game2.move(path11);
        view.print_info((CellT)game2.get_obj().get(0),(int)game2.get_obj().get(1),game2.get_score(),game2.get_moves());
        view.print_board(game2.get_boardT());
        assertTrue(game2.is_win());
    }
}
