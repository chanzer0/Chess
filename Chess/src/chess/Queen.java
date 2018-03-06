package chess;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int row, int col, Color color, PieceType identifier) {
		super(row, col, color, identifier);
	}

	@Override
	public Tile[] getAvailableMoves(Tile[][] board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		boolean flag = false;
		
		int row1 = this.row - 1;
		int col1 = this.col - 1;
		// Check up diagonal left
		while (row1 >= 0 && col1 >= 0) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 -= 1;
			row1 -= 1;
		}
		flag = false;

		row1 = row - 1;
		col1 = col + 1;
		// Check up diagonal right
		while (row1 >= 0 && col1 < 8) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 += 1;
			row1 -= 1;
		}
		flag = false;

		row1 = row + 1;
		col1 = col - 1;
		// Check down diagonal left on the file
		while (row1 < 8 && col1 >= 0) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 -= 1;
			row1 += 1;
		}
		flag = false;

		row1 = row + 1;
		col1 = col + 1;
		// Check down diagonal right on the file
		while (row1 < 8 && col1 < 8) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 += 1;
			row1 += 1;

		}
		flag = false;
		
		// Check to the right on rank
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
		
		// Check to left on rank
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
		
		// Check above on file
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
		
		// Check below on file
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
		
		Tile[] ret = new Tile[legalMoves.size()];
		System.out.print("arraylist: " + legalMoves.size() + " ");
		System.out.print("array: " + ret.length + "\n");
		return legalMoves.toArray(ret);
	}
}
