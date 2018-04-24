package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class testKnightAvailableMoves {

	@Test
	public void test() {
		/*
		 * This initializes an 8x8 board to empty tiles
		 */
		ChessClassical cb = new ChessClassical(new ChessGUI());
		cb.emptyBoard();
		Tile[][] testBoard = cb.getBoard();
		/*
		 * Now we create 4 different testKnights and place them on the board
		 */
		Knight testKnightCorner = new Knight(7, 7, PlayerEnum.White, PieceType.Knight);
		testBoard[7][7] = new Tile(7, 7, true, testKnightCorner);
		Knight testKnightHalfCorner = new Knight(1, 6, PlayerEnum.White, PieceType.Knight);
		testBoard[1][6] = new Tile(1, 6, true, testKnightHalfCorner);
		Knight testKnightOneFourthCorner = new Knight(2, 1, PlayerEnum.White, PieceType.Knight);
		testBoard[2][1] = new Tile(2, 1, true, testKnightOneFourthCorner);
		Knight testKnightOpenSpace = new Knight(4, 3, PlayerEnum.White, PieceType.Knight);
		testBoard[4][3] = new Tile(4, 3, true, testKnightOpenSpace);
		/*
		 * Now we will place other pieces on the board and test them one at a time
		 * First we will test the Corner
		 */
		Rook rook1 = new Rook(6, 5, PlayerEnum.White, PieceType.Rook);
		testBoard[6][5] = new Tile(6, 5, true, rook1);
		Knight knight1 = new Knight(5, 6, PlayerEnum.Black, PieceType.Knight);
		testBoard[5][6] = new Tile(5, 6, true, knight1);
		Tile testArray1[] = { testBoard[5][6] };
		assertArrayEquals(testArray1, testKnightCorner.getAvailableMoves(cb).toArray());
		/*
		 * Now we will test the Half Corner
		 */
		Pawn pawn2 = new Pawn(3, 7, PlayerEnum.Black, PieceType.Pawn);
		testBoard[3][7] = new Tile(2, 7, true, pawn2);
		Rook rook2 = new Rook(3, 5, PlayerEnum.Black, PieceType.Rook);
		testBoard[0][7] = new Tile(3, 5, true, rook2);
		Tile testArray2[] = { testBoard[3][7], testBoard[3][5], testBoard[2][4], testBoard[0][4] };
		assertArrayEquals(testArray2, testKnightHalfCorner.getAvailableMoves(cb).toArray());
		/*
		 * Now to test the One-Fourth Corner
		 */
		Queen queen3 = new Queen(0, 0, PlayerEnum.Black, PieceType.Queen);
		testBoard[0][0] = new Tile(0, 0, true, queen3);
		Bishop bishop3 = new Bishop(0, 2, PlayerEnum.White, PieceType.Bishop);
		testBoard[0][2] = new Tile(0, 2, true, bishop3);
		Tile testArray3[] = { testBoard[4][2], testBoard[4][0], testBoard[0][0], testBoard[3][3], testBoard[1][3] };
		assertArrayEquals(testArray3, testKnightOneFourthCorner.getAvailableMoves(cb).toArray());
		/*
		 * Finally, to test the Open Space Knight
		 */
		Bishop bishop4 = new Bishop(3, 5, PlayerEnum.Black, PieceType.Bishop);
		testBoard[3][5] = new Tile(3, 5, true, bishop4);
		Pawn pawn4 = new Pawn(6, 2, PlayerEnum.Black, PieceType.Pawn);
		testBoard[6][2] = new Tile(6, 2, true, pawn4);
		Knight knight4 = new Knight(6, 4, PlayerEnum.White, PieceType.Knight);
		testBoard[6][4] = new Tile(6, 4, true, knight4);
		Rook rook4 = new Rook(5, 1, PlayerEnum.White, PieceType.Rook);
		testBoard[5][1] = new Tile(5, 1, true, rook4);
		Tile testArray4[] = { testBoard[6][2], testBoard[2][4], testBoard[2][2], testBoard[5][5], testBoard[3][5], testBoard[3][1] };
		assertArrayEquals(testArray4, testKnightOpenSpace.getAvailableMoves(cb).toArray());
	}

}
