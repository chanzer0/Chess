package chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test960Board {
	/**
	 * Makes sure that the center area in the board is empty when the game starts.
	 */
	@Test
	public void centerIsEmpty() {
		Board960 b960 = new Board960(new ChessGUI());

		for (int col = 2; col <=5; col++){
			for (int row = 0; row <=7; row++){
				assertNotNull(b960.board[col][row]);
				assertFalse(b960.board[col][row].isOccupied);
			}
		}
	}
	
	/**
	 * Check if the where the pawns should be are not null, locations are occupied, are pawns, and are white.
	 */
	@Test
	public void pawnsWhiteExist(){
		Board960 b960 = new Board960(new ChessGUI());

		for(int row = 0; row <= 7; row++){
			assertNotNull(b960.board[6][row]);
			assertTrue(b960.board[6][row].isOccupied);
			assertEquals(PieceType.Pawn, b960.board[6][row].piece.identifier);
			assertEquals(PlayerEnum.White, b960.board[6][row].piece.color);
		}
	}
	
	/**
	 * Check if the where the pawns should be are not null, locations are occupied, are pawns, and are black.
	 */
	@Test
	public void pawnsBlackExist(){
		Board960 b960 = new Board960(new ChessGUI());
		for(int row = 0; row <= 7; row++){
			assertNotNull(b960.board[1][row]);
			assertTrue(b960.board[1][row].isOccupied);
			assertEquals(PieceType.Pawn, b960.board[1][row].piece.identifier);
			assertEquals(PlayerEnum.Black, b960.board[1][row].piece.color);
		}
	}
	
	/**
	 * Check that the back rank of both sides are correct except for the exact piece type.
	 */
	@Test 
	public void backRankExists(){
		Board960 b960 = new Board960(new ChessGUI());
		for(int row = 0; row <= 7; row++){
			assertNotNull(b960.board[0][row]);
			assertNotNull(b960.board[7][row]);
			assertTrue(b960.board[0][row].isOccupied);
			assertTrue(b960.board[7][row].isOccupied);
			assertEquals(PlayerEnum.Black, b960.board[0][row].piece.color);
			assertEquals(PlayerEnum.White, b960.board[7][row].piece.color);
		}
	}
	

	/**
	 * Check if the opposite locations on the board contain the same type of piece.
	 */
	@Test
	public void backRankPieceOpositeTest(){
		Board960 b960 = new Board960(new ChessGUI());
		for (int col = 0; col <= 7; col++){
			assertEquals(b960.board[7][col].piece.identifier, b960.board[0][col].piece.identifier);
		}
	}
	
	/**
	 * Check if the king is between rooks.
	 */
	@Test
	public void kingBetweenRooksTest(){
		Board960 b960 = new Board960(new ChessGUI());
		boolean right = false;
		boolean left = false;
		for (int col = 0; col < 8; col++){
			if(b960.board[0][col].piece.identifier == PieceType.King){
				for(int col2 = col; col2 < 8; col2++){
					if(b960.board[0][col2].piece.identifier == PieceType.Rook){
						right = true;
					}
				}
				for(int col3 = col; col3 >= 0; col3--){
					if(b960.board[0][col3].piece.identifier == PieceType.Rook){
						left = true;
					}
				}
			}
		}
		assertTrue(right);
		assertTrue(left);
	}
	
	/**
	 * Check if the bishops are on opposite colors.
	 */
	@Test
	public void bishopsOnOppositeColors(){
		Board960 b960 = new Board960(new ChessGUI());
		boolean pass = false;
		for (int col = 0; col < 8; col++){
			if (b960.board[0][col].piece.identifier == PieceType.Bishop){
				for (int col0 = col + 1; col0 < 8; col0++){
					if (b960.board[0][col0].piece.identifier == PieceType.Bishop){
						if (col % 2 != col0 % 2){
							pass = true;
						}
					}
				}
			}
		}
		assertTrue(pass);
	}
}
