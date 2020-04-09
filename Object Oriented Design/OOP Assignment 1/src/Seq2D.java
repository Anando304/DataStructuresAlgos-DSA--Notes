/** @file Seq2D.java
 @author Anando Zaman
 @brief Generic Seq2D module that takes generic type T
 @date March 12,2020
 @details generic module that takes type T along with Real number scale
 */
package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.PointT;

/** @brief This class represents Seq2D that has generic type T
*  @details Generic class for Seq2D
*/
public class Seq2D<T> { 
	
	//State variables
	private ArrayList<ArrayList<T>> s;
	private double scale;
	private int nRow;
	private int nCol;

	/** @brief Constructor that initializes the Seq2D object
     @details Seq2D constructor takes 2 parameters to create Seq2D object
     @param S represents a 2D sequence of type generic type T
	 @param scl represents a double value for scale
	 @throws IllegalArgumentException
	 */
	public Seq2D(ArrayList<ArrayList<T>> S, double scl){
		if((scl <= 0) || (S.size() == 0) || ((S.get(0)).size() == 0)) {
			throw new IllegalArgumentException();
		}

		for(List<T> row : S){
			
			if( row.size() != (S.get(0)).size() )
			{
				throw new IllegalArgumentException();
			}
		}
		
		
		
		this.s = S;
		this.scale = scl;
		this.nRow = S.size();
		this.nCol = (S.get(0)).size();
	}
	
	/** @brief mutator that sets the point p if it exists, with value v
     @details modifies a point (row,col) to be set to a value v
     @param p represents a PointT object
	 @param v represents Genric type T object
	 @throws IndexOutOfBoundsException
	 */
	public void set(PointT p, T v){
	  if(!(validPoint(p))){
		  throw new IndexOutOfBoundsException(); // Same as s[p.row()][p.col()] = v for normal array
	  }
	  
	  s.get(p.row()).set(p.col(),v);
	}
	
	/** @brief gets the point p if it exists
     @details returns the value at point p if it exists
     @param p represents a PointT object	
	 @return generic data type at PointT p
	 @throws IndexOutOfBoundsException
	 */
	public T get(PointT p){
		  if(!(validPoint(p))){
			  throw new IndexOutOfBoundsException();
		  }
		  
		  return (s.get(p.row())).get(p.col()); //return the coordinates from the seq of seq where first seq is row and second is col if the PointT of row and col is valid meaning exists in our seq of seq
	}
	
	/** @brief gets the total number of rows
     @details Gets the total number of rows from state variable
	 @return Returns total number of rows for the object
	*/
	public int getNumRow(){
		return this.nRow;
	}

	/** @brief gets the total number of columns
     @details Gets the total number of columns from state variable
	 @return Returns total number of columns for the object
	*/
	public int getNumCol(){
		return this.nCol;
	}
	
	/** @brief gets the scale value
     @details Gets the scale value as double type
	 @return Returns the scale value
	*/
	public double getScale(){
		return this.scale;
	}

	/** @brief counts occurences of t
	 *@param t is a generic object
     @details Counts the number of times t occurs in the grid
	 @return Returns integer value representing count
	*/
	public int count(T t){
		  int count = 0;
  
		  for(ArrayList<T> row : s){ //no need for validRow becuz this essentially is 0 <= i <= (nRow-1) of s
			for(T element : row){ //essentially s[i][j] where here its going through all the possible j's, in other words, columns, of row and returns the value saved at that point.
			  if(element == t){
				count++;
			  }
			}
		  }
			  
		  return count;
	}
	/** @brief counts occurences of t in a row
	 * @param t is a Generic object
	 * @param i is an Integer for the row
     @details Counts the number of times t occurs in the for a specific row
	 @return Returns integer value representing count for a row
	 @throws IndexOutOfBoundsException
	*/
	public int countRow(T t, int i){
		
		if(!(validRow(i))){
		  throw new IndexOutOfBoundsException();
	  }
		
		int count = 0;
		ArrayList<T> row = s.get(i); //returns the row associated with the index of the row ONLY from the seq of seq<T> stored in this.s
		for(T element : row){
			if(element == t){
				count++;
			}
		}
		return count;
	}
	
	/** @brief computes total area at point T
	 * @param t is a Generic object
     @details Total area in the grid taken up by cell value t
	 @return Returns double type of total area in the grid taken up by cell value t
	*/
	public double area(T t){
		return count(t) * (scale * scale);
	}
	
	
	
	// private/local methods
	/** @brief Checks validity of a point on the grid
	 * @param p is a PointT object
     @details PointT p is valid point if its a valid row and valid column
	 @return Returns boolean type of true if valid point on the grid
	*/
	private boolean validPoint(PointT p)
	{
	  return validRow(p.row()) && validCol(p.col());
	}
	
	/** @brief Checks validity of a row on the grid
     @details row is valid if 0 < i < (nRow - 1)
	 @param i is the integer row
	 @return Returns boolean type of true if valid row on the grid
	*/
	private boolean validRow(int i)
	{
	  return (i >= 0) && (i <= (nRow - 1));
	}
	/** @brief Checks validity of a column on the grid
      @details column is valid if 0 < j < (nCol - 1)
	   *@param j is the integer column
	  @return Returns boolean type of true if valid column on the grid
	*/
	private boolean validCol(int j)
	{  
	  return (j >= 0) && (j <= (nCol- 1));
	}

	
}