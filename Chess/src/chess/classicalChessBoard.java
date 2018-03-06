package chess;

public class classicalChessBoard extends Board {

	/*
	 * Chess is played on an 8x8 board, with the 1st and 8th ranks being composed of
	 * the composition [RNBQKBNR]. where R->Rook, N->Knight, B->Bishop, Q->Queen,
	 * K->King. In front of each players back rank, on the 2nd and 7th ranks, are a
	 * row of pawns [pppppppp]. In between, ranks 3, 4, and 5, are all empty.
	 */
	public void initBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) { // if on the 8th rank, fill with standard chess setup (RNBQKBNR) black
					generateBackRankClassical(i, Color.Black);
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, Color.Black, PieceType.Pawn);
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, Color.White, PieceType.Pawn);
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 7) { // if on the 1st rank, fill with standard chess setup (RNBQKBNR) white
					generateBackRankClassical(i, Color.White);
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
	private void generateBackRankClassical(int i, Color color) {
		Rook r = new Rook(i, 0, color, PieceType.Rook);
		board[i][0] = new Tile(i, 0, true, r);
		Knight n = new Knight(i, 1, color, PieceType.Knight);
		board[i][1] = new Tile(i, 1, true, n);
		Bishop b = new Bishop(i, 2, color, PieceType.Bishop);
		board[i][2] = new Tile(i, 2, true, b);
		Queen q = new Queen(i, 3, color, PieceType.Queen);
		board[i][3] = new Tile(i, 3, true, q);
		King k = new King(i, 4, color, PieceType.King);
		board[i][4] = new Tile(i, 4, true, k);
		Bishop bi = new Bishop(i, 5, color, PieceType.Bishop);
		board[i][5] = new Tile(i, 5, true, bi);
		Knight ni = new Knight(i, 6, color, PieceType.Knight);
		board[i][6] = new Tile(i, 6, true, ni);
		Rook ro = new Rook(i, 7, color, PieceType.Rook);
		board[i][7] = new Tile(i, 7, true, ro);
	}
}