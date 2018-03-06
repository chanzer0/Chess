package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class testRookAvailableMoves {
	/*
	 * This test will place a rook on an empty board at 
	 * position row:2, col:1 and get available moves, then test them
	 */
	@Test
	public void testOpenRook() {
		
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
		
		/*
		 * Create new rook, place it on the board
		 */
		Rook testRook = new Rook(2, 1, Color.White, PieceType.Rook);
		testBoard[testRook.row][testRook.col] = new Tile(testRook.row, testRook.col, true, testRook);
		/*
		 * Get legalMoves and expectedMoves 
		 */
		Tile[] legalMoves = testRook.getAvailableMoves(testBoard);
		Tile[] expectedMoves = getExpectedMoves(testRook, testBoard);
		
		/*
		 * Test equality
		 */
		assertNotNull(legalMoves);
		assertArrayEquals(expectedMoves, legalMoves);
	}

	/*
	 * This test will create a new classical board, initialize it to 
	 * it's standard position, and then assign a Tile[][] array to the classical board
	 * 
	 * For the Rooks in a starting classical game, we would expect them to
	 * have no legal moves to make, since they are trapped in by their own pieces. 
	 * This test checks to ensure that they all return the correct getAvailableMoves()
	 * when there are no available moves to make.
	 */
	@Test
	public void testInitClassicalBoard() {
		
		classicalChessBoard board = new classicalChessBoard();
		board.initBoard();
		Tile[][] testBoard = board.getBoard();
		/*
		 * Make rooks (2 for white side, 2 for black side)
		 */
		Piece whiteRook1 = testBoard[7][0].piece;
		Piece whiteRook2 = testBoard[7][7].piece;
		Piece blackRook1 = testBoard[0][0].piece;
		Piece blackRook2 = testBoard[0][7].piece;
		
		/*
		 * Get the available moves from each rook
		 */
		Tile[] whiteRook1Moves = whiteRook1.getAvailableMoves(testBoard);
		Tile[] whiteRook2Moves = whiteRook2.getAvailableMoves(testBoard);
		Tile[] blackRook1Moves = blackRook1.getAvailableMoves(testBoard);
		Tile[] blackRook2Moves = blackRook2.getAvailableMoves(testBoard);
		
		/*
		 * These are all empty, as if there are no available moves, getLegalMoves() 
		 * has no objects to return, thus returns empty Tile array.
		 */
		Tile[] whiteRook1ExpectedMoves = new Tile[0];
		Tile[] whiteRook2ExpectedMoves = new Tile[0];
		Tile[] blackRook1ExpectedMoves = new Tile[0];
		Tile[] blackRook2ExpectedMoves = new Tile[0];
		
		/*
		 * Test equality
		 */
		assertNotNull(whiteRook1ExpectedMoves);
		assertNotNull(whiteRook2ExpectedMoves);
		assertNotNull(blackRook1ExpectedMoves);
		assertNotNull(blackRook2ExpectedMoves);
		assertArrayEquals(whiteRook1ExpectedMoves, whiteRook1Moves);
		assertArrayEquals(whiteRook2ExpectedMoves, whiteRook2Moves);
		assertArrayEquals(blackRook1ExpectedMoves, blackRook1Moves);
		assertArrayEquals(blackRook2ExpectedMoves, blackRook2Moves);
	}
	
	/*
	 * This test will create various random positions and then test them
	 */
	@Test
	public void testRandom() {
		/*
		 * Things we'll need later
		 */
		Random rand = new Random();
		Scanner in = new Scanner(System.in);
		/*
		 * Create a new classical board and empty it
		 */
		classicalChessBoard testBoard = new classicalChessBoard();
		
		/*
		 * Tests 64 iterations of 5 random piece placements 
		 */
		for (int i = 0; i < 64; i++) {
			/*
			 * Upon a new iteration, clear the board 
			 */
			testBoard.emptyBoard();
			/*
			 * Random Rook
			 */
			int randRow = rand.nextInt(8); // [0-7]
			int randCol = rand.nextInt(8); // [0-7]
			int randColor = rand.nextInt(2); // [0-1]
			Color playerColor = (randColor == 1) ? Color.White : Color.Black; // Choose White if 1, Black if 0
			Rook testRook = new Rook(randRow, randCol, playerColor, PieceType.Rook);
			testBoard.placeTile(new Tile (randRow, randCol, true, testRook)); // Add it to the board
			
			/*
			 * Random Bishop
			 */
			randRow = rand.nextInt(8); // [0-7]
			randCol = rand.nextInt(8); // [0-7]
			randColor = rand.nextInt(2); // [0-1]
			playerColor = (randColor == 1) ? Color.White : Color.Black; // Choose White if 1, Black if 0
			Bishop testBishop = new Bishop(randRow, randCol, playerColor, PieceType.Bishop); // Create piece
			testBoard.placeTile(new Tile (randRow, randCol, true, testBishop)); // Add it to the board
			
			/*
			 * Random Knight
			 */
			randRow = rand.nextInt(8); // [0-7]
			randCol = rand.nextInt(8); // [0-7]
			randColor = rand.nextInt(2); // [0-1]
			playerColor = (randColor == 1) ? Color.White : Color.Black; // Choose White if 1, Black if 0
			Knight testKnight = new Knight(randRow, randCol, playerColor, PieceType.Knight); // Create piece
			testBoard.placeTile(new Tile (randRow, randCol, true, testKnight)); // Add it to the board
			
			/*
			 * Random Queen
			 */
			randRow = rand.nextInt(8); // [0-7]
			randCol = rand.nextInt(8); // [0-7]
			randColor = rand.nextInt(2); // [0-1]
			playerColor = (randColor == 1) ? Color.White : Color.Black; // Choose White if 1, Black if 0
			Queen testQueen = new Queen(randRow, randCol, playerColor, PieceType.Queen); // Create piece
			testBoard.placeTile(new Tile (randRow, randCol, true, testQueen)); // Add it to the board
			
			/*
			 * Random Rook2
			 */
			randRow = rand.nextInt(8); // [0-7]
			randCol = rand.nextInt(8); // [0-7]
			randColor = rand.nextInt(2); // [0-1]
			playerColor = (randColor == 1) ? Color.White : Color.Black; // Choose White if 1, Black if 0
			Rook testRook2 = new Rook(randRow, randCol, playerColor, PieceType.Rook); // Create piece
			testBoard.placeTile(new Tile (randRow, randCol, true, testRook2)); // Add it to the board
			
			/*
			 * Get testRook's available moves
			 */
			Tile[] legalMoves = testRook.getAvailableMoves(testBoard.getBoard());
			
			/*
			 * Analyze the board, get the expected moves for testRook
			 */
			Tile[] expectedMoves = getExpectedMoves(testRook, testBoard.getBoard());
			
			/*
			 * Verify that the 2 tile arrays are the same
			 * and generate a GUI with the test position (only if the user wishes)
			 */
			System.out.println("Do you wish to generate a GUI for test #" + (i + 1) + "?\nEnter \"yes\" for yes, or \"no\" for no.\nAlternatively, you may hold enter to skip through all GUI prompts.");
			String userIn = in.nextLine();
			if (userIn.toLowerCase().equals("yes")) {
				chessBoardGUI GUI = new chessBoardGUI(testBoard);
			}
			assertArrayEquals(expectedMoves, legalMoves);
			System.out.println("");
		}
		in.close();
	}

	/*
	 * This private method takes in a Rook, and a board, and generates an 
	 * expected list of legalMoves for the given rook and board.
	 */
	private Tile[] getExpectedMoves(Rook testRook, Tile[][] board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		boolean flag = false;
		/*
		 * Checks to the right of the rook.
		 * 
		 * If the board at that position is not occupied,
		 * and flag is false, add that board position to legalMoves
		 * 
		 * If the board at that position is occupied & the piece that is 
		 * occupying it is not the same color as the rook & flag is false, 
		 * add that position to legalMoves and set flag = true
		 * 
		 * If the board at that position is occupied & the piece that is
		 * occupying it is the same color as the rook, set col = 8, 
		 * effectively terminating the loop
		 * 
		 * At the end of the loop, reset flag = false
		 */
		for (int col = testRook.col; col < 8; col++) {
			if (!board[testRook.row][col].isOccupied && !flag) {
				legalMoves.add(board[testRook.row][col]);
			} else if (board[testRook.row][col].isOccupied && board[testRook.row][col].piece.color != testRook.color && !flag) {
				legalMoves.add(board[testRook.row][col]);
				flag = true;
			} else if (board[testRook.row][col].isOccupied && board[testRook.row][col].piece.color == testRook.color && col != testRook.col) {
				col = 8;
			}
		} flag = false;
		
		/*
		 * Checks to the left of the rook.
		 * 
		 * If the board at that position is not occupied,
		 * and flag is false, add that board position to legalMoves
		 * 
		 * If the board at that position is occupied & the piece that is 
		 * occupying it is not the same color as the rook & flag is false, 
		 * add that position to legalMoves and set flag = true
		 * 
		 * If the board at that position is occupied & the piece that is
		 * occupying it is the same color as the rook, set col = -1, 
		 * effectively terminating the loop
		 * 
		 * At the end of the loop, reset flag = false
		 */
		for (int col = testRook.col; col >= 0; col--) {
			if (!board[testRook.row][col].isOccupied && !flag) {
				legalMoves.add(board[testRook.row][col]);
			} else if (board[testRook.row][col].isOccupied && board[testRook.row][col].piece.color != testRook.color && !flag) {
				legalMoves.add(board[testRook.row][col]);
				flag = true;
			} else if (board[testRook.row][col].isOccupied && board[testRook.row][col].piece.color == testRook.color && col != testRook.col) {
				col = -1;
			}
		} flag = false;
		
		/*
		 * Checks above the rook.
		 * 
		 * If the board at that position is not occupied,
		 * and flag is false, add that board position to legalMoves
		 * 
		 * If the board at that position is occupied & the piece that is 
		 * occupying it is not the same color as the rook & flag is false, 
		 * add that position to legalMoves and set flag = true
		 * 
		 * If the board at that position is occupied & the piece that is
		 * occupying it is the same color as the rook, set row = -1, 
		 * effectively terminating the loop
		 * 
		 * At the end of the loop, reset flag = false
		 */
		for (int row = testRook.row ; row >= 0; row--) {
			if (!board[row][testRook.col].isOccupied && !flag) {
				legalMoves.add(board[row][testRook.col]);
			} else if (board[row][testRook.col].isOccupied && board[row][testRook.col].piece.color != testRook.color && !flag) {
				legalMoves.add(board[row][testRook.col]);
				flag = true;
			} else if (board[row][testRook.col].isOccupied && board[row][testRook.col].piece.color == testRook.color && row != testRook.row) {
				row = -1;
			}
		} flag = false;
		
		/*
		 * Checks below the rook.
		 * 
		 * If the board at that position is not occupied,
		 * and flag is false, add that board position to legalMoves
		 * 
		 * If the board at that position is occupied & the piece that is 
		 * occupying it is not the same color as the rook & flag is false, 
		 * add that position to legalMoves and set flag = true
		 * 
		 * If the board at that position is occupied & the piece that is
		 * occupying it is the same color as the rook, set row = 8, 
		 * effectively terminating the loop
		 * 
		 * At the end of the loop, reset flag = false
		 */
		for (int row = testRook.row; row < 8; row++) {
			if (!board[row][testRook.col].isOccupied && !flag) {
				legalMoves.add(board[row][testRook.col]);
			} else if (board[row][testRook.col].isOccupied && board[row][testRook.col].piece.color != testRook.color && !flag) {
				legalMoves.add(board[row][testRook.col]);
				flag = true;
			} else if (board[row][testRook.col].isOccupied && board[row][testRook.col].piece.color == testRook.color && row != testRook.row) {
				row = 8;
			}
		} flag = false;
		
		/*
		 * Create a new temporary Tile array, ret,
		 * which is the size of legalMoves.size
		 * 
		 * This allows us to use the .toArray() function,
		 * returning our ArrayList as a Tile[] array
		 */
		Tile[] ret = new Tile[legalMoves.size()];
		return legalMoves.toArray(ret);
	}
}
