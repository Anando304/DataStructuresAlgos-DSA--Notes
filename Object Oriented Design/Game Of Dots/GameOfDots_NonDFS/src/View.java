/** @file View.java
 @author Anando Zaman
 @brief View module that uses CellT and GameBoardT modules
 @date March 25,2020
 @details View of MVC for outputting results to the user
 */
package src;

/** @brief This class outputs the board and game details to the user
 *  @details This is the view portion of the MVC design pattern
 */
public class View {


    /** @brief Method used to show the board to the user
     @details Prints the board to the console to view
     @param boardT is a 2D sequence of CellT that represents the game board
     */
    public void print_board(CellT[][] boardT) {
        for(CellT[] row : boardT) {
            for (CellT col : row) {
                System.out.print(col + "   ");
            }
            System.out.println();
        }
        System.out.println("***************************");
    }

    /** @brief Method used to show the users status in the game
     @details Prints the current objectives, moves, and score to the console
     @param objective_type represents the CellT objective
     @param objective_goal represents the target goal for CellT objective
     @param score represents the running total of the users points
     @param moves represents total available moves for the user
     */
    public void print_info(CellT objective_type, int objective_goal, int score, int moves){
        System.out.println("Objective: " + objective_type + ", " + objective_goal);
        System.out.println("Score: " + score);
        System.out.println("Remaining moves: " + moves);
    }


}
