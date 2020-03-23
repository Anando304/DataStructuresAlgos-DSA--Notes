/** @file LuT.java
@author Anando Zaman
@brief Enum Java class for CellT
@date March 12,2020
@details Used for the exported LandTypes
*/

package matrix_dfs;
/** @brief This class represents CellT
@details This is an enum class for the various CellT types
*/
public enum CellT{
	R, O, G, B, P,;
	
    public static CellT getRandomCell() {
        return values()[(int) (Math.random()*((values().length)))];
    }
	
}