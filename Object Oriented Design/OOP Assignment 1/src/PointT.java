/** @file PointT.java
   @author Anando Zaman
   @brief Class for PointT MODULE
   @date March 12,2020
   @details Used for doing row,col manipulation
*/
package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @brief This class represents PointT
   @details This is an enum class for the various LandTypes from spec
*/
public class PointT{
	
	private int r;
	private int c;
	
	/** @brief Constructor that initializes the PointT object
       @details PointT constructor that takes 2 inputs to create PointT object
       @param row is an integer that represents row index
	   @param col is an integer that represents col index
	*/
	public PointT(int row, int col){
		this.r = row;
		this.c = col;
	}

	/** @brief Getter method that extract row index
        @details takes no inputs and returns row index
		@return returns integer representing row
	*/	
	public int row(){
		return this.r;
	}

	/** @brief Getter method that extract col index
        @details takes no inputs and returns col index
		@return returns integer representing col
	*/	
	public int col(){
		return this.c;
	}
	
	/** @brief returns a translated PointT with two paramters
        @details returns a new PointT object with delta_r and delta_c change
		@return returns a PointT object with translated PointT
	*/
	public PointT translate(int delta_r, int delta_c){
		PointT pointT = new PointT(this.r+delta_r, this.c+delta_c);
		return pointT;
	}
	
	
}