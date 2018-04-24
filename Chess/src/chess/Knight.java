package chess;

import java.util.ArrayList;

public class Knight extends Piece {
	public Knight(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public Knight(Piece piece) {
		super(piece);
	}
	
	public ArrayList<Tile> getAvailableMoves(Board board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		
		for (int bRow = 0; bRow < 8; bRow++) {
			for (int bCol = 0; bCol < 8; bCol++) {
				if (isValidMove(board.getBoard(), this.row, this.col, bRow, bCol)) {
					legalMoves.add(board.getTile(bRow, bCol));
				}
			}
		}
		return legalMoves;
	}
	
	@Override
	public boolean isValidMove(Tile[][] board, int fromRow, int fromCol, int toRow, int toCol) {
		if (toRow >= 8 || toRow < 0 || toCol >= 8 || toCol < 0) return false;
		
		if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 1) {
			if (board[toRow][toCol].isOccupied) {
				// can only move to occupied spot if its opponent's piece
				if (board[toRow][toCol].piece.color != this.color) return true;
			} else {
				return true;
			}
		}
		
		if (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 2) {
			if (board[toRow][toCol].isOccupied) {
				// can only move to occupied spot if its opponent's piece
				if (board[toRow][toCol].piece.color != this.color) return true;
			} else {
				return true;
			}
		}
		
		return false;
	}
}

