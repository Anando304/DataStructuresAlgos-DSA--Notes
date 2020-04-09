/** @file DemT.java
 * @author Anando Zaman
 * @brief DemT module that extends Seq2D
 * @date March 12,2020
 * @details module that extends Seq2D with Integer type
 */
package src;
import src.Seq2D;
import src.PointT;
import java.util.ArrayList;
import java.util.List;

/** @brief class for DemT that extends Seq2D
  @details This class represents DemT that extends Seq2D with Integer Type
*/
public class DemT extends Seq2D<Integer>{

	/** @brief Constructor that initializes the DemT object
      @details constructor takes 2 parameters to also establish Seq2D class
      @param elems represents a 2D sequence of type generic type Integer
	  @param scl represents a double value for scale	*/
	public DemT(ArrayList<ArrayList<Integer>> elems, double scl){
		super(elems, scl); //invokes constructor of super class
	}
	
	/**
	  @brief method that calculates total sum of all elements on Seq2D
	  @details Summation of all extracted elements in Seq2D to get the total
      @return returns the total sum as an integer.
	 */
	public int total (){
	  int total = 0;
	  for(int i = 0; i<getNumRow(); i++){
		  for(int j = 0; j<getNumCol(); j++){
			  PointT point = new PointT(i, j);
			  total += get(point);
		  }
	  }
		  
	  return total;
	}
	
	/**
	  @brief method that finds max of all elements in Seq2D
	  @details Compares each element and returns the max of the entire Seq2D object
      @return returns the max element as an integer.
	 */
	public int max(){
		int max = 0;
		
		for(int i = 0; i<getNumRow(); i++){
		  for(int j = 0; j<getNumCol(); j++){
			  PointT point = new PointT(i, j);
			  if(get(point)>max){
				  max = get(point);
			  }
		  }
	  }

	  return max;
		
	}

	/**
	  @brief method that verifies if sum of elements in rows are ascending
	  @details Compares the sum of the elements in each row and returns true if sums are ascending order
      @return Returns True if the sum of all values in each row increases as the row num increases
	 */
	public boolean ascendingRows(){
		int old_sum = -1;
		int curr_sum = 0;
		
		for(int i = 0; i<getNumRow(); i++){
			for(int j = 0; j<getNumCol(); j++){
			  PointT point = new PointT(i, j);
			  curr_sum += get(point);
			}
		  
			if(curr_sum < old_sum){
				return false;
			}
			old_sum = curr_sum;
		  
		}

	  return true;
		
	}

	/**
	  @brief Checks validity of a row on the grid
      @details row is valid if 0 < i < (nRow - 1)
	  @param i is the integer row
	  @return Returns boolean type of true if valid row on the grid
	*/
	private boolean validRow(int i)
	{
	  return (i >= 0) && (i <= (getNumRow() - 1));
	} 
	
	/**
	  @brief Checks validity of a column on the grid
      @details column is valid if 0 < j < (nCol - 1)
	  @param j is the integer Column
	  @return Returns boolean type of true if valid column on the grid
	*/
	private boolean validCol(int j)
	{  
	  return (j >= 0) && (j <= (getNumCol() - 1));
	}

	
}