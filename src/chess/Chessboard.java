package chess;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Chessboard {
	private Tile[][] board = new Tile[8][8];

	public Chessboard(String gametype) {
		if (gametype == "Classical") {
			initClassical();
		} else if (gametype == "Chess960") {
			initChess960();
		}
	}

	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].isOccupied) {
					if (j == 0) {System.out.print("|");}
					System.out.print(board[i][j].piece.identifier + "|");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	private void initClassical() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) { // if on the 8th rank, fill with standard chess setup (RNBQKBNR) black
					Rook r = new Rook(i, 0, "Black", "R");
					board[i][0] = new Tile(i, 0, true, r);
					Knight n = new Knight(i, 1, "Black", "N");
					board[i][1] = new Tile(i, 1, true, n);
					Bishop b = new Bishop(i, 2, "Black", "B");
					board[i][2] = new Tile(i, 2, true, b);
					Queen q = new Queen(i, 3, "Black", "Q");
					board[i][3] = new Tile(i, 3, true, q);
					King k = new King(i, 4, "Black", "K");
					board[i][4] = new Tile(i, 4, true, k);
					Bishop bi = new Bishop(i, 5, "Black", "B");
					board[i][5] = new Tile(i, 5, true, bi);
					Knight ni = new Knight(i, 6, "Black", "N");
					board[i][6] = new Tile(i, 6, true, ni);
					Rook ro = new Rook(i, 7, "Black", "R");
					board[i][7] = new Tile(i, 7, true, ro);
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, "Black", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, "White", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 7) { // if on the 1st rank, fill with standard chess setup (RNBQKBNR) white
					Rook r = new Rook(i, 0, "White", "R");
					board[i][0] = new Tile(i, 0, true, r);
					Knight n = new Knight(i, 1, "White", "N");
					board[i][1] = new Tile(i, 1, true, n);
					Bishop b = new Bishop(i, 2, "White", "B");
					board[i][2] = new Tile(i, 2, true, b);
					Queen q = new Queen(i, 3, "White", "Q");
					board[i][3] = new Tile(i, 3, true, q);
					King k = new King(i, 4, "White", "K");
					board[i][4] = new Tile(i, 4, true, k);
					Bishop bi = new Bishop(i, 5, "White", "B");
					board[i][5] = new Tile(i, 5, true, bi);
					Knight ni = new Knight(i, 6, "White", "N");
					board[i][6] = new Tile(i, 6, true, ni);
					Rook ro = new Rook(i, 7, "White", "R");
					board[i][7] = new Tile(i, 7, true, ro);
				} else { // else, the board must have an empty tile at this position
					board[i][j] = new Tile(i, j, false, null);
				}
			}
		}
		return;
	}

	/*
	 * Chess960, also called Fischer Random Chess is a variant of chess. It employs
	 * the same board and pieces as standard chess, but the starting position of the
	 * pieces on the players' home ranks is randomized with a few caveats 1. The
	 * bishops must be placed on opposite colored squares 2. the king must be placed
	 * between the 2 rooks
	 */
	private void initChess960() {
		return;
	}
}
