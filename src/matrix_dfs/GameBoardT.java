package matrix_dfs;

import java.util.ArrayList;
import java.util.List;

public class GameBoardT {
	
	private int moves;
	private ArrayList<Object> objectives = new ArrayList<Object>();
	private int score;
	
	//Default board 6X6
	private CellT[][] boardT = new CellT[6][6];
	
	public GameBoardT() {
		this.boardT = null;
	}
	
	public void initialize_board() {
		CellT[][] temp = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
				   {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
				   {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
				   {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
				   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
				   {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		this.boardT = temp.clone();
		this.moves = 20;
		this.objectives.add(6);
		this.objectives.add(CellT.getRandomCell());
		this.score = 0;
	}
	

	public static void main(String[] args) {
		/*String matrix [][] = {{"RED","RED","RED","BLUE","RED","RED"},
				   {"ORANGE","RED","RED","RED","BLUE","ORANGE"},
				   {"BLUE","BLUE","ORANGE","BLACK","BLACK","BLACK"},
				   {"BLACK","RED","RED","BLACK","ORANGE","BLUE"},
				   {"BLACK","BLUE","RED","ORANGE","BLACK","BLUE"},
				   {"RED","RED","ORANGE","BLUE","BLACK","BLUE"}};*/
		
		CellT matrix [][] = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
						   {CellT.O,CellT.R,CellT.B,CellT.B,CellT.B,CellT.O},
						   {CellT.O,CellT.O,CellT.O,CellT.G,CellT.G,CellT.G},
						   {CellT.G,CellT.R,CellT.R,CellT.O,CellT.O,CellT.B},
						   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
						   {CellT.R,CellT.B,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		GameBoardT game = new GameBoardT();
		System.out.println(DFS.DFS_EXECUTE(matrix,0,0,0,2));
		
		String[] arr = {"1","2","3"};
		
		try {
			System.out.println(arr[3]);
		}catch (Exception e) {
			System.out.println("OUT OF BOUNDS");
		}

		

		System.out.println(CellT.getRandomCell());
		game.initialize_board();
		//System.out.println(game.boardT[0][3]);
		System.out.println(game.objectives);
	}
	


}
