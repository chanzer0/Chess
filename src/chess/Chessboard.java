package chess;

import java.util.Random;

public class Chessboard {
	private Tile[][] board = new Tile[8][8];

	public Chessboard(String gametype) {
		if (gametype.toLowerCase().equals("classical")) {
			initClassical();
		} else if (gametype.toLowerCase().equals("chess960")) {
			initChess960();
		}
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
					if (j == 0) {System.out.print("|");}
					System.out.print(board[i][j].piece.identifier + "|");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	/*
	 * Chess is played on an 8x8 board, with the 1st and 8th ranks being composed of the composition [RNBQKBNR].
	 * where R->Rook, N->Knight, B->Bishop, Q->Queen, K->King.
	 * In front of each players back rank, on the 2nd and 7th ranks, are a row of pawns [pppppppp].
	 * In between, ranks 3, 4, and 5, are all empty.
	 */
	private void initClassical() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) { // if on the 8th rank, fill with standard chess setup (RNBQKBNR) black
					generateBackRankClassical(i);
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, "Black", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, "White", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 7) { // if on the 1st rank, fill with standard chess setup (RNBQKBNR) white
					generateBackRankClassical(i);
				} else { // else, the board must have an empty tile at this position
					board[i][j] = new Tile(i, j, false, null);
				}
			}
		}
		return;
	}
	
	/*
	 * Helper method for reducing duplicate code in initClassical()
	 */
	private void generateBackRankClassical(int i) {
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
	}

	/*
	 * Chess960, also called Fischer Random Chess is a variant of chess. It employs
	 * the same board and pieces as standard chess, but the starting position of the
	 * pieces on the players' home ranks is randomized with a few caveats 1. The
	 * bishops must be placed on opposite colored squares 2. the king must be placed
	 * between the 2 rooks. Note that while the back rank positions are randomized for 
	 * each game, the players must share the same randomized back rank position.
	 * 
	 * The composition of this function is similar to initClassical() except that instead
	 * of generating the back ranks in order, we use a helper function that creates one of 
	 * the 960 possible positions and assigns it to the back rank.
	 */
	private void initChess960() {
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				if (i == 0) { // if on the 8th rank, fill with standard chess setup (RNBQKBNR) black
//					generateBackRank960();
//				} else if (i == 1) { // if on the 7th rank, fill with black pawns
//					Pawn p = new Pawn(i, j, "Black", "p");
//					board[i][j] = new Tile(i, j, true, p);
//				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
//					Pawn p = new Pawn(i, j, "White", "p");
//					board[i][j] = new Tile(i, j, true, p);
//				} else if (i == 7) { // if on the 1st rank, fill with standard chess setup (RNBQKBNR) white
//					generateBackRank960();
//				} else { // else, the board must have an empty tile at this position
//					board[i][j] = new Tile(i, j, false, null);
//				}
//			}
//		}
		generateBackRank960();
		return;
	}

	/*
	 * Helper method to reduce duplicate code in initChess960().
	 * This method generates a random back rank using generateRandomBackRank()
	 * and then checks to see if that position is valid given the rules
	 * of Chess 960, which again, are as follows:
	 *      1. King must be between the 2 rooks
	 *      2. Bishops must be on opposite colors
	 * 
	 * If the position is valid, it applies it to the board,
	 * otherwise, the function will generate another random back rank
	 * and test compliance again.
	 */
	
	private void generateBackRank960() {
		String[] randomBackRank = generateRandomBackRank();
		
	}
	
	/*
	 * Helper method that returns a randomly generated array of back rank pieces
	 */
	private String[] generateRandomBackRank() {
		String[] availablePieces = {"R", "N", "B", "Q", "K", "B", "N", "R"};
		for (int i = availablePieces.length - 1; i > 0; i--) {
			Random r = new Random();
			int j = r.nextInt(i);
			String swap = availablePieces[i];
			availablePieces[i] = availablePieces[j];
			availablePieces[j] = swap;
		}
		return availablePieces;
	}
}










