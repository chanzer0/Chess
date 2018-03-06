package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class testKnightAvailableMoves {

	@Test
	public void testKnightMoves() {
		Tile[][] testBoard = new Tile[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Tile t = new Tile(j, i, false, null);
				testBoard[i][j] = t;
			}
		}
		// Checking corner
		Tile[] test1;
		Knight k1 = new Knight(7, 7, Color.White, PieceType.Knight);
		test1 = k1.getAvailableMoves(testBoard);
		assertEquals(test1[0].color, "RED");
		assertEquals(test1[0].color, "RED");
		if (test1[0].color == "RED") {
			fail("Too many red squares");
		}
		// Checking half corner
		Tile[] test2;
		Knight k2 = new Knight(1, 1, Color.White, PieceType.Knight);
		test2 = k2.getAvailableMoves(testBoard);
		assertEquals(test2[0].color, "RED");
		assertEquals(test2[0].color, "RED");
		assertEquals(test2[0].color, "RED");
		assertEquals(test2[0].color, "RED");
		if (test2[0].color == "RED") {
			fail("Too many red squares");
		}
		//Checking 1/4 corner
		Tile[] test3;
		Knight k3 = new Knight(1, 4, Color.White, PieceType.Knight);
		test3 = k3.getAvailableMoves(testBoard);
		assertEquals(test3[0].color, "RED");
		assertEquals(test3[0].color, "RED");
		assertEquals(test3[0].color, "RED");
		assertEquals(test3[0].color, "RED");
		assertEquals(test3[0].color, "RED");
		assertEquals(test3[0].color, "RED");
		if (test3[0].color == "RED") {
			fail("Too many red squares");
		}
		//Checking open area
		Tile[] test4;
		Knight k4 = new Knight(4, 4, Color.White, PieceType.Knight);
		test4 = k4.getAvailableMoves(testBoard);
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		assertEquals(test4[0].color, "RED");
		if (test4[0].color == "RED") {
			fail("Too many red squares");
		}
	}

}
