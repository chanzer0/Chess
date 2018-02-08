package chess;

import java.util.Random;

public class chess960Board {
	private Tile[][] board = new Tile[8][8];
	
	public chess960Board() {
		initChess960();
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
	 * Chess960, also called Fischer Random Chess is a variant of chess. It employs
	 * the same board and pieces as standard chess, but the starting position of the
	 * pieces on the players' home ranks is randomized with a few caveats 1. The
	 * bishops must be placed on opposite colored squares 2. the king must be placed
	 * between the 2 rooks. Note that while the back rank positions are randomized
	 * for each game, the players must share the same randomized back rank position.
	 * 
	 * The composition of this function is similar to initClassical() except that
	 * instead of generating the back ranks in order, we use a helper function that
	 * creates one of the 960 possible positions and assigns it to the back rank.
	 */
	private void initChess960() {
		generateBackRank960(0);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0 || i == 7){
					//do nothing, as the first call to generateRandomBackRank960() will create the 1st rank position
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, "Black", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, "White", "p");
					board[i][j] = new Tile(i, j, true, p);
				} else { // else, the board must have an empty tile at this position
					board[i][j] = new Tile(i, j, false, null);
				}
			}
		}
		return;
	}
	/*
	 * This method generates a random back rank using randomBackRank() 
	 * and then checks to see if that position is valid given the rules of Chess 960
	 * 
	 * If the position is valid, it applies it to the board, otherwise, the function
	 * will generate another random back rank and re-test compliance.
	 */

	private void generateBackRank960(int i) {
		Piece[] backRank = randomBackRank(i, "Black");
		do {
			backRank = randomBackRank(i, "Black");
		} while (!isValid(backRank));

		for (int j = 0; j < backRank.length; j++) {
			backRank[j].x = j;
			board[i][j] = new Tile(i, j, true, backRank[j]);
			backRank[j].y = i + 7;
			board[i + 7 ][j] = new Tile(i + 7, j, true, backRank[j]);
		}
	}

	/*
	 * This method returns true if the given randomBackRank given is valid given the
	 * rules of Chess960 1. King must be between the 2 rooks 2. Bishops must be on
	 * opposite colors
	 */
	private Boolean isValid(Piece[] randomBackRank) {
		/*
		 * boolBishop -> The boolean for satisfying the bishop condition
		 * boolKing[Left/Right] -> Satisfying the king condition (either left or right
		 * side)
		 */
		boolean boolBishop = false;
		boolean boolKingLeft = false;
		boolean boolKingRight = false;
		for (int i = 0; i < randomBackRank.length; i++) {
			/*
			 * Bishops must be placed where 1 is on an even index and one is on an odd index
			 * for the requirement of opposite-colored bishops.
			 */
			if (randomBackRank[i].identifier == "B") {
				for (int j = i + 1; j < randomBackRank.length; j++) {
					if (randomBackRank[j].identifier == "B") {
						if (i % 2 != j % 2) {boolBishop = true;}
					}
				}
			}
			/*
			 * A rook must be found on either side of the king to satisfy the requirement
			 * that the king be between both rooks.
			 */
			if (randomBackRank[i].identifier == "K") {
				for (int j = i; j >= 0; j--) { // check left side
					if (randomBackRank[j].identifier == "R") {boolKingLeft = true;}
				}
				for (int j = i; j < randomBackRank.length; j++) { // check right side
					if (randomBackRank[j].identifier == "R") {boolKingRight = true;}
				}
			}
		}
		return (boolBishop && boolKingLeft && boolKingRight);
	}

	/*
	 * Helper method that returns a randomly generated array of back rank pieces
	 */
	private Piece[] randomBackRank(int i, String color) {
		Rook r = new Rook(i, 9, color, "R");
		Rook ro = new Rook(i, 9, color, "R");
		Knight n = new Knight(i, 9, color, "N");
		Knight ni = new Knight(i, 9, color, "N");
		Bishop b = new Bishop(i, 9, color, "B");
		Bishop bi = new Bishop(i, 9, color, "B");
		Queen q = new Queen(i, 9, color, "Q");
		King k = new King(i, 9, color, "K");
		Piece[] availablePieces = { r, ro, n, ni, q, k, b, bi };
		for (int z = availablePieces.length - 1; z > 0; z--) {
			Random rand = new Random();
			int j = rand.nextInt(z);
			Piece swap = availablePieces[z];
			availablePieces[z] = availablePieces[j];
			availablePieces[j] = swap;
		}
		return availablePieces;
	}
}
