/** @file CellT.java
@author Anando Zaman
@brief Enum Java class for CellT
@date March 25,2020
@details Used for the exported LandTypes
*/

package src;
/** @brief This class represents CellT
@details This is an enum class for the various CellT types
*/
public enum CellT{
	R, O, G, B;


    /** @brief method that returns a random CellT type
     @details Method that returns either of the four CellT colors
     * @return Returns a random CellT value
     */
    public static CellT getRandomCell() {
        return values()[(int) (Math.random()*((values().length)))];
    }
    

	
}