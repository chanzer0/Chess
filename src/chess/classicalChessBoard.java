package chess;

public class classicalChessBoard {
	private Tile[][] board = new Tile[8][8];
	
	public classicalChessBoard() {
		initClassical();
	}

	/*
	 * Will print out the current board position as such:
	 * 
	 * |R|N|B|Q|K|B|N|R| 
	 * |p|p|p|p|p|p|p|p|
	 *
	 * 
	 * 
	 * 
	 * |p|p|p|p|p|p|p|p| 
	 * |R|N|B|Q|K|B|N|R|
	 * 
	 */
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].isOccupied) {
					if (j == 0) {
						System.out.print("|");
					}
					System.out.print(board[i][j].piece.identifier + "|");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	/*
	 * Chess is played on an 8x8 board, with the 1st and 8th ranks being composed of
	 * the composition [RNBQKBNR]. where R->Rook, N->Knight, B->Bishop, Q->Queen,
	 * K->King. In front of each players back rank, on the 2nd and 7th ranks, are a
	 * row of pawns [pppppppp]. In between, ranks 3, 4, and 5, are all empty.
	 */
	private void initClassical() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) { // if on the 8th rank, fill with standard chess setup (RNBQKBNR) black
					generateBackRankClassical(i, "Black");
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, "Black", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, "White", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 7) { // if on the 1st rank, fill with standard chess setup (RNBQKBNR) white
					generateBackRankClassical(i, "White");
				} else { // else, the board must have an empty tile at this position
					board[i][j] = new Tile(i, j, false, null);
				}
			}
		}
		return;
	}

	/*
	 * Helper method for reducing duplicate code in initClassical()
	 * Sets up the back rank given the file and color in standard position
	 * [R N B Q K B N R]
	 */
	private void generateBackRankClassical(int i, String color) {
		Rook r = new Rook(i, 0, color, "R");
		board[i][0] = new Tile(i, 0, true, r);
		Knight n = new Knight(i, 1, color, "N");
		board[i][1] = new Tile(i, 1, true, n);
		Bishop b = new Bishop(i, 2, color, "B");
		board[i][2] = new Tile(i, 2, true, b);
		Queen q = new Queen(i, 3, color, "Q");
		board[i][3] = new Tile(i, 3, true, q);
		King k = new King(i, 4, color, "K");
		board[i][4] = new Tile(i, 4, true, k);
		Bishop bi = new Bishop(i, 5, color, "B");
		board[i][5] = new Tile(i, 5, true, bi);
		Knight ni = new Knight(i, 6, color, "N");
		board[i][6] = new Tile(i, 6, true, ni);
		Rook ro = new Rook(i, 7, color, "R");
		board[i][7] = new Tile(i, 7, true, ro);
	}
}