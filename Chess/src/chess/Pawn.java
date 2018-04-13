package chess;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Pawn extends Piece {

	public Pawn(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public Pawn(Piece piece) {
		super(piece);
	}

	public ArrayList<Tile> getAvailableMoves(Board board) {
		ArrayList<Tile> available = new ArrayList<Tile>();
		for (int bRow = 0; bRow < 8; bRow++) {
			for (int bCol = 0; bCol < 8; bCol++) {
				if (isValidMove(board.getBoard(), this.row, this.col, bRow, bCol)) {
					available.add(board.getTile(bRow, bCol));
				}
			}
		}
		return available;
	}
	
	public boolean isValidMove(Tile[][] board, int fromRow, int fromCol, int toRow, int toCol) {
		// pawns can't move backwards
		if (this.color == PlayerEnum.White && fromRow <= toRow) return false;
		if (this.color == PlayerEnum.Black && fromRow >= toRow) return false;
		
		// not taking a piece
		if (fromCol == toCol) {
			if (board[toRow][toCol].isOccupied) return false;
			if (Math.abs(toRow - fromRow) == 1 || (Math.abs(toRow - fromRow) == 2 && !this.hasMoved)) return true;
		}
		// taking a piece
		if (Math.abs(toRow - fromRow) == 1 &&
				Math.abs(toCol - fromCol) == 1 &&
				board[toRow][toCol].piece != null &&
				board[toRow][toCol].piece.color != this.color) {
				return true;
		}
		return false;
	}
	
	@Override
	public boolean move(Tile moveTo, Board board) {
		this.hasMoved = true;
		this.row = moveTo.row;
		this.col = moveTo.col;
		
		// if pawn is going to be promoted, make popup window to choose new piece
		if (this.color == PlayerEnum.White && moveTo.row == 0 ||
		   (this.color == PlayerEnum.Black && moveTo.row == 8)) {
			// set the piece to the selected piece
			Piece selected = promotion(moveTo, board);
			moveTo.setPiece(selected);
		} else {
			moveTo.setPiece(this);
		}
		return true;
	}
	
	public PieceType getPieceType() {
		return this.identifier;
	}
	
	public Piece promotion(Tile moveTo, Board board) {
		PromotionPopup popup = new PromotionPopup(board.getCurrentPlayer(), moveTo);
		JOptionPane.showMessageDialog(board.gui.f, popup);
		return popup.getSelected();
	}
}
