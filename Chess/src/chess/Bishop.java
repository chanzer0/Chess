package chess;

import java.util.ArrayList;
//
public class Bishop extends Piece {

	public Bishop(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public Bishop(Piece piece) {
		super(piece);
	}

	public ArrayList<Tile> getAvailableMoves(Board board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		
		for (int bRow = 0; bRow < 8; bRow++) {
			for (int bCol = 0; bCol < 8; bCol++) {
				if (this.isValidMove(board.getBoard(), this.row, this.col, bRow, bCol)) {
					legalMoves.add(board.getTile(bRow, bCol));
				}
			}
		}
		return legalMoves;
	}
	
	@Override
	public boolean isValidMove(Tile[][] board, int fromRow, int fromCol, int toRow, int toCol) {
		// needs to move diagonally
		if (fromRow == toRow || fromCol == toCol) return false;
		if (board[toRow][toCol].isOccupied && board[toRow][toCol].piece.color == this.color) return false;
		
		// needs to move the same amount of col and row (diagonal)
		if (Math.abs(fromRow - toRow) != Math.abs(fromCol - toCol)) return false;
		
		// if current row is less then next row, we want to increment rows else decrement
		int rowOffset = fromRow < toRow ? 1 : -1;
		// if current col is less then next col, we want to increment columns else decrement
		int colOffset = fromCol < toCol ? 1 : -1;
		
		boolean flag = true;
		int y = fromCol + colOffset;
		for (int x = fromRow + rowOffset; x != toRow; x += rowOffset) {
			// if there is a piece in the way you can't move there
			if (!flag) return false;
			if (board[x][y].isOccupied) {
				if (board[x][y].piece.color == this.color) {
					return false;
				} else {
					flag = false;
				}
			}
			
			y += colOffset;
		}
		
		return flag;
	}
}
