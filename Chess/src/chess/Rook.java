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
	public Rook(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public Rook(Piece piece) {
		super(piece);
	}

	/**
	 * Returns a Tile array that contains all available tiles that
	 * this piece can move to given the board position. Returns an empty 
	 * array if there are no places for the rook to move
	 *
	 * @see chess.Piece#getAvailableMoves(Board)
	 * @param board The chess board that you want to check this piece's available moves for
	 * @return legalMoves A Tile array that contains all available Tiles that this piece can move to (empty if none).
	 */
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
		// no diagonals
		if (fromRow != toRow && fromCol != toCol) return false;
		if (board[toRow][toCol].isOccupied && board[toRow][toCol].piece.color == this.color) return false;
		
		boolean flag = true;
		int offset;
		
		// moving along row
		if (fromRow != toRow) {
			offset = fromRow <= toRow ? 1 : -1;
			
			for (int x = fromRow + offset; x != toRow; x += offset) {
				if (board[x][this.col].isOccupied) {
					if (board[x][this.col].piece.color == this.color) {
						return false;
					} else {
						flag = false;
					}
				}
			}
		}
		
		// moving along col
		if (fromCol != toCol) {
			offset = fromCol <= toCol ? 1 : -1;
			
			for (int y = fromCol + offset; y != toCol; y += offset) {
				if (board[this.row][y].isOccupied) {
					if (board[this.row][y].piece.color == this.color) {
						return false;
					} else {
						flag = false;
					}
				}
			}
		}
		
		return flag;
	}
}
