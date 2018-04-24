package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testPawnAvailableMoves {

	/*
	 * Tests if black and white pawn in starting positions without any other pieces are returning the correct moves.
	 */
	@Test
	public void testPawnMoves() {
		//new empty board
		Board cb = new Board(new ChessGUI());
		Tile[][] testBoard = cb.getBoard();
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				Tile t = new Tile(row, col, false, null);
				testBoard[col][row] = t;
			}
		}
		Pawn testerw = new Pawn(6, 4, PlayerEnum.White, PieceType.Pawn);
		testBoard[6][4] = new Tile(6, 4, true, testerw);
		Pawn testerb = new Pawn(1, 4, PlayerEnum.Black, PieceType.Pawn);
		testBoard[1][4] = new Tile(1, 4, true, testerb);
		
		
		ArrayList<Tile> pawnMovesW = testerw.getAvailableMoves(cb);
		ArrayList<Tile> pawnMovesB = testerb.getAvailableMoves(cb);
		assertEquals(2, pawnMovesB.size());
		assertEquals(2, pawnMovesW.size());
	}
	/*
	 * Tests if a piece in the middle of an empty board is getting the correct number of allowed moves
	 */
	@Test
	public void testAllowedMoves() {
		//new empty board
		Board cb = new Board(new ChessGUI());
		Tile[][] testBoard = cb.getBoard();
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				Tile t = new Tile(row, col, false, null);
				testBoard[col][row] = t;
			}
		}
		Pawn testerw = new Pawn(3, 3, PlayerEnum.White, PieceType.Pawn);
		testBoard[3][3] = new Tile(3, 3, true, testerw);
		
		ArrayList<Tile> pawnMoves = testerw.getAvailableMoves(cb);
		assertEquals(2, pawnMoves.size());
	}

	/*
	 * Tests if a piece in the middle of an empty board is getting the correct number of allowed moves
	 */
	@Test
	public void testIfhasMoved() {
		//new empty board
		Board b = new ChessClassical(new ChessGUI());
		b.emptyBoard();
		Tile[][] testBoard = b.getBoard();
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				Tile t = new Tile(row, col, false, null);
				testBoard[col][row] = t;
			}
		}
		Pawn testerw = new Pawn(6, 4, PlayerEnum.White, PieceType.Pawn);
		testBoard[6][4] = new Tile(6, 4, true, testerw);
		Pawn testerb = new Pawn(1, 4, PlayerEnum.Black, PieceType.Pawn);
		testBoard[1][4] = new Tile(1, 4, true, testerb);
		
		testerw.move(b.getTile(5, 4), b);
		testerb.move(b.getTile(2, 4), b);
		assertTrue(testerw.hasMoved);
		assertTrue(testerb.hasMoved);
	}


	
}
