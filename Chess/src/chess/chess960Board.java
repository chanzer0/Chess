package chess;

import java.util.Random;

public class chess960Board extends Board {
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
	public void initBoard() {
		generateBackRank960();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0 || i == 7){
					//do nothing, as the first call to generateRandomBackRank960() will create the 1st rank position
				} else if (i == 1) { // if on the 7th rank, fill with black pawns
					Pawn p = new Pawn(i, j, Color.Black, PieceType.Pawn);
					board[i][j] = new Tile(i, j, true, p);
				} else if (i == 6) { // if on the 2nd rank, fill with white pawns
					Pawn p = new Pawn(i, j, Color.White, PieceType.Pawn);
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

	private void generateBackRank960() {
		Piece[] backRank = randomBackRank(0, Color.Black);
		do {
			backRank = randomBackRank(0, Color.Black);
		} while (!isValid(backRank));
		
		for (int j = 0; j < backRank.length; j++) {
			backRank[j].row = 0;
			backRank[j].col = j;
			board[0][j] = new Tile(0, j, true, backRank[j]);
		}

		for (int j = 0; j < backRank.length; j++) {
			if (backRank[j].identifier == PieceType.Rook) {
				board[7][j] = new Tile(7, j, true, new Rook(7, j, Color.White, PieceType.Rook));
			}
			if (backRank[j].identifier == PieceType.Bishop) {
				board[7][j] = new Tile(7, j, true, new Bishop(7, j, Color.White, PieceType.Bishop));
			}
			if (backRank[j].identifier == PieceType.Knight) {
				board[7][j] = new Tile(7, j, true, new Knight(7, j, Color.White, PieceType.Knight));
			}
			if (backRank[j].identifier == PieceType.Queen) {
				board[7][j] = new Tile(7, j, true, new Queen(7, j, Color.White, PieceType.Queen));
			}
			if (backRank[j].identifier == PieceType.King) {
				board[7][j] = new Tile(7, j, true, new King(7, j, Color.White, PieceType.King));
			}
		}
		return;
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
			if (randomBackRank[i].identifier == PieceType.Bishop) {
				for (int j = i + 1; j < randomBackRank.length; j++) {
					if (randomBackRank[j].identifier == PieceType.Bishop) {
						if (i % 2 != j % 2) {boolBishop = true;}
					}
				}
			}
			/*
			 * A rook must be found on either side of the king to satisfy the requirement
			 * that the king be between both rooks.
			 */
			if (randomBackRank[i].identifier == PieceType.King) {
				for (int j = i; j >= 0; j--) { // check left side
					if (randomBackRank[j].identifier == PieceType.Rook) {boolKingLeft = true;}
				}
				for (int j = i; j < randomBackRank.length; j++) { // check right side
					if (randomBackRank[j].identifier == PieceType.Rook) {boolKingRight = true;}
				}
			}
		}
		return (boolBishop && boolKingLeft && boolKingRight);
	}

	/*
	 * Helper method that returns a randomly generated array of back rank pieces
	 */
	private Piece[] randomBackRank(int i, Color color) {
		Rook r = new Rook(i, 9, color, PieceType.Rook);
		Rook ro = new Rook(i, 9, color, PieceType.Rook);
		Knight n = new Knight(i, 9, color, PieceType.Knight);
		Knight ni = new Knight(i, 9, color, PieceType.Knight);
		Bishop b = new Bishop(i, 9, color, PieceType.Bishop);
		Bishop bi = new Bishop(i, 9, color, PieceType.Bishop);
		Queen q = new Queen(i, 9, color, PieceType.Queen);
		King k = new King(i, 9, color, PieceType.King);
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
