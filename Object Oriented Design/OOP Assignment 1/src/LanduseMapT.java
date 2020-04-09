/** @file LanduseMapT.java
   @author Anando Zaman
   @brief Type alias of the generic ADT Seq2D(T)
   @date March 12,2020
   @details A type alias of Seq2D module with type LanduseT.
*/

package src;
import src.LuT;
import src.Seq2D;
import java.util.ArrayList;
import java.util.List;

/**
 *@brief This class represents LanduseMapT which inherits the Seq2D class
 @details This class inherits from the Seq2D class with LuT as type
*/
public class LanduseMapT extends Seq2D<LuT>{ 

	/** @brief Constructor that initializes the LanduseMapT object
       @details LanduseMapT constructor that also sets up Seq2D constructor
       @param elems represents a 2D sequence of type LuT
	   @param scl represents a double value for scale
	*/
	public LanduseMapT(ArrayList<ArrayList<LuT>> elems, double scl){
		super(elems, scl);
	}
}