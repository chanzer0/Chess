package chess;

import java.util.ArrayList;

public class Rook extends Piece {
	/**
	 * Generic constructor given by super
	 * @param row The row that you want the rook to be constructed on
	 * @param col The column that you want the rook to be constructed on 
	 * @param color The color of the rook (Player.White)
	 * @param identifier The identifier of the rook (PieceType.Rook)
	 */
	public Rook(int row, int col, Color color, PieceType identifier) {
		super(row, col, color, identifier);
	}

	/**
	 * Returns a Tile array that contains all available tiles that
	 * this piece can move to given the board position. Returns an empty 
	 * array if there are no places for the rook to move
	 *
	 * @see chess.Piece#getAvailableMoves(chess.Tile[][])
	 * @param board The chess board that you want to check this piece's available moves for
	 * @return legalMoves A Tile array that contains all available Tiles that this piece can move to (empty if none).
	 */
	public Tile[] getAvailableMoves(Tile[][] board) {
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
		for (int col = this.col; col < 8; col++) {
			if (!board[this.row][col].isOccupied && !flag) {
				legalMoves.add(board[this.row][col]);
			} else if (board[this.row][col].isOccupied && board[this.row][col].piece.color != this.color && !flag) {
				legalMoves.add(board[this.row][col]);
				flag = true;
			} else if (board[this.row][col].isOccupied && board[this.row][col].piece.color == this.color && col != this.col) {
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
		
		for (int col = this.col; col >= 0; col--) {
			if (!board[this.row][col].isOccupied && !flag) {
				legalMoves.add(board[this.row][col]);
			} else if (board[this.row][col].isOccupied && board[this.row][col].piece.color != this.color && !flag) {
				legalMoves.add(board[this.row][col]);
				flag = true;
			} else if (board[this.row][col].isOccupied && board[this.row][col].piece.color == this.color && col != this.col) {
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
		for (int row = this.row ; row >= 0; row--) {
			if (!board[row][this.col].isOccupied && !flag) {
				legalMoves.add(board[row][this.col]);
			} else if (board[row][this.col].isOccupied && board[row][this.col].piece.color != this.color && !flag) {
				legalMoves.add(board[row][this.col]);
				flag = true;
			} else if (board[row][this.col].isOccupied && board[row][this.col].piece.color == this.color && row != this.row) {
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
		for (int row = this.row; row < 8; row++) {
			if (!board[row][this.col].isOccupied && !flag) {
				legalMoves.add(board[row][this.col]);
			} else if (board[row][this.col].isOccupied && board[row][this.col].piece.color != this.color && !flag) {
				legalMoves.add(board[row][this.col]);
				flag = true;
			} else if (board[row][this.col].isOccupied && board[row][this.col].piece.color == this.color && row != this.row) {
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
