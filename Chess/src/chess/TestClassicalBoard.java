package chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestClassicalBoard {
	
	/**
	 * Makes sure that the center area in the board is empty when the game starts.
	 */
	@Test
	public void centerIsEmpty() {
		ChessClassical classical = new ChessClassical(new ChessGUI());
		for (int col = 2; col <=5; col++){
			for (int row = 0; row <=7; row++){
				assertNotNull(classical.board[col][row]);
				assertFalse(classical.board[col][row].isOccupied);
			}
		}
	}
	
	/**
	 * Check if the where the pawns should be are not null, locations are occupied, are pawns, and are white.
	 */
	@Test
	public void pawnsWhiteExist(){
		ChessClassical classical = new ChessClassical(new ChessGUI());
		for(int row = 0; row <= 7; row++){
			assertNotNull(classical.board[6][row]);
			assertTrue(classical.board[6][row].isOccupied);
			assertEquals(PieceType.Pawn, classical.board[6][row].piece.identifier);
			assertEquals(PlayerEnum.White, classical.board[6][row].piece.color);
		}
	}
	
	/**
	 * Check if the where the pawns should be are not null, locations are occupied, are pawns, and are black.
	 */
	@Test
	public void pawnsBlackExist(){
		ChessClassical classical = new ChessClassical(new ChessGUI());
		for(int row = 0; row <= 7; row++){
			assertNotNull(classical.board[1][row]);
			assertTrue(classical.board[1][row].isOccupied);
			assertEquals(PieceType.Pawn, classical.board[1][row].piece.identifier);
			assertEquals(PlayerEnum.Black, classical.board[1][row].piece.color);
		}
	}
	/**
	 * Check that the back rank of both sides are correct except for the exact piece type.
	 */
	@Test 
	public void backRankExists(){
		ChessClassical classical = new ChessClassical(new ChessGUI());
		for(int row = 0; row <= 7; row++){
			assertNotNull(classical.board[0][row]);
			assertNotNull(classical.board[7][row]);
			assertTrue(classical.board[0][row].isOccupied);
			assertTrue(classical.board[7][row].isOccupied);
			assertEquals(PlayerEnum.Black, classical.board[0][row].piece.color);
			assertEquals(PlayerEnum.White, classical.board[7][row].piece.color);
		}
	}
	
	/**
	 * Check if the locations of the pieces are correct in the back rank of white side.
	 */
	@Test
	public void backRankPieceWhiteTest(){
		ChessClassical classical = new ChessClassical(new ChessGUI());
		//Rook
		assertEquals(PieceType.Rook, classical.board[7][0].piece.identifier);
		assertEquals(PieceType.Rook, classical.board[7][7].piece.identifier);
		assertEquals(PieceType.Rook, classical.board[0][0].piece.identifier);
		assertEquals(PieceType.Rook, classical.board[0][7].piece.identifier);
		//Knight
		assertEquals(PieceType.Knight, classical.board[7][1].piece.identifier);
		assertEquals(PieceType.Knight, classical.board[7][6].piece.identifier);
		assertEquals(PieceType.Knight, classical.board[0][1].piece.identifier);
		assertEquals(PieceType.Knight, classical.board[0][6].piece.identifier);
		//Bishop
		assertEquals(PieceType.Bishop, classical.board[7][2].piece.identifier);
		assertEquals(PieceType.Bishop, classical.board[7][5].piece.identifier);
		assertEquals(PieceType.Bishop, classical.board[0][2].piece.identifier);
		assertEquals(PieceType.Bishop, classical.board[0][5].piece.identifier);
		//King
		assertEquals(PieceType.King, classical.board[7][4].piece.identifier);
		assertEquals(PieceType.King, classical.board[0][4].piece.identifier);
		//Queen
		assertEquals(PieceType.Queen, classical.board[7][3].piece.identifier);
		assertEquals(PieceType.Queen, classical.board[0][3].piece.identifier);
	}

}
