package chess;

import javax.swing.JPanel;

public class ChessClassical extends Board {

	public ChessClassical(ChessGUI gui) {
		super(gui);
	}
	/*
	 * Chess is played on an 8x8 board, with the 1st and 8th ranks being composed of
	 * the composition [RNBQKBNR]. where R->Rook, N->Knight, B->Bishop, Q->Queen,
	 * K->King. In front of each players back rank, on the 2nd and 7th ranks, are a
	 * row of pawns [pppppppp]. In between, ranks 3, 4, and 5, are all empty.
	 */
	@Override
	public void setPieces() {
		Pawn p;
		generateBackRankClassical(0, PlayerEnum.Black);
		generateBackRankClassical(7, PlayerEnum.White);
		for (int j = 0; j < 8; j++) {
			p = new Pawn(1, j, PlayerEnum.Black, PieceType.Pawn);
			board[1][j].setPiece(p);
			p = new Pawn(6, j, PlayerEnum.White, PieceType.Pawn);
			board[6][j].setPiece(p);
		}
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col].isOccupied) {
					if (board[row][col].piece.color == PlayerEnum.Black) {
						players[PlayerEnum.Black.ordinal()].addRemainingPieces(board[row][col].piece);
					} else {
						players[PlayerEnum.White.ordinal()].addRemainingPieces(board[row][col].piece);
					}
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
	private void generateBackRankClassical(int i, PlayerEnum color) {
		Rook r = new Rook(i, 0, color, PieceType.Rook);
		board[i][0].setPiece(r);
		Knight n = new Knight(i, 1, color, PieceType.Knight);
		board[i][1].setPiece(n);
		Bishop b = new Bishop(i, 2, color, PieceType.Bishop);
		board[i][2].setPiece(b);
		Queen q = new Queen(i, 3, color, PieceType.Queen);
		board[i][3].setPiece(q);
		King k = new King(i, 4, color, PieceType.King);
		board[i][4].setPiece(k);
		Bishop bi = new Bishop(i, 5, color, PieceType.Bishop);
		board[i][5].setPiece(bi);
		Knight ni = new Knight(i, 6, color, PieceType.Knight);
		board[i][6].setPiece(ni);
		Rook ro = new Rook(i, 7, color, PieceType.Rook);
		board[i][7].setPiece(ro);
	}
}