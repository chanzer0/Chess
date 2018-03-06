package chess;

import java.util.ArrayList;

public class Pawn extends Piece {
	
	protected boolean hasMoved = false;

	public Pawn(int row, int col, Color color, PieceType identifier) {
		super(row, col, color, identifier);
	}

	public Tile[] getAvailableMoves(Tile[][] board) {
		ArrayList<Tile> available = new ArrayList<Tile>();
		for (int bRow = 0; bRow < 8; bRow++) {
			for (int bCol = 0; bCol < 8; bCol++) {
				if (bCol == this.col) {
					// first move
					if (!hasMoved && (bRow == this.row - 2 || bRow == this.row + 2)) {
						available.add(board[bRow][bCol]);
					} else if (bRow == this.row - 1 && this.color == Color.White) {
						if (!board[bRow][bCol].isOccupied) {
							available.add(board[bRow][bCol]);
						}
					} else if (bRow == this.row + 1 && this.color == Color.Black) {
						if (!board[bRow][bCol].isOccupied) {
							available.add(board[bRow][bCol]);
						}
					}
				} else if (bCol == this.col + 1 || bCol == this.col - 1) {
					if (board[bRow][bCol].isOccupied && this.isOpponent(board[bRow][bCol].piece.color)) {
						if (bRow == this.row - 1 && this.color == Color.White) {
							available.add(board[bRow][bCol]);
						}
						if (bRow == this.row + 1 && this.color == Color.Black) {
							available.add(board[bRow][bCol]);
						}
					}
				}
			}
		}
		return available.toArray(new Tile[available.size()]);
	}

	@Override
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
		this.hasMoved = true;
	}
}
