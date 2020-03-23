package matrix_dfs;

import java.util.List;

public class GameBoardT extends DFS<CellT> {

	public GameBoardT(CellT[][] matrix, int start_x, int start_y, int end_x, int end_y) {
		super(matrix, start_x, start_y, end_x, end_y);
		
	}
	

	public static void main(String[] args) {
		/*String matrix [][] = {{"RED","RED","RED","BLUE","RED","RED"},
				   {"ORANGE","RED","RED","RED","BLUE","ORANGE"},
				   {"BLUE","BLUE","ORANGE","BLACK","BLACK","BLACK"},
				   {"BLACK","RED","RED","BLACK","ORANGE","BLUE"},
				   {"BLACK","BLUE","RED","ORANGE","BLACK","BLUE"},
				   {"RED","RED","ORANGE","BLUE","BLACK","BLUE"}};*/
		
		CellT matrix [][] = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
						   {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
						   {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
						   {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
						   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
						   {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		GameBoardT game = new GameBoardT(matrix,0,0,1,2);
		game.DFS_EXECUTE();
		System.out.println(game.pathTo("0,0", "1,2"));
		System.out.println(CellT.getRandomCell());
	}
	


}
