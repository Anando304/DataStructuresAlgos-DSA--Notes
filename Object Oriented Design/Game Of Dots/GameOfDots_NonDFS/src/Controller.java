/** @file Controller.java
 @author Anando Zaman
 @brief Controller class for model and view modules
 @date March 25,2020
 @details Used to control/update model(GameBoardT) and view modules
 */
import java.util.ArrayList;

/** @brief This class outputs the controller to update model and view
 *  @details Controller of the MVC design pattern
 */
public class Controller {

    private GameBoardT gameboard;
    private View view;

    /** @brief Constructor that that initializes for the controller
     @details Initializes the model(gameboard) and view modules
     @param gameboard represents a GameboardT object
     @param view represents a View object
     */
    public Controller(GameBoardT gameboard, View view){
        this.gameboard = gameboard;
        this.view = view;
    }


    /** @brief Getter method used to retrieve boardT grid
     @details getter method to return the boardT state variable
     @return Returns a 2D array of CellT representing the boardT
     */
    public CellT[][] get_game_boardT(){
        return gameboard.get_boardT();
    }

    /** @brief Getter method used to retrieve objectives
     @details getter method to return the objectives state variable
     @return Returns an arraylist containing objective parameters
     */
    public ArrayList get_game_obj()
    {
        return gameboard.get_obj();
    }

    /** @brief Getter method used to retrieve score
     @details getter method to return the score state variable
     @return Returns an integer containing the score
     */
    public int get_game_score(){
        return gameboard.get_score();
    }

    /** @brief Getter method used to retrieve number of moves
     @details Gets the number of moves remaining anytime during the game
     @return Returns an integer containing the number of moves remaining
     */
    public int get_game_moves(){
        return gameboard.get_moves();
    }

    /** @brief Controller Method that updates the boardT after a valid move
     @details Mutator method that updates the boardT with a valid path from user
     @param path represents the 2D integer array of the path chosen by the client
     @throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException
     */
    public void execute_move(int[][] path){
        gameboard.move(path);
    }

    /** @brief Controller method that updates the view output for the user
     @details Outputs the state variables for the game by printing to the console
     @throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException
     */
    public void updateView(){
        view.print_info((CellT)get_game_obj().get(0),(int)get_game_obj().get(1),get_game_score(),get_game_moves());
        view.print_board(get_game_boardT());
    }

    /** @brief Checks if game is over when ran out of available moves
     @details Outputs a boolean signifying if available moves is < = 0
     @return returns True if available moves is less than or equal to 0
     */
    public boolean game_over(){
        return gameboard.game_over();
    }

    /** @brief Checks if user has won the game
     @details Outputs a boolean if user has met the objective
     @return returns True if objective has reached or gone below 0
     */
    public boolean is_win(){
        return gameboard.is_win();
    }

}
