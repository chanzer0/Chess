package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class testBishopAvailableMoves {
	/*
	 * This test will place a bishop on an empty board at 
	 * position col:2 row:3 and get available moves then test them
	 */
	@Test
	public void testOpenBishop() {
		
		/*
		 * This initializes an 8x8 board to empty tiles
		 */
		Tile[][] testBoard = new Tile[8][8];
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				Tile t = new Tile(row, col, false, null);
				testBoard[col][row] = t;
			}
		}
		Bishop testBishop = new Bishop(2, 1, Color.White, PieceType.Bishop);
		testBoard[2][1] = new Tile(2, 1, true, testBishop);
		
		Tile[] bishopMoves = testBishop.getAvailableMoves(testBoard);
		System.out.println("rme " + "9");
		System.out.println("rm " + bishopMoves.length);
		assertEquals(9, bishopMoves.length);
	}
	@Test
	public void testInit() {
		
		classicalChessBoard board = new classicalChessBoard();
		board.initBoard();
		Tile[][] testBoard = board.getBoard();
		/*
		 * Get each Bishop (2 for white side, 2 for black side)
		 */
		Piece whiteB1 = testBoard[7][0].piece;
		Piece whiteB2 = testBoard[7][7].piece;
		Piece blackB1 = testBoard[0][0].piece;
		Piece blackB2 = testBoard[0][7].piece;
		
		/*
		 * Get the available moves from each Bishop
		 */
		Tile[] whiteB1Moves = whiteB1.getAvailableMoves(testBoard);
		Tile[] whiteB2Moves = whiteB2.getAvailableMoves(testBoard);
		Tile[] blackB1Moves = blackB1.getAvailableMoves(testBoard);
		Tile[] blackB2Moves = blackB2.getAvailableMoves(testBoard);
		
		/*
		 * These are all empty, as if there are no available moves, getLegalMoves() 
		 * has no objects to return, thus returns empty Tile array.
		 */
		Tile[] whiteB1ExpectedMoves = new Tile[0];
		Tile[] whiteB2ExpectedMoves = new Tile[0];
		Tile[] blackB1ExpectedMoves = new Tile[0];
		Tile[] blackB2ExpectedMoves = new Tile[0];
		
		/*
		 * Test equality
		 */
		assertArrayEquals(whiteB1ExpectedMoves, whiteB1Moves);
		assertArrayEquals(whiteB2ExpectedMoves, whiteB2Moves);
		assertArrayEquals(blackB1ExpectedMoves, blackB1Moves);
		assertArrayEquals(blackB2ExpectedMoves, blackB2Moves);
	}
}
