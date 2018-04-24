package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testQueenAvailableMoves {
	/*
	 * This test will place a bishop on an empty board at 
	 * position col:2 row:3 and get available moves then test them
	 */
	@Test
	public void testOpenQueen() {
		
		/*
		 * This initializes an 8x8 board to empty tiles
		 */
		Board cb = new Board(new ChessGUI());
		Tile[][] testBoard = cb.getBoard();
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				Tile t = new Tile(row, col, false, null);
				testBoard[col][row] = t;
			}
		}
		Queen testQueen = new Queen(2, 1, PlayerEnum.White, PieceType.Queen);
		testBoard[2][1] = new Tile(2, 1, true, testQueen);
		
		ArrayList<Tile> QueenMoves = testQueen.getAvailableMoves(cb);
		assertEquals(23, QueenMoves.size());
	}
	@Test
	public void testInit() {
		
		ChessClassical board = new ChessClassical(new ChessGUI());
		Tile[][] testBoard = board.getBoard();

		// TODO
	}
}
