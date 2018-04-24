package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testBishopAvailableMoves {
	/*
	 * This test will place a bishop on an empty board at 
	 * position col:2 row:3 and get available moves then test them
	 */
	@Test
	public void testOpenBishop() {
		
		Board board = new ChessClassical(new ChessGUI());
		board.emptyBoard();
		
		Piece testBishop = new Bishop(2, 1, PlayerEnum.White, PieceType.Bishop);
		
		ArrayList<Tile> bishopMoves = testBishop.getAvailableMoves(board);
		assertEquals(9, bishopMoves.size());
	}
	@Test
	public void testInit() {
		
		ChessClassical board = new ChessClassical(new ChessGUI());
		Board testBoard = board;
		/*
		 * Get each Bishop (2 for white side, 2 for black side)
		 */
		Piece whiteB1 = testBoard.getTile(7, 2).piece;
		Piece whiteB2 = testBoard.getTile(7, 5).piece;
		Piece blackB1 = testBoard.getTile(0, 2).piece;
		Piece blackB2 = testBoard.getTile(0, 5).piece;
		
		/*
		 * Get the available moves from each Bishop
		 */
		ArrayList<Tile> whiteB1Moves = whiteB1.getAvailableMoves(testBoard);
		ArrayList<Tile> whiteB2Moves = whiteB2.getAvailableMoves(testBoard);
		ArrayList<Tile> blackB1Moves = blackB1.getAvailableMoves(testBoard);
		ArrayList<Tile> blackB2Moves = blackB2.getAvailableMoves(testBoard);
		
		/*
		 * These are all empty, as if there are no available moves, getLegalMoves() 
		 * has no objects to return, thus returns empty Tile array.
		 */
		ArrayList<Tile> whiteB1ExpectedMoves = new ArrayList<Tile>();
		ArrayList<Tile> whiteB2ExpectedMoves = new ArrayList<Tile>();
		ArrayList<Tile> blackB1ExpectedMoves = new ArrayList<Tile>();
		ArrayList<Tile> blackB2ExpectedMoves = new ArrayList<Tile>();
		
		/*
		 * Test equality
		 */
		assertEquals(whiteB1ExpectedMoves.size(), whiteB1Moves.size());
		assertEquals(whiteB2ExpectedMoves.size(), whiteB2Moves.size());
		assertEquals(blackB1ExpectedMoves.size(), blackB1Moves.size());
		assertEquals(blackB2ExpectedMoves.size(), blackB2Moves.size());
	}
}
