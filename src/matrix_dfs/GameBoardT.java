package matrix_dfs;

import java.util.List;

public class GameBoardT {
	
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	
	public GameBoardT(CellT[][] matrix, int start_x, int start_y, int end_x, int end_y) {
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;
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
		
		GameBoardT game = new GameBoardT(matrix,0,0,0,1);
		System.out.println(DFS.DFS_EXECUTE(matrix,0,0,0,5));
		//System.out.println(CellT.getRandomCell());
	}
	


}
