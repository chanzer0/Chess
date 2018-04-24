package chess;

import java.util.ArrayList;

public class King extends Piece {
	
	public King(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public King(Piece piece) {
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
		if (fromRow == toRow && fromCol == toCol) return false;
		// maybe castling
		if (fromRow == toRow) {
			if (Math.abs(toCol - fromCol) >= 2) return false;
			int offset = fromCol < toCol ? 1 : -1;
			
			for (int y = fromCol + offset; y != toCol; y += offset) {
				if (board[toRow][y].isOccupied && board[toRow][y].piece.identifier != PieceType.King) {
					return false;
				}
			}
		}
		
		if (Math.abs(fromRow - toRow) <= 1 && Math.abs(fromCol - toCol) <= 1) {
			if (board[toRow][toCol].isOccupied && board[toRow][toCol].piece.color == this.color) return false;
			return true;
		}
		
		return false;
	}
	

	@Override
	public boolean move(Tile moveTo, Board b) {
		Tile[][] board = b.getBoard();
		if (Math.abs(this.col - moveTo.col) >= 2 && !hasMoved) { // castling
			// move rook
			if (moveTo.col < this.col) {
				// rook in the left corner
				b.getTile(this.row, 0).moveTo(board[this.row][moveTo.col + 1], b);
			} else {
				// rook in the right corner
				b.getTile(this.row, 7).moveTo(board[this.row][moveTo.col - 1], b);
			}
		}
		
		if (b.getCurrentPlayer().getCheck()) {
			this.row = moveTo.row;
			this.col = moveTo.col;
			this.hasMoved = true;
			moveTo.setPiece(this);
			return false;
		}
		
		this.row = moveTo.row;
		this.col = moveTo.col;
		this.hasMoved = true;
		moveTo.setPiece(this);

		return true;
	}
}
