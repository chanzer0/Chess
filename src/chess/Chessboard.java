package chess;

public class Chessboard {
	private Tile[][] board = null;
	
	public Chessboard(String gametype) {
		if (gametype == "Classical") {
			initClassical();
		} else if (gametype == "Chess960") {
			initChess960();
		}
	}
	
	private void initClassical() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 1) { //if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, "White");
					board[i][j] = new Tile(i, j, true, p);
				}
				if (i == 6) { //if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, "Black");
					board[i][j] = new Tile(i, j, true, p);
				}
				
			}
		}
		return;
	}
	
	private void initChess960() {
		
		return;
	}
}
